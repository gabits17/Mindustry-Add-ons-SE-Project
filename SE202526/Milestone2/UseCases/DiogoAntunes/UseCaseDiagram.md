# Use Case Diagram Report
## Author
- Diogo Antunes (67763)

# Use Cases - Game Management System
Use Case Diagram pertaining to:
- Loading existing games
- Creating new games
- Joining existing games (from other players)

#### Actors
Player - Mindustry game user.  
Game Host - Mindustry game user hosting a LAN-access map.

## Diagram
![UseCaseDiagram.svg](UseCaseDiagram.svg)

Note:
Both the campaign and the Load custom game have a Game paused extension point to allow connecting to multiplayer.
They do not share logic except for the very end when the player enters the map, allowing the user to pause and
choose the option to Host a multiplayer game, which by itself I feel doesn't justify creating a behaviour fragment.

## Load custom game

### Description
The player selects a saved game to continue playing.
### Actors

#### Primary
Player
#### Secondary
None

## Create Custom Game (extending use case)

### Description
The player customises a game from available maps and rules and starts playing.
### Actors

#### Primary
Player
#### Secondary
None

## Delete custom game

### Description
The player deletes an existing save for a custom game.
### Actors

#### Primary
Player
#### Secondary
None

## Load campaign

### Description
The player picks a planet and sector map from unlocked content and continues exploration.
### Actors

#### Primary
Player
#### Secondary
None.

## Start campaign

### Description
Starts the game tutorial in an available, pre-determined planet and sector map picked by the player.
### Actors

#### Primary
Player
#### Secondary
None.

## Host multiplayer game (extending use case)

### Description
The player's active game becomes temporarily available to other players in the same LAN (becomes the host).
### Actors

#### Primary
Game Host
#### Secondary
None

## Join external game (abstract)

### Description
The player participates in a game organised by another entity.
### Actors

#### Primary
Player
#### Secondary
Player (0 or more) - other players in the map that affect gameplay.

## Join network game

### Description
The player participates in a game organised by another player in the local network.
### Actors

#### Primary
Player
#### Secondary
Player (0 or more) - other players in the map that affect gameplay.  
Game Host

## Join remote game

### Description
The player chooses a game server hosted by a remote server to play in from a list of options.
### Actors

#### Primary
Player
#### Secondary
Player (0 or more) - other players in the map that affect gameplay.

## Import save

### Description
The player creates a new game save from an external file.
### Actors

#### Primary
Player
#### Secondary
None

## Export save

### Description
The player stores a generated file from a game save.
### Actors

#### Primary
Player
#### Secondary
None