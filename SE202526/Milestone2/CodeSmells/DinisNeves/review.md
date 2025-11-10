# Code Smell Review
## Reviewers
- Carolina Ferreira (67804)
- Manel Oliveira (68547)
- Diogo Antunes (67763)

## Change Log
- Carolina Ferreira 67804 (07/11/2025 21:19)
- Manel Oliveira 68547 (07/11/2025 23:33)
- Diogo Antunes (10/11/2025 09:46 - extra review)

# Reviews

## Review of Primitive Obsession
#### Author
- Carolina Ferreira 67804

By viewing the provided cone snippets, I agree it is a Primitive Obsession code smell and partially with the proposed solution: it solver the Primitive Obsession code smell, but creates a whole new one - a Data Class code smell. With that being said, the given solution would not be enough and we would need to ensure a Data Class is not created, by giving this new Class more than just ``get()`` and ``set()``, more coherent method.

In order to do so, we need to study the code and understand the use of these primitives, and check if solving a problem doesn't result in a bigger one, in which case it's better to leave what's done.

With this said, some information about the code smell would be a good add, but not a crucial one.

## Review of Duplicated Code
#### Author
- Manel Oliveira 68547

This is, unfortunately a quite common and standard code smell for this codebase.

In this case there is repeated code to a degree, but there is a crucial difference in purpose in the two functions, so the solution purposed might introduce more problems than the ones that it solves.

## Review of Long Method
#### Author
Note: This is my fourth review, not one of the 3 counted towards milestone 2.
The last person to have reviews to do, only had their own work without review, so I'm reviewing this one as an extra
so they can complete 3 reviews on different people's work.

- Diogo Antunes 67763

Since there are 3 functions exemplified, and one is elaborated, I'd like to see a code snippet of the function being refactored
to get a better grasp of its scale. The example also creates an auxiliary function for a two line code section. As such, unless the code logic
was repeated many times, it might not be the best point of refactoring in the code. Isolating logic in larger blocks may also be a helpful way
to abstract method logic into a series of smaller steps.

Its also worth noting that the CONTRIBUTING.md document explicitly mentions avoiding creating methods (including auxiliary methods)
unless strictly necessary. As such, the reasoning that causes these long methods is very much on purpose, and I feel that this would be worth mentioning
when discussing the code smell.