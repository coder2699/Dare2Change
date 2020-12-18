package com.example.inout2020_aimers.appblock

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentBlockerBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BlockerFragment : Fragment(R.layout.fragment_blocker){

    private val TAG = "BlockerFragment"
    private lateinit var binding : FragmentBlockerBinding
    private lateinit var installedApps : ArrayList<AppListModel>

    private lateinit var bottomSheetAdapter: AppsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBlockerBinding.bind(view)

        // Initialization of BottomSheet

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bmSheet.layoutSelectAppsBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        installedApps = ArrayList()
        bottomSheetAdapter = AppsAdapter(installedApps)

        // BottomSheet RecyclerView Setup
        binding.bmSheet.rvSelectApps.apply {
            adapter = bottomSheetAdapter
            layoutManager =LinearLayoutManager(activity)
        }



        // Expanding select apps bottom sheet
        binding.toolBarBlockerFragment.setOnMenuItemClickListener { menuItem ->

            if (menuItem.itemId == R.id.selectApps){


                // Get App list
                if(isAccessGranted()){
                    val listApps = activity?.packageManager?.getInstalledPackages(0)

                    for( i in listApps!!){
                        val appIcon = i.applicationInfo.loadIcon(activity?.packageManager)
                        val appName = i.applicationInfo.loadLabel(activity?.packageManager!!).toString()
                        val appPackageName = i.applicationInfo.packageName.toString()

                        installedApps.add(
                            AppListModel(
                                icon = appIcon,
                                name = appName,
                                packageName = appPackageName
                            )
                        )
                    } // End For loop

                    bottomSheetAdapter.notifyDataSetChanged()

                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                }else{
                    Toast.makeText(requireContext(),"Give usage permission", Toast.LENGTH_SHORT).show()

                    // Redirecting to Usage Settings
                    startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                }


            }

            return@setOnMenuItemClickListener true
        }


    }

    // Usage stats permission
    fun isAccessGranted():Boolean{
        try {
            val applicationInfo = activity?.packageManager!!.getApplicationInfo(activity?.packageName!!,0)

            val appsOpsManager = activity?.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

            var mode = 0

            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT){
                mode = appsOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid,applicationInfo.packageName)

            }

            return (mode== AppOpsManager.MODE_ALLOWED)
        }catch (ex : PackageManager.NameNotFoundException){
            return false
        }
    }

}