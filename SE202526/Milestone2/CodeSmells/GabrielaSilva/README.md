# Code Smell Report
## Author
- Gabriela Silva (#STUDENT ID)
# Code Smells
- Attach a picture of the block of code with the smell
- Reference the file in which the smell was found
- Possible solutions for the code smell you found
## 1. Long Parameter List
*(And Data Clumps)*

The **Long Parameter List** code smell is clearly present in the ``EntityCollisions`` class (``core/src/mindustry/entities``). The static method ``collide()`` is the perfect example of this case.

````Java
public static boolean collide(float x1, float y1, float w1, float h1, float vx1, float vy1,
                            float x2, float y2, float w2, float h2, float vx2, float vy2, Vec2 out)
{ ... }
````

This smell is a consequence of the **Data Clump** code smell, since it is common here for four floats (``x``,``y``,``w`` and ``h``) to be together.

#### How to fix?
Instead of repeating the same parameters over and over, it should be refactored: change the method signature to accept a single ``Rect`` object. This way, the method's signature becomes shorter (thus easier to read and comprehend) and it is safer, since it is impossible to pass the parameters unordered.

This way, it would fix the **Data Clump** code smell and, consequently, the **Long Parameter List** as well, since it would only accept two ``Rect`` objects as parameters.
## 2. 



## (Code Smell 3 Name)
