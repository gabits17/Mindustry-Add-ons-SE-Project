# Use Case Diagram Review
## Reviewers
- Gabriel Falcão (67775)

## Change log
- Gabriel Falcão (67775) (07/11/2025 19:14)

# Review
While describing the use case diagram, instead of saying it "and deepens on two of those use cases",
you could just say "and further details two of the subsystems involved with the GameSettings System"

Since **player** seems to be the primary actor in all the use cases described, to make
things a little more compact you could just add a section bellow the use cases section
where you describe the **primary** actor (for further reference you could look at mine
where the same thing happened, Map Maker is the only actor and is always the primary actor [UseCaseDiagram.md](../GabrielFalcao/UseCaseDiagram.md)).

The diagram looks good, im just not sure if the Manage Game Data should be a super class to the options of Managing the Game Data,
as it does not refer to some sort of action or interface common between them, but only a menu that the player may enter.

I don't have much more to say, I was doubtful of the Reset to Defaults use case, but when looking at the code it does indeed seem to be similar
between the different settings.