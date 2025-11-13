# Use Case Diagram Review
## Reviewers
- Manel Oliveira (68547)

## Change log
- Manel Oliveira (68547) (09/11/2025 23:31)
- Diogo Antunes (67763) (11/11/2025 23:25)
- Manel Oliveira (68547) (12/11/2025 18:38)
- Diogo Antunes (67763) (13/11/2025 17:29)

# Review
Because the images appear fully black to mee I was not able to evaluate or review anything too critical to this use case. I also tried opening the images standalone, and they also appeared fully black.
I also encountered this issue, and solved it by exporting the svg files to png and using that version instead. That said the problem might be with my IDE as I'm using IntelliJ.

### Review Response - Diogo Antunes
Viewing the svg on Github should resolve these problems.


# Review

The Diagram seems generally fine, and all the uses cases, and how they are related, make a lot of sense to me.

The only thing I can question is in the relation between a **Player** and **Join external game**, how can a **Player** join multiple games, I was of the impression that to join a different game a Player had to quit their current one. Maybe it was supposed to be the other way around.

Despite being extra, I also checked your diagram on Mod Management and also seems good to me.

### Review Response - Diogo Antunes
I'm not entirely sure I understand, but A player can't join multiple games simultaneously. As mentioned in the use case, one of the players is the one joining the external game, the others are other players
that the user interacts with.
