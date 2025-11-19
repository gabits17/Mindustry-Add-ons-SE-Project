# Main Functionalities
##  Undo
- **Name**: Undo
- **ID**: UC1
- **Description**: This UC undoes the last action performed by the player.
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
	1. The use case starts when the player requests an action to be undone (keybind for example)
	2. <mark style="background: #FFB86CA6;">The system removes the done action from its stack</mark>
	3. <mark style="background: #FFB86CA6;">The system adds the action as undone to its stack</mark>
	4. The system executes the action just added
	5. The use case ends
- **Alternative Flows**
	- No Done Action
	- Undone Stack Full
- **Post-Conditions**: The undone action is recorded
### No Done Action

- **Name**: No Done Action
- **ID**: AF1
- **Description**: The player tried to undo an action and no action had been done
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: 
- **Main Flow**:
	1. The use case starts before step 2 of the main flow
	2. The system displays an error message saying that there does not exist an action to undo
	3. The use case and the main flow end
- **Alternative Flows**
	- None
- **Post-Conditions**: None

###  Undone Stack Full
- **Name**: Undone Stack Full
- **ID**: AF2
- **Description**: The stack used to store the actions undone by the player is full
- **Actors**:
	- *Main*: Player
	- *Secondary*: 
- **Pre-Conditions**: None 
- **Main Flow**:
	1. The use case starts before step 3 of the main flow
	2. The system removes the stack's last element
	3. The system adds the undone action to the stack
	4. Return to step 3 of the main flow
- **Alternative Flows**
	- None
- **Post-Conditions**: Undone Stack not Full

##  Redo
- **Name**: Redo
- **ID**: UC2
- **Description**: This UC redoes the last action undone by the player.
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
	1. The use case starts when the player requests an action to be redone (keybind for example)
	2. <mark style="background: #FFB86CA6;">The system removes the undone action from its stack</mark>
	3. <mark style="background: #BBFABBA6;">Include Add Done Action</mark>
- **Alternative Flows**
	- No Undone Action
- **Post-Conditions**: The redone action is recorded
### No Undone Action
- **Name**: No Undone Action
- **ID**: AF3
- **Description**: The player tried to redo an action and no action had been undone
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: 
- **Main Flow**:
	1. The use case starts before step 2 of the main flow
	2. The system displays an error message saying that there does not exist an action to redo
	3. The use case and the main flow end
- **Alternative Flows**
	- None
- **Post-Conditions**: None
# Actions
## Paste Schematic
- **Name**: Paste Schematic
- **ID**: UC3
- **Description**: The player pastes a schematic
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: The player has a schematic selected to be placed 
- **Main Flow**:
	1.  The use case starts after the player requests to paste a schematic
	2. The system creates an action to paste the schematic
	3. <mark style="background: #BBFABBA6;">Include Add Done Action</mark>
- **Alternative Flows**
	- None
- **Post-Conditions**: The done action is recorded
## Build Building
- **Name**: Build Building
- **ID**: UC4
- **Description**: The player builds a building
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: The player has a building selected to be built
- **Main Flow**:
	1. The use case starts after the player requests to build a building
	2. The system creates an action to build the building
	3. <mark style="background: #BBFABBA6;">Include Add Done Action</mark>
- **Alternative Flows**
	- None
- **Post-Conditions**: The done action is recorded
## Remove Selection
- **Name**: Remove Selection
- **ID**: UC5
- **Description**: The player removes a selected area
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: The player has an area selected to remove
- **Main Flow**:
	1. The use case starts after the player requests to remove a selection
	2. The system creates an action to remove the selection
	3. <mark style="background: #BBFABBA6;">Include Add Done Action</mark>
- **Alternative Flows**
	- None
- **Post-Conditions**: The done action is recorded
## Rotate Building
- **Name**: Rotate Building
- **ID**: UC6
- **Description**: The player rotates a building
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: The player has its cursor on a building to be rotated
- **Main Flow**:
	1. The use case starts after the player requests to rotate a building
	2. The system creates an action to rotate the building
	3. <mark style="background: #BBFABBA6;">Include Add Done Action</mark>
- **Alternative Flows**
	- None
- **Post-Conditions**: The done action is recorded
# Map
## Enter Map
- **Name**: Enter Map
- **ID**: UC7
- **Description**: The player enters a map, be it a sector, a custom map a server...
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
	1. The use case starts when the player enters a map (sector, a custom map a server...)
	2. <mark style="background: #BBFABBA6;">Include Reset Action Commander</mark>
- **Alternative Flows**
	- None
- **Post-Conditions**: None
## Leave Map
- **Name**: Leave Map
- **ID**: UC8
- **Description**: The player leaves a map, be it a sector, a custom map a server...
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
	1. The use case starts when the player leaves a map (sector, a custom map a server...)
	2. <mark style="background: #BBFABBA6;">Include Reset Action Commander</mark>
- **Alternative Flows**
	- None
- **Post-Conditions**: None
# Includes
## Add Done Action
- **Name**: Add Done Action
- **ID**: IUC1
- **Description**: This use case adds a done action to the systems memory
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None 
- **Main Flow**:
	1. The use  case starts when the player performs any request to add a done action (redo, placing for example)
	2. <mark style="background: #FFB86CA6;">The system adds the done action to its stack</mark>
	3. The system executes the action just added
- **Alternative Flows**
	- Done Stack Full
- **Post-Conditions**: The done action is recorded
### Done Stack Full
- **Name**: Done Stack Full
- **ID**: AF4
- **Description**: The stack used to store the actions done by the player is full
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None 
- **Main Flow**:
	1. The use case starts after step 2 of the main flow
	2. The system removes the stack's last element
	3. return to step 2 of the main flow
- **Alternative Flows**
	- None
- **Post-Conditions**: The done stack is not full
## Reset Action Commander
- **Name**: Reset Action Commander
- **ID**: IC2
- **Description**: The use case clears the system's memory about actions
- **Actors**:
	- *Main*: Player
	- *Secondary*: None
- **Pre-Conditions**: None
- **Main Flow**:
	1. The use case when the player requests to reset the action commander
	2. The system clears the done action stack
	3. The system clears the undone action stack
- **Alternative Flows**
	- None
- **Post-Conditions**: None