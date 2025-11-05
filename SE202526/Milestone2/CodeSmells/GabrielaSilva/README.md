# Code Smell Report
## Author
- Gabriela Silva (67286)
## 1. Long Parameter List
*(and Data Clumps)*

The **Long Parameter List** code smell is clearly present in the ``EntityCollisions`` class (``core/src/mindustry/entities``). The static method ``collide()`` is the perfect example of this case, since it takes **13 parameters**.

Its signature is difficult to read and error-prone due to the number of *ordered* parameters:

````Java
public static boolean collide(float x1, float y1, float w1, float h1, float vx1, float vy1,
              float x2, float y2, float w2, float h2, float vx2, float vy2, Vec2 out)
````

The ``collide()`` method calculates both velocities of two entities with respect to each other, along with the entry and exit times of it. If a collision is detected within the time step, it also precises the collision point.

This smell is a consequence of the **Data Clump** code smell, since the four floats defining the bounds of a box (``x``,``y``,``w`` and ``h``) are repeatedly together, along with two floats (``vx`` and ``vy``) that define the box's velocity.

#### How to fix?
Instead of repeating the same parameters over and over, it should be refactored. To do this, an extra class could be implemented: ``MovingBox``. This class would store a ``Rect`` object that limited the bounds of the box, and the a ``Vec2`` object that represents the movement ``(vx, vy)``. 

````Java
public class MovingBox() {
    private Rect bounds;
    private Vec2 vel;
    public MovingBox(Rect r, Vec2 v) {
      this.bounds = r;
      this.vel = v;
    }
    public MovingBox(float x, float y, float w, float h, Vec2 v) {
      this.bounds = new Rect(x,y,w,h);
      this.vel = v;
    }
    public MovingBox(Rect r, float vx, float vy) {
      this.bound = r;
      this.vel = new Vec2(vx, vy);
    }
    public MovingBox(float x, float y, float w, float h, float vx, float vy) {
      this.bounds = new Rect(x,y,w,h);
      this.vel = new Vec2(vx,vy);
    }
} 
````

It would be needed to be careful to not do a *Data Class*, which also is a Code Smell.

This object class would clean the ``collide()`` method parameters and the calculation of each box's *bounds* and *velocity* could be done outside of it, thus reducing its complexity. 

````Java
public static boolean collide(MovingBox b1, MovingBox b2, Vec2 out) {}
````

This way, the method's signature becomes shorter (thus easier to read and comprehend) and it is safer, since it is impossible to pass the parameters unordered.

This solution would fix the **Data Clump** code smell and, consequently, the **Long Parameter List** as well, since it would only accept two ``MovingBox`` objects and a ``Vec2`` as parameters.


## 2. Duplicated Code

The *Duplicated Code* Code Smell can be identified a few times in the ``UI`` class (``core/mindustry/src/core``).

`````Java
public void showInfoFade(String info, float duration){
    var cinfo = Core.scene.find("coreinfo");
    Table table = new Table();
    table.touchable = Touchable.disabled;
    table.setFillParent(true);
    if(cinfo.visible && !state.isMenu())
        table.marginTop(cinfo.getPrefHeight() / Scl.scl() / 2);
    // (...)
}

public void showInfoToast(String info, float duration){
    var cinfo = Core.scene.find("coreinfo");
    Table table = new Table();
    table.touchable = Touchable.disabled;
    table.setFillParent(true);
    if(cinfo.visible && !state.isMenu())
        table.marginTop(cinfo.getPrefHeight() / Scl.scl() / 2);
    // (...)
}
`````
These two methods have clearly different functionalities, but both need to setup a new ``Table`` element to display a temporary message.

Actually, many methods of this class use a similar logic while doing its setup to display a message. Another example of this smell that also is related to the code block above is the following, on ``showInfoPopup(String, float)``:

````Java
public void showInfoPopup(String info, float duration) {
    Table table = new Table();  
    table.setFillParent(true);  
    table.touchable = Touchable.disabled;
    // (...)
}
````

It is safe to say that these three methods execute the same three lines of code:

````Java
Table table = new Table();  
table.setFillParent(true);  
table.touchable = Touchable.disabled;
````

Although it seems little, refatoring this code block would also benefit the code.

#### How to fix?

A possible solution to this Code Smell could be writing a new method that contains the repeated code. Refactoring the code would make it easier to read, clearer to maintain and be simple to write new code in this class.

To fix it, the suggested solution would, then, be to implement two new (private or not) functions that contain each of the repeated code blocks:

````Java
private void setupNewTable() {
    Table table = new Table();  
    table.setFillParent(true);  
    table.touchable = Touchable.disabled;
}
````

````Java
private void handleCoreInfo() {
  var cinfo = Core.scene.find("coreinfo");
  if(cinfo.visible && !state.isMenu())
      table.marginTop(cinfo.getPrefHeight() / Scl.scl() / 2);
}
````

These two new methods would be called everytime an arbitrary method needs to execute its functionalities. In this case:
- The methods ``showInfoFade()`` and ``showInfoToast()`` would call both ``setupNewTable()`` and ``handleCoreInfo()`` functions;
- The method ``showInfoPopup()`` would only call ``setupNewTable()``.

#### Notes

This Code Smell can also be detected in the same class in the methods ``showConfirm()`` and ``showCustomConfirm()``. The solution suggestion would be the same as the one did above.

## 3. Divergent Class

The ``Map`` class can be considered a *Divergent Class* Code Smell since it takes too many responsibilities. This class can be found in ``core/src/mindustry/maps``.

Below, there is a few examples of responsabilites that the class handles.

- **Stores and returns map's meta data**:
````Java
public String name(){}
public String author(){}
public String description(){}
public String plainName() {}
public String plainAuthor(){}
public String plainDescription(){}
// (...)
````
- **Handles *Steam* management**:
````Java
public String getSteamID() {}
public void addSteamID() {}
public String steamTitle() {}
public Fi createSteamPreview() {}
public String steamTag() {}
// (...)
````

- **Applies rules to the specified gamemode of the map**
````Java
public Rules applyRules(Gamemode mode) {}
public Rules rules() {}
public Rules rules(Rules base) {}
// (...)
````
And so on.

#### How to fix?

The most practical and clearer solution is to separate the ``Map`` class into several other small classes that handles each responsbility. That way, it would be easier to understand what each class is responsible for.

For example, there could be the following classes:
- ``MapMetaData``: (Intuitively) contains the map's meta data and all methods related to it.
- ``MapSteamManagement``: Stores all the necessary *Steam* information about the map and handle *Steam Workshop* management.
  - Note: This class could actually be *map-independent*, i.e., ``SteamManagement``, so it could take responsibility for everything regarding *Steam*, even if not map-related.
- ``MapRules``: Responsible for everything about the rules of the map, scores, filters, and so on.
- ``Map``: The actual ``Map`` class, handling only the crucial methods (such as file handling, ``compareTo``, etc). 