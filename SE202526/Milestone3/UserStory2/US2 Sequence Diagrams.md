# Sequence Diagrams
### Notation use
Underlined classifiers: specific classifier instances.  
Specific as they're usually the instance that can be statically accessed from the singleton ``Vars``.
Or, in the case of ``ApplicationListener`` in ``ApplicationCore``, the instances are processed once during setup.

Not underlined: classifier instance roles. Can represent not just a specific instance, but a role performed by instances in that logic.

Classifiers without ``:`` : Statically accessed. Eg: ``Events``, a mediator class.

## Enter map
![seqEnterMap.svg](assets/seqEnterMap.svg)

Simple sequence diagram focusing on the transition from outside a map, to within one.
Follows a simple direction of the **Player** pressing a button indicated by the ``MapPlayDialog`` instance,
which initiates the ``playMap()`` method in ``Control`` under a specific map and ruleset.
To clear existing ``Logic`` from previous played maps, ``reset()`` is called.  
The functionality in reset given focus for the use case is related to clearing the leaks of previous maps.

## Leave map
![seqLeaveMap.svg](assets/seqLeaveMap.svg)

Similar to Enter **map**, but it's a sequence diagram focusing on the transition from being within a map, to outside of it.
Follows a simple direction of the **Player** pressing a quit button indicated by the ``PausedDialog`` instance.
This triggers a method ``showQuitConfirm()`` that during its execution calls to exit the map and save progress (saving goes beyond the use case scope)
with ``runExitSve()``. During this exit, the logic is reset, which leads to clearing leaks.

## Clear leaks
![seqClearLeaks.svg](assets/seqClearLeaks.svg)

When the ``Logic`` reset is triggered in the previous use cases, this involves firing a ``ResetEvent``.
I didn't consider this event as an entity since it carries no data and is used to trigger a list of functionalities registered
to this event type.
Additionally, while ``get(type)`` is called within a method in ``Events``, it's called on an interface ``Cons`` that contains the function/method to be called,
to handle the event, which in this case is **clear()** belonging to ``Leaks``. This ``Cons`` isn't present in the class diagram because it's an interface without a concrete implementation
(mostly lambda expressions).
This only happens if ``Leaks`` has been instantiated, that is, if the singleton's ``getInstance()`` method has been called at any point since the
game started running (if a map has been ).
In such a case the ``clear()`` method wipes the stored leaks (I didn't include a sub-activity vertical rectangle for clear as it is the sole activity being performed in ``Leaks``
for this functionality).

Note: ``Events`` is a mediator class that allows classes to create custom logic as a response to an event corresponding to a specific kind of occurrence.

## Place block
![seqPlaceBlock.svg](assets/seqPlaceBlock.svg)

A ``Call`` occurs when a user places a block, and the method ``beginPlace`` is called, which is forwarded to the control class
``Build`` using a static method of the same name and parameters.
The corresponding tile is obtained from the ``World`` instance, and the block the tile represents (previous) is retrieved from the tile.
A block of the same size as the one desired (result) is obtained from ``ConstructBlock``.
The current building in that tile is also retrieved from the tile (previous).
If the dimensions of the block being placed (result) do not correspond to the block already there (previous), air is placed, otherwise, the desired block is placed.
This case wasn't considered in the use case as it assumes the block can be placed, but I chose to include it here since it affects passed parameters.

## Break block
![seqBreakBlock.svg](assets/seqBreakBlock.svg)

A ``Call`` occurs when a user breaks a block, and the method ``beginBreak`` is called, which is forwarded to the control class
``Build`` using a static method of the same name and parameters.
The corresponding tile is obtained from the ``World`` instance, and the block the tile represents (previous) is retrieved from the tile.
The building is also retrieved from the tile, so that ``setDeconstruct`` can perform the block removal, but, more importantly for the use case,
leading to checking for leaks in the surrounding blocks.
At the end, there is an explicit attempt to remove the tile from the ``Leaks`` instance, as the normal route of calling ``checkLeaks`` on update
doesn't work when the tile is being removed.

## Request tile update
![seqRequestTileUpdate.svg](assets/seqRequestTileUpdate.svg)

When a tile is to be updated in the scenario where a block is broken or placed, it goes through the ``Pathfinder`` which calls ``updateTile`` in ``ControlPathFinder``
(both instances are static attributes in a class ``Vars``). From here, the tiles of the block (multiple if the block occupies multiple tiles) are enqueued for update.
(``updateSingleTile()`` is applied per tile in the linked tiles as part of the ``getLinkedTiles()`` method).

Later on (omission of correspondence between many intermediate classes), these tiles corresponding to active buildings that are updated, the tiles of those buildings that can leak, result
in the attempt to check for leaks that may have occurred/stopped (in ``UpdateLeakableBlock``).

## Update building
![seqUpdateBuilding.svg](assets/seqUpdateBuilding.svg)

