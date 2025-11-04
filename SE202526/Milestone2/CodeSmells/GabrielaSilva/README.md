# Code Smell Report
## Author
- Gabriela Silva (#STUDENT ID)
# Code Smells
- Attach a picture of the block of code with the smell
- Reference the file in which the smell was found
- Possible solutions for the code smell you found
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
Instead of repeating the same parameters over and over, it should be refactored. To do this, an extra class could be implemented: ``MovingHitbox``. This class would store a ``Rect`` object that limited the bounds of the box, and the a ``Vec2`` object that represents the movement ``(vx, vy)``. 

````Java
public class MovingHitbox() {
  private Rect bounds;
  private Vec2 vel;
  public MovingHitbox(Rect r, Vec2 v) {
    this.bounds = r;
    this.vel = v;
  }
  public MovingHitbox(float x, float y, float w, float h, Vec2 v) {
    this.bounds = new Rect(x,y,w,h);
    this.vel = v;
  }
  public MovingHitbox(Rect r, float vx, float vy) {
    this.bound = r;
    this.vel = new Vec2(vx, vy);
  }
  public MovingHitbox(float x, float y, float w, float h, float vx, float vy) {
    this.bounds = new Rect(x,y,w,h);
    this.vel = new Vec2(vx,vy);
  }
} 
````

It would be needed to be careful to not do a *Data Class*, which also is a code smell.

This object class would clean the ``collide()`` method parameters and the calculation of each box's *bounds* and *velocity* could be done outside of it, thus reducing its complexity as well. 

````Java
public static boolean collide(MovingHitbox b1, MovingHitbox b2, Vec2 out) {}
````

This way, the method's signature becomes shorter (thus easier to read and comprehend) and it is safer, since it is impossible to pass the parameters unordered.

This solution would fix the **Data Clump** code smell and, consequently, the **Long Parameter List** as well, since it would only accept two ``MovingHitbox`` objects and a ``Vec2`` as parameters.


## 2. Duplicated Code

The *Duplicated Code* Code Smell can be identified once in the ``UI`` class (``core/mindustry/src/core``).

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

## 3. 
