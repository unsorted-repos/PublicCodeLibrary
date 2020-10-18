# How to use
0. After a clean Ubuntu installation first make the shell scripts runnable by opening terminal in this directory and running:
```
chmod +x *.sh
```
1. Then install all programs you need with:
```
./ubuntu20postsetup.sh
```
2. Next set up your productivity block, by:
2.0 First adding websites you want to block to: /EnergizedProtection/personal_blacklist.txt (without their domain).
so if you want to block youtube.com, you just add: `youtube` as a separate line in your blocklist.
2.1 Next activate the EnergizedPro block:
```
./energized.sh
```
and enter the options you want, may I suggest:
```
p
s
b
```
You can search the web without unwanted destractions, and with privacy, using: startpagina.nl.
If you find a functioning way to also block subdomains instead of entire websites, please send me a pull request with your shell (tested) code.
