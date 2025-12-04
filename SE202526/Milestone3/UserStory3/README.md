[comment]: <> (% ------------------ HEADER ---------------------- %)

# User story 3
Swap Turret's Target Configurations
## Author(s)
- Dinis Neves (68130)
- Gabriela Silva (67286)
## Reviewer(s)
- Diogo Antunes (67763)
- Carolina Ferreira (67804)
- Manuel Oliveira (68547)

---

## User Story:
[User Story 3: Swap Target Configurations](../../Milestone1/UserStory3.md)


### Review
**Authors**: Carolina Ferreira (67804) and Gabriela Silva (67286) on 24/10/2025

This functionality would definitly give a better taste for pvp enthusiasts. It might need testing to ensure it doesn't make the defese overpowered too soon in the game. We should also consider a clear display to let players change targetting settings easily during gameplay.

Even though it looks like a great add to the game, we need to ensure it doesn't change highly the gameplay and the base goal of the game.

---
[comment]: <> (% ------------------ USE CASE ---------------------- %)

## Use case diagram
The use case diagram is in the following link.

## Use case textual description
[Swap Target Configurations Use Case Diagram textual description](./use-case-template.md)


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


**Answer**:
I understand what you mean. *Type* was changed to **Environment**, since it fits better the context of air units and ground units. Also, the unselect turret use case was added.

_The use case diagram and its textual description were modified according to this review._

---

[comment]: <> (% ------------------ IMPLEMENTATION DOC ---------------------- %)

## Implementation documentation
[Swap Target Configurations Implementation Documentation](./implementation-documentation.md)


#### Review
**Author** : Manuel Oliveira (68547), 04/12/2025 16:50

This may be me, but I couldn't quite understand the part where you explain the ``configure`` method in ``Turret``, maybe its poorly written. It could also be it is just a hard topic to grasp.

I think you could also add the absolut path to the package where most of your classes are, it isn't necessary but could ease more research on your work.

This may be me, but I couldn't quite understand the part where you explain the ``configure`` method in ``Turret``, maybe its poorly written. It could also be it is just a hard topic to grasp.

I think you could also add the absolut path to the package where most of your classes are, it isn't necessary but could ease more research on your work.

There are some minor spelling mistakes, like " it was added new configurations to it.".

**Answer**:
The ``configure()`` method is not directly explained. We have added some lines to the report so that it is more understable. 

_The implementation report was modified according to this review._



[comment]: <> (% ------------------ CLASS DIAGRAM ---------------------- %)

---
### Class diagrams
[Swap Target Configurations Class Diagram](./class-diagrams.md)


### Review
**Author** : Carolina Ferreira (67804), 04/12/2025 03:26

For starters, as defined between the group members, Gabriela and Dinis followed the color code, which shows a sense of knowledge of what are the group decisions.

With this in mind, I have nothing to point about the color chosen for each class, or the type. I would recommend, though, to add the name of the attribute used in all relations of type One-to-One, and One-to-Many.

In the **Usage of Table** there is a contradiction when saying "If one is `true`, the other is `false`." and right after it's written "Both are set to `false` on the `onConfigureClose()` method (...)", referring to the ``showMenus`` and ``showEnvs`` variables. I would recommend rephrasing this sentence in order to be more accurate and to avoid confusion.

In the **Usage of Reads and Write**, it's my opinion that the phrase "it was discovered several issues with the serialization of data" should be rewritten to "several issues with the serialization of data were discovered".

**Answer**:
We understand the confusion in *Usage of Table* and wrote it in a cleaner way. The attributes were added as suggested.

_The use class diagram and its report were modified according to this review._

[comment]: <> (% ------------------ SEQUENCE DIAGRAMS ---------------------- %)

---
### Sequence diagrams
[Swap Target Configurations Sequence Diagrams](./sequence-diagrams.md)


#### Review
**Author** : Diogo Antunes (67763), 29/11/2025 14:46

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


**Answer:**
We believe that, since every message and condition of the sequence diagram must be justified by code in the codebase, the methods should stay as abstract as the coding is, with a textual explanation (when necessary) in the report.

We also want to explain that when the first version of the sequence diagrams report was made, the boundary handling with buttons was then not understanded by us. After a deeper research, we managed to understand that "*mode button is pressed*" can be translated to the method ``isPressed()`` in the ``TextButton`` and ``ClickListener`` classes.

The *strict* fragment was ambiguous in the first version of the sequence diagrams report. After modifying it, we've understood that in the current version, it must have the *strict* fragment in both *Change Turret's Target Mode* and *Change Turret's Target Environment* sequence diagrams, since different and not directly related lifelines have behaviors that must be from a *strict order*.

The "*return*" in the return messages was explicitly written because in the *Visual Paradigm* application, we did not manage to hide the sequence numbers of the time line, which leads us to decide that a message with just a number would look weird.

_The sequence diagrams report was modified according to this review._


---


## Test specifications
[Swap Targeting Configurations Functionality Testing](./test-case-specifications.md)
### Review
**Author:** Gabriel Falcão (67775) (3/12/25) 10:53

Besides the typos I have described bellow, I like your test specification report, not only that I would say that the video is well done, and correctly represents what the tests ask for.

**Better Test Descriptions**:
- In test *Test Targeting Display* instead of "2. Hover over the turret" you should write "2. Hover the cursor over the turret"
- In test  *Test Targeting broad (using custom map for testing targeting)* you should elaborate on what these two turrets are **2. Navigate towards the first two turrets.**, I do understand that they are within view but the Player's view covers quite a lot of screen, maybe add some indicator on the map; maybe define their type, scatter, duo, salvo.
**Typos**:
- thee targeting -> the targeting
- through all of the targeting as if it -> through all of the targeting options as if it were
- The selected option is also has -> The selected option also has
	- Test Targeting broad (using any map), step 3
- 3. Press one of the options or option. -> 3. Press one of the options.
	- I'm not sure if you meant something else with this.

From what was stated above, I really like that you included the video, gives us some visuals of what is actually being testes, not just some script to be followed.

**Answer**

_The sequence diagrams report was modified according to this review._
