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






## (Code Smell 2 Name)
## (Code Smell 3 Name)
