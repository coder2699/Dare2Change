package com.example.inout2020_aimers.appblock

import android.app.AppOpsManager
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.appblock.blockService.AppBlockService
import com.example.inout2020_aimers.appblock.database.BlockedApps
import com.example.inout2020_aimers.appblock.database.BlockedAppsRepository
import com.example.inout2020_aimers.appblock.database.BlockerDatabase
import com.example.inout2020_aimers.appblock.util.AppsAdapter
import com.example.inout2020_aimers.appblock.util.BlockerVMF
import com.example.inout2020_aimers.appblock.util.BlockerViewModel
import com.example.inout2020_aimers.databinding.FragmentBlockerBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_blocker.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class BlockerFragment : Fragment(R.layout.fragment_blocker) {

    private val TAG = "BlockerFragment"
    private lateinit var binding: FragmentBlockerBinding
    private lateinit var installedApps: ArrayList<AppListModel>

    private lateinit var bottomSheetAdapter: AppsAdapter

    private lateinit var viewModel: BlockerViewModel

    private lateinit var blockedAppList: ArrayList<BlockedApps>
    private lateinit var blockIntent: Intent
    private var time: Int = 0

    private lateinit var progressDialog: ProgressDialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        progressDialog = ProgressDialog(requireContext());
        progressDialog.setMessage("Getting installed apps");
        progressDialog.setTitle("A moment please...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        val database = BlockerDatabase(requireActivity().applicationContext)
        val repository = BlockedAppsRepository(database)
        val factory = BlockerVMF(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(BlockerViewModel::class.java)

        blockedAppList = ArrayList()

        // BlockScreen Intent
        blockIntent = Intent(requireContext(), AppBlockService::class.java)


        viewModel.getBlockedApps().observe(viewLifecycleOwner, Observer {
            blockedAppList.clear()
            blockedAppList.addAll(it)
            bottomSheetAdapter.notifyDataSetChanged()
        })


        binding = FragmentBlockerBinding.bind(view)

        // Initialization of BottomSheet
        val bottomSheetBehavior =
            BottomSheetBehavior.from(binding.bmSheet.layoutSelectAppsBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        installedApps = ArrayList()
        bottomSheetAdapter = AppsAdapter(installedApps, viewModel, blockedAppList)

        // BottomSheet RecyclerView Setup
        binding.bmSheet.rvSelectApps.apply {
            adapter = bottomSheetAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        // backButton - Hiding BottomSheet
        binding.bmSheet.ibBack.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        if (!isAccessGranted()) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Usage stats permission required")
                .setMessage("Permission required to go further")
                .setCancelable(false)
                .setPositiveButton("Accept") { dialog, which ->
                    // Redirecting to Usage Settings
                    startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                }
                .show()
        }


        // Starting Focus Mode
        binding.btnStartFocusMode.setOnClickListener {

            if (binding.etTime.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Enter time in minutes", Toast.LENGTH_SHORT).show()
            } else if (blockedAppList.isEmpty()) {
                Snackbar.make(requireView(), "Select apps to block", Snackbar.LENGTH_SHORT).show()
            } else {
                time = binding.etTime.text.toString().toInt()

                if (isAccessGranted()) {
                    val blockedAppsString = ArrayList<String>()

                    for (app in blockedAppList) {
                        blockedAppsString.add(app.packageName)
                    }

                    blockIntent.putExtra("blockedApps", blockedAppList)
                    blockIntent.putExtra("time", time)

                    Log.d(TAG, "onCreate: SERVICE CALLED")

                    activity?.startService(blockIntent)

                    btnStartFocusMode.text = "Focus Mode is on!"
//                    activity?.finish()

                } else {

                    // Asking user for permission
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Usage stats permission required")
                        .setMessage("Permission required to go further")
                        .setCancelable(false)
                        .setPositiveButton("Accept") { dialog, which ->
                            // Redirecting to Usage Settings
                            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                        }
                        .show()
                }
            }

        }


        // Expanding select apps bottom sheet
        binding.toolBarBlockerFragment.setOnMenuItemClickListener { menuItem ->

            if (menuItem.itemId == R.id.selectApps) {

                // Get App list

                showProgressBar()

                CoroutineScope(Dispatchers.IO).launch {
                    val listApps = activity?.packageManager?.getInstalledPackages(0)

                    for (i in listApps!!) {
                        val appIcon = i.applicationInfo.loadIcon(activity?.packageManager)
                        val appName =
                            i.applicationInfo.loadLabel(activity?.packageManager!!).toString()
                        val appPackageName = i.applicationInfo.packageName.toString()

                        installedApps.add(
                            AppListModel(
                                icon = appIcon,
                                name = appName,
                                packageName = appPackageName
                            )
                        )
                    }

                    withContext(Dispatchers.Main) {
                        bottomSheetAdapter.notifyDataSetChanged()
                        hideProgressBar()
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                // End For loop
            }

            return@setOnMenuItemClickListener true
        }

    }

    // Usage stats permission
    fun isAccessGranted(): Boolean {
        try {
            val applicationInfo =
                activity?.packageManager!!.getApplicationInfo(activity?.packageName!!, 0)

            val appsOpsManager =
                activity?.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

            var mode = 0

            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appsOpsManager.checkOpNoThrow(
                    AppOpsManager.OPSTR_GET_USAGE_STATS,
                    applicationInfo.uid,
                    applicationInfo.packageName
                )
            }
            return (mode == AppOpsManager.MODE_ALLOWED)
        } catch (ex: PackageManager.NameNotFoundException) {
            return false
        }
    }

    private fun hideProgressBar() {
        progressDialog.hide()
    }

    private fun showProgressBar() {
        progressDialog.show()
    }

}