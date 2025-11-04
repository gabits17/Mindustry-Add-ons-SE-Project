# Code Smell Review
## Reviewers
- Diogo Antunes (67763)
- Manuel Oliveira (68547)

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


# Review
- Manuel Oliveira (68547)
### Review
Except for the Refused Bequest, the code smells seem valid and consistent, and are clearly presented and explained, as well as the solutions. In the case of Reused Bequest, I agree with Diogo Antunes (67763), who previously reviewed this code smell. I think that in this case, would **load()** do anything it would be well implemented, but seeing as it doesn't do anything it might qualify better as **Dead Code**. That said I think the proposed solution still applies, to simply remove the method.
That said there are some minor spelling mistakes and weird phrase construction that make the report hard to follow at times.
