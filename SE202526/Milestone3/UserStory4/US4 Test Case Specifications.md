# Tests

## Small note
The system we built strictly adds on top of the pre-existing Schematics, building and selecting systems of the game, and therefore we felt it would be unnecessary and repetitive to be testing those aspects of the entire copying and pasting functions.

Instead, we preferred testing the code that we made. The tests bellow are implemented in **tests/src/test/java/ApplicationTests.java**.

For this document it should be referred the explanation at the beginning of our [US4 Use Case Textual Description](US2%20Use%20Case%20Textual%20Description.md) report.

In which it is clarified the difference between "schematic" and "Schematic" for the context of explaining our work. In summary the game already implements "Schematics" in a different system, and every time we talk about "schematics", it means a way to keep a selection of tiles.

Just for clarification, in the game there is an element called "Schematics" that are player made constructions that are kept in files.

## copiesValidSelectionTest()
This test is designed to assert if, given valid selection is selected and the code to copy it is run, that selection it appears as a schematic in the history to which it was saved.

## copiesEmptySelectionTest()
This test is design to assert that if a valid selection is not copied then no selection can be copied.

## nothingToPasteTest()
This test checks if a newly created history, or one that nothing has been copied to, is empty.

## getNextTest()
This test verifies the correct functioning of the `getNext()` method of the `CopyHistClass`.

It does this by copying three valid selections to a `CopyHistClass` object, and then cycling through the copied schematics and verifying the order of which they appear.

## getPreviousTest()
Works very similarly to the previous **getNextTest()**. Copies schematics and checks if they are in the correct order, except now they should be in the opposite order.

## pasteLoopTest()
Once again this is a similar test to the above, except this one is designed to test the ability to loop around the collection of schematics.

For example if the current schematic is the last one, and **getNext()** is run, then the first schematic in the collection is the correct output.

In this test that scenario i verified for **getNext()** as well as for **getPrevious()**.

## copyLimitsTest()
The purpose of this test is to make sure that after the limit amount of schematics has been copied, the correct ones are kept.

This is achieved copying a schematic that should not be kept, and then copying 5 more, the maximum. If the first schematic is still in any point of the collection then the test fails.
