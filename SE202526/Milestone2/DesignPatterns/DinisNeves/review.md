# Design Pattern Review
## Reviewers
- Gabriela Silva (67286)
- Diogo Antunes (67763)
- #REVIEWER NAME (#STUDENT ID)

# Change Log
- Gabriela Silva (67286) 06/11/2025 18:00
- Diogo Antunes (67763) 07/11/2025 19:10

# Review

## Review of Strategy
- Gabriela Silva (67286)

This detection of the Strategy GoF pattern has some mistakes, having quite space to improve. It is confusing to understand what was considered as the *Context* here. Consider rewriting the text so it becomes clearer. Also, think about the hierarchy: ``UnitBlock`` class is an extension of ``Block``. Is it possible that the *Context* is the ``Block`` class, since the code snippet is from there?

There's also an error in the UML diagram, since the ``Consume`` class is an **abstract class** and **not an interface**. The diagram should be accurate to the codebase structure.

Despite these improvement points, the analysis regarding the Strategy "*interface*" and the *Concrete Strategies* are well structured and clear.

## Review of Composite
- Diogo Dourado Antunes (67763)

I understand the logic being used to suggest a composite pattern, even if this example should be considered a code smell
since it could be resolved by application of a Design Pattern.
However, I'm not sure if it makes sense for MultiTiles to contain other MultiTiles, instead of simply containing some collection of SingleTiles.
When MultiBlocks exist, they are composed of multiple tiles, but this doesn't seem like it could have recursion.
In example, this feels like the difference between a block occupying a 1x1 spot on the map or an XxY spot on the map.
That scenario doesn't require storing multiple sets of different tiles, just one set of single tiles.

### Spelling mistakes
"siminalr" -> similar
"creeating" -> creating