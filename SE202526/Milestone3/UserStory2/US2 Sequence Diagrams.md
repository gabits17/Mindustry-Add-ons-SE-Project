# Sequence Diagrams
## Enter map
![seqEnterMap.svg](seqEnterMap.svg)

Simple sequence diagram focusing on the transition from outside a map, to within one.
Follows a simple direction of the **Player** pressing a button indicated by the ``MapPlayDialog`` instance,
which initiates the ``playMap()`` method in ``Control`` under a specific map and ruleset.
To clear existing ``Logic`` from previous played maps, ``reset()`` is called.  
The functionality in reset given focus for the use case is related to clearing the leaks of previous maps.

## Leave map
![seqLeaveMap.svg](seqLeaveMap.svg)

Similar to Enter **map**, but it's a sequence diagram focusing on the transition from being within a map, to outside of it.
Follows a simple direction of the **Player** pressing a quit button indicated by the ``PausedDialog`` instance.
This triggers a method ``showQuitConfirm()`` that during its execution calls to exit the map and save progress (saving goes beyond the use case scope)
with ``runExitSve()``. During this exit, the logic is reset, which leads to clearing leaks.

## Clear leaks
![seqClearLeaks.svg](seqClearLeaks.svg)

When the ``Logic`` reset is triggered in the previous use cases, this involves firing a ``ResetEvent``.
I didn't consider this event as an entity since it carries no data and is used to trigger a list of functionalities registered
to this event type.
Additionally, while ``get(type)`` is called in Events, it's called on an interface ``Cons`` that contains the function/method to be called,
to handle the event, which in this case is **clear()** belonging to ``Leaks``.
This only happens if ``Leaks`` has been instantiated, that is, if the singleton's ``getInstance()`` method has been called at any point since the
game started running (if a map has been ).

## Place block
![seqPlaceBlock.svg](seqPlaceBlock.svg)

A Call is created when a user places a block, and the method ``beginPlace`` is called, which is forwarded to the control class
Build using a static method of the same name and parameters.
The corresponding tile is obtained from the ``World`` instance, and the block the tile represents (previous) is retrieved from the tile.
A block of the same size as the one desired (result) is obtained from ``ConstructBlock``.
The current building in that tile is also retrieved from the tile (previous).
If the dimensions of the block being placed (result) do not correspond to the block already there (previous), air is placed, otherwise, the desired block is placed.
This case wasn't considered in the use case as it assumes the block can be placed, but I chose to include it here since it affects passed parameters.

## Break block
![seqBreakBlock.svg](seqBreakBlock.svg)

A Call is created when a user breaks a block, and the method ``beginBreak`` is called, which is forwarded to the control class
Build using a static method of the same name and parameters.
The corresponding tile is obtained from the ``World`` instance, and the block the tile represents (previous) is retrieved from the tile.
The building is also retrieved from the tile, so that ``setDeconstruct`` can perform the block removal, but, more importantly for the use case,
leading to checking for leaks in the surrounding blocks.
At the end, there is an explicit attempt to remove the tile from the ``Leaks`` instance, as the normal route of calling ``checkLeaks`` on update
doesn't work when the tile is being removed.

## Request tile update
![seqRequestTileUpdate.svg](seqRequestTileUpdate.svg)

When a tile is to be updated in the scenario where a block is broken or placed, it goes through the ``Pathfinder`` which calls ``updateTile`` in ``ControlPathFinder``
(both instances are static attributes in a class ``Vars``). From here, the tiles of the block (multiple if the block occupies multiple tiles) are enqueued for update.
(``updateSingleTile()`` is applied per tile in the linked tiles as part of the ``getLinkedTiles()`` method).

Later on, these tiles corresponding to buildings that are activated are updated, some being tiles that can leak, in which case the
attempt to check for leaks will occur (in ``UpdateLeakableBlock``).

## Update building
![seqUpdateBuilding.svg](seqUpdateBuilding.svg)

At a specific time interval, the ``update()`` method in the ``ApplicationCore`` that ``DesktopLauncher`` extends is called.
This involves updating each listener in the modules (eg: renderers), but in this diagram we're focusing on the use case, which relates to the ``Logic`` update.
Updating the logic includes updating ``Groups``, which stores all entities. These entities implement an interface ``Entityc``.
However, in this use case, we're only interested in the ``Building`` type entities. These are updated by calling ``update()``, which itself calls ``updateTile(9``, where we make
an exception for Buildings that are instances of Conduit, which are leakable buildings. By default ``updateTile()`` will depend on the subClass functionality, since it is empty in base.
In the case of Buildings of leakable block types, it interacts with the added functionality of **Leak Identification** (this reflects the extension point).

## Update leakable block tile
