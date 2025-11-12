# Design Pattern Review
## Reviewers
- Diogo Antunes (67763)
- Carolina Ferreira (67804)
- Dinis Neves (68130)

## Change log
- Diogo Antunes (67763) (18:03)
- Carolina Ferreira (67804) - 22:45
- Dinis Neves (68130) - 07/11/2025

# Review
## Review of Strategy Design Pattern (02/11/2025)
- Diogo Antunes (67763)

The final Diagram has the Turret aggregation with the class instead of the interface.

The sentence that initially describes the rationale is difficult to understand.
I'd rewrite  
"determines a Strategy design pattern this class exposes algorithms used to sort units based on their properties. It is usually used by turrets such as foreshadow, malign, lustre, etc...; to sort through the strongest unit."  
as  
"uses a Strategy design pattern where a single algorithm is attributed to a unit (a Turret in this case).
The algorithm is responsible for deciding what targets the Turret prioritises, based on properties like distance and strength.
Named turrets like foreshadow, malign, lustre use an algorithm that sorts the target units by strength."

Spelling error in "functionlalities" -> functionalities

- I have updated the report and fixed what was mentioned here (67775)

## Review of Command Design Pattern
- Carolina Ferreira (67804)

A small explanation of the design pattern would be a nice add to the beginning of first paragraph. 
With that said, I agree with the taken conclusions and have no further comments.


## Review of Visitor Design pattern
#### Author
- Dinis Neves 68130

#### Review
I think it is a valid identification of the design pattern and believe it does slightly go above the visitor design pattern being the opposite. I believe due to how it is explained in the report it is to me it is agreeable that this is a valid reverse visitor design pattern.