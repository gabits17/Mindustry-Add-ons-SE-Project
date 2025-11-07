# Code Smell Review
## Reviewers
- Diogo Antunes (67763)

## Change Log
- Diogo Antunes (67763) (07/11/2025 22:13)

# Review
- Diogo Antunes (67763)

The initial summary of each metric is useful, and makes the graph significantly easier to understand.
Exceptions were pointed out, and patterns regarding abstraction were spotted
(eg: many points lacking abstractness but also lacking in instability, meaning they are neither relied upon to
further extend functionality, nor do they depend on other packages but are instead depended on).

I assume a package being both stable and concrete is bad because it might indicate that the class packages would be better
placed in another package.

I think it might've been interesting to explore what content certain packages have that makes them more balanced.
Regarding comp, it's interesting to note that these component packages are mentioned effectively nowhere by name, not even for extension purposes.
It seems to be an annotation typical of frameworks we have yet to cover in class.