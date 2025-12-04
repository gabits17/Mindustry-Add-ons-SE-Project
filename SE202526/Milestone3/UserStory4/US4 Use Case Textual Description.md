# Copy history
### Note on Schematics
Just for clarification, in the game there is an element called "Schematics" that are player made constructions that are kept in files.
On the other hand, in the codebase for the game, a schematic is a selection of blocks that can be saved to a file, but a schematic in this context is also used for many other purposes.

In our case we used schematics as the main way of keeping a collection of selections that were copied.

For that reason, except for titles, throughout this document we'll be referring to the schematics used in the code as **"schematics"**, with lower case, and schematics that are saved to a file as **"Schematics"**, with higher case.

## Main Functionalities
###  **Select tiles**
- **Name**: Select tiles
- **ID**: UC1
- **Description**: This Player selects the desired tiles.
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: The Player is in a valid game and map.
- **Main Flow**:
  1. The player moves the cursor to one fo the corners of the intended selection.
  2. The player presses the binding defined for selection(default is the "F" key).
  3. The player drags the mouse until the corner of the selection opposite of the initial corner.
  4. The player stops pressing the selection key, defining the schematic selected.
  5. If the player presses the "Save Schematic" button.
     1. Then the menu to save a Schematic is open.
  6. If the player presses the keybind to copy a schematic (default is "ctrl + c").
     1. Then the schematic selected is copied to the history of copied schematics.
  7. The use case ends.

Extension point: Save Schematic

Extension point: Copy Schematic

- **Alternative Flows**
	- None
- **Post-Conditions**: The tiles selected are displayed.

###  **Terminate Game**
- **Name**: Terminate Game
- **ID**: UC2
- **Description**: The player quits the game.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
  1. The player quits the game, shutting down the application.
  2. <mark style="background: #BBFABBA6;">Include (Eliminate History).</mark>
- **Alternative Flows**
    - None
- **Post-Conditions**: The game ends and the application stops running.

###  **Change Planet**
- **Name**: Change Planet
- **ID**: UC3
- **Description**: The player selects a planet.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: None
  - **Main Flow**:
    1. The Player Selects a planet to play.
    2. The game loads the planet chosen and respective Copy History.
    3. <mark style="background: #BBFABBA6;">Include (Load Planet's History).</mark>
- **Alternative Flows**
    - None
- **Post-Conditions**: The game displays the planet selected.

###  **Change Copy**
- **Name**: Change Copy
- **ID**: UC4
- **Description**: The player chooses what copied schematic they want to build.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player is in a map.
    - **Main Flow**:
      1. The Player presses the keybinds to paste a copied schematic (default is Ctrl + V).P
      2. The game loads and displays the last copied schematic.
      3. If the player presses the keybind to see the previous copied schematic.
         1. Then displays the previous copied schematic.
      4. If the player presses the keybind to see the next copied schematic.
         1. Then displays the next copied schematic.
      5. If the player presses the place keybind (left click by default)
         1. Then places the displayed schematic in the game world.

Extension point: Paste Copy

Extension point: Scroll through copies

- **Alternative Flows**
    - None
- **Post-Conditions**: The last copied schematic is displayed and ready to be built.

## Includes
## **Eliminate History**
- **Name**: Eliminate History
- **ID**: IUC1
- **Description**: This use case clears the history of copied schematics.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player selected to quit the game.
- **Main Flow**:
    1. The instance that kept the copied schematics is eliminated.
- **Alternative Flows**
    - None
- **Post-Conditions**: The game ended.

## **Load Planets History**
- **Name**: Load Planets History
- **ID**: IUC2
- **Description**: This use case loads a planet's history of copied schematics.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player selected a planet.
- **Main Flow**:
    1. The planet the player chose is loaded, as is it's history if it previously existed, otherwise its created.
- **Alternative Flows**
    - None
- **Post-Conditions**: The planet's history of loaded schematics is loaded.


## Extends
## **Save Schematic**
- **Name**: Save Schematic
- **ID**: EUC1
- **Description**: This use case adds the functionality of saving a selected schematic to a file 
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a schematic.
- **Main Flow**:
  1. The player presses the "Save Schematic" button.
  2. The menu to save a Schematic is open.
  3. The player enters the necessary info and the schematic is saved.

- **Alternative Flows**
    - None
- **Post-Conditions**: The schematic is saved to a file.

## **Copy Schematic**
- **Name**: Copy Schematic
- **ID**: EUC2
- **Description**: This use case adds the functionality of saving a selected schematic to a history of copied schematics.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a schematic.
- **Main Flow**:
    1. The player presses the keybind to copy a schematic (default is "ctrl + c").
    2. The schematic selected is copied to the history of copied schematics.

- **Alternative Flows**
    - None
- **Post-Conditions**: The schematic is saved to the planet's history.

## **Paste Copy**
- **Name**: Paste Copy
- **ID**: EUC3
- **Description**: This use case adds the functionality of pasting a copied schematic.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a schematic from the history of copied ones.
- **Main Flow**:
    1. The player presses the place keybind (left click by default).
    2. Then places the displayed schematic in the game world.

- **Alternative Flows**
    - None
- **Post-Conditions**: The schematic is placed in the game world.

## *Scroll through copies*
- **Name**: Copy Schematic
- **ID**: EUC4
- **Description**: This use case adds the function of browsing through the saved schematics.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a schematic.
- **Main Flow**:
    1. The player scrolls through the copies of schematics saved to the history.
- **Alternative Flows**
    - None
- **Post-Conditions**: None.

## **Get next copy**
- **Name**: Get next copy
- **ID**: EUC4.1
- **Description**: This use case adds the function of saving a selected schematic to a history of copied schematics.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a schematic.
- **Main Flow**:
  1. (01.) The player scrolls through the copies of schematics saved to the history.
  2. The player presses the keybind to see the next copied schematic.
  3. The game displays the next copied schematic.
- **Alternative Flows**
    - None
- **Post-Conditions**: None.

## **Get previous copy**
- **Name**: Get previous copy
- **ID**: EUC4.2
- **Description**: This use case adds the function of saving a selected schematic to a history of copied schematics.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a schematic.
- **Main Flow**:
    1. (01.) The player scrolls through the copies of schematics saved to the history.
    2. The player presses the keybind to see the previous copied schematic.
    3. The game displays the previous copied schematic.
- **Alternative Flows**
    - None
- **Post-Conditions**: None.


