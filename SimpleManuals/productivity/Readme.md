## Instructions
To block distractions (on Windows (and android)) perform the following steps:
0. Download the portable version of SwitchHosts! from: https://github.com/oldj/SwitchHosts/releases/ with for example: https://github.com/oldj/SwitchHosts/releases/download/v3.5.4/SwitchHosts._windows_portable_3.5.4.5517.exe
1. Store it in a directory and RMB on it.
2. Create shortcut
3. RMB on shortcut>Properties>Shortcut>Advanced> select "Run as Administrator">press:`ok`.
4. Press the start button>type:`run`>press:`<enter>`.
5. Type:`shell:startup` and copy the shortcut into the startup folder.
6. Restart your pc.
7. Select a blocklist from the section `Formats`> `Packs` on this site: https://github.com/EnergizedProtection/block
8. I recommend you first test with the lightest `/spark` protection to verify it works. You can download that from this link: https://block.energized.pro/spark/formats/hosts.txt
9. After the restart the SwitchHosts program should be running in the bottom (right) of your icon overview in the taskbar. Open it.
10. Click the `+` icon in the bottom left of the switchhost program.
11. At `Type` click `Remote`.
12. At `Title` give a title to your block list.
13. At `URL` give the filename (located in the same folder as the `.exe` e.g. if your blocklist is named `host.txt` type:`host`.
14. IMPORTANT: At `Auto refresh` select something like `24 hours` 
14.1 Otherwise, if you pick the 30 mb one, and your pc happends to be slow in parsing text, it gets in an infine loop that just keeps loading more data than it can processs, which will also prevent you from removing the file or increasing the `Auto Refresh` setting to give the device more time to process the blocklist before loading the new one.
15. That's it now verify if the first website of the list is indeed blocked on all browsers.


## Block your personal digital distractions
To add specific parts of websites, e.g. your linkedin- or facebook feed, add the following urls. (Verify what they are for yourself as they are often changed). 

0. To block a specific sub domain/link, e.g. `https://www.facebook.com/groups/feed/` but allow other subdomains of `https://www.facebook.com/groups/`, perform the following steps.
1. First disable the blocklist you want to edit in the left pane (otherwise you can't save the changes).
2. Then add:`0.0.0.0 www.facebook.com/groups/feed/`. Note this does not appear to work, it appears one can only block entire domains, not subdomains.

So instead, go full cold turkey (but then free and opensource) with:
3. `0.0.0.0 facebook