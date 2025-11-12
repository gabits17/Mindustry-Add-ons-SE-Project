# Use Case Diagram Review
## Reviewers
- Diogo Antunes (67763)

## Change log
- Diogo Antunes (67763) (20:05)

# Review
The dependency arrow between Inspect Core Database and Save Progress isn't annotated. It doesn't seem to follow the style we learned in class.

It might also be useful to annotate what extension point each extending case originates at. The lesson uses rectangular note shapes for this purpose.

I feel like "Play Mission on Sector" should be an abstract use case, since the Player must necessarily launch(start) or load the mission before "playing" the mission.

The Progression Display makes sense as a sub-system tied to campaign progression.

I think Autosave should be a specialization of Save Progress or there should be some "save data" use case that both include. Since AutoSave doesn't actually require used input, it might not make sense for it to extend Save Progress, which interacts with the player.

The descriptions are helpful. I'm not sure they're all written in the tense of the typical format though.

# Typos
Use case 6 - Descritpion 