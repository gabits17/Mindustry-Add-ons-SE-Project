# Use Case Diagram Report
## Author
- Gabriela Silva (67286)

# Use Cases - System Names
*Campaign Progression* and *Progression Display*

## Diagram
![game-progression.svg](Assets/progression-system.svg)

---

## Use Case 1 Name
*Play Mission on Sector*
### Description
This use case represents the act of playing a mission on a planet sector. For that, the player must choose a planet where the sector is located, and they can load an ongoing mission or launch a new one.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 2 Name
*Unlock Item*
### Description
The goal of this use case is to show an interaction between a player and their progress, by unlocking new items of the game. These items can be **Units**, **Effects**, **Ores**, **Blocks** and **Liquids**. They can be unlocked when the player is exploring during a mission.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 3 Name
*Unlock Sector*
### Description
This use case has the purpose to show that the player can unlock a new sector. For that, they must have conquered a sector where they were playing a mission, by surviving all wave attacks and protecting their base.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 4 Name
*View Tech Tree*
### Description
It is possible to represent the action of the player to view the progression tree of the campaign by this use case. The player must choose a planet to inspect the *tech tree* of that planet. By default, the planet chosen is *Serpulo*.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 5 Name
*Inspect Tech Node*
### Description
This use case allows the action of the player to inspect each item of the campaign progression tree. It requires that the player must have opened the campaign tech tree.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 6 Name
*Inspect Core Database*
### Descritpion
The goal of this use case is to show all *current* unlocked items for both planets. This use case requires that the tech tree is opened and depends on the progress saved so far.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 7 Name
*Save Progress*
### Description
This use case represents the storage of the game progress in the game's data base. It is important, since viewing the campaign progression depends on it. The saving can be made automatically.
### Actors
Player
#### Primary
Player
#### Secondary
None

---

## Use Case 8 Name
*Auto Save*
### Description
The purpose of this use case is to demonstrate that the time can automatically save progression, contributing effectively to the *Save Progress* use case.
### Actors
Time
### Primary
Time
### Secondary
None