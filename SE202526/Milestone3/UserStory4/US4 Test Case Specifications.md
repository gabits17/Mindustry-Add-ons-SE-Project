# Tests

## Small note
The system we built strictly adds on top of the pre-existing Schematics, building and selecting systems of the game, and therefore we felt it would be unnecessary and repetitive to be testing those aspects of the entire copying and pasting functions.

Instead, we preferred testing the code that we made. The tests bellow are implemented in **tests/src/test/java/ApplicationTests.java**.

For this document it should be referred the explanation at the beginning of our [US4 Use Case Textual Description](US4%20Use%20Case%20Textual%20Description.md) report.

In which it is clarified the difference between "schematic" and "Schematic" for the context of explaining our work. In summary the game already implements "Schematics" in a different system, and every time we talk about "schematics", it means a way to keep a selection of tiles.

Just for clarification, in the game there is an element called "Schematics" that are player made constructions that are kept in files.

---
## Scenario Based Testing

---

## copiesValidSelectionTest()
This test is designed to assert if, given valid selection is selected and the code to copy it is run, that selection it appears as a schematic in the history to which it was saved.

Link: https://youtu.be/u9RMGJ_Ahd8

| Pre Condition | Test Case Id           | Steps                                                                         | Expected Result                                                                                                                                                |
| ------------- |------------------------|-------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| none          | Copies Valid Selection | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav"). | A new game is launched in sandbox mode.                                                                                                                        |
|               |                        | 2. Build any building if one isn't built yet.                                 | An example building appears.                                                                                                                                   |
|               |                        | 3. Pressing the selection key, select any building.                           | The selection appears as a building plan.                                                                                                                      |
|               |                        | 4. Press the copying bind (Ctrl + c).                                         | The previously selected buildings remain as a building plan, and a fading text appears on screen with the message "Copied!".                                   |
|               |                        | 5. Press the right mouse button.                                              | The previously selected buildings dissapear from the building plan.                                                                                            |
|               |                        | 6. Press the copying bind (Ctrl + v).                                         | The previously selected buildings appear as a building plan, and a fading text appears on screen with the message "Scroll to access other copied schematics!". |


---

## copiesEmptySelectionTest()
This test is design to assert that if a valid selection is not copied then no selection can be copied.

Link: https://youtu.be/_cSPmlDqngc

| Pre Condition | Test Case Id           | Steps                                                                         | Expected Result                                                                             |
| ------------- |------------------------|-------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| none          | Copies Empty Selection | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav"). | A new game is launched in sandbox mode.                                                     |
|               |                        | 2. Pressing the selection key, select an empty tile.                          | Nothing happens.                                                                            |
|               |                        | 3. Press the copying bind (Ctrl + c).                                         | A fading text appears on screen with the message "Nothing to copy!".                        |
|               |                        | 4. Press the copying bind (Ctrl + v).                                         | A fading text appears on screen with the message "Nothing to Paste!", and nothing is built. |

---

## nothingToPasteTest()
This test checks if a newly created history, or one that nothing has been copied to, is empty.

Link: https://youtu.be/ulmCzKz4up4

| Pre Condition | Test Case Id     | Steps                                                                         | Expected Result                                                                             |
| ------------- |------------------|-------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| none          | Nothing To Paste | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav"). | A new game is launched in sandbox mode.                                                     |
|               |                  | 2. Press the copying bind (Ctrl + v).                                         | A fading text appears on screen with the message "Nothing to Paste!", and nothing is built. |

---

## getNextTest()
This test verifies the correct functioning of the `getNext()` method of the `CopyHistClass`.
It does this by copying three valid selections to a `CopyHistClass` object, and then cycling through the copied schematics and verifying the order of which they appear.

Link: https://youtu.be/b95tjMnFOsg

| Pre Condition | Test Case Id | Steps                                                                                             | Expected Result                                                                                                                                            |
| ------------- |-----------|---------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| none          | Get Next  | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav").                     | A new game is launched in sandbox mode.                                                                                                                    |
|               |           | 2. Build a distinct building different from any building built yet if one does not already exist. | An example building appears.                                                                                                                               |
|               |           | 3. Pressing the selection key and select the previously built building.                           | The selection appears as a building plan.                                                                                                                  |
|               |           | 4. Press the copying bind (Ctrl + c).                                                             | The previously selected building remain as a building plan, and a fading text appears on screen with the message "Copied!".                                |
|               |           | 5. Press the right mouse button.                                                                  | The previously selected buildings dissapear from the building plan.                                                                                        |
|               |           | 6. Repeat the steps 2., 3., 4. and 5. twice again.                                                | The output of the respective steps should be observed.                                                                                                     |
|               |           | 7. Press the copying bind (Ctrl + v).                                                             | The last selected building appears as a building plan, and a fading text appears on screen with the message "Scroll to access other copied schematics!".   |
|               |           | 8. Press the bind to scroll to the next copy (Ctrl + v + scroll wheel up).                        | The second selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!". |

---

