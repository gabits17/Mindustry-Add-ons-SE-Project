# Code Smell Review
## Reviewers
- Carolina Ferreira (67804)
- #REVIEWR NAME (#STUDENT ID)

## Change Log
- Carolina Ferreira 67804 (07/11/2025 21:19)

# Reviews

## Review of Primitive Obsession
#### Author
- Carolina Ferreira 67804

By viewing the provided cone snippets, I agree it is a Primitive Obsession code smell and partially with the proposed solution: it solver the Primitive Obsession code smell, but creates a whole new one - a Data Class code smell. With that being said, the given solution would not be enough and we would need to ensure a Data Class is not created, by giving this new Class more than just ``get()`` and ``set()``, more coherent method.

In order to do so, we need to study the code and understand the use of these primitives, and check if solving a problem doesn't result in a bigger one, in which case it's better to leave what's done.

With this said, some information about the code smell would be a good add, but not a crucial one.
