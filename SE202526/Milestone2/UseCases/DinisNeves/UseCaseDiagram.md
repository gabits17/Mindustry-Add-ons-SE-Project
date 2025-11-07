# Use Case Diagram Report

## Author
- Dinis Neves (68130)

# Use Cases - Unit Control System
Unit control / command System
## Diagram
![UnitControlSystem.png](assets/UnitControlSystem.png)
---  

## Use Case 1 Name
*Select a unit*
### Description
This use case represents the act selecting a unit. the player must own the unit and click on it while in command mode (holding gown shift) to select said unit.
### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 2 Name
Select Group of Units
### Description
This use case is similar to use case 1 except the player will select a region and the units inside that region will be selected.
### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 3 Name
*Attack target*
### Description
This use case is commanding selected units to fire if possible at a specific target. Player selects as a target. It might not be possible to target with the current selection of units the specific target. there are flying, naval and ground. Selected units may not be able to hit the target due to being unable to fire at that type of target or because they cannot get in range to fire at that target.
Selected units will also move towards, chase down the target.

### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 4 Name
*Move Selected*
### Description
This use case is about giving the orders for units to move to a specific place / spot. If not possible the units that cannot make it there will not move.
### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 5 Name
*Repair Target*
### Description
Just like Use Case 3 (Attack Target) Selected units if possible will repair the target building or unit, (if possible). The unit being repaired can be flying, ground or naval.

### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 6 Name
*Deselect Unit or Units*
### Descritpion
The goal of this use case is to stop giving commands to units, stop commanding them.

### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 7 Name
*Deconstruct Unit*
### Description
This use case through the interaction of a Deconstractor block recycle and destroy a selected unit.
### Actors
Player
#### Primary
Player
#### Secondary
None
  
---  

## Use Case 8 Name
*Respawn*
### Description
The goal of this use case is to go back to controlling the Core Unit (alpha) of the game. If already controlling a Core Unit it is destroyed.

### Actors
Player
### Primary
Player
### Secondary
None
  
---  

## Use Case 9 Name
*Take Control of a Unit*
### Description
This use case is controlling a unit not commanding it. The player will now interact with the unit as if it was a Core Unit, except it may not have all the same actions as a Core Unit.
### Actors
Player
### Primary
Player
### Secondary
None

  
---  

## Use Case 10 Name
*Move Unit*
### Description
This use case is controlling the movement of a character by pressing (w,a,s,d).

### Actors
Player
### Primary
Player
### Secondary
None

  
---  

## Use Case 11 Name
*Fire Weapon*
### Description
This use case has the objective to make the controlled unit fire in the direction of the mouse. The player points at where it wants the current unit being controlled to fire if passible. There are some units that might not be able to fire, don not have a weapon.

### Actors
Player
### Primary
Player
### Secondary
None

  
---  

## Use Case 12 Name
*Mine ore*
### Description
This use case is making if possible the unit being controlled mine a target ore. Certain units may not the the ability to mine ores.

### Actors
Player
### Primary
Player
### Secondary
None

  
---  

## Use Case 13 Name
*Repair*
### Description
This use case has the objective to repair blocks that have been destroyed by selecting a area to reconstruct buildings inside of that area, when the controlled unit is close enough to that area.

### Actors
Player
### Primary
Player
### Secondary
None