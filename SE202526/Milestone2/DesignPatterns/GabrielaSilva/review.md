# Design Pattern Review
## Reviewers
- Diogo Antunes (67763)
- #REVIEWR NAME (#STUDENT ID)

# Review
- Diogo Antunes (67763)

## Review of Template Method Design Pattern (04/11/2025)

While this example of the template method doesn't have a lot of steps that are overridden by subclasses,
the methods that are changed can contain very substantial additions of logic, such as the generate method in Serpulo,
with 500 lines of code, including the class Room defined within the method.
The generate method in the abstract class goes to the process of map generation as mentioned, and each planet can have its own
logic without such additions having to be explicitly known by the method, which allows for making further additions more easily
in future updates.