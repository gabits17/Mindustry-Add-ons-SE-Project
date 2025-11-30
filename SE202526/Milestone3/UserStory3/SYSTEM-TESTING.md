## System Testing

### Introduction

Due to the nature of the implementation we think System Testing is more suitable because it offers a better look into the targeting implementation working. More than one test characterized below with complexity and number of Steps being decreased.

A video of the implemented feature is available, it uses a custom map to show clearly thee targeting in action.

The video goes through all of the targeting as if it was looping through steps 3 through 6 of Test Case Id: "Test Targeting broad (using custom map for testing targeting)".

- Video Link: (https://youtu.be/ZgU6H4FOpDI?si=zcoV3SOzqkmD1HZo)


---

---

## Scenario Based Testing - Test Targeting

| Pre Condition | Test Case Id                                                  | Steps                                                                                | Expected Result                                                                                                                                                                                                                             |
| ------------- | ------------------------------------------------------------- | ------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| none          | Test Targeting broad (using custom map for testing targeting) | 1. Start a custom game in sandbox mode (preferably using "Test_Targeting_Map.msav"). | A new game is launched in sandbox mode.                                                                                                                                                                                                     |
|               |                                                               | 2. Navigate towards the first two turrets.                                           | The first two turrets are within view and clickable.                                                                                                                                                                                        |
|               |                                                               | 3. Click on one turret.                                                              | Turret targeting menu appears below the turret.                                                                                                                                                                                             |
|               |                                                               | 4. Pick a desired targeting to change (closest or any).                              | The targeting mode picked is selected and a drop down options appear.  <br>For ground the options that appear are: (Closest, Farthest, Strongest, Weakest, Fastest, Slowest)  <br>For any the options that appear are: (Any, Air, Ground) . |
|               |                                                               | 5. Pick an option from the options menu.                                             | The desired option is selected and if different replaces the current option.                                                                                                                                                                |
|               |                                                               | 6. Press the arrow in the top left to start the next wave.                           | Enemies spawn and the turrets fire at the enemies with the specified targeting.                                                                                                                                                             |

---

---
## Scenario Based Testing - Test Targeting using any map

| Pre Condition                                         | Test Case Id                         | Steps                                                                                                                                                     | Expected Result                                                                                                                                                                                            |
| ----------------------------------------------------- | ------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Inside a map<br>Map has a turret of the player's team | Test Targeting broad (using any map) | 1. Click on the owned turret.                                                                                                                             | Menu appears below the turret.                                                                                                                                                                             |
|                                                       |                                      | 2. Press button of the two targeting buttons.<br>(If turret can only target ground or air, the button on the right is grayed out and can not be selected) | A corresponding drop down menu appears with more options.                                                                                                                                                  |
|                                                       |                                      | 3. Select a option  from the drop down.                                                                                                                   | The selected option swaps to being the current, replacing the text above. The selected option is also has it's boarder highlighted and the previous selected option swaps from highlighted boarder to not. |

---

---

## Scenario Based Testing - Test Targeting Menu

| Pre Condition                          | Test Case Id        | Steps                                                 | Expected Result                                                                                                                                           |
| -------------------------------------- | ------------------- | ----------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Inside custom game <br>in sandbox mode | Test Targeting menu | 1. Place down any turret.                             | Turret is placed on the map.                                                                                                                              |
|                                        |                     | 2. Click on the placed down turret.                   | Menu appears below turret. On the left a button "closest" appears.  <br>On the right a button "any" appears or grayed out text saying: "Air" or "Ground". |
|                                        |                     | 3. Press one of the options or option.                | A drop down menu of buttons appears below the pressed option (with one of the buttons being highlighted).                                                 |
|                                        |                     | 4. Press one of the buttons except the "closest" one. | The Pressed button becomes highlighted in the place of the "closest" one. The button above the drop down list swaps to the pressed option.                |

---

---

## Scenario Based Testing - Test Targeting Display

| Pre Condition               | Test Case Id           | Steps                     | Expected Result                                                                                                                                                                                                                                                                                                                                                |
| --------------------------- | ---------------------- | ------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Custom game in sandbox mode | Test Targeting Display | 1. Place down any turret. | Turret is placed on the map.                                                                                                                                                                                                                                                                                                                                   |
|                             |                        | 2. Hover over the turret  | On the bottom right display that contains the turret information appears the text:  <br>"Targeting configurations:  <br>MODE: (some mode)  <br>Units environment: (some environment)"  <br>with some mode, and some environment depending on the turrets current targeting.  <br>(due to being a new turret it will be "CLOSEST" and "ANY","AIR" or "GROUND"). |




