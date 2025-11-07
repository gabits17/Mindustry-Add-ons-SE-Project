# Code Smell Review
## Reviewers
- Manel Oliveira (68547)
- Carolina Ferreira (67804)

## Change Log
- Manel Oliveira 68547 (05/11/2025 23:25)
- Carolina Ferreira 67804 (06/11/2025 21:14)
- Dinis Neves 68130 (07/11/2025 10:27)


# Reviews

## Review of Duplicated Code
#### Author
- Manel Oliveira 68547

#### Review
The attribution of **Duplicated Code** in this case is accurate, the block of code selected is clearly repeated, and seems to behave the same in all its occurrences. 
Even tho the quantity of repeated section that ends up being refactored for each new function is quite small, the code still benefits from this, and it should be done.


## Review of Divergent Class
#### Author
- Carolina Ferreira 67804

#### Review
We can define a Divergent Class as one that assumes multiple functionalities or suffers loads of transformations. With that said, I agree with Gabriela about the presence of this code smell in the ```Map``` class, since this class takes care of many aspects it shouldn't: Game rule updates,
UI/editor updates, Steam Workshop integration, etc...

Given this, and having Gabriela's solution in mind, I agree with the advised implementations.

## Review of Long Parameter List
#### Author
- Dinis Neves 68130

#### Review
I most definitely agree with the classification of this long parameter list code smell. A function with 13 parameters that are similar, and contain a similar naming scheme is a Long Parameter List. The proposed solution and its concerns I am would also be on the same page. Still it is better than the 13 parameters.
