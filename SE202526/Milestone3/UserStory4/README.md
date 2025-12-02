# User story 4
Copy and Paste
## Author(s)
- Carolina Ferreira (67804)
- Manuel Oliveira (68547)

## Reviewer(s)

- 

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
**Author**: Added the implementation documentation
## Implementation documentation

[Textual Description](US2%20Use%20Case%20Textual%20Description.md)

#### Review
*(Please add your implementation summary review here)*
### Class diagrams

[US4ClassDiagrams.md](US4%20Use%20Case%20Diagrams.md)

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
### Sequence diagrams
(*Sequence diagrams and their discussion in natural language.*)
#### Review
*(Please add your sequence diagram review here)*
## Test specifications
(*Test cases specification and pointers to their implementation, where adequate.*)
### Review
*(Please add your test specification review here)*
