# Leak interaction system
(Supplementary to new functionality)
Note - included steps are those relating to reaching the **new functionality**, unrelated steps are omitted.
## Enter map
- **Name**: Enter map
- **ID**: UC1
- **Description**: The player enters a game map
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player has selected a map to play.
- **Main Flow**:
    1. The use case starts when the player presses the button to play the selected map.
    2. The system resets the game state.
    3. <mark style="background: #BBFABBA6;">Include (Clear Leaks)</mark>
- **Alternative Flows**
    - None
- **Post-Conditions**: The player is within the map. No traces from other maps are present.
## Leave map
- **Name**: Leave map
- **ID**: UC2
- **Description**: The player leaves a game map
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**: The player is on the pause menu of an active map.
- **Main Flow**:
    1. The use case starts when the player presses the button to quit the map on the pause menu.
    2. <mark style="background: #BBFABBA6;">Include (Clear Leaks)</mark>
- **Alternative Flows**
    - None
- **Post-Conditions**: The player is at a menu outside a loaded game.

## Place block
- **Name**: Place block
- **ID**: UC3
- **Description**: The player puts a block onto the map.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**:
    - The player is in an unpaused map.
    - The player has a selected block.
    - The player has the required building resources.
    - The player is in a valid building location.
- **Main Flow**:
    1. The use case starts when the player performs the gesture mapped to block placement.
    2. The system constructs the block.
    3. <mark style="background: #BBFABBA6;">Include (Request tile update)</mark>
- **Alternative Flows**
    - None
- **Post-Conditions**: A new block is on the map.
## Break block
- **Name**: Break block
- **ID**: UC4
- **Description**: The player breaks a block on the map.
- **Actors**:
    - *Main*: Player
    - *Secondary*: None
- **Pre-Conditions**:
    - The player is in an unpaused map.
    - The block can be destroyed by player action.
- **Main Flow**:
    1. The use case starts when the player performs the gesture mapped to block breaking.
    2. The system deconstructs the block.
    3. <mark style="background: #BBFABBA6;">Include (Request tile update)</mark>
    4. If the system verifies that the tile of the block is leaking.
        1. The system explicitly removes the tile from known leaks.
- **Alternative Flows**
    - None
- **Post-Conditions**: The map floor is present where the block was located.
## Request tile update (behavior fragment)
- **Name**: Request tile update
- **ID**: UC5
- **Description**: A map tile is modified.
- **Actors**:
    - *Main*: None
    - *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
    1. The system sets up the update of all block tiles, enqueuing them.

Extension point: Update leakable block tile
- **Alternative Flows**
    - None
- **Post-Conditions**: None.
## Update building
- **Name**: Update building
- **ID**: UC6
- **Description**: A building's parameters are modified according to current game events.
- **Actors**:
    - *Main*: Time
    - *Secondary*: None
- **Pre-Conditions**: The game is in an active, unpaused map.
- **Main Flow**:
    1. The use case starts when a fixed interval of time (game ticks) passes.
    2. The system updates the active building according to its traits.
    3. The system updates the main tile belonging to the building.

Extension point: Update leakable block tile
- **Alternative Flows**
    - None
- **Post-Conditions**: The building remains active in the unpaused map.
# Leak identification system
(new functionality use cases)
## Clear leaks (behavior fragment)
- **Name**: Clear leaks
- **ID**: UC7
- **Description**: Delete all registered leaks.
- **Actors**:
    - *Main*: None
    - *Secondary*: None
- **Pre-Conditions**: None.
- **Main Flow**:
    1. The system resets the game logic.
    2. The system clears the consultation set of known leaking tiles.
    3. The system clears the map grid of known leaking tiles.

- **Alternative Flows**
    - None
- **Post-Conditions**: No leaks are registered.
## Update leakable block tile
- **Name**: Clear leaks
- **ID**: UC8
- **Description**: The tile's liquid is pushed forward, and checked for leaks.
- **Actors**:
    - *Main*: None
    - *Secondary*: None
- **Pre-Conditions for segment1**: A building that can leak is having its tiles updated.
- **Segment 1 Flow**:
    1. The use case starts when a game logic reset event is triggered.
    2. The system forwards liquid in the tile to the next connected tile.
    3. If the system verifies that building tile can leak and belongs to the player team.
       1. The system checks if the tile connected to the current is solid (to know if it's leaking)
       1. If the system verifies that the tile has started leaking.
           1. The system registers the tile as leaking.
       2. If the system verifies that the tile has been plugged by a block.
           1. The system removes the register of the tile as leaking.
       3. If the system verifies a transition from not leaking to leaking in either direction.
           1. The system sets up a minimap update for the pixel corresponding to the tile.
       4. If the system verifies liquid stopped flowing in the tile.
           1. The system removes the register of the tile as leaking.
           2. The system sets up a minimap update for the pixel corresponding to the tile.

- **Alternative Flows**
    - None
- **Post-Conditions**: The system has an accurate register of all active leaks.
## Update leak display
- **Name**: Update leak display
- **ID**: UC9
- **Description**: Displays active leaks in the minimap and local leaks in the overworld.
- **Actors**:
    - *Main*: Time
    - *Secondary*: None
- **Pre-Conditions**: None.
- **Main Flow**:
    1. The use case starts when the time interval corresponding to a display update has passed.
    2. The system starts handling pending pixel updates for the minimap.
    3. The system gets the color for new pixels on the minimap (pixels in leaking tiles shown as a light blue).
    4. The system draws dashed rings around the leaks.

- **Alternative Flows**
    - None
- **Post-Conditions**:
    - All active leaks are identified in the minimap.
    - All active local leaks are identified visually on-screen.
