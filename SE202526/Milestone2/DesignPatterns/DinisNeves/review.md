# Design Pattern Review
## Reviewers
- Gabriela Silva (67286)
- #REVIEWER NAME (#STUDENT ID)
- #REVIEWER NAME (#STUDENT ID)

# Change Log
- Gabriela Silva (67286) 06/11/2025 18:00

# Review

## Review of Strategy
- Gabriela Silva (67286)

This detection of the Strategy GoF pattern has some mistakes, having quite space to improve. It is confusing to understand what was considered as the *Context* here. Consider rewriting the text so it becomes clearer. Also, think about the hierarchy: ``UnitBlock`` class is an extension of ``Block``. Is it possible that the *Context* is the ``Block`` class, since the code snippet is from there?

There's also an error in the UML diagram, since the ``Consume`` class is an **abstract class** and **not an interface**. The diagram should be accurate to the codebase structure.

Despite these improvement points, the analysis regarding the Strategy "*interface*" and the *Concrete Strategies* are well structured and clear.