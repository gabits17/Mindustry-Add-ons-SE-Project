# Code Smell Review
## Reviewers
- Diogo Antunes (67763)
- #REVIEWR NAME (#STUDENT ID)

# Review
- Diogo Antunes (67763)
### Review of Shotgun Surgery (03/11/2025)
Since all these classes extend the same class to provide some differing, but often overlapping effect, I'm not too sure this is similar
to the traditional example of shotgun surgery.

At least in this example, the separation of code seems somewhat justified as, 
while similar, the for loops used and their inner logic differ enough that it wouldn't make sense to create templates.
The logic is already abstracted by using a bullet handler, so I feel further abstracting the logic might be difficult or read to unnecessary generalization.

### Commenting on the proposed solution
Merging the classes and using a switch statement would create a switch statements code smell, and the current implementation
is actually the solution to the code smell created by the proposed change.

As such, I don't necessarily find it to be a helpful alteration.

### Minor spelling mistakes and other things
"ays" instead of "ways"

The order of image labels is wrong:
The first image currently shows ShootBarrel, not ShootAlternate.
The second shows ShootHelix, not ShootBarrel.
The third shows ShootMulti, not ShootHelix.
The fourth shows the BulletHandler interface within ShootPattern, not ShootMulti.
The fifth shows ShootSine, not ShootPattern.
The sixth shows ShootSpread, not ShootSine.
The seventh shows ShootSummon, not ShootSpread.
