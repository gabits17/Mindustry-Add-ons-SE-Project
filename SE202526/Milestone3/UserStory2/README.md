# User story 2
Leak identification via minimap and proximity
## Author(s)
Diogo Antunes 67763
## Reviewer(s)
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
*(Please add your user story review here)*
## Note about content of the following work

This user story extends existing user interaction with the game by showing leaks, so aside from time, there's no primary actor directly interacting with the created system.

For example, the act of breaking/placing of a block stays the same. However, at some point in the logic that handles the update for this scenario, if the block is a leaking liquid-carrying block, or is adjacent to one such block,
the new functionality is triggered.

Many of the methods that are passed by to reach this new functionality take care of much more than what this user story covers.
With this in mind, I will omit the sections of logic of such methods unrelated to this particular topic, except for key steps that connect to the next relevant method.

## Use case diagram
(*Please add the use case diagram here.*)
## Use case textual description
(*Please add the use case textual description here.*)
### Review
*(Please add your use case review here)*
## Implementation documentation
(*Please add the class diagram(s) illustrating your code evolution, along with a technical description of the changes made by your team. The description may include code snippets if adequate.*)
### Implementation summary
(*Summary description of the implementation.*)
#### Review
*(Please add your implementation summary review here)*
### Class diagrams
(*Class diagrams and their discussion in natural language.*)
#### Update leak display
![img.png](img.png)
This class diagram is related to the frequent calling of the renderer update method that updates the minimap, ensuring pending updates are drawn it.
In the user story scenario, these updates concern tiles labelled as leaking in the ``Leaks`` singleton class.
It also covers drawing the rendering of the overlay that shows leaks close to the player.

The ``DesktopLauncher`` is executed to launch the game and start running it. It is a specialization of ``ClientLauncher``, which during setup,
adds an instance of ``Renderer`` (an implementation of ``ApplicationListener``), among other modules, to the ``ApplicationCore`` super class.

As such:
- The game runs with an instance of ``Renderer`` (which contains a ``MinimapRenderer`` attribute), and frequently calls its ``update()`` method.
- If the game is not in a menu state, it needs to update the minimap, which it does by calling the ``update()`` method in the ``MinimapRenderer`` instance **minimap**.

#### Minimap Update
- ``update()`` in ``MinimapRenderer`` goes through pending updates for world positions that were sent for update
  (the global Vars can translate these into tiles, and then blocks, from which a color can be fetched).
- Adding to pending updates is described in another use case, but as shown in the diagram involves the ``updateTile`` method in ``ConduitBuild`` I modified to allow this.

I won't go further into detail of the many intermediate classes that the renderer goes through to get the Block and color avoid confusion.

What I'm omitting in the diagram to condense the logic is the following: 

- The ``Vars`` class has an attribute of class ``World`` that stores the tiles of the active map and can get a tile given an ``int`` position value using a method ``Tile(int pos)``.
 
**Relevant case for minimap**
- The relevant case here is when the tile corresponds to a ``Block`` specialization ``Conduit``. Getting the color for display is done using ``minimapColor(Tile tile)`` I overrode in ``Conduit``
to return a light blue color ``Pal.leakingWarn`` if the tile is leaking (``super`` otherwise). The ``rgba()`` method is called on this instance of ``Color`` to obtain a value that can stored in the int buffer that represents the colors to put to screen.
- This presence of a leak is quickly checked by using the ``isLeaking(tile Tile)`` method in the singleton ``Leaks``, which checks if it has the tile stored.
- Note: ``Pal`` is the palette class that stores used colors of the ``Color`` class. The pixel map and such buffers that the application uses after getting 
the color from a block to place each color were also omitted to avoid confusion.

#### Showing local leaks (going back to ``update`` in ``Renderer``)
- The ``update()`` method in ``Renderer`` also calls the own class's ``draw()`` method if the ``boolean pixelate`` from the ``Renderer`` static instance in ``Vars`` is turned off (decided in settings).
- This method performs ``drawBottom()`` in ``OverlayRenderer``, which calls a method of the same name in ``DesktopInput``(assuming playing in Desktop).
- in ``DesktopInput``, the **new functionality** is finally called on the instance of the ``Leaks`` singleton: ``drawLocalLeaks``.
- This method uses the player's x and y coordinates from the static ``Player`` instance in ``Vars`` to find all leaks within a 10 block radius of the player
and show a blue-dashed ring as below (drawn using the static ``dashCircle(float x, float y, float rad, Color color)`` method in the ``Drawf`` class):
![img_1.png](img_1.png)

### Review
*(Please add your class diagram review here)*
### Sequence diagrams
(*Sequence diagrams and their discussion in natural language.*)
#### Review
*(Please add your sequence diagram review here)*
## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*
