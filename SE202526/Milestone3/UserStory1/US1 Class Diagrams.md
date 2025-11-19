# Main Functionalities
**The description of both diagrams below will be given here as the only aspect that differs between both is the relevant functions, shown in `Commander`**

The undo and redo functionalities have only been implemented in the desktop version of the game; thus, only `DesktopInput` is relevant in the diagram. The implementation is based on the command pattern learned in class.  It utilizes the `Commander` class, which serves as both an Invoker and a command history. 
The `Group` abstract class is also rather important as it is used to show an error message to the user.

Some key points to take going into the next descriptions are:
- `DesktopInput` has one and only one `Commander`
- `Commander` belongs to a composite association with `DesktopInput`
- `Commander` has two stacks one for done `Command`'s (`doneCommands`), another for undone `Command`'s (`undoneCommands`)
##  Undo
![[Assets/UndoClass.drawio.svg]]
##  Redo
![[Assets/RedoClass.drawio.svg]]
# Actions
## Paste Schematic
**The description of the two following diagrams will only be given here, as pasting schematics and building buildings use the same implementation.**

`BuildPlansCommand`implements the `Command` interface. This is the command created by `DesktopInput` when a player either needs to build a building or paste a schematic. 
This command uses methods present in the superclass of `DesktopInput`, `InputHandler`.
And employs the global variable class `Vars` to access important methods in the `Player`.
- Execute/Redo $\rightarrow$ `flushPlans`
- Undo $\rightarrow$ `tryBreakBlock`, `removeBuild`
The command keeps a list of `BuildPlan`'s, a list that will be used by `flushPlans` to create a plan in the world for the player to build.
![[Assets/PasteSchematic_BuildBuildingClass.drawio.svg]]
## Build Building
![[Assets/PasteSchematic_BuildBuildingClass.drawio.svg]]
## Remove Selection
`RemoveSelectionCommand`implements the `Command` interface. This is the command created by `DesktopInput` when a player requests to remove a selection. 
This command uses methods present in the superclass of `DesktopInput`, `InputHandler`.
- Execute/Redo $\rightarrow$ `removeSelection`
- Undo $\rightarrow$ `flushPlans`, ``
The command keeps a list of `BuildPlan`'s (`removed`), a list that will be used in the case that the player decides to undo the remove.
![[Assets/RemoveSelectionClass.drawio.svg]]
## Rotate Building
`BlockRotateCommand`implements the `Command` interface. This is the command created by `DesktopInput` when a player either needs to build a building or paste a schematic. 
This command uses methods present in the `Call` class.
And employs the global variable class `Vars` to access the `Player`.
- Execute/Redo $\rightarrow$ `rotateBlock`
- Undo $\rightarrow$ `rotateBlock`
The command keeps the `Building` that was bellow the cursor upon its creation.
![[Assets/RotateBuildingClass.drawio.svg]]
# Map
## Enter Map
#TODO
## Leave Map
#TODO
# Includes
## Add Done Action
#TODO ?
## Reset Action Commander
#TODO ?