## getPreviousTest()
Works very similarly to the previous **getNextTest()**. Copies schematics and checks if they are in the correct order, except now they should be in the opposite order.

Link: https://youtu.be/vHOyyKgHLuI

| Pre Condition | Test Case Id | Steps                                                                            | Expected Result                                                                                                                                           |
| ------------- |-----------|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| none          | Get Next  | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav").    | A new game is launched in sandbox mode.                                                                                                                   |
|               |           | 2. Build a distinct building different from any building built yet if one does not already exist.              | An example building appears.                                                                                                                              |
|               |           | 3. Pressing the selection key and select the previously built building.          | The selection appears as a building plan.                                                                                                                 |
|               |           | 4. Press the copying bind (Ctrl + c).                                            | The previously selected building remain as a building plan, and a fading text appears on screen with the message "Copied!".                               |
|               |           | 5. Press the right mouse button.                                                 | The previously selected buildings dissapear from the building plan.                                                                                       |
|               |           | 6. Repeat the steps 2., 3., 4. and 5. twice again.                               | The output of the respective steps should be observed.                                                                                                    |
|               |           | 7. Press the copying bind (Ctrl + v).                                            | The last selected building appears as a building plan, and a fading text appears on screen with the message "Scroll to access other copied schematics!".  |
|               |           | 8. Press the bind to scroll to the previous copy (Ctrl + v + scroll wheel down). | The first selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!". |

---

## pasteLoopTest()
Once again this is a similar test to the above, except this one is designed to test the ability to loop around the collection of schematics.

For example if the current schematic is the last one, and **getNext()** is run, then the first schematic in the collection is the correct output.

In this test that scenario i verified for **getNext()** as well as for **getPrevious()**.

Link: https://youtu.be/zp-bTWbrMfI

| Pre Condition | Test Case Id | Steps                                                                             | Expected Result                                                                                                                                            |
| ------------- |-----------|-----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| none          | Get Next  | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav").     | A new game is launched in sandbox mode.                                                                                                                    |
|               |           | 2. Build a distinct building different from any building built yet if one does not already exist.               | An example building appears.                                                                                                                               |
|               |           | 3. Pressing the selection key and select the previously built building.           | The selection appears as a building plan.                                                                                                                  |
|               |           | 4. Press the copying bind (Ctrl + c).                                             | The previously selected building remain as a building plan, and a fading text appears on screen with the message "Copied!".                                |
|               |           | 5. Press the right mouse button.                                                  | The previously selected buildings dissapear from the building plan.                                                                                        |
|               |           | 6. Repeat the steps 2., 3., 4. and 5. twice again.                                | The output of the respective steps should be observed.                                                                                                     |
|               |           | 7. Press the copying bind (Ctrl + v).                                             | The last selected building appears as a building plan, and a fading text appears on screen with the message "Scroll to access other copied schematics!".   |
|               |           | 8. Press the bind to scroll to the next copy (Ctrl + v + scroll wheel up).        | The second selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!". |
|               |           | 9. Press the bind to scroll to the next copy (Ctrl + v + scroll wheel up).        | The first selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!".  |
|               |           | 10. Press the bind to scroll to the next copy (Ctrl + v + scroll wheel up).       | The last selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!".   |
|               |           | 11. Press the bind to scroll to the previous copy (Ctrl + v + scroll wheel down). | The first selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!". |

---

## copyLimitsTest()
The purpose of this test is to make sure that after the limit amount of schematics has been copied, the correct ones are kept.

This is achieved copying a schematic that should not be kept, and then copying 5 more, the maximum. If the first schematic is still in any point of the collection then the test fails.

Link: https://youtu.be/SDYVKbUl8As

| Pre Condition | Test Case Id | Steps                                                                            | Expected Result                                                                                                                                            |
| ------------- |-----------|----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| none          | Get Next  | 1. Start a custom game in sandbox mode (preferably using "US4_Testing.msav").    | A new game is launched in sandbox mode.                                                                                                                    |
|               |           | 2. Build a distinct building different from any building built yet if one does not already exist.              | An example building appears.                                                                                                                               |
|               |           | 3. Pressing the selection key and select the previously built building.          | The selection appears as a building plan.                                                                                                                  |
|               |           | 4. Press the copying bind (Ctrl + c).                                            | The previously selected building remain as a building plan, and a fading text appears on screen with the message "Copied!".                                |
|               |           | 5. Press the right mouse button.                                                 | The previously selected buildings dissapear from the building plan.                                                                                        |
|               |           | 6. Repeat the steps 2., 3., 4. and 5. five more times.                           | The output of the respective steps should be observed.                                                                                                     |
|               |           | 7. Press the copying bind (Ctrl + v).                                            | The last selected building appears as a building plan, and a fading text appears on screen with the message "Scroll to access other copied schematics!".   |
|               |           | 8. Press the bind to scroll to the previous copy (Ctrl + v + scroll wheel down). | The second selected building appears as a building plan, and a fading text remains on screen with the message "Scroll to access other copied schematics!". |
