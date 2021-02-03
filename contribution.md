# Contributing Guidelines

Thanks for your interest in contributing to Dare2Change! Contributing to open source projects like this one are a rewarding way to learn, and build experience. Not only that, contributing is a great way to get involved with social coding. We are excited to see what amazing contributions you will make, as well as how your contributions will benefit others.

If you are new to contributing to open source projects, the process can be quite confusing. Not to worry! To help ensure both you and the community get the most out of your contributions, we've put together the following guidelines.

# Ways of Contributing

A developer can contribute in the following ways:
1. Take a look at the open issues and find one you can tackle.
2. Locate and fix bugs.
3. Ideate and implement new features.

# How to Contribute

If you'd like to contribute, a good place to start is by searching through the issues and pull requests to see if someone else had a similar idea or question. 

If you don't see your idea listed, and you think it fits into the goals of the project, you should:

1. Discuss about the same on our <a href="https://gitter.im/Dare2Change/community">gitter channel</a>
2. Once your idea is discussed and approved, you can open a new issue and start working on it.
 
# Steps to contribute

1. Fork the project.
2. Clone your fork.

```bash
git clone https://github.com/<YOUR-USERNAME>/Dare2Change
```

3. Add a new remote to upstream

```bash
git remote add upstream https://github.com/coder2699/Dare2Change
```

4. Before start working, create a new branch from the current branch

```bash
git checkout -b <YOUR-NEW-BRANCH>
```

5. Work on the new branch. 

6. Before pushing the code, make sure:
- There should be proper code indentation after making changes in a file. You can use ``` Ctrl+Shift+L ``` in android studio to indent the code after making changes in a file.
- Pull from upstream and check if there are any conflicts. If so solve them and then push the code.
- The commit message should be short, meaningful, and precise. Try not to add more than 6-7 words.
- Use ```git status``` to check that no extra file has been modified.

7. To actually make the commit and push it to your GitHub fork, run:

```bash
  git add .	
  git commit -m "YOUR COMMIT MESSAGE HERE"
  git push origin {YOUR BRANCH NAME}
```
8. From your forked repository, open a new pull request.

9. Once the pull request is approved and merged, you can pull the changes from upstream to your local repository and delete your extra branch(es).
