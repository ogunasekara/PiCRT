# PiCRT

An extension of https://github.com/lanceseidman/PiCAST

Use your android phone to cast youtube videos/twitch streams to a raspberry pi.

Install PiCAST on the Raspberry Pi, replace the picast.js file with the one provided, and run picast_start.sh.
Modify the app to reflect the ip of the Pi, install, and from youtube or twitch, share to PiCRT app. 

To change the resolution of the Raspberry Pi to that of the CRT, you need to edit /boot/config.txt using the command "sudo nano /boot/config.txt". Uncomment the disable_overscan=1 line and then change hdmi_mode according to the chart here (http://elinux.org/RPiconfig). For our CRT, setting hdmi_mode=1 sufficed.

Requirements:
VLC player
youtube-dl ver. 2017 or later
livestreamer ver. 1.12 or later

Can play youtube videos (including protected videos), and twitch streams. 

This was designed to play on a small CRT, so the resolutions of the streams is rather low. You can change these in the picast.js.
