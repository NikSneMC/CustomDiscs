# CustomDiscs
[README.md](https://github.com/NikSneMC/CustomDiscs#readme)

[README_RUSSIAN.md](https://github.com/NikSneMC/CustomDiscs/blob/main/README_RUSSIAN.md)

[Go back to the main directory](https://github.com/NikSneMC/CustomDiscs)

What has been changed?
-------------
* All plugin messages in the chat and in the console (except for two) are translated into Russian
* Plugin messages in the console have become colored
* Comments in the file [`config.yml`](https://github.com/NikSneMC/CustomDiscs/blob/main/src/main/resources/config.yml ) translated into Russian
* Changed the message about the successful loading of the plugin (p.s in the original there was just a line `Successfully registered CustomDiscs plugin` in white)
![image](https://user-images.githubusercontent.com/88781618/189207874-b318a632-4819-4d95-8c18-19230634cea3.png)
* Plugin names in `/pl` (and analogues of this command) and plugin folders have been changed to `CustomDiscs_Translated`

The original
--------
Yes, yes, it's just a translation with some changes in the console messages

* **Author of the original plugin: [Navoei](https://github.com/Navoei )**
* **GitHub of the original plugin: https://github.com/Navoei/CustomDiscs**
<details>
<summary>Original readme.md </summary>

Custom Discs 1.19.x
-------------------

A Paper fork of henkelmax's Audio Player.
* Play custom music discs using the Simple Voice Chat API. (The voice chat mod is required on the client and server.)
* Use `/customdisc` or `/cd` to create a custom disc.
* Music files should go into `plugins/CustomDiscs/musicdata/`
* Music files must be in the `.wav` or `.mp3` format.
* Only custom discs are compatible with hoppers.

Permission Node (Required to run the command. Playing discs does not require a permission.):

* `customdiscs.command`

Dependencies:
=======
* This plugin depends on the latest version of ProtocolLib for

https://user-images.githubusercontent.com/64107368/178426026-c454ac66-5133-4f3a-9af9-7f674e022423.mp4
</details>

MY ranslation of the original article (there are minor changes in the direction of correctness)
---------------------------
### Note: due to the fact that this is a translation into Russian language translated back into English this part has no meaning
Paper fork of Henkelmax Audio Player
* Play your music using the Simple Voice Chat API. (The voice chat mod must be installed on the client, and the plugin on the server)
* Use the command `/customdisc` or `/cd` to create your record
* Audio recording files should be in the folder `plugins/CustomDiscs_Translated/musicdata/`
* Audio recording files must be in `.wav` or `.mp3` format.
* Only custom plates work with funnels.

Plugin permissions (required to execute the command. No permission is required to play discs.):
* `customdiscs.command`

Required Plugins:
* [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)
* [Simple Voice Chat](https://www.curseforge.com/minecraft/bukkit-plugins/simple-voice-chat/files/all)

https://user-images.githubusercontent.com/111704048/186036791-9388be12-939c-41eb-b6cf-7161ab4ec4ff.mp4

How to install on the server?
-------------------------
* Download the required version from [this](https://github.com/NikSneMC/CustomDiscs/releases ) pages
* Download the appropriate version of [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) (link to spigotmc.org )
* Download the appropriate version of [Simple Voice Chat](https://www.curseforge.com/minecraft/bukkit-plugins/simple-voice-chat/files/all ) (link to CurseForge.com )
* Transfer downloaded plugins to the `plugins` folder of your server
* restart the server

How to compile?
--------------------
**IMPORTANT!!! This article was written purely by me, it has nothing to do with the author of the original plugin!**


**I highly recommend building the plugin in [Intellij IDEA](https://www.jetbrains.com/idea/). If you know how to do it using another program, then do it in it.**
* Install [Intellij IDEA](https://www.jetbrains.com/idea/), if you don't have it installed
* Download `Source code (zip)` or `Source code (tar.gz)` archive from the desired release
* Unpack downloaded archive to the Intellij IDEA projects folder (in IntelliJ IDEA Community Edition, the default path `C:\Users \<Your user>\IdeaProjects`)
* In the new project creation menu, click <kbd>Open</kbd> () and in the window that opens, select the newly unpacked folder
* Expect the project to be fully loaded

**After the previous step, you can change the files**

* Open the <kbd>Gradle</kbd> tab (shown in the picture below) and click on ![image](https://user-images.githubusercontent.com/111704048/186053563-1b693c0e-0c7d-458d-850d-2434ec770af5.png)

![image](https://user-images.githubusercontent.com/111704048/186021694-9c49575d-b758-47e9-9ddc-1e5178f4d32e.png )
* After that, in the folder ``Customdiscs/Tasks/build`` run the file ``build`` (screenshot below)

![image](https://user-images.githubusercontent.com/111704048/186022904-caf31c7e-1b33-4d88-8522-a5e482995ecc.png )
* At the end of compilation, open the project folder in Explorer, then go to ``build\libs``
* Open ``.jar`` file in any archiver and copy the contents of the folder ``to drop into .jar``(it is located in the folder with the `` itself.jar`` file)
* After everything is copied to the archive, you can close the archiver. This is the end of the plugin compilation
