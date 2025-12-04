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
