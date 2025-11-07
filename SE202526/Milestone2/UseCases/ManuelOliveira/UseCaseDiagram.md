# Use Case Diagram Report
## Author
- Manel Oliveira (68547)

# Use Cases - Building System
My intention was to describe most of the possible interactions a **Player** can directly have with a Block in the game.
Please note that this does **not** include interactions with other units, or interactions other units can have player placed blocks, hence, for example, why the player cannot directly damage blocks.

## Diagram
![Use_Case.svg](Assets/Use_Case.svg)


## Select Placed Block
### Description
The player selects and copies one or many placed blocks to "clipboard" in one motion.
### Actors
#### Primary
Player
#### Secondary
None

## Destroy Block
### Description
The player destroys a placed block that belongs to them, by selecting it with right mouse button.
### Actors
#### Primary
Player
#### Secondary
None

## Copy Block
### Description
The player selects and copies one or many placed blocks to "clipboard" in one motion.
### Actors
#### Primary
Player
#### Secondary
None

## Save Schematic
### Description
The player can save a schematic containing all the placed blocks that are copied.
### Actors
#### Primary
Player
#### Secondary
None

## Select Type of Block
### Description
The player selects the type of block they intend to select, like turrets, miners, defences, etc.
### Actors
#### Primary
Player
#### Secondary
None

## Select Block
### Description
The player selects a block in the building blocks menu, this use case has many includes to other use cases because a lot of actions automatically require the selection of the block in one way or the other.
### Actors
#### Primary
Player
#### Secondary
None

## Places Block
### Description
The player places a block they have selected on the ground to be built.
### Actors
#### Primary
Player
#### Secondary
None

## Builds Block
### Description
The player builds a block that was previously placed on the ground.
### Note
It's important to note that the action of building a block in the game is slightly ambiguous.
A player doesn't manually build each block once they are placed on the ground per say, but a player still has to take their inputs into account in the building process.
To put it simply, the player doesn't simply build a block, but the unit the player controls builds a block, witch I believe is still an interaction with the player as an actor.
### Actors
#### Primary
Player
#### Secondary
None

## Clicks Built Block
### Description
The player clicks in a block built in the game world.
### Actors
#### Primary
Player
#### Secondary
None

## View Block Info
### Description
Displays the information of a block that has been selected form the building menu.
### Actors
#### Primary
Player
#### Secondary
None

## View Built Block Info
### Description
Displays the information of a block that has been built in the game world.
### Actors
#### Primary
Player
#### Secondary
None

