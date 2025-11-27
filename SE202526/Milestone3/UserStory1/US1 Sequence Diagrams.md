# Undo
![](Assets/US1-UndoSeq.svg)
This sequence diagram shows how a player may interact with the program, in order to undo some action it had done previously, some important things to note here are:
- The two occurrences of `undo`, the one invoked by the player and the one that `Input` “invokes” on `DesktopInput`, are not actual methods. They are simplified "calls" added to prevent the diagram from going into unnecessary internal detail.
- In reality, `undo` represents the polling performed by `DesktopInput` each frame to check whether the player is pressing the undo keybinds.
## Sequence Description
Whenever the `Player` presses the keybinds associated with the undo action, the `Input` is notified, and thus `DesktopInput` while polling `Input` understands the `Player` wants to undo the previous action, thus it calls the `undoTop` method in `Commander`,  that will thus undo the last done action, remove the done action from the `Commander` history, and add it as an undone action, if something does not go as planed (nothing to undo), the method throws an exception and `DesktopInput` uses the `showErrorFade` method in `UI` to show an error to the `Player`.
## Add Undone
![](Assets/US1-AddUndoneSeq.svg)
This diagram shows the referred diagram in undo, it shows how the `addUndone`  method in `Commander` works.
# Redo
![](Assets/US1-RedoSeq.svg)This sequence diagram shows how a player may interact with the program, in order to redo some action it had undone previously, the important things noted above also apply here in the case of the redo call".
## Sequence Description
Whenever the `Player` presses the keybinds associated with the redo action, the `Input` is notified, and thus `DesktopInput` while polling `Input` understands the `Player` wants to redo the previous action, thus it calls the `redoTop` method in `Commander`,  that will thus redo the last undone action, remove the undone action from the `Commander` history, and add it as a done action, if something does not go as planed (nothing to redo), the method throws an exception and `DesktopInput` uses the `showErrorFade` method in `UI` to show an error to the `Player`.
# Actions
The following sequence diagrams are related to the actions that a `Player` may perform and are undo able/re doable, to note that the first call made by `Player` and the first call made by `Input` to `DesktopInput`, are just like the ones described above in undo and redo, they are mere simplifications of the keybind polling system used by `DesktopInput`.
All the diagrams have a similar flow, `Player` does an action -> `DesktopInput` detects the action -> `DesktopInput` creates the appropriate `Command`, and calls the `Commader` to execute that `Command`.
## Paste Schematic
![](US1-PasteSchematicSeq.svg)
## Build
![](Assets/US1-BuildBuildingSeq.svg)
## Remove Selection
![](Assets/US1-RemoveSelectionSeq.svg)
## Rotate Building
![](Assets/US1-RotateBuildingSeq.svg)
# Add Command
![](Assets/US1-AddCommandSeq.svg)
This diagram shows the refereed diagram in redo, and the other actions, it shows how the `addCommand`  method in `Commander` works.
# Enter Map
![](Assets/US1-EnterMapSeq.svg)
This sequence diagram, shows how the `Player` may trigger the `Reset Action Commander` sequence diagram upon entering a map, either playing a local map (\[join local\] guard) or when entering a server (\[join server\] guard).
# Leave Map
![](Assets/US1-LeaveMapSeq.svg)
This sequence diagram, shows how the `Player` may trigger the `Reset Action Commander` sequence diagram upon leaving a map.
# Reset Action Commander
![](Assets/US1-ResetActionCommanderSeq.svg)
This sequence diagram illustrates the steps triggered by a call to the `reset` method in `Logic`. When `reset` is invoked, a new `ResetEvent` entity is created and passed to `Events`, which then forwards the event to all of its listener, in this case, `DesktopInput`. Upon receiving the event, `DesktopInput` triggers the `clear` method in `Commander`, which clears the entire history of actions stored in `Commander`.