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
### Implementation summary
The implementation consists of adding a ``Leaks`` singleton that stores currently active leaking tiles in both a ``HashSet`` (for quickly checking if a tile is present
in the set) and a ``QuadTree`` that stores it in a grid layout to find leaks within a rectangular area more efficiently.

The classes above were modified so that when an action occurs that updates the flow of liquid, this is shown in the minimap (in example: plugging a leak with a solid block,
removing a block that was plugging a leak, or putting down an open-ended pipe (``Conduit``) that begins to leak into a puddle).
The minimap gains a bright blue spot dot to indicate that a spot is leaking, which is removed when the leak stops.  
Additionally, moving close to a leak shows a circle around the leak,
which is useful if the leak wasn't very visible in a busy base.

### Tour Report
#### Commits:
12/11/2025
![img_4.png](assets/commitImgs/img_4.png)
15/11/2025
![img_3.png](assets/commitImgs/img_3.png)
18/11/2025
![img_2.png](assets/commitImgs/img_2.png)
19/11/2025
![img_1.png](assets/commitImgs/img_1.png)
20/11/2025
![img.png](assets/commitImgs/img.png)
26/11/2025
![img_5.png](assets/commitImgs/img_5.png)

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

Omitted private constants as they're either magic numbers (a specific max range) or "shortcuts" to another variable (the color used for leaks).
I feel these aren't specifically relevant to the class structure and don't need to exist (just syntactic sugar), therefore I didn't put them into class diagrams.

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

