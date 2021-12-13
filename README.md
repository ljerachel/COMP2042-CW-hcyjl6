# COMP2042_hcyjl6 project 


Name: Low Jia En 

OWA: hcyjl6 

HOW TO RUN 
1.Download the zipped folder and extract it.
2.Under the target folder, right click on the jar file with the name Software-Maintenance-CW-main-1.0-SNAPSHOT.jar and click run.
3. The game should be running.

INSTRUCTIONS OF THE GAME 
Key A - move left 
Key B - move right 
SPACEBAR - pause / continue the game 
Key F - to restart the game after viewing the leaderboard 
Key Alt + Shift + F1 - to open up debug panel (change speed and skip levels) 

VERSION CONTROL - GITHUB 
commits and push are done after each meaningful change in the local file 

JUNIT TEST 
meaningful tests for:
Ball
Brick
Player
Wall 

CODES REFACTORING 
removal of unnecessary variables:
BallModel- radius A and radius B - put into one variable radius 

SOLID principles
single responsibility:   
Brick - crack class taken out 

liskov substitution: 
ball and bricks object can be substituted by the inherited classes instances

DESIGN PRINCIPLES
singleton: 
Player controller class - player is only instantiated once, the constructor is set to private so the class cannot be instantiated in other claseses, the player controller object is referenced with getInstance() method 

Factory: 
brickFactory - to facilitate modifying and additions to the type of bricks 
ballFactory - to make it easier to be maintained and modified, especially in the case where there are different types of balls to be added.

MVC 
Ball, Brick, Player, Gameboard classes were split into Model, View, Controller


ADDITIONS 
instruction screen 
- instruction button in home menu, which brings you to the instructions page. 
- start game button also added 

highscore 
- permanent data stored in a Highscore CSV file 
- User has the option to view the leaderboard which contains their ranking, name and score

Power Ups 
- Add Life (add ball count upon collecting the life icon )  
- Increase speed (speed is increased after collectinng the speed icon)  

Additional Level 
- stone brick introduced 
- cannot be broken 

![image](https://user-images.githubusercontent.com/75315835/145765783-22e7cd5c-df96-4be8-ac15-0a7b0e89271c.png)
-added a instruction button to go to instruction page 

![image](https://user-images.githubusercontent.com/75315835/145766059-725c6dc1-4ef1-41b6-af0f-8fa9c305aa16.png)
-instruction page with start game button 

![image](https://user-images.githubusercontent.com/75315835/145766139-27217ac3-d7e1-4833-8dbc-d181d8ea265a.png)
-extra life power up 
-ball count is incremented 

![image](https://user-images.githubusercontent.com/75315835/145766203-f394496a-fa79-4758-bff0-d229bf1531e9.png)
-increase speed power up 

![image](https://user-images.githubusercontent.com/75315835/145766579-f9c2f6f1-5942-4930-b27f-6caa71b7ccf0.png)
-jdialog box that allows user to input name 

![image](https://user-images.githubusercontent.com/75315835/145766613-54c12bd0-499a-4238-91a2-6d0e5d898ad1.png)
-to view leadeboard or not 

![image](https://user-images.githubusercontent.com/75315835/145767003-4dd2f896-7426-4196-a13f-de51f94ca3d4.png)
-leaderboard 
-press F key to restart game 

![image](https://user-images.githubusercontent.com/75315835/145767220-35837537-5611-46be-87b6-f1926fb92ddc.png)
-extra level with unbreakable brick 










