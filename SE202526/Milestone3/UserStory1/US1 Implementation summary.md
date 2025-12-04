### Implementation summary
The implementation of this user story was primarily carried out within the `input` package, as it is closely tied to player input. The design follows the Command Pattern covered in class, where three basic commands were implemented:
- `BuildPlansCommand` $\rightarrow$ Schematics and Normal Placement
- `RemoveSelectionCommand` $\rightarrow$ Removing something
- `BlockRotateCommand` $\rightarrow$ Rotating a block with the scroll wheel

These commands make use of pre-existing functions in the `DesktopInput` and `InputHandler` classes to achieve their intended behaviour. Together, they allow the player to perform an action, undo a previously executed action, and redo an action that was undone.
Several modifications were required to the existing methods in `DesktopInput` and `InputHandler` to improve the undo/redo experience. For example, the `removeSelection` method in `InputHandler` had to be updated to return the list of blocks or plans that were actually removed.

To display error messages when the player attempts to undo or redo a non-existent action, the `UI` class also required modifications. A new method, `showErrorFade`, was introduced, derived from the existing `showInfoFade` method in the same class. This allows the `UI` to present error notifications, that "pulse" with a red color.
####  Class Diagram
The classes in yellow are the ones that have been modified the ones in green were the ones created to aid with the implementation.
![US1-ModificationClass.svg](Assets/US1-ModificationClass.svg)
####    Created
All of the **created** classes were implemented in the package `mindustry/input`.
#####    Commander
`Commander` was created to full fill the role of *Command Invoker* and *Command History*.
This classes stores stacks of `Command`s, that store done and undone commands.
#####    Command
`Command` is an interface specifying the methods that any `Command` must implement.
#####    BuildPlansCommand
This class is an implementation of `Command` it is used to "plan" buildings, aka player building, or player schematic placing.
#####    RemoveSelectionCommand
This class is an implementation of `Command` it is used remove a selection, aka remove buildings or planed the player as selected.
#####    BlockRotateCommand
This class is an implementation of `Command` it is used to rotate a place building/block.
####   Modified
All the classes here presented, apart from `UI`, are from the `mindustry/input` package.
`UI` is a class implemented in `mindsutry/core`
#####   DesktopInput
######   Constructor
First of all, the `DesktopInput`'s constructor had to be modified so that it listened/observed for the `ResetEvent` event, this is used to clear the history of `Command`s in `Commander` upon the player triggering a `ResetEvent`, usually when a map/sector is left or entered.
```java
public DesktopInput() {  
    Events.on(ResetEvent.class, e -> this.commander.clear());  
}
```
######   Undo/Redo
To handle keybinds related to undoing and redoing some action, the following if statement was introduced in lines `832-846` in the `pollInputPlayer` method. This block checks if a player is trying to undo/redo something, if such action is detected the correspondent method of `Commander` will be called, `undoTop` or `redoTop`, if the `Commander` does not posses any commands to undo or redo, an exception will be thrown and the catch block of the respective action (undo/redo), will show an error message in the Player's interface, `showErrorFade`.
```java
if (Core.input.keyDown(Binding.control) && Core.input.keyDown(Binding.boost) && Core.input.keyTap(Binding.undo)) {  
    try {  
        commander.redoTop();  
    } catch (IllegalStateException ignore) {  
  
        Vars.ui.showErrorFade("Nothing to redo!!!", 5f);  
    }  
} else if (Core.input.keyDown(Binding.control) && Core.input.keyTap(Binding.undo)) {  
    try {  
        commander.undoTop();  
    } catch (IllegalStateException ignore) {  
  
        Vars.ui.showErrorFade("Nothing to undo!!!", 5f);  
    }  
}
```

