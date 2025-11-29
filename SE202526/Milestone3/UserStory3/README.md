# User story 3
Swapping turret's targeting priorities
## Author(s)
- Dinis Neves (68130)
- Gabriela Silva (67286)
## Reviewer(s)
- Diogo Antunes (67763)

(*Please add the user story reviewer(s) here, one in each line, providing the authors' name and surname, along with their student number. In the reviews presented in this document, add the corresponding reviewers.*)
## User Story:
[User Story 3: Swap Targeting](../../Milestone1/UserStory3.md)
### Review
*(Please add your user story review here)*
## Use case diagram
![us3-UCD](./assets/swap-targeting-ucd.svg)
## Use case textual description
[Textual description from Swap Targeting's use case diagram](./USECASE-TEMPLATE.md)
### Review
**Author** : Diogo Antunes (67763), 23/11/2025 16:13

**Select placed turret** steps 3 and 4 allude to a separate interaction from the current use case.
The player doesn't seem to have to deselect, it can happen at some point. Therefore, I think it would make more sense for deselection
to be a separate use case since it seems to originate from a separate interaction by the player.

Another point, it might be my own lack of familiarity with the functionality, but I find it difficult to understand the difference between
target **type** and target **mode**. I assume type would relate to prioritising health, defence and such enemy traits, while type refers to factors like distance,
potentially? However, this wouldn't make sense as it should only be one or the other. Therefore, it's not immediately obvious to me what functionality this may indicate.
Since the "type" used for priority may cover a set of traits not easily grouped, it's an understandably abstract term. 

Upon reading the Alternative flow, I can gather that type is related to grounded or flying enemies. However, I feel that **type** and **mode** are a little unclear.
However, I'm also unable to suggest a better word at this point, maybe (for mode) something like "Change turret's target unit environment" but even that's not entirely appropriate.

With that in mind, the functionality is an easy to understand extension to the turret mechanics. I personally just think the wording for **type** and **mode** could be clearer,
as well as the deselection being considered part of the **Select placed turret** use case.

---
_The use case diagram's textual description was modified according to this review._

## Implementation documentation
(*Please add the class diagram(s) illustrating your code evolution, along with a technical description of the changes made by your team. The description may include code snippets if adequate.*)
### Implementation summary

The implemenation of this user story took place mostly around the ``Turret`` class, since it was added new configurations to it. It was created two new classes: Java Enums ``TargetingMode`` and ``TargetingEnvironment``. They contain a list of possible modes and environments for the targeting configuration of a turret.
- Possible targeting modes:
  - *Closests* first
  - *Farthest* first
  - *Strongest* first
  - *Weakest* first
  - *Fastest* first
  - *Slowest* first

- Possible targeting environments:
  - *Ground units* first
  - *Air units* first
  - *Any* first

It is possible to **combine** the targeting environment with a mode. For instance, it is possible for a turret to target the strongest ground units around it. For targets that target both air and ground units, it is possible to (or not) focus on an environment.

To sort the enemy units that a turret will attack, the ``SortUnits`` was utilized. There was already four ways for sorting them (``Sortf`` objects): ``closest``, ``farthest``, ``strongest`` and ``weakest``. It was added two new ways: ``fastest`` and ``slowest``.

To combine the targeting environment with a targeting mode, it was also implemented two new (static) functions that handle air or ground units first, with the mode parameterized: ``airFirst(mode)`` and ``groundFirst(mode)``.

These configurations are defined and depend on the ``Turret``'s ``targetingMode`` and ``targetingEnv`` new instance variables. The sort occurs in the ``unitSorter()`` new method in the ``TurretBuild`` class (inner class from ``Turret``). This method is called in the function ``findEnemy()`` in the same way the default sort was before this functionality was added.

To display this configuration, the ``Turret`` block became configurable (``configurable = true`` in its constructor), which means it can be configured by the player, by clicking on it. 

<mark style="background: #f4f44fa2;"> *// TODO: Describe UI implementation to display and change the targeting configuration*</mark>

#### Review
*(Please add your implementation summary review here)*
### Class diagrams
(*Class diagrams and their discussion in natural language.*)
### Review
*(Please add your class diagram review here)*
### Sequence diagrams
[Sequence diagrams for Swapping Turret's Targeting User Story](./SEQUENCE-DIAGRAMS.md)
#### Review
**Author** : Diogo Antunes (67763), 29/11/2025 14:35

**Select Placed Turret**

- Boundary ``InputHandler`` interacts directly with an entity ``TurretBuild``, this shouldn't happen in a sequence diagram.  
- I think there should be separate vertical activity rectangles for the interactions with TurretBuild as it doesn't seem to be one run on interaction, but instead multiple starting from the InputHandler.  
- The arrows having the "return" text are a different style from what I used, instead of being empty, but the lesson powerpoints also show return arrows in a variety of ways.

**Change Turret's Target Mode**

- There is no return arrow at the end for a synchronous message, which I think is wrong.  
- The combined fragment strict seems to have been applied incorrectly. It should encompass the section with the opt combined fragment as well in a separate operand.
In truth, there actually doesn't seem to be a need for this combined fragment.
- "Playerr" has a typo.

**Change Turret's Target Environment**

- Strict doesn't seem to be necessary here.
- There's also direct communication between the boundary and the entity within the operand in strict.

I'm assuming "environment button is pressed" is the value of an attribute and not an additional interaction from the player, since ``ClickListener`` receives all input.
With that assumption, the opt looks right.

**Unselect Turret**

- Boundary communication with entity again.  
- The ``onConfigureBuildTapped(build)`` is synchronous but has no empty return.
- The activity bar in ``hideConfig()`` that starts in ``BlockConfigFragment`` starts within an "alt" operand and carries over to the next. i'm not sure this is according to specification,
but I might be wrong in pointing this out.

**Other notes**

- There seems to be a use of public methods like ``toString()`` at times or using java boolean negation like "!targetsBoth()" which potentially lessens readability
considering it's meant to be understood by non-programmers. However, since each method in the sequence diagram is supposed to correspond to something in the code, I'm not sure how this could be changed without removing meaning.
Altering the method name in the sequence diagram and then explaining the abstraction that was used could be an option but I'm not at all sure that would be the right thing to do.

## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*
