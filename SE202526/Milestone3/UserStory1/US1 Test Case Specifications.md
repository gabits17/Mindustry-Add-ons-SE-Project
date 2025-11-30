# Tests
All the tests here are in the test class `tests/src/test/java/ApplicationTest.java`.
## undoRedoLineBuildingTest()
This test, tests the `BuildPlansCommand` and its undo function, it starts by executing the command, checking if all the copper walls were built, finally  it undoes the command, and checks if all the walls are now air.
## undoRedoIncompleteBuildingTest() 
The test starts by doing the same as the one above, but it only updates the player 3 times, thus it only builds 3 out of the 5 planned walls, after this it undoes the command, and redoes the command to make sure that:
- Buildings planned to remove after an undo have their removal plan removed
- Plans that were yet to build are planned again
## undoBuildingOverlapTest()
This test was made for two reasons:
1. The Building Command stores what was actually built
2. When undoing make sure that what was actually built is correct
This is done by first placing a conveyor and then placing a line of copper walls thus we would have the following configuration:
$$
\begin{matrix}
Wall \\
Wall \\
Converyor \\
Wall \\
Wall
\end{matrix}
$$
Then we undo the placing of walls, and check if the conveyor was removed, if the conveyor was removed that means that the command did not store correctly what was built.
## undoRedoRemoveSelectionTest()
This test, tests the `RemoveSelectionCommand`, it starts as the two described above, by placing a line of copper walls, and then executes a `RemoveSelectionCommand`,  after the command's execution it will check that it only removed what was in the rectangular region of the command. After the command's execution it'll be undone and a check will be made to ensure that everything was rebuilt properly.
## undoRedoPlannedRemoveSelectionTest()
This test is an "extension" of the on above, but instead of testing that the `RemoveSelectionCommand` correctly, undoes, thus placing, the remove action for already built blocks, it now tests if the `RemoveSelectionCommand` correctly stored the to be built plans (different entity to placed building thus the importance of the test).
## undoRedoRotationTest()
This test, tests the `BlockRotateCommand` and its undo function, it starts by placing a conveyor, and then rotating it, checking its rotation, after this it is undone and the rotation is checked once more.
# Helpers
## initUndoRedo()
The `initUndoRedo` function is an auxiliar function, that acts as a startup for all the undo and redo tests, it creates the `Player`, gives it an `Unit` and changes some state rules to ensure ease of testing.
```java  
initBuilding();  
player = Player.create();  
Unit playerUnit = UnitTypes.poly.create(Team.sharded);  
playerUnit.add();  
player.unit(playerUnit);  

//infinite build range  
state.rules.editor = true;  
state.rules.infiniteResources = true;  
state.rules.buildSpeedMultiplier = 999999f;  
assert player.unit().isValid();
```