At a specific time interval, the ``update()`` method in the ``ApplicationCore`` that ``DesktopLauncher`` extends is called.
This involves updating each listener in the modules (eg: renderers), but in this diagram we're focusing on the use case, which relates to the ``Logic`` update.
Updating the logic includes updating ``Groups``, which stores all entities. These entities implement an interface ``Entityc``.
However, in this use case, we're only interested in the ``Building`` type entities. These are updated by calling ``update()``, which itself calls ``updateTile()``, where we make
an exception for ``Building`` that are instances of ``ConduitBuild``, which are leakable buildings. By default ``updateTile()`` will depend on the subClass functionality, since it is empty in base.
In the case of ``Building`` of leakable block types, it interacts with the added functionality of **Leak Identification** (this reflects the extension point).

## Update leakable block tile
![seqUpdateLeakableBlockTile.svg](assets/seqUpdateLeakableBlockTile.svg)

This is a sequence diagram reusable component, which corresponds to a behavior fragment in the use cases.
It starts when ``updateTile()`` is called on the ``ConduitBuild`` in particular, since it is the type of ``Building`` that can create leaks.
To identify a leak in the tile ``checkLeak()`` is called on the singleton instance.

It's first relevant to know whether the ``ConduitBuild`` can actually leak, since the ``ArmoredConduit`` is a type of pipeline that extends ``Conduit`` which does not leak. The **opt** "buildingCanLeak" abstracts
away the process of checking if the block type indicated by the building is one that can leak resources.
It's then relevant to know whether the tile is in the ``Player`` team, as the leaks displayed are only those in buildings belonging to the player team.
With this verified, the tile of the building is obtained. to later check for leaks (``Leaks`` stores the tiles that are identified as leaks).
If the building has liquid, then a leak might have started (or stopped, if the leak was plugged, the building starts to fill with liquid).
Otherwise, we're sure there's no leak, so just in case, there is an attempt to remove the tile from registered leaks, and an update is sent to the minimap.

In the former case, the tile attached to the current (depends on rotation) is necessary to know if the tile is leaking, so it is retrieved using the ``nearby()`` method on ``tile``.
This ``next`` tile is said to be leaking if it isn't solid and can't carry liquid: because being solid plugs the leak, and carrying liquid makes the leak that next building's problem, not the current.

With this, we can check for a transition:
- From not leaking to leaking -> register leak via ``addLeak(tile)``
- From leaking to not leaking -> attempt to remove leak (if not registered as leak does nothing) via ``removeLeak(title)``

In either of those cases, the minimap needs an update in that tile -> ``updatePixel(tile)``

## Update leak display
![seqUpdateLeakDisplay.svg](assets/seqUpdateLeakDisplay.svg)

This sequence diagrams covers the update of display elements related to leaks (minimap and local leaks circle overlays).  
Similar to ``Logic`` updates, ``Renderer`` is one of the ``ApplicationListener`` modules updated at a regular interval.
The two renderers within this instance we will be covering are the ``MinimapRenderer`` and the ``OverlayRenderer`` with respect
to their functionality regarding ``Leaks``.  
The loop checks for the listener that instantiates ``Renderer`` since it´s the one covered in this use case.
From there, the update is passed into the ``MinimapRenderer`` instance in ``Renderer``, that handles each pending update identified by a position ``pos``.
This position is converted to a tile according to the ``World`` instance, and then the color is obtained to update the minimap.
For the sake of this use case, the diagram checks that the block is a ``Conduit`` (type of leaking block) so that the modified implementation of ``minimapColor()`` can be explained
(other blocks have their own method override, and it wouldn't make sense to go through unrelated cases here). This modification simply checks if the ``tile`` is a known leaking tile in ``Leaks``:
- if leaking -> return designated color used for leaks **LEAK_COLOR** (using the ``getLeakColorValue()`` method).
- not leaking -> return superclass implementation result color.

The color **col** is just a variable to facilitate explanation in the diagram. It is then used to update the ``pixelMap`` in the ``MinimapRenderer``,
which is omitted as updating the color buffer seems to go beyond the use case scope of explanation, and it might be confusing to go into detail in the data structures.

The next section handles the update of the ``OverlayRenderer`` instance.  
The relevant draw call occurs in the ``drawBottom()`` method in ``OverlayRenderer`` and then ``DesktopInput``, where the ``drawLocalLeaks()`` method for the ``Leaks`` singleton instance is called.
This consists of getting the player location **(x, y)**, and iterating over the leaking tiles within a ``MIN_RANGE`` of this position.
The loop condition uses ``leakTilesInRange`` to refer to these tiles, since the actual code uses a lambda expression parameter for a method called on a data structure of ``Leaks``, which I feel are auxiliary
and not entities themselves. For the sake of simplicity, this abstraction was used to make representation in the sequence diagram more readable.  
For each of these local leaking tiles, they are only considered if within a ``MIN_RADIUS`` of the player. If so, a dashed circle is drawn around the tile in the color ``LEAK_COLOR``.
It's worth noting that the ``dashCircle()`` method is an abstraction of the code's use of a utility class that handles drawing to the display (so it's neither an entity, control, nor boundary).
It's also worth noting that the ``Tile`` entities aren't underlined because it represents the role of a leaking tile, and not a particular instance of it.