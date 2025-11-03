# Code Smell Review
## Reviewers
- Diogo Antunes (67763)
- #REVIEWR NAME (#STUDENT ID)

# Review
- Diogo Antunes (67763)
### Review of Refused Bequest (02/11/2025)
The codebase seems to have many locations where defined methods are unused and not always documented as such.  
In this case, the method is properly implemented by one subclass (RegionPart), so it seems necessary, just not for every subclass.  
#### Proposed Solution difference
As such, instead of simply deleting it, it would be more useful to separate DrawPart into 2 classes:
- A superclass with most of the methods and functionality
- An abstract subclass with the additional load method
RegionPart could inherit fom this subclass, while the other could inherit from the superclass.

If the intention in the future would be to implement the method for all classes, then they could be moved to the subclass,
and eventually refactor the subclass to become the superclass DrawPart again, once this has been finalised.