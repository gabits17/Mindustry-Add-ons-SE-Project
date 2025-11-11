# Code Smell Review
## Reviewers
- Carolina Ferreira (67804)

# Change Log
- Carolina Ferreira (67804) (07/11/2025)
- Diogo Antunes (67763) (11/11/2025) 23:21

# Review

First of all, I want to say that using the base code, as delivered in the beginning of the project was very smart and crucial to deliver the best report possible. I want to congratulate you for going out of your way and search even more about this Metrics.

With that being said, I agree that this plugin is prone to some error and doesn't always give the best consistency, even though, when I ran it to calculate this metrics, the values I received in my machine are the same Diogo has in the presented table.

This report shows a good understanding of the matter in question and the "Contemplating the results produced by a bad scope - Analysing Coupling Factor (CF)" was an aspect I didn't think of but a great add-on to this report. I think some "opinions" could be more assertive and the trouble spots have their own category, but that is not crucial and more of a possible improvement

I have no further to say, just compliment my peer for his great work and his search.

### Review Response - Diogo Antunes
I'd just like to mention that the source of the bad values weren't plugin errors, but instead due to the scope constraint
of the metrics set itself. Good subsystem metric data can be found, but aren't guaranteed especially when there are cross-package dependencies.

Also, I feel these metrics by themselves serve as an overview instead of identifying trouble spots in particular regions,
which is why the examples I give aren't actually trouble spots indicated by the metric itself but merely particular instances
I remembered from doing the code smells.