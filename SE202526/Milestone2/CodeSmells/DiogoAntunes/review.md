# Code Smell Review
## Reviewers
- Gabriel Matias Falcão (67775)
- Gabriela Silva (67286)
- #REVIEWER NAME (#STUDENT ID)

# Change Log
- Gabriel Matias Falcão (67775) -
- Gabriela Silva (67286) - 05/11/2025 12:15

# Review

## Data Class Review
- Gabriel Matias Falcão 67775

From what I read, this really looks like a simple case of a data class, and I totally agree with the proposed solution
of creating a record to pass these values around.

The only wrong thing I found in the review is a minor spelling mistake in the first paragraph, "do" should be "due".
  

## Divergent Class Review

## Switch Statements Review
- Gabriela Silva 67286

The code smell detected is well analysed.

This code smell here violates both the **Open/Close** and the **Single Responsibility** design principles. If a new ``TileOp`` is to be added, it is necessary to change the ``DrawOperation`` class to extend a new switch case in both methods ``getTile()`` and ``setTile()``.

I would say the proposed solution is well designed. From what I undertsood, it mostly follows the **Strategy** GoF design pattern. However, I believe it would be better if some *code examples* for the solution were pointed out, in order to make it more clear.