# Code Smell Review
## Reviewers
- Diogo Antunes (67763)
- #REVIEWR NAME (#STUDENT ID)

# Review
- Diogo Antunes (67763)
### Review of Duplicated Code (03/11/2025)
The code snippet shown is of a class with many attributes, not all of which are used by every block.
Since having one constructor per combination would be too many, and mostly unused, setting values to these public attributes
ends up working well enough.

### Commenting on the proposed solution
I'm not sure how useful having a method (a constructor) with these parameters would be. It could be helpful
for cases where many of the instances take the same parameters, helping to group them.

However, this would lead to method signature problems if instances wanted the same number and type of parameters, but corresponding
to a different subset of public attributes.

Alternatively, creating a single constructor that takes every parameter, and passes in default values to those that aren't relevant for the object
also seems like a bad idea as it worsens code readability (especially given that the class itself seems to employ default values for each attribute).
The important attributes within the block would become harder to differentiate.

So overall, I think that the current implementation, while leading to lengthy code, looks consistent enough for its given purpose.

### Minor spelling mistakes and other things
"on purpose" instead of "purposeful"
