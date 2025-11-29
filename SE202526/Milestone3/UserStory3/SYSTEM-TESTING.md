## System Testing



---

### ID: 1

### Title: Test Targeting broad (using custom map for testing targeting)

### Purpose: verify that targeting is working

- Step 1:

Description: Start a custom game in sandbox mode (preferably using "Test_Targeting_Map.msav").

Expected Result: A new game is launched in sandbox mode.


- Step 2:

Description: Navigate towards the first two turrets.

Expected Result: The first two turrets are within view and clickable.

- Step 3:

Description: Click on one turret.

Expected Result: Turret targeting menu appears below the turret.


- Step 4:

Description: Pick a desired targeting to change (closest or any).

Expected Result: The targeting mode picked is selected and a drop down options appear.
For ground the options that appear are: (Closest, Farthest, Strongest, Weakest, Fastest, Slowest)
For any the options that appear are: (Any, Air, Ground)


- Step 5:

Description: Pick a option from the options menu.

Expected Result: The desired option is selected and if different replaces the current option.

---

---

## Scenario Based Testing

### Scenario Based Testing - Test Targeting

Pre-Condition: none

#### Test case id: Test Targeting broad (using custom map for testing targeting)

---
Step 1:

Description: Start a custom game in sandbox mode (preferably using "Test_Targeting_Map.msav").

Expected Result: A new game is launched in sandbox mode.


- Step 2:

Description: Navigate towards the first two turrets.

Expected Result: The first two turrets are within view and clickable.


- Step 3:

Description: Click on one turret.

Expected Result: Turret targeting menu appears below the turret.


- Step 4:

Description: Pick a desired targeting to change (closest or any).

Expected Result: The targeting mode picked is selected and a drop down options appear.
For ground the options that appear are: (Closest, Farthest, Strongest, Weakest, Fastest, Slowest)
For any the options that appear are: (Any, Air, Ground)


- Step 5:

Description: Pick añ option from the options menu.

Expected Result: The desired option is selected and if different replaces the current option.


- Step 6:

Description: Press the arrow in the top left to start the next wave.

Expected Result: Enemies spawn and the turrets fire at the enemies with the specified targeting.


---

---

### Scenario Based Testing - Test Targeting using any map

#### Pre-Condition: Inside a map; map has a turret of the player's team

#### Test case id: Test Targeting broad (using any map)

Step 1:
Description: Click on the owned turret.
Expected Result: Menu appears below the turret.


Step 2:
Description: Press button of the two targeting buttons.
(If turret can only target ground or air, the button on the right is grayed out and can not be selected)
Expected Result: A corresponding drop down menu appears with more options.


Step 3:
Description: Select a option  from the drop down.
Expected Result: The selected option swaps to being the current, replacing the text above. The selected option is also has it's boarder highlighted and the previous selected option swaps from highlighted boarder to not.


---



### Scenario Based Testing - Test Targeting Menu

#### Pre-Condition: Custom game in sandbox mode

#### Test case id: Test Targeting menu

- Step 1:

Description: Place down any turret.

Expected Result: Turret is placed on the map.

- Step 2:

Description: Click on the placed down turret.

Expected Result: Menu appears below turret. On the left a button "closest" appears.
On the right a button "any" appears or grayed out text saying: "Air" or "Ground".


- Step 3:

Description: Press one of the options or option.

Expected Result: A drop down menu of buttons appears below the pressed option (with one of the buttons being highlighted).


- Step 4:

Description: Press one of the buttons except the "closest" one.

Expected Result: The Pressed button becomes highlighted in the place of the "closest" one. The button above the drop down list swaps to the pressed option.


---

### Scenario Based Testing - Test Targeting Display

#### Pre-Condition: Custom game in sandbox mode

#### Test case id: Test Targeting Display

- Step 1:
 
Description: Place down any turret.

Expected Result: Turret is placed on the map.

- Step 2:

Description: Hover over the turret

Expected Result: On the bottom right display that contains the turret information appears the text:
"Targeting configurations:
MODE: (some mode)
Units environment: (some environment)"
with some mode, and some environment depending on the turrets current targeting.
(due to being a new turret it will be "CLOSEST" and "ANY","AIR" or "GROUND").




