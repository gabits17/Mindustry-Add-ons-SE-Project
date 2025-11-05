# Code Smell Review
## Reviewers
- Diogo Antunes (67763)
- Gabriela Silva (67286)
- #REVEIWER NAME (#STUDENT ID)

## Change Log
 - Diogo Antunes 67763 (03/11/2025 23:00)
 - Gabriela Silva 67286 (04/11/2025 11:37)

# Reviews
## Review of Duplicated Code (03/11/2025)
#### Author
- Diogo Antunes (67763)

#### Review
The code snippet shown is of a class with many attributes, not all of which are used by every block.
Since having one constructor per combination would be too many, and mostly unused, setting values to these public attributes
ends up working well enough.

#### Commenting on the proposed solution
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

## Review of Data Clumps
#### Author
- Gabriela Silva 67286

#### Review
Data Clumps detection here is accurate, since both ``findTile()`` and ``findEnemyTile()`` repeat similar groups of parameters. Consolidating them would definetly benefit the code.

Although the proposed solution would work, perhaps it is heavier than necessary. Instead of *hard-merging* every parameter together into a single object, it could be more effective to group them into smaller groups of parameters that relate to each other, such as the *position* and *range* together, or the *team* and *predicade*.

This way, it would still remove duplication and the refactoring would be clearer and more modular.

## Review of Feature Envy

First of all the code would be better if displayed in a code block, as the snippet is quite large
and thus the image becomes unreadable on smaller devices/screens, this is of course a personal opinion and has 
nothing todo with the code smell itself.

About the code smell, maybe point lines of code that actually have the smell instead of giving the whole method.
When reviewing I had to spend some time trying to understand where the smell was.

**Example**

Here the `ContentParser` is directly accessing some an attribute of node (`TechNode`).
```java
if(research.has("objectives")){
    node.objectives.addAll(parser.readValue(Objective[].class, research.get("objectives")));
}

```


**Another Example**

Here we see the `ContentParser` directly accessing a `Seq` of `TechTree` and using a method of said `Seq`.
- Envious of `TechTree`'s attributes
```java
TechNode lastNode = TechTree.all.find(t -> t.content == unlock);
```

### Proposed Solution
1. About the proposed solution, I don't believe that refactoring this method onto `TechTree` or `TechNode` would solve the problem
it would just create the same code smell inversed, where either `TechTree` or `TechNode` would be envious of the parser's
capabilities.
2. Creating a new class just to house this method would make the problem even 
worse since that proposed class would only access features either from
`ContentParser`, `TechTree` or `TechNode`, thus making it envious of all three.


I propose that, as said in [Refactoring Guru Feature Envy](https://refactoring.guru/smells/feature-envy), we could create
methods in `TechTree` or `TechNode` that would aid the parser executing its function instead of it needing to directly 
access the objects attributes would be better. This would also solve the encapsulation issue 
(even if that is something we have noted to be normal throughout the codebase).
 

