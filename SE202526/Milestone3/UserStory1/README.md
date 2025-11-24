# User story 1
Undo and Redo
## Author(s)
- Gabriel Matias (67775)
## Reviewer(s)
- Gabriela Silva (67286)
- Dinis Neves (68130)

(*Please add the user story reviewer(s) here, one in each line, providing the authors' name and surname, along with their student number. In the reviews presented in this document, add the corresponding reviewers.*)
## User Story
[User Story 1](../../Milestone1/UserStory1.md)

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
#### Answer
Gabriel Falcão (67775), 24/11/2025 10:17

I have updated the diagram's description, given the points above.
## Implementation documentation
(*Please add the class diagram(s) illustrating your code evolution, along with a technical description of the changes made by your team. The description may include code snippets if adequate.*)
### Implementation summary
(*Summary description of the implementation.*)
#### Review
*(Please add your implementation summary review here)*
### Class diagrams
[Class Diagrams](US1%20Class%20Diagrams.md)
### Review
**Author**: Dinis Neves (68130), 23/11/2025 00:30

As someone who has followed this implementation the class diagrams seem to provide further details that dialogue and glances at the code did not. The Class Diagrams are well defined and the only issue with the Class Diagram that as of now can be identified is the repetition of the [Assets/PasteSchematic_BuildBuildingClass.drawio.svg]. I might be wrong but it looks to me as a small issue with the Class Diagram document. The use of the same diagram is to highlight that the implementation is identical. I believe at first glace that would hinder the understanding of the implementation. I believe an approach where adding to the small disclaimer at the top: "The description of the two following diagrams will only be given here, ... use the same implementation." (adding: "therefore only one diagram for both is shown below.") would be more understandable, maybe even removing Base Building and adding it to the previous subtitle.

A small nitpick that I have is that for ease of traversing through the code to check the implemented and changed classes a path like [mindustry/input/Commander.java] would help out in this regard.

Concluding, the Class Diagram is correct and I only have a small difference in preference for the explanation document layout of the Class Diagram.


#### Answer
Gabriel Falcão (67775), 24/11/2025 10:14

I don't agree with the first point as it is my personal preference to have the document structured as is, maybe we can ask for the group's opinion on it.

I did not understand the second point, where would you like me to write the path?
### Sequence diagrams
[Sequence Diagrams](US1%20Sequence%20Diagrams.md)
### Review
**Author** : Diogo Antunes (67763), 23/11/2025 23:10

In **Undo**, I think it'd be useful to create a local variable that stores the result of ``hasDone()``
so that this result can be used as the condition in opt. This goes for other cases throughout the diagrams.

Also, I think the sequence diagram referenced in **ref** should be within the label as "ref \<name\>".

Typo of "DesktopInpu" in description.

**Add Undone** and check inn all diagrams after **Add Command**
Minor formatting comment - The text starts immediately after the image, should have an ``Enter`` there.
Typo "refereed" -> referreed

**Remove Selection**
My own understanding of the vertical rectangles that indicate an activity are limited, but it may be useful to include one after ``execute()`` to show the activity roughly taking place.

In general, the vertical activity bars should be present for most entities due to use of synchronous method calls.
This should go along with return arrows, even if empty, since the synchronous message means waiting for a response.

It might also be worth checking out notation in terms of underlining classifiers when they represent specific instances of classifiers, and not just roles.
#### Answer
About the first point, I will have to ask one of the professors, what would be the best way to portray the diagram.

I do not agree with the "ref \<name\>" formatting, I've been following this site as formatting reference, [UML Sequence Diagrams](https://www.uml-diagrams.org/sequence-diagrams.html ), and the author does not seem to use that type of formatting while portraying ref frames.

I do agree with the rectangles after execute.
## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*

