## New Actions
###  Select placed turret
- **Name**: Select placed turret
- **ID**: 1
- **Description**: The player selects a placed turret on the grid.
- **Actors**:
  - *Main*: Player
  - *Secondary*: None
- **Pre-Conditions**: The turret must be built and alive (not destroyed).
- **Main Flow**:
  1. The use case starts when the player clicks on a placed turret.
  2. The system displays the turret's current targeting mode and type.

  *Extension points*: <mark style="background: #bff79fc9;">Press target mode button</mark>, <mark style="background: #bff79fc9;">Press target type button</mark>
  
  3. The player stops selecting the turret.
  4. The system stops displaying the turret's current targeting mode and type.
- **Alternative Flows**
  - None
- **Post-Conditions**: The current targeting mode and type are updated accordingly.

---
## Extension Points

### Change turret's target mode
- **Name**: Change turret's target mode
- **ID**: 2
- **Description**: The player swaps the turret's current targeting mode.
- **Actors**:
  - *Main*: Player
  - *Secondary*: None
- **Pre-Conditions**: The player pressed the turret's target mode button. 
- **Segment 1 Flow**:
  1. The use case starts when the player presses the turret's target mode button.
  2. The system swaps the turret's targeting mode to the next one and displays it.
- **Alternative Flows**
  - None
- **Post-Conditions**: The turret's targeting mode is swapped.

### Change turret's target type
- **Name**: Change turret's target type
- **ID**: 3
- **Description**: The player swaps the turret's current targeting type.
- **Actors**:
  - *Main*: Player
  - *Secondary*: None
- **Pre-Conditions**: The player pressed the turret's target type button. 
- **Segment 2 Flow**:
  1. The use case starts when the player presses the turret's target type button.
  2. The system swaps the turret's targeting type to the next one and displays it.
- **Alternative Flows**
  - <mark style="background: #bff79fc9;">Can't swap targeting type</mark>
- **Post-Conditions**: The turret's targeting type is swapped.

---

## Alternative flows
### Can't swap turret's targeting type
- **Name**: Can't swap turret's targeting type
- **ID**: 3.1
- **Description**: The system doesn't allow the player to change the turret's targeting type.
- **Actors**:
  - *Main*: Player
  - *Secondary*: None
- **Pre-Conditions**: The player pressed the target type button from a turret that targets only one type of enemy units (air or ground).
- **Alternative Flow**:
  1. The alternative flow begins after step 1 of the segment 2 flow.
  2. The system doesn't allow the player to press the button.
- **Alternative Flows**
  - None
- **Post-Conditions**: The turret's targeting type stays fixed on the only type it targets.