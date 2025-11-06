# Design Pattern Report
## Author
- Manuel Oliveira (68547)
# Design Patterns
# **$Apagar-depois!!$**
- Attach a picture of the block of code with the Design Pattern
- Reference the file in which the Pattern was found
- Possible improvements you found
- Possible blocks of code you believe a Pattern could be implemented
    - Picture of said blocks
## Template Method
The example of **Template Method** I found is in **core/src/mindustry/entities/abilities/bullet** and consists of all the bullet types that exist in the game. 
What is happening here is that each class represents a different type of bullet and to achieve this, nearly all classes in this package extend the **BulletType** class, or the **BasicBulletType** class, that itself extends the **BulletType** class, and modify some methods from it while keeping the basis for the implementation of a bullet. This way the responsibility of implementing a bullet is spread out, and **BulletType** implements the more common and fundamental aspects and the rest of the classes specify upon it.

For example the method **draw**, it is implemented for the "first" time in **BulletType**, but as needed it is reimplemented in classes that extend **BulletType**:
- This is the more basic implementation of the **draw()** function implementation in **BulletType**:
![Template_1.png](Assets/Template_1.png)

- Then the implementation in **BasicBulletType** that extends **BulletType**:
![Template_2.png](Assets/Template_2.png)

- And in the class **LaserBoltBulletType** that extends **BasicBulletType**:
![Template_3.png](Assets/Template_3.png)

### Diagram
Given that **BulletType** for example has about 1000 lines of code, quite a few methods were ignored for simplification.

![Template_diagram.png](Assets/Template_diagram.png)

## Strategy
The case of **Strategy** that I want to talk about is in **core/src/mindustry/graphics/g3d**. As many examples in this Codebase, this one doesn't fit exactly in the textbook standard of the **Strategy** design pattern.
What is happening is that we have the interface **GenericMesh**, witch would be our **Context**, that only declares one method, **render**, witch is then implemented in different ways in a variety of classes depending on the class's purpose.

I believe this still counts as a **Strategy** design pattern because it still applies the basic principles of one. The situation is still the **Context** estabilishing the basic guidelines for implementation of a **Strategy**, and then the **Context** delegates the work of implementing it to a **linked Strategy object**.

### Example
Here, as stated above the **GenericMesh** declares the **render** function, witch is then implemented by abstract class **PlanetMesh** and used in **ShaderSphereMesh**, and finally is used in regular code in **core/src/mindustry/type/Planet.java**
**Screenshots of the function, an implementation and it being used
-core/src/mindustry/graphics/g3d/GenericMesh.java
-core/src/mindustry/graphics/g3d/PlanetMesh.java
-core/src/mindustry/type/Planet.java
-core/src/mindustry/graphics/g3d/ShaderSphereMesh.java**
### Diagram


## Observer
In this case I found a very evident example of the **Observer** design pattern in **core/src/mindustry/game/** with **EventType** as the **Publisher**.
This design pattern specifically is very close from the one given in class

### Diagram
