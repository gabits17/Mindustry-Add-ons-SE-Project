# User story 2

## Author(s)

- Diogo Antunes (67763)

## Reviewer(s)

- Gabriel Matias Falcão (67775)
- Manuel Oliveira (68547)

### User story

As a USER WITH AN IN-GAME BASE THAT COVERS A WIDE AREA, 
I want to be instantly informed of the location of any leaks of 
liquid resources within my base, with their locations clearly highlighted 
on the minimap in a different colour the moment they occur, 
so that I don't have to identify them manually, 
reducing time wasted on looking for the source of resource loss
in a busy game environment.
#### Notes

- The Game already checks for leaking pipes (plays a leaking animation with a puddle).

- When a block that carries a liquid is identified as leaking, display the pixel representing the block in the minimap with a unique colour.

- Only identify leaks for blocks placed by the player.

- Inform the player of the leak location via warning message (if non-obstructive) and a dotted circle centered on the leak.
### Review

Gabriel Matias Falcão 67775

*This could be developed further as a sort of alert system, using the chat or some user interface, in which the player could sort between alerts (destroyed units, pipes, blocks...)*

Manuel Oliveira 68547

*The feature looks worth implementing if not too worksome for our timeframe.*

#### Change Log

*created - *

*reviewed by 68547 - 24/10/2025*

*reviewed by 67775 - 24/10/2025*

*modified by 67763: modified user story and notes to increase clarity - 30/10/2025*