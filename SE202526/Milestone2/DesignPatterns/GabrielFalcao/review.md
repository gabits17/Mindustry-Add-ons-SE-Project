# Design Pattern Review
## Reviewers
- Diogo Antunes (67763)
- #REVIEWR NAME (#STUDENT ID)

# Review
- Diogo Antunes (67763)

## Review of Strategy Design Pattern

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