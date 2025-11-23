# User story 1
Undo and Redo
## Author(s)
- Gabriel Matias (67775)
## Reviewer(s)
- Gabriela Silva (67286)

(*Please add the user story reviewer(s) here, one in each line, providing the authors' name and surname, along with their student number. In the reviews presented in this document, add the corresponding reviewers.*)
## User Story
[User Story 1](../../Milestone1/UserStory1.md)
### Review
*(Please add your user story review here)*
## Use case diagram
![Use Case](Assets/UseCases.drawio.svg)
## Use case textual description
[Textual Description](US1%20Use%20Case%20Textual%20Description.md)
### Review
**Author**: Gabriela Silva (67286), 20/11/2025 23:30

First of all, I would like to point out the organization of this document. Since each user story's documentation is all in just one file, I think Gabriel's organization is to be adopted.

The use case diagram, in general, represents well the undo/redo new functionality implemented. On a first look of it, I was not sure about what did "*Add Done Action*" mean. After reading the complete textual description of the diagram as a whole, I understood it clearly, which is a good sign.

Despite that, I have some things to point out:
- Some fields are empty (not even "*None*" is written):
  - Pre-conditions field at *No Done Action* and *No Undone Action*
  - Secondary Actors field at *Undone Stack Full*
- *Reset Action Commander*'s ID should stay consistent with the other include use case: should be *IUC2*, instead of *IC2*.
- Alternative flows' ID could be moduled as *UCX.1*, *UCX.2*, etc for better identification from what use case the alternative flow is
- The expression "*(a sector, a custom map, a server, etc)*" should be consistent every time it is written in *Enter Map* and *Leave Map*
- *Reset Action Commander* should have some post-conditions statement, such as "*The system's memory of actions is empty/reset*"
- The post conditions field in *Undone Action* is probably wrong. If it removes the last stack's element to add the most recent action undone, it should still be full. As I understood, the element from the stack that was removed was replaced by the most recent undone action, which maintains the stack full.

## Implementation documentation
(*Please add the class diagram(s) illustrating your code evolution, along with a technical description of the changes made by your team. The description may include code snippets if adequate.*)
### Implementation summary
(*Summary description of the implementation.*)
#### Review
*(Please add your implementation summary review here)*
### Class diagrams
[Class Diagrams](US1%20Class%20Diagrams.md)
### Review
*(Please add your class diagram review here)*
### Sequence diagrams
[Sequence Diagrams](US1%20Sequence%20Diagrams.md)
#### Review
*(Please add your sequence diagram review here)*
## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*
