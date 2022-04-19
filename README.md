# SpearGame

Authors: Tedi Qafko, Sam Tommy

Game Description
-----------------

A single-player game with a top-view perspective where the user plays the character of a caveman who is attempting to shoot down randomly moving spaceships called bosses. The goal of the game is to score the highest level possible by destroying all of the spaceships using the caveman attack called “bullet” while maneuvering around the spaceships’ moving attacks. You will always begin with 100 hit points and when those hit points go to 0, you are defeated and restart from level one.

Main Menu
---------

Start Game -> starts game.

How to Play -> (Currently set to do nothing.)

Options -> Displays Game Options screen.

Quit -> Quits game.

Game Options: (All options are set to default when program is started
---------------------------------------------------------------------

"Bullet single-kill" or "Bullet multi-kill" -> Changes the number of bosses each bullet fired can kill (Disabled or lowered opacity button is the seclected option)

Bullet Speed -> Changes the rate at which bullets can be fired. Option 1 has a duration of 200 ms per bullet, option 10 has a duration of 20 ms per bullet.

Player Speed -> Changes the speed that the player can move. Option 1 player moves 1 pixel at a time, option 10 player moves 10 pixels at a time. 

Boss Speed -> Changes the speed the space ships are moving.

Boss Size -> Changes the hit box of the space ships.

Intalling Game Instructions
----------------------------------------

To run the game:

IMPORTANT: You will need a working and up-to-date javaFX with your javaFX class library already made and knowledge of how to Add User Library for JavaFX buildpath.
If you do not know how to do this please refer to this video on YouTube for how to set up JavaFX on Eclipse: https://www.youtube.com/watch?v=bk28ytggz7E. 

----------------------------------------

Unzip the SpearGame folder and save to your computer to an easy location, like Desktop || Documents || Files.
(To allow you to paste the file directory quickly into Eclipse)
-select the file path of this saved location and Copy it to your keyboard using Ctrl + C.
Ex: C:\Users\yourusername\Desktop\SpearGame-main\SpearGame-main\SpearGame

----------------------------------------

Open Eclipse. Make a new project named "SpearGame" case sensitive. When you have the SpearGame folder created, let's import the SpearGame files:

File -> Import -> General -> File System -> (At the top) From Directory 

->(Paste your directory. Ex: C:\Users\yourusername\Desktop\SpearGame-main\SpearGame-main\SpearGame)

Once you click away, Select SpearGame and check the box. -> Into Folder -> "SpearGame" -> Click Finish -> Yes to All

----------------------------------------

---> Finished Importing the game, now checking JavaFX buildpath <---

----------------------------------------

If you had success importing the game but see red error marks preventing you from running the code,
It may be an issue with the buildpath. Configure the build path by 

Right Click -> SpearGame Folder -> Select -> Build Path -> Configure Build Path 
Under "Libraries" Tab "Modulepath" needs javaFX, javaFX SDK, and JRE System Library

----(You may need to delete these and re-add them to make sure all the correct versions are added)----

-> Apply and Close

----------------------------------------

Run the Game
-> Package Explorer -> SpearGame -> src -> application -> (Right Click) Main.java -> Run As -> Java Application -> Start Game

----------------------------------------

End
---
