# What
This is a latex sample report which is syncred with git(hub). It (semi-)automatically syncs the code into your latex, and semi-automatically exports the plots into your report.

# Why
Personally I like how the integrated system allows one to colaborate on code, and get a neat and structured report, nice plots without having to copy paste things.

# How
0. Fork this repository.
1. Delete everything except this (parent) folder named `exampleReportMatlab`. (put it at the top of the repository, so basically in the same folder as the folder `AutomationsAndSAystems`.
2. if you want to work in overleaf: 
2.a Get a(n) (free) overleaf account.
2.b In: https://www.overleaf.com/project click: "New project>import from github" and select your (copy/fork of this) repository.

That's it.

3. Now if you change your code, or create plots that you want in your overleaf:
3.a Note, first time you use github with command you probably need to login, but it'll ask you to do so if you need to.
3.a push your code to github by opening cmd, browsing to the directory of the repository with `cd` and use commands:
```
git pull
git add "some_folder/the_file_you_changed.py"
git add "some_other_folder/the_plot_you_created.jpeg"

git commit -m "Created a plot for something specific."
git push
```
3.b (or you can use the mouse and the github gui (graphical user interface) to click on pull, commit, push etc.
3.c Then in your overleaf report click "Menu>github>pull changes into overleaf".
3.d recompile to see your changes.
