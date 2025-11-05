# Code Smell Review
## Reviewers
- Diogo Antunes (67763)
- Manuel Oliveira (68547)
- Gabriela Silva (67286)

## Change Log
- Diogo Antunes 67763 (02/11/2025 10:11)
- Gabriela Silva 677286 (05/11/2025 14:09)
- Manuel Oliveira 68547 (05/11/2025 18:52)

# Review
### Review of Refused Bequest (02/11/2025)
- Diogo Antunes (67763)

The codebase seems to have many locations where defined methods are unused and not always documented as such.  
In this case, the method is properly implemented by one subclass (RegionPart), so it seems necessary, just not for every subclass.  
#### Proposed Solution difference
As such, instead of simply deleting it, it would be more useful to separate DrawPart into 2 classes:
- A superclass with most of the methods and functionality
- An abstract subclass with the additional load method
RegionPart could inherit fom this subclass, while the other could inherit from the superclass.

If the intention in the future would be to implement the method for all classes, then they could be moved to the subclass,
and eventually refactor the subclass to become the superclass DrawPart again, once this has been finalised.

### Review of Long Method
- Gabriela Silva (67286)

The Long Method code smell, as stated, is the most common code smell in this codebase. The example analysed is a pretty good example, since it has 330 lines (more than 10 times greater than the expected average) and **71 (!)** ``if`` statements. This clearly make the code really hard do read and understand. 

The proposed solution might be the best one accross the possibilities. Since there are multiple ``if`` statements, having stand-alone methods that execute each ``if`` would definetly improve readability. The example solution is well demonstrated.

Overall, this code smell report is really well structured. There's only room for improvement to consider in the random *enters* done during a sentence and the frequent use of the expression "*I believe*". I recommend reviewing it.

# Review of Data Clumps
- Manuel Oliveira (68547)

I personally have also encountered this same code smell, specifically applied to the same, or very similar, coordinates set, throughout the code base in distinct instances from the ones showcased. And even if they serve slightly different purposes each time they appear, these 4 variables truly do appear consistently together.
And therefore it is a good example of a recurring **Data Clump**.

The solution presented can often be regarded as the "Standard" solution for this smell and for a good reason, and the only thing I can comment on it is that I don't see a reason this was not done from the beginning.

In summary, the code smell seems valid and consistent, and is clearly presented and explained, apart from some minor spelling mistakes ( like a ")" missing at the end of a sentence), and weird phrase construction that make the report hard to follow at times, but nothing too major.