I wasn't able to create unit tests for the new functionality. The ``ApplicationTests`` supports setting up a very reduced game environment to allow for testing functionalities. The problem is that the ``Leaks`` class specifically interacts with the ``Renderer`` from ``Vars``, and ``Renderer``
has a series of **public final** attributes that in test mode cause errors due to some structures at a lower level of abstraction (a ByteBuffer that isn't working in test mode). No existing unit test seems to use renderers either, due to their communication with the display that becomes problematic in a test case.

The issue with these is that I unfortunately cannot use anonymous classes to override their function since attributes cannot be overridden by anonymous classes.
Since the main important method in ``Leaks`` ``checkLeak()`` requires using the ``Renderer`` instance in ``Vars`` to access the minimap, and others are simply complementary to it (or in the case of showLocalLeaks interact with drawing to the display,
so can't be tested in test mode), it means no tests can be reasonably done without creating an entirely separate branch with modified logic to allow for testing, but that would then not function for the normal game scenario. This doesn't
seem to be the outcome we're looking for in unit tests, so I'll set that aside.

Additionally, with ``Leaks`` being a singleton, the class instantiation is done in the static method ``getInstance()``, so I can't simply create an override in an anonymous class, because that anonymous class would never be used.
Classes call ``Leaks.getInstance()`` to gain access to the singleton instance, so this behavior cannot be modified.

### Scenario-based testing
#### Test guide
Note: I don't specify different tests for different liquid types because the logic does not interact with liquid types directly, only with a certain kind of liquid-transferring block, for which the functionality while
holding any liquid should not differ, as the liquids are not handled separately. Most tests show leaks over ground instead of water. This is because leaks on the ground create a puddle, so are more visible and therefore easier
to confirm visually than leaks over water. The code implementation doesn't actually involve puddles, these were an existing, separate part of the game.

These tests cover single player tests:

The block placed here is a conduit, there is another one that looks the same but is also ``Conduit``, it is simply the version of the conduit from another type of map (looks different visually, same class used).

| Pre-Condition                                                                                                                                                                 | Test case id                                            | Description                                                                                                                                                                                                | Expected Result                                                                                                                                                                                                                                                                       | Video                                                                                                                        |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------|
| - At map selection screen                                                                                                                                                     | Joining map with leaks                                  | 1. Join a custom game.<br/>2. Open minimap.                                                                                                                                                                | 1. Map loads all active leaks. Nearby player-team leaks have light-blue dashed circle.<br/>2. All leak tiles have corresponding pixels on minimap as light blue color.                                                                                                                | [![JoinMapTest](https://img.youtube.com/vi/JiBHQCbIdM8/0.jpg)](https://www.youtube.com/watch?v=JiBHQCbIdM8)                  |
| - Within a map with player-team leaks<br/>- Enough resources for block placement                                                                                              | Plugging player-team leak with block                    | 1. Move close to a nearby leak in the team-controlled section.<br/>2. Open minimap.<br/>3. Close minimap.<br/>4. Place block on leaking end of conduit.<br/>5. Open minimap.                               | 1. Dashed circle around leak shows.<br/>2. The minimap pixel corresponding to the leaking tile is light-blue.<br/>3. Normal overworld shown.<br/>4. Leak stops. Dashed circle disappears.<br/>5. Minimap pixel for corresponding tile stops being light blue.                         | [![PlugTeamLeakWBlock](https://img.youtube.com/vi/WlNNyXtdOHI/0.jpg)](https://www.youtube.com/watch?v=WlNNyXtdOHI)           |
| - Within a map with plugged leak <br/>- Player-team conduit leak over ground plugged by solid block                                                                           | Creating player-team leak by breaking block over ground | 1. Approach nearby plugged leak in the team-controlled section without liquid floor.<br/>2. Open minimap.<br/>3. Close minimap.<br/>4. Remove solid block from flowing end of conduit.<br/>5. Open minimap. | 1. No dashed circle loads around plugged leak (since there is no leak).<br/>2. Corresponding minimap pixel is not light blue<br/>3. Normal overworld shown.<br/>4. Puddle forms on ground. Dashed circle around newly created leak.<br/>5. Corresponding minimap pixel is light blue. | [![StartTeamLeakWBlock](https://img.youtube.com/vi/UEp_P90wBQo/0.jpg)](https://www.youtube.com/watch?v=UEp_P90wBQo)          |
| - Within a map with plugged leak<br/>- Player-team conduit leak over water plugged by solid block                                                                             | Creating player-team leak by breaking block over water  | 1. Approach nearby plugged leak in the team-controlled section with liquid floor.<br/>2. Open minimap.<br/>3. Close minimap.<br/>4. Remove solid block from flowing end of conduit.<br/>5. Open minimap.   | 1. No dashed circle loads around plugged leak (since there is no leak).<br/>2. Corresponding minimap pixel is not light blue<br/>3. Normal overworld shown.<br/>4. Dashed circle around newly created leak.<br/>5. Corresponding minimap pixel is light blue.                         | [![StartTeamLeakWBlockOverWater](https://img.youtube.com/vi/cgb5jHEgM08/0.jpg)](https://www.youtube.com/watch?v=cgb5jHEgM08) |
| - Within a map with player-team active leak<br/>- Within range of player-team conduit leak                                                                                    | Player team leak out of range                           | 1. Move away from nearby leak.                                                                                                                                                                             | 1. Dashed circle around leak disappears.                                                                                                                                                                                                                                              | [![moveAway](https://img.youtube.com/vi/oFDy8mPUPRY/0.jpg)](https://www.youtube.com/watch?v=oFDy8mPUPRY)                     |
| - Within a map with player-team active leak<br/>- Outside of range of player-team conduit leak                                                                                | Player team leak within range                           | 1. Move towards nearby leak.                                                                                                                                                                               | 1. Dashed circle around leak shows.                                                                                                                                                                                                                                                   | [![moveTowards](https://img.youtube.com/vi/ljXKNiBmXXw/0.jpg)](https://www.youtube.com/watch?v=ljXKNiBmXXw)                  |
| - Within a map with multiple player-team active leak<br/>- Player-team leaks within intersecting range of detection<br/>- Outside of range of at one player-team conduit leak | Multiple player-team leaks within range                 | 1. Move between multiple leaks.                                                                                                                                                                            | 1. Dashed circle around all nearby leaks show.                                                                                                                                                                                                                                        | [![leakIntersect](https://img.youtube.com/vi/K5OS1r5hDzw/0.jpg)](https://www.youtube.com/watch?v=K5OS1r5hDzw)                |
| - Within a map with non-player-team leaks<br/>- Nearby non-player-team leak                                                                                                   | Non-player-team leaks aren't registered                 | 1. Open minimap.                                                                                                                                                                                           | 1. There is no light-blue pixel in the tile with a leaking conduit.                                                                                                                                                                                                                   | [![nonTeamLeak](https://img.youtube.com/vi/MDHXi_B1FlA/0.jpg)](https://www.youtube.com/watch?v=MDHXi_B1FlA)                  |
| - Within a map <br/>- Nearby armored conduit pipeline                                                                                                                         | Armored Conduits (non-leaking) don't register leaks     | 1. Open minimap.                                                                                                                                                                                           | 1. There is no light-blue pixel in the pipeline ending with an armored conduit.                                                                                                                                                                                                       | [![armoredConduitsNoLeak](https://img.youtube.com/vi/-SUYHOVlutU/0.jpg)](https://www.youtube.com/watch?v=-SUYHOVlutU)        |

Leak forwarding (creating new leak and patching old ones on extending a leaking pipeline).

| Pre-Condition                                                                                                                                                                    | Test case id                               | Description                                                                                                                                                                                               | Expected Result                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | Video                                                                                                             |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| - Within a map with player-team active leak<br/> - Within building range of leak over ground (not the same as dashed-circle range)                                               | Extending leaking pipeline over ground     | 1. Open minimap.<br/>2. Close minimap.<br/>3. Place a line of conduits at the leaking end of a conduit.<br/>4. Open minimap.                                                                              | 1.The minimap tile corresponding to the leaking tile is light blue.<br/>2. Normal overworld shown.<br/>3. The leak is plugged (puddle on the ground in front of conduit gets smaller). A new leak is created (puddle on the ground in front of last conduit in pipeline begins to grow)<br/>4. Minimap previous leak pixel no longer light blue. Pixel corresponding to end of leaking pipeline now light blue.                                                                                                                                                             | [![leakForwarding](https://img.youtube.com/vi/YFRAVxSkq4E/0.jpg)](https://www.youtube.com/watch?v=YFRAVxSkq4E)    |
| - Within a map with player-team active leak from a long pipeline (>15 conduit blocks)<br/> - Within block breaking range of second conduit in pipeline. | Breaking long leaking pipeline over ground | 1. Open minimap.<br/>2. Close minimap.<br/>3. Break the second conduit in the pipeline (starting from the source pump).<br/>4. Immediately open minimap.<br/>5. Wait a few seconds.<br/>6. Close minimap. | 1. There is a leak on the minimap corresponding to the tile at the end of the pipeline (last conduit).<br/>2. Normal overworld shown. <br/>3. There are two leaks, one at the end of the pipeline (liquid still draining), one where the pipeline was broken.<br/>4. Both leaks are shown on the minimap in light blue color.<br/>5. The pixel at the end of the pipeline (from before the break) loses the light blue color (remainining liquid from conduit has drained, no longer leaking).<br/>6. Puddle at the end of the disconnected pipeline is reducing (or gone). | [![longPipelineBreak](https://img.youtube.com/vi/lIkaxv7ZYqM/0.jpg)](https://www.youtube.com/watch?v=lIkaxv7ZYqM) |

Map changing resets known leaks (doesn't keep active leaks from other map):  
Non-campaign here is just for simplicity, exiting a map in general triggers a logic reset. It's just so the instructions better match exact steps.
Picking another game with the same map is for the same purpose, since it will result in spawning in a similar area, which is more helpful for ensuring that the leaks from the last map aren't still active.

| Pre-Condition                                                                                                      | Test case id      | Description                                                                                                                                                                                                                               | Expected Result                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Video                                                                                           |
|--------------------------------------------------------------------------------------------------------------------|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|
| - Within a map for a custom game (non-campaign) with player-team active leaks<br/>- The user has other saved games |  Leaving a map and joining another | 1. Press "Esc".<br/>2. Press "Save & Quit".<br/>3. Press "ok". <br/>4. Press "Play" in the main menu.<br/>5. Press "Load Game".<br/>6. Click on another game in the same map (different from the game at the start).<br/>7. Open minimap. | 1. Pause menu is displayed. Including "Save & Quit" button.<br/>2. Quit confirmation menu is displayed.<br/>3. Main menu displayed.<br/>4. Play options displayed "Campaign, Join Game, Custom Game, Load Game".<br/>5. Existing saves for custom games are displayed.<br/>6. The map is joined. Map leaks are loaded.<br/>7. None of the leaks from the old map are visible in light blue (unless there are active player-team leaks in the same tiles). All active leaks are shown in light-blue color. | [![changeMap](https://img.youtube.com/vi/wZOfWQnyZTI/0.jpg)](https://www.youtube.com/watch?v=wZOfWQnyZTI) |

### Review
*(Please add your test specification review here)*