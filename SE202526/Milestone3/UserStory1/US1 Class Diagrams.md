# Main Functionalities
**The description of both diagrams below will be given here as the only aspect that differs between both is the relevant functions, shown in `Commander`**

The undo and redo functionalities have only been implemented in the desktop version of the game; thus, only `DesktopInput` is relevant in the diagram. The implementation is based on the command pattern learned in class.  It utilizes the `Commander` class, which serves as both an Invoker and a command history. 
The `Group` abstract class is also rather important as it is used to show an error message to the player if the player tries to undo or redo something, and there is no action to be taken.

Some key points to take going into the next descriptions are:
- `DesktopInput` has one and only one `Commander`
- `Commander` belongs to a composite association with `DesktopInput`
- `Commander` has two stacks, one for done `Command`'s (`doneCommands`), another for undone `Command`'s (`undoneCommands`)
##  Undo
![](Assets/UndoClass.drawio.svg)
##  Redo
![](Assets/RedoClass.drawio.svg)
# Actions
## Paste Schematic
**The description of the two following diagrams will only be given here, as pasting schematics and building buildings use the same implementation.**

`BuildPlansCommand`implements the `Command` interface. This is the command created by `DesktopInput` when a player either needs to build a building or paste a schematic. 
This command uses methods present in the superclass of `DesktopInput`, `InputHandler`.
And employs the global variable class `Vars` to access important methods in the `Player`.
- Execute/Redo $\rightarrow$ `flushPlans` takes in a sequence of `BuildPlan`s and "flushes" them to be built
- Undo $\rightarrow$ `tryBreakBlock` tries to break a block, built or building, in coordinates,x,y, `removeBuild` method in the interface `BuilderRc` used to remove plans to be built 
The command keeps a list of `BuildPlan`'s, a list that will be used by `flushPlans` to create a plan in the world for the player to build.
![](Assets/PasteSchematic_BuildBuildingClass.drawio.svg)
## Build Building
![](Assets/PasteSchematic_BuildBuildingClass.drawio.svg)
## Remove Selection
`RemoveSelectionCommand`implements the `Command` interface. This is the command created by `DesktopInput` when a player requests to remove a selection. 
This command uses methods present in the superclass of `DesktopInput`, `InputHandler`.
- Execute/Redo $\rightarrow$ `removeSelection` method to remove any sort of tile that is removable by the player in a rectangular selection
- Undo $\rightarrow$ `flushPlans` method already explained above in *Paste Schematic*
The implementation of the method `removeSelection` had to be changed to return the sequence of buildings that were removed for 2 reasons:
1. The player must be able to undo something, and its configs may not be reset
2. To undo the command, it is much easier to keep the `removed` sequence that will be talked about below
The command keeps a list of `BuildPlan`'s (`removed`), a list returned by `removeSelection` that stores all the buildings that were removed as `BuildPlan`s, that will be used in the case that the player decides to undo the removal.
![](Assets/RemoveSelectionClass.drawio.svg)
## Rotate Building
`BlockRotateCommand`implements the `Command` interface. This is the command created by `DesktopInput` when a player either needs to build a building or paste a schematic. 
This command uses methods present in the `Call` class.
And employs the global variable class `Vars` to access the `Player`.
- Execute/Redo $\rightarrow$ `rotateBlock`
- Undo $\rightarrow$ `rotateBlock`
The command keeps the `Building` that was below the cursor upon its creation.
![](Assets/RotateBuildingClass.drawio.svg)
# Map
## Enter Map
The `DesktopInput`, when instantiated, class uses the `on` method in `Events`, to make itself an observer of the `ResetEvent`, and associates the following lambda function to its firing:
```java
DesktopInput Class

public DesktopInput() {  
    Events.on(ResetEvent.class, e -> {this.commander.clear(); });  
}
```
Thus, whenever the `reset` method in `Logic` is called, a method that creates a `ResetEvent` and uses the `fire` method in `Events` to trigger it, the lambda function above is called, thus clearing the buffers present in `DesktopInput`'s `Commander`:
```java
Commander Class

public void clear() {  
    doneCommands.clear();  
    undoneCommands.clear();  
}
```
The `Control` and `JoinDialog` classes are shown as they trigger `reset` upon the player loading a map and joining an online game. Note that other classes do indeed trigger this method; some may also trigger it upon the player entering maps under different circumstances. For example, `Control` also possesses the method `playSector`, which triggers this event upon entering a sector (sectors are campaign maps).
![](Assets/EnterMapClass.drawio.svg)
## Leave Map
Continuing the description given above, the only difference this diagram has is that instead of `Control` or `JoinDialog`, the `reset` method is, when leaving a game, called by the `PauseDialog` class.
![](Assets/LeaveMapClass.drawio.svg)
# Includes
No class diagrams are necessary for the included use cases, as the diagrams above already cover the elements they would contain.
