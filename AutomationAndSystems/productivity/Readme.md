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
```
You can search the web without unwanted destractions, and with privacy, using: startpagina.nl.
If you find a functioning way to also block subdomains instead of entire websites, please send me a pull request with your shell (tested) code.

## Adding websites to your personal blocklist
If you want to add additional websites beyond the default packages, you can:
0. Select the website domain(s) you want to block e.g. www.somesite.com and www.somesite.fr  (and all other extentions, e.g. .org, .fr etc)
1. then add `somesite` to the `EnergizedProtection/personal_blacklist.txt` file.
2. Open Energized with argument:
```
b
```
3. Reboot your pc.

## Note
Running the script with an argument, e.g. `sudo ./energized.sh s`  yields an infinite loop.
