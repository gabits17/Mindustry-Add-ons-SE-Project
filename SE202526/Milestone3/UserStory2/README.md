# User story 2
Leak identification via minimap and proximity
## Author(s)
- Diogo Antunes (67763)
## Reviewer(s)
- Gabriel Matias Falcão (67775)
- Manuel Oliveira (68547)
- Carolina Ferreira (67804)  

(*Please add the user story reviewer(s) here, one in each line, providing the authors' name and surname, along with their student number. In the reviews presented in this document, add the corresponding reviewers.*)
## User Story:
As a USER WITH AN IN-GAME BASE THAT COVERS A WIDE AREA,
I want to be instantly informed of the location of any leaks of
liquid resources within my base, with their locations clearly highlighted
on the minimap in a different colour the moment they occur,
so that I don't have to identify them manually,
reducing time wasted on looking for the source of resource loss
in a busy game environment.

#### Note (regarding changes from Milestone 1)
- Did not go forward with the decision to add leak messages because when extending an existing leaking pipe, this would be considered
  a new leak, resulting in messages being sent often during casual base-building when setting up liquid flow systems.
### Review

Gabriel Matias Falcão (67775), 24/10/2025

*This could be developed further as a sort of alert system, using the chat or some user interface, in which the player could sort between alerts (destroyed units, pipes, blocks...)*

Manuel Oliveira (68547), 24/10/2025

*The feature looks worth implementing if not too worksome for our timeframe.*

*(Please add your user story review here)*
## Note about content of the following work

This user story extends existing user interaction with the game by showing leaks, so aside from time, there's no primary actor directly interacting with the created system.

For example, the act of breaking/placing of a block stays the same. However, at some point in the logic that handles the update for this scenario, if the block is a leaking liquid-carrying block, or is adjacent to one such block,
the new functionality is triggered.

This means that despite the work document specifying (in the case of class diagrams) to include classes modified or implemented, that alone isn't
enough to describe many of the use cases that eventually access the new functionality. As such, I will include the main pathway of class methods used to reach that point
if it is necessary to explain the added functionality.

Additionally, only the Desktop version of the game is considered, since that is the version readily available for testing.

## Use case diagram
![LeakIdentificationSystem.svg](assets/LeakIdentificationSystem.svg)

#### Actors
Player - Mindustry game user.  
Time - Fixed interval of time in game ticks (game time unit).

## Use case textual description
[Textual Description](US2%20Use%20Case%20Textual%20Description.md)

#### Review
**Author** : Manel Oliveira (68547), 21/11/2025 20:00
A simple note about the document and folder's organization. Maybe split this readme into different files akin to what Gabriel did, and an Assets folder. It really isn't that big of an issue, but it's important to keep things organized.

**Break block** and **Place block** maybe should be generalised to a possible **Interact With Block**. This may not be the case, but from my point of view in this situation they seem to have the same effect on the system. The same could be said to **Enter map** and **Leave map**.

Shouldn't **Enter map** include **Update leakable block tile**?
My reasoning for this is, when a player enters a map with a pre-existing leak, they didn't break or place blocks, but a leak is still displayed.

Other than this the diagram and every use case report seemed very comprehensible and represents very well what I experienced personally testing this feature.

**Author** : Diogo Antunes (67763), 21/11/2025 23:44  
Review response:  
I intend on organising the folder once the final version for class diagrams has occurred, to avoid image name duplication when I'm still changing files.
I will take note to name these images properly, instead of just numbering them, and will adopt the subsection structure as in User Story 1 to better organise my work.

Regarding break block and place block, as mentioned in the use case, break block has an extra step not present in place block.
Additionally, the player interaction is set off under different circumstances, so grouping the two doesn't feel like it follows specification,
even if the two use cases are quite similar.  
Enter map and Leave map have very similar logic, but different ways of starting the interaction, so once again, I feel I shouldn't group them.
On the other hand, I will further factorise the line of resetting logic into clear leaks common to both use cases, and modify the class diagrams accordingly too.  
Regarding enter map loading all tiles, which would add them to the leaks registered. However, I feel this goes under the update buildings use case,
as the focus for the use case of entering a map and leaving a map is purely the logic reset that happens as a result on directly entering the map.
Anything that happens afterward involves loading the map save and retrieving data and then loading buildings, which I feel goes beyond the use case, and more into the scenario into **Update building**.

## Implementation documentation
### Tour Report
#### Commits:
12/11/2025
![img_4.png](img_4.png)
15/11/2025
![img_3.png](img_3.png)
18/11/2025
![img_2.png](img_2.png)
19/11/2025
![img_1.png](img_1.png)
20/11/2025
![img.png](img.png)

#### Affected classes
**Created:**  
``Leaks`` (mindustry.game package in core/src)  
Manages known leaks in the game.

**Modified:**  
``Conduit`` (mindustry.world.blocks.liquid package in core/src)  
Added minimapColor method to override ``Block`` implementation so that the color returned depends on whether the tile is leaking:
````java
    @Override
    public int minimapColor(Tile tile){
        if (Leaks.getInstance().isLeaking(tile)) {
            return Leaks.getLeakColorValue();
        } else {
            return super.minimapColor(tile);
        }
    }
````

``ConduitBuild`` (nested class of Conduit)  
Added a call to check for leaks at the end of ``updateTile()`` so that when the tile updates, there's a check for whether it stopped/started leaking:
````java
Leaks.getInstance().checkLeak(this);
````

``Pal`` (mindustry.graphics package in core/src)  
Contains the colors used in game. Added a new color ``leakingWarn`` to be used when showing leaks.

``DesktopInput`` (mindustry.input package in core/src)  
Added a call to draw the leak overlays (the dashed circles) in ``drawBottom()``.
````java
Leaks.getInstance().drawLocalLeaks();
````

``Build`` (package mindustry.world package in core/src)  
When breaking a block in ``beginBreak()``, there is an explicit attempt to remove the tile from leaks.
````java
Leaks.getInstance().removeLeak(tile);
````

#### Modification class diagram (all classes and methods modified/created)
![cdModifications.png](assets/cdModifications.png)

### Implementation summary
The implementation consists of adding a ``Leaks`` singleton that stores currently active leaking tiles in both a ``HashSet`` (for quickly checking if a tile is present
in the set) and a ``QuadTree`` that stores it in a grid layout to find leaks within a rectangular area more efficiently.

The classes above were modified so that when an action occurs that updates the flow of liquid, this is shown in the minimap (in example: plugging a leak with a solid block,
removing a block that was plugging a leak, or putting down an open-ended pipe (``Conduit``) that begins to leak into a puddle).
The minimap gains a bright blue spot dot to indicate that a spot is leaking, which is removed when the leak stops.  
Additionally, moving close to a leak shows a circle around the leak,
which is useful if the leak wasn't very visible in a busy base.

#### Review
*(Please add your implementation summary review here)*
### Class diagrams
[Class Diagrams](US2%20Class%20Diagrams.md)
#### Review
**Author** : Carolina Ferreira (67804) 23/11/2025 19:11

Besides a little punctuation error in the **Update leakable block tile** section - "The ``checkLeak`` method, checks for a transition from *not leaking -> leaking* and vice versa and in such case adds/removes the tile
from the data structures ``leakTree`` and ``leakSet`` using the ``addLeak(Tile tile)`` or ``removeLeak(Tile tile)`` methods (missing the comma - **,**) respectively." - there are no other elements to correct, in my opinion.

During my review, Diogo corrected the **Clear leaks** diagram, which is confirmed by the commits made in the past few hours. Therefore, I have no further comment on his report. It's a comprehensive report that provides the desired information.

### Sequence diagrams

[Sequence Diagrams](US2%20Sequence%20Diagrams.md)
#### Review
*(Please add your sequence diagram review here)*
## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*