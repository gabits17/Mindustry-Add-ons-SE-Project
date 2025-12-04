# Sequence Diagrams

### Notes

    In the following Sequence diagrams, the CopyHist Interface is ommited.
## Choose Copy
![Choose Copy.svg](assets/Choose%20Copy.svg)

This sequence diagram demonstrates the inner logic behind the ``Copy`` functionality a player can do. It is important to note that the two initial ``Paste`` reference are not methods, but calls. This decision was made in order to not over-complicate this diagram with unnecessary logic.

When pressing the ``Ctrl``+ ``V``, the player notifies the ``Input`` class and, per consequence, the ``DesktopInput``. In ``DesktopInput``, it's checked if the History of copies is empty, via ``isEmpty()``. If false, the player can choose to copy the current visible Copy, by calling ``getCurrent()``, which is developed in the ``CopyHistClass``, or to navigate between the saved copies, by scrolling, which affects the value of axisTap, a behaviour described later on in this report. If true, ``DesktopInput`` communicates with the ``UI``, in order to display the message ``Nothing to paste!``.


## Copy Schematic
![Copy Schematic.svg](assets/Copy%20Schematic.svg)

As justified before, the ``CopySchematic`` messages are not method, but calls.

This sequence diagram showcases the consequence of a player pressing the ``Ctrl`` + ``C`` keys, notifying the ``Input`` class and, per consequence, the ``DesktopInput``, similar to the previous described behaviour. After this call, it's checked if the ``selectPlans`` aren't empty and the ``lastSchematic`` is not null, which I decided to omit in order to not deviate from our functionality. In case this isn't the case, the ``Nothing to copy!`` is displayed via ``UI``. If this is checked, the ``copy`` method is called, where, if the history isn't empty, it's ordered in a queue-like order, followed by the addition of the copy to the history, on condition that it doesn't exist already. When finished, ``DesktopInput`` calls the ``UI``, as described in the previous Sequence diagram, displaying ``Copied!``.

## Get next
![Get next.svg](assets/Get%20next.svg)

This simple Sequence diagram is the behaviour behind the reference ``getNext()`` present in the Choose Copy diagram. It returns the next Copy after the last kept one, if there's one.

## Get previous
![GetPrevious.svg](assets/GetPrevious.svg)

This simple Sequence diagram is the behaviour behind the reference ``getNext()`` present in the Choose Copy diagram. It returns the previous Copy after the last kept one, if there's one.

## Paste Copy
![PasteCopy.svg](assets/PasteCopy.svg)

The same behaviour present in the two first sequence diagrams also occurs in Paste Copy.

This sequence diagram deals with the logic behind placing a block onto the world. This was already a functionality, so it isn't explored in depth in the diagram. 
## Select tiles
![Select Tiles.svg](assets/Select%20Tiles.svg)

Similar to the previous Sequence diagram, Select tiles leads with an already existent functionality, so it's simplified in order to not deviate from the main goal, reason why```create``` is just a reference.

This sequence diagram deals with the tiles selection to later copy, or save in the ``Schematics``.
 