# User story 2
Leak identification via minimap and proximity
## Author(s)
- Diogo Antunes (67763)
## Reviewer(s)
- Gabriel Matias Falcão (67775)
- Carolina Ferreira (67804)
- Dinis Neves (68130)
- Manuel Oliveira (68547)

(*Please add the user story reviewer(s) here, one in each line, providing the authors' name and surname, along with their student number. In the reviews presented in this document, add the corresponding reviewers.*)
## User Story:
[User Story 2: Leak identification](../../Milestone1/UserStory2.md)

#### Note (regarding changes from Milestone 1)
- Did not go forward with the decision to add leak messages because when extending an existing leaking pipe, this would be considered
  a new leak, resulting in messages being sent often during casual base-building when setting up liquid flow systems.
### Review

**Author** : Gabriel Matias Falcão (67775), 24/10/2025

*This could be developed further as a sort of alert system, using the chat or some user interface, in which the player could sort between alerts (destroyed units, pipes, blocks...)*

**Author** : Manuel Oliveira (68547), 24/10/2025

*The feature looks worth implementing if not too worksome for our timeframe.*

## Note about content of the following work

This user story extends existing user interaction with the game by showing leaks, so aside from time, there's no primary actor directly interacting with the created system.

For example, the act of breaking/placing of a block stays the same. However, at some point in the logic that handles the update for this scenario, if the block is a leaking liquid-carrying block, or is adjacent to one such block,
the new functionality is triggered.

This means that despite the work document specifying (in the case of class diagrams) to include classes modified or implemented, that alone isn't
enough to describe many of the use cases that eventually access the new functionality. As such, I will include the main pathway of class methods used to reach that point
if it is necessary to explain the added functionality.

Additionally, only the Desktop version of the game is considered, since that is the version readily available for testing.

## Use case diagram
(In page linked below)

## Use case textual description
[Textual Description](use-case-template.md)

#### Review
**Author** : Manel Oliveira (68547), 21/11/2025 20:00
A simple note about the document and folder's organization. Maybe split this readme into different files akin to what Gabriel did, and an Assets folder. It really isn't that big of an issue, but it's important to keep things organized.

**Break block** and **Place block** maybe should be generalised to a possible **Interact With Block**. This may not be the case, but from my point of view in this situation they seem to have the same effect on the system. The same could be said to **Enter map** and **Leave map**.

Shouldn't **Enter map** include **Update leakable block tile**?
My reasoning for this is, when a player enters a map with a pre-existing leak, they didn't break or place blocks, but a leak is still displayed.

Other than this the diagram and every use case report seemed very comprehensible and represents very well what I experienced personally testing this feature.

**Author** : Diogo Antunes (67763), 21/11/2025 23:44  
Review response:  
I intend on organising the folder once the final version for class diagrams has occurred, to avoid image name duplication when I'm still changing files.
I will take note to name these images properly, instead of just numbering them, and will adopt the subsection structure as in User Story 1 to better organise my work.

Regarding break block and place block, as mentioned in the use case, break block has an extra step not present in place block.
Additionally, the player interaction is set off under different circumstances, so grouping the two doesn't feel like it follows specification,
even if the two use cases are quite similar.  
Enter map and Leave map have very similar logic, but different ways of starting the interaction, so once again, I feel I shouldn't group them.
On the other hand, I will further factorise the line of resetting logic into clear leaks common to both use cases, and modify the class diagrams accordingly too.  
Regarding enter map loading all tiles, which would add them to the leaks registered. However, I feel this goes under the update buildings use case,
as the focus for the use case of entering a map and leaving a map is purely the logic reset that happens as a result on directly entering the map.
Anything that happens afterward involves loading the map save and retrieving data and then loading buildings, which I feel goes beyond the use case, and more into the scenario into **Update building**.

## Implementation documentation
[Implementation Documentation](implementation-documentation.md)

#### Review

**Author** : Dinis Neves (68130) 03/12/2025 11:11

The Implementation summary is nice and concise not wasting the readers time. All information seems to be present. If there is something that is missing form the documentation and or from the summary I have missed it. Everything is correctly mentioned and no real issue was noticed by me.

### Class diagrams
[Class Diagrams](class-diagrams.md)
#### Review
**Author** : Carolina Ferreira (67804) 23/11/2025 19:11

Besides a little punctuation error in the **Update leakable block tile** section - "The ``checkLeak`` method, checks for a transition from *not leaking -> leaking* and vice versa and in such case adds/removes the tile
from the data structures ``leakTree`` and ``leakSet`` using the ``addLeak(Tile tile)`` or ``removeLeak(Tile tile)`` methods (missing the comma - **,**) respectively." - there are no other elements to correct, in my opinion.

During my review, Diogo corrected the **Clear leaks** diagram, which is confirmed by the commits made in the past few hours. Therefore, I have no further comment on his report. It's a comprehensive report that provides the desired information.

### Sequence diagrams
[Sequence Diagrams](sequence-diagrams.md)
#### Review

**Author:** Dinis Neves (68130) (04/12/2025) 3:00

The communication in between boundary, entity, controller, seems to be correct. Issues with repetition of explanations could be addressed to make the writing more concise. ex: logic reset that is mentioned in enter, exit map and clear leaks could be only addressed once instead of being mentioned in all sequence diagram explanations.

I appreciate the addresses like the addressing the `Event` mediator class. The seclusion of this explanation could be used in other parts of the Sequence diagram explanation. ex: `Place block` having "This case wasn't considered in the use case... "


Tackling `Update leakable block tile` I agree with the sequence diagram. Regarding the explanation I believe the separate conditions (opt) like (abbreviated) can leak, which team, and within those the was to is leaking and vice versa.
Could be explained in a shorter manner. For example bullet points (like how it is used for the leaking to not transitions).

- "know whether the ``ConduitBuild`` can actually leak"
- "know whether the tile is in the ``Player`` team"

And then if need be delve into further detail.

(Personal preference would be to have the "... we can check for a transition:" bullet points first and then explain them).

`Update leak display` Sequence diagram in my perspective looks unapproachable at first. I think it could, (not saying it should), be split into minimap and dash circle sections.

The `opt[not pixalated]` could be addressed separately and have a diagram just for it. Although more effort and not necessary 3 diagrams to go over `Update leak display` in my eyes would help out the who is to interpret the diagram. (full one like the original,  and the two renderers: minimap and local circle overlays).

(Not to repeat myself but, "For the sake of this use case, the diagram checks that the block is a ``Conduit``" could be addressed separately as a "Note:" I believe it is not the most important in part of the explanation and could be addressed separately, (personal preference)).

There are more picky grievances than actual issues (if any) with the sequence diagrams. The effort into these diagrams cannot be understated and the report does more than suffice.

#### Review response
**Author:** Diogo Antunes (67763) (4/12/2025) : 11:30
I have made changes to explanations as requested. I did not change, however, the opt conditions because they explicitly use the variable in messages in the sequence diagram,
and I think it helps show where those variables are relevant.

Absolutely agree with the separation of the large diagram, and have done so.

I only use the "Note" notation to indicate when things may not explicitly match the class diagram: like the ``Cons`` interface mostly composed of lambda expressions relevant to ``Events``
and, after changing according to this review, the checking for an alternate block size in "Place block" since it differs from the use case scenario.

## Test specifications
[Test Cases](test-case-specifications.md)
### Review
*(Please add your test specification review here)*