######   Schematics
In lines `759-761` the following was changed:
```java
else if (!selectPlans.isEmpty()) {  
    Command c = new BuildPlansCommand(selectPlans, this);  
    commander.execute(c);
```
This is located in the `pollInputPlayer` method, this section of code is used to create `BuildPlansCommand`s that will be executed. This `Command`s are instantiated with the `selectPlans` variable, this variable holds the current schematic the player is trying to place.
######   Building Placement
In lines `854-857` the following was changed:
```java
} else {  
    Command c = new BuildPlansCommand(linePlans, this);  
    commander.execute(c);  
}
```
This is located in the `pollInputPlayer` method, this section of code is used to create `BuildPlansCommand`s that will be executed. This `Command`s are instantiated with the `linePlans` variable, this variable holds the current line of plans the Player is trying to plan/build.
######   Remove Selection
In lines `862-864` the following was changed:
```java
int maxSize = !Core.input.keyDown(Binding.schematicSelect) ? maxLength : Vars.maxSchematicSize;  
Command c = new RemoveSelectionCommand(selectX, selectY, cursorX, cursorY, maxSize, false, this);  
commander.execute(c);
```
This is located in the `pollInputPlayer` method, this section of code is used to create `RemoveSelectionCommad`s that will be executed. This `Command`s are instantiated with the rectangular selection the Player is attempting to remove.
######   Block Rotation
In lines `975-976` the following was changed:
```java
Command c = new BlockRotateCommand(cursor.build, Core.input.axisTap(Binding.rotate) > 0);  
commander.execute(c);
```
This is located in the `pollInputPlayer` method, this section of code is used to create `BlockRotateCommad`s that will be executed. This `Command`s are instantiated with the direction and the current building bellow the Player's cursor.
#####   InputHandler
######   Flush Plans
The method `flushPlans` had to be modified as to return a list of the plans that were actually build, valid. This is used mainly by the `BuildPlansCommand` as to prevent some edge cases described in [US1 Test Case Specifications](US1%20Test%20Case%20Specifications)
```java
protected Seq<BuildPlan> flushPlans(Seq<BuildPlan> plans) {  
    Seq<BuildPlan> builtPlans = new Seq<>();  
    for (var plan : plans) {  
        if (plan.block != null ) {  
            if (world.build(plan.x, plan.y) != null && Objects.equals(world.build(plan.x, plan.y).block.name, plan.block.name)) {  
                player.unit().removeBuild(plan.x, plan.y, true); //This is used in undo, why? because when undoing a build action one needs to remove the remove buildplans that were there  
                //And make sure that it does not remove the remove plans of blocks that are not equal to the planned block                builtPlans.add(plan);  
            }  
            if (validPlace(plan.x, plan.y, plan.block, plan.rotation, null, true)) {  
            BuildPlan copy = plan.copy();  
            plan.block.onNewPlan(copy);  
            player.unit().addBuild(copy);  
            builtPlans.add(plan);}  
        }  
    }  
    return builtPlans;  
}
```
######   Remove Selection
The method `removeSelection` also had to be modified for some reasons:
- One of the features offered by undo is keeping the configurations of a removed block and if the removal of that block was undone the configurations would stay the same
- In the event that that removed was not a fully build block, a state between a plan and a fully fledged building, an image is provide to illustrate what this is.
![[Assets/US1-PlansIlustration.png]]
```java
if (tryBreakBlock(wx, wy)) {  
    Building build = world.build(wx, wy);  
    if (build instanceof ConstructBuild cBuild) { //Remove build plans and add them  
        removedBuilds.add(new BuildPlan(build.tileX(), build.tileY(), build.rotation, cBuild.current, build.config()));  
    } else {  
        if (build != null)  
            removedBuilds.add(new BuildPlan(build.tileX(), build.tileY(), build.rotation, build.block, build.config()));  
    }  
}

```
This section ensures that `BuildPlans` illustrated above, are also added to what was removed.
```java
if (!plan.breaking && plan.bounds(Tmp.r2).overlaps(Tmp.r1)) {  
    removedBuilds.add(plan);  
    it.remove();  
}
```
######   Try Break Block
The `tryBreakBlock` had to be modified so that it returned true if the action was valid, or false if the action was invalid. This was modified so that the `validBreak`  method did not have to be called twice once by `removeSelection` and again by `tryBreakBlock`.
```java
public boolean tryBreakBlock(int x, int y) {  
    if (validBreak(x, y)) {  
        breakBlock(x, y);  
        return true;  
    }  
    return false;  
}
```
#####    UI
The following was changed:
```java
public void showInfoFade(String info, float duration){  
    Table table = getFadeInfoTable(duration);  
    table.top().add(info).style(Styles.outlineLabel).padTop(10);  
    Core.scene.add(table);  
}  
  
  
public void showErrorFade(String info, float duration){  
    Table table = getFadeInfoTable(duration);  
    table.top().add(info).style(Styles.outlineLabel).padTop(10).update(l -> l.setColor(Tmp.c1.set(Color.white).lerp(Color.scarlet, Mathf.absin(Time.time, 10f, 1f))));  
    Core.scene.add(table);  
}  
  
private static Table getFadeInfoTable(float duration) {  
    Table table = createInfoTable();  
    table.actions(Actions.fadeOut(duration, Interp.fade), Actions.remove());  
    return table;  
}  
  
private static Table createInfoTable() {  
    var cinfo = Core.scene.find("coreinfo");  
    Table table = new Table();  
    table.touchable = Touchable.disabled;  
    table.setFillParent(true);  
    if(cinfo.visible && !state.isMenu()) table.marginTop(cinfo.getPrefHeight() / Scl.scl() / 2);  
    return table;  
}

public void showInfoToast(String info, float duration){  
    Table table = createInfoTable();  
    table.update(() -> {  
        if(state.isMenu()) table.remove();  
    });  
    table.actions(Actions.delay(duration * 0.9f), Actions.fadeOut(duration * 0.1f, Interp.fade), Actions.remove());  
    table.top().table(Styles.black3, t -> t.margin(4).add(info).style(Styles.outlineLabel)).padTop(10);  
    Core.scene.add(table);  
    lastAnnouncement = table;  
}
```
The method `showErrorFade` was created to display error messages to the player, which would fade after a parameterized amount of time. This method is very similar to `showInfoFade`, as it differs from this one only in adding a gradient to the label in the info, transitioning from red to white and vice versa. 

The methods `showInfoToast` and `showInfoFade` were modified to utilize the `createInfoTable` and `getFadeInfoTable` methods, thereby eliminating code duplication.
#####   Binding
In line `50` the following was added:
```java
undo = KeyBind.add("undo", KeyCode.z),
```
All this does is declare a variable `undo` associated with the key `z`, this is used in `DesktopInput` to detect any input related to undoing and redoing.
