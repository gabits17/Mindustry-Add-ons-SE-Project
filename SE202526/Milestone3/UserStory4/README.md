# User story 4
Copy and Paste
## Author(s)
- Carolina Ferreira (67804)
- Manuel Oliveira (68547)

## Reviewer(s)
- Diogo Antunes (67763)
- Gabriel Matias (67775)
- Gabriela Silva (67286)

(*Please add the user story reviewer(s) here, one in each line, providing the authors' name and surname, along with their student number. In the reviews presented in this document, add the corresponding reviewers.*)
## User Story

As an EXPERIENCED USER, I want to have access to a traversable history of
copied block selections that I can pick to paste,
so that I don't need to save quick changes as schematics (saved block selections).

### Review
*(Please add your user story review here)*
## Use case diagram
![CopyPaste.jpg](./Assets/CopyPaste.jpg)

## Use case textual description

[Textual Description](US4%20Use%20Case%20Textual%20Description.md)

### Review
**Author**: Gabriela Silva, 04/12/2025, 14:20

**Most important:** Every use case main flow should start with "*The use case starts when...*"!

On **Select Tiles**:
- The extension points should not be only at the end of the use case, but in the middle were they can happen.

On **Choose Copy**:
- Its name (*Change Copy*) does not match its title; it should be *Choose Copy*.
- Main flow is not with sequence numbers but with sequence letters; "inner sequences" are also with sequence letters, which could be confusing. Keep the textual description consistent: display sequence numbers in the outter sequence and letters in the inner.
- Again, the extention points should be in the middle where they can happen, not only at the end.

I think it would be better to write "*Extension Points*" as the title for the extension points use cases, instead of "*Extends*".

On **Paste Copy**:
- As I said, every use case main flow should start with "*The use case starts when...*". Point *ii.* of the main flow has "*Then places the displayed schematic in the game world.*" Do not use "then", I recommend using "*The system/game places the displayed schematic in the game world.*". At least specify a subject that acts in the point, don't just write "then does this".

On **Scroll Through Copies**:
- If this use case is abstract (because in the template it is written with italic), it should be noted in the diagram. That is, have ``{abstract}`` on it or write it as italic as well.

On **Get Next Copy** and **Get Previous Copy**:
- Since this use cases are generalizations of the abstract *Scroll Through Copies*, both should have an additional field "**Specializes**: *Scroll through copies*".
- Me and my colleague modeled the use cases IDs with, for instance, "1.1" for one alternative flow for the use case with ID "1". Here, although *Get Next Copy* and *Get Previous Copy* are generalizations of *Scroll Through Copies*, I think it would be best if they had their own ID, as "EUC4" and "EUC5", to follow your style of identification.
- I don't understand what is "(01.) in the first point (i.) of both main flows.

I understand that in this report there are no alternative flows, because they were modeled as "``ifs``" on the use case's main flow. Despite understanding your intentions, I believe there should be an alternative flow named "No copied schematics".

## Implementation documentation

[Implementation Documentation](US4%20Implementation%20Documentation.md)

#### Review

**Author** : Diogo Antunes (67763), 3/12/2025 11:55

As with other user stories, the implementation is done with a focus on desktop, since it can be more readily tested.

The class diagram has already been given feedback in its own section, so I won't be commenting here, aside from a typo in "DesktoInput" instead of "Deskto**p**Input"

The ``getNext`` and ``getPrevious`` methods seem fine from a surface-level glance, the copyHist doesn't know about inputs, as should be the case,
to separate its functionality from the input control.

The use of an interface for the class isn't a necessity, but also does no harm. It allows, in example for other types of copyHistory, without introducing speculative generality.
In example, if in the future there was another type of copy history that would reset with a ``ResetEvent``, or with some other varying functionality that justified a separate class and
not merely a specialization, such a change would require modifying less variables.

The implementation summary covers the user story functionality well, and explains the rationale for the actions taken, so I've little to comment there.

### Class diagrams

[US4 Class Diagram](US4%20Class%20Diagrams.md)

### Review
**Author:** Gabriel Falcão (67775) (2/12/25) 16:53

First of all the embed you used, for the class diagrams document is wrong as it directs to a non existent file.

#### Problems found
The use arrow will be talked about below, but you should have an arrow or an attribute in `DesktopInput` that shows it possesses an instance of `CopyHist` as it does not directly depend on the class itself, but the interface; this should also be changed regarding the use arrow.
The same can be said about the `CopyHistClass` have some sort of arrow, aggregation, from it to `Schematics`, and name that arrow *history* as it is the name of the attribute in the class itself.
`KeyBind` is not a nested class of `Binding`, if you want to keep it in, just say that `Binding` is an aggregation of `KeyBinds`, all it has is data.
`KeyBind` is `KeyBind` not `KeyBinds`
`Schematic` is either **<\<interface>>** or **<\<auxiliary>>** I don't believe it can be both.
`DesktopInput` should be classified as a **<\<control>>**.
`UI` should be stereotyped as a **<\<boundary>>**
Maybe use the `Input` in `Core` as another **<\<boundary>>**, this is a personal opinion as I believe it would make the sequence diagram more readable.

In my personal opinion, and Diogo's, static/constants should not be shown.
##### <\<use>>
All of the usage arrow dependencies are flipped, as `Schematic` does not use `CopyHistClass`, `ArrayList` does not use `CopyHistClass`, `CopyHistClass` does not use `DesktopInput`, `Binding` does not use `DesktopInput`, `Planet` does not use `DesktopInput`, `UI` does not use `DesktopInput`.
They are in the correct places but all should be flipped around.
##### Implementation
The arrow used for an interface implementation is dashed and non solid, thus you should change the arrow between `CopyHistClass` and `CopyHist` and the arrow between `ArrayList` and `List`.

#### Author's note
What was said in this review was taken into account and the diagram changed accordingly.


#### Further Review
The diagram is much better, the implementation arrows are ok, but they should be dashed.
I know I didn't say this before and maybe the deadline is a little to close to change, but `DesktopInput` instead of a use to `CopyHist`, you should you a attribute as an arrow, instead of an attribute in `DesktopInput`, the same goes for planet.
The same logic for `CopyHistClass` and its `Schematic`s maybe use an aggregation, instead of an use.

Besides what was stated above, the diagram looks fine.
### Sequence diagrams
[US4 Sequence Diagrams](US4%20Sequence%20Diagrams.md)
#### Review
**Author**: Gabriela Silva (67286) 04/12 12:30

More important: Include ``%20`` between spaces in your assets' links or change the name of the files so it does not include spaces. The figures does not appear in every IDEA. 

Overall, I've understand the general behavior represented in all of these sequence diagrams. Most of the things I've point out are related to return messages, even if they are void. Some guard-conditions could be textual explained so it is cleaner to understand what they mean. The report should be more consistent in the key binds that call the start of the behavior and the return messages for every ``showInfoFade()`` message from the ``UI`` should be equal: either passage of time messages or just returns.

On **Choosy copy**:
- The message "*5. showInfoFade(...)*" does not have a return message.
- The message "*10. useSchematic(current)*" does not have a return message.
- The message "*12. showInfoFade(...)*" returns to the wrong lifeline. It should return to the ``DesktopInput`` lifeline.
- After and outside the alternative fragment, the messages 2 and 1 should return to the player.
- Personally, while working on the sequence diagrams for the third user story, me and my colleague considered the ``UI`` class as z *control* instead of *boundary*. The reason I am saying this is because the behavior we've modeled that envolves the ``UI`` class is similar, showing fade info messages. The main difference is that the ``UI`` here is used by the ``DesktopInput`` class, which is a *control* class, while in the third user story it is used by an entity class ``TurretBuild``. Maybe that is why it is different, but I'm not sure. I believe that because of this differences, it is well designed.
- I understand ``axisTap`` is checking if the mouse scroll is going in one direction or other. I just think it would be good to mentioned that it is never zero or that when it is, nothing happens. I also think that explaining what this guard condition do would enrich the report.
	
On **Copy Schematic**:
- The message "*3. copy(lastSchematic)*" does not have a return message. I'm not sure if it would be returned right away or only after the events that occur after.
- The message "*8. add(0, scheme)*" does not have a return message
- The guard-condition *[!contains]* is not well formatted, it should be below "opt". However, it is clear that it is the guard-condition for that opt fragment.
- I believe the ``ArrayList`` lifeline should be continuous from the 6th to the 10th message and the ``CopyHistClass`` should as well be continuous from the 3rd to the 10th message.
- The 11th and 13th messages should return to the ``DesktopInput`` lifeline first, then return to the ``Input``, then return to the Player.
- Be consistent between the diagrams. If you choose to represent the calls as "Keybind : Paste" in the first diagram, do the same in this diagram, instead of just "Copy Schematic". Also, if you choose to represent the passage of time for the showInfoFade messages, do it for every message or don't do for any.
- In the textual description, I believe it should be written "...the ``Nothing to copy!`` **text** is displayed via ``UI``".
	
	
On **Get Next** and **Get Previous**:
- Although it is clearly understandable for code developers, mentioning what is the ``size`` field in the guard condition and what does each guard condition check, would enrich the report.
	
On **Paste Copy** and **Select Tiles**:
- Even if it returns void, there should be a return message from the ``DesktopInput`` lifeline, then to the ``Input`` and then to the player.
	
Also in **Select Tiles**:
- The message "*3. useSchematic(lastSchematic)*" should have a return message
- From what I understand, the reference ``create(schemX, schemY, rawCursorX, rawCursorY)`` is some behavior that happens that is not that relevant to the details of this sequence diagram, but it is important to know that it happens in that timeline. It should be explained in the textual description that it was not designed for that reason.



## Test specifications
[US4 Test Case Specifications](US4%20Test%20Case%20Specification.md)
### Review
**Author:** Gabriel Falcão (67775) (2/12/25) 16:53

This is already marked for review, but as of review, in the master branch:
- Embed to use case textual description points to US2's textual description instead of US4's
- Tests: `copiesValidSelectionTest`, `getNextTest`, `getPreviousTest`, `pasteLoopTest`, `copyLimitsTest`, run with errors.

I´m guessing you guys tagged this for review accidentally, I'll review again when its done.
#### Further Review
I believe you made the right decision to change to "guided" tests, not only are they easier to understand. The only wrong things I found that were wrong is the link to the user story and "Instead, we preferred testing the code that we made. The tests bellow are implemented in **tests/src/test/java/ApplicationTests.java**." since your tests aren't unit tests now, this should be changed, though I believe this as already been changed in the newer version of the report.