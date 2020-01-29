Welcome to this manual on how to lock your phone to reduce distractions and condition your focus.

The reason for developing this system is based on the concept of ego depletion. As much as I like to believe it is better to activly decide where you spend your focus and time on, modern (technologies designed to) [trigger hormonal responses][1] that can make focussing quite challenging, in my experience.

I have decided I do not want to spend more than 10 minutes a day on galery images, whatsapp etc. (Yes that's still 2.5 whole days (of 24 hours) every year that I spend staring at that information.) And to enable/train/condition myself on desired behavior, I disabled the option of unwanted behavior (spending more than 2,5 days per year on that data). Additionally it enforces a schedule, which aids me in achieving structure, that allows me to have more free time.

If you have thought about how much time of your life you want to allocate on what, or what not, and want to implement it feel free, and if you have any questions please ask, for example by "creating an Issue".

The guide is still a bit unstructured, any improvements are welcome! To do so, you can click on "issues" and create a new one, or already implement it and send a pull request :)


[1]: http://sitn.hms.harvard.edu/flash/2018/dopamine-smartphones-battle-time/


# Change wifi pw:
NOTE IF YOU MAKE A TYPO/SPACE/wrong change to the `.conf` file, you soft-brick your phone, need factory reset.
0. Reboot into twrp (press volume up down +power>recovery mode)
1. Click advanced>file browser, copy the `data/misc/wifi/wpa_supplicant.conf"` to a folder that does not require root, e.g.
`/storage/17EE-2356/`
Pull file to windows with:
`adb pull /storage/17EE-2356/wpa_supplicant.conf`
Or:
`adb pull /external_sd/wpa_supplicant.conf`
Edit the pw with notepad++ (ensure End Of Line conventions (EOL)  remain Linux)
Restore the file:
`adb push wpa_supplicant.conf /external_sd/wpa_supplicant.conf`
Reboot into twrp (press volume up down +power>recovery mode)
The file browser, copy the `data/misc/wifi/wpa_supplicant.conf"` to a folder that does not require root, e.g.
