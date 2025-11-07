# Code Smell Review
## Reviewers

- Carolina Ferreira (67804)
- Gabriela Silva (67286)
- #REVIEWR NAME (#STUDENT ID)

# Change Log
- Carolina Ferreira (67804) 07/11/2025 21:00
- Gabriela Silva (67286) 07/11/2025 22:45

# Review

## Review of Template Method Design Pattern
- Gabriela Silva (67286)


The source path is wrong, I believe it was supposed to be just ``core/src/mindustry/entitites/bullet``, without the ``/abilites`` before the ``bullet`` directory.

Overall, the example found and analysed is a great illustration of how inheritance is used to customize behavior incrementally. It achieves the goals of the Template Method pattern. The ``BulletType`` class (in some way) acts as the *Template* of the pattern, but it "fails" to garantee a final method that is the *fixed skeleton* for the classes that implement the diferent steps of the pattern. It also "fails", since it is not an abstract class.

I dont think the transparent background of the UML diagram is a good idea, try exporting it with a black or white background so it is clear in the markdown file, wheter the theme is dark or light.



## Review of Strategy Design Pattern 
- Carolina Ferreira (67804)

On this implementation of the **Strategy** design pattern, the **Strategy** interface is wrongly identified as the **Context** "interface". Another lacking aspect of this report is the absence of a reference to one the concrete strategies in the Context Class, and the class itself. These are majors problems that make this **not** a Strategy Design pattern.

Another topic is the lack of context and information about the said Design pattern that I think would be nice to add, but not entirely crucial, being an option to the author to consider.

