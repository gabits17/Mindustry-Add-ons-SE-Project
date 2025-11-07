# Code Metrics Report
## Author
- Diogo Antunes (67763)
# Metrics Set - MOOD
## Metrics Source
To avoid any changes made by added files and code modification undertaken by project members, data was collected from
a clone of the last version of the Mindustry Repository before the project management files were added:
![img.png](img.png)
The changes I noticed were mostly small percentage amounts, but for accuracy I still felt it was better to do it this way.

The metrics were collected at a project level using MetricsReloaded, giving the following result: 

| MOOD metrics | AHF | AIF | CF | MHF | MIF | PF |
| --- | --- |---------| --- | --- | --- | --- |
| project | 24.27% |  95.45% | 2.60% | 22.14% | 93.10% | 1.84% |

To learn about MOOD metrics, among other sites, I read part of the following paper:
http://ctp.di.fct.unl.pt/~mgoul/papers/Metrics/TOOLS96.pdf

MOOD metrics are supposed to be applied at system or sub-system levels. The above paper discussed results of the metrics set
applied to Eiffel libraries.

However, in this ES project, there is effectively only one system that can be reliably analysed, which is the whole project.
I made an attempt to break down subsystems as follows. The data is in the data for consultation despite resulting in a clear misuse of metrics scope:

| MOOD metrics             | AHF    | AIF    | CF      | MHF      | MIF      | PF       |
|--------------------------|--------|--------|---------|----------|----------|----------|
| core/src/mindustry       | 23.53% | 95.50% | 2.74%   | 20.74%   | 93.32%   | 1.77%    |
| core/src/mindustry/ai   | 38.01% | 55.57% | 83.74%  | 24.22%   | 76.55%   | 85.00%   |
| core/src/mindustry/async | 31.58% | 0.00%  | 400.00% | 3.70% | 6.90% | 91.67%   |
| desktop/src/mindustry.deskotop | 38.51% | 72.18% | 493.33% | 18.58% | 35.65% | 100.00%  |
| server                  | 52.63% | 0.00%  | 5600%   | 15.38%   | 65.79%   | 100.00%  |

As such, seen that the plugin doesn't seem to generate data per class, given this possibility of invalid scoping (since the metric is usually applied system level),
I have no graphs to present alongside my analysis.

## Contemplating the results produced by a bad scope - Analysing Coupling Factor (CF)

The values above 100%, such as 400.00%, 493.00%, and 5600.00% for the Coupling Factor (CF) clearly tells me
that the coupling occurs cross-package, likely via the globally used Vars class that stores static-access public variables.

This gives me less confidence in attempting to use different metrics for the same row with bad values, as despite the overall value going less than 100%,
the denominator used in this metric's formula is still calculated on a package level, while the numerator can count cross-package relationships.

Where according to "The Design of Eiffel Programs: Quantitative Evaluation Using the MOOD Metrics" by Fernando Brito e Abreu, Rita Esteves and Miguel Goulão,
the formula for the coupling factor is defined as:

$ CF = Actual number of couplings/ Maximum possible number of couplings $

With the denominator as *"the maximum number of non-inheritance couplings in a system with TC classes"* (Brito e Abreu et al., 1996, p. 9):

$ TC^2 - TC $


Even in "core/src/mindustry/ai", despite no "illegal" values, the package still has connections to the prior mentioned Vars class, which is merely an example of how the
metrics are still being used on the wrong scope, despite no clear indication in the resulting numbers (Coupling Factor would still be affected by
a class beyond the package).

I don't feel these more unreliable values should be analysed for the sake of comparison, leaving me effectively with only the project metrics set to analyse.
On the other hand, the illegal values in and of themselves tell us about cross-package coupling.

## Analysing metrics

To obtain potential points of comparison, I've added two rows to the table, including the 90% confidence interval indicated
by the analysis of the Eiffel Libraries (Brito e Abreu et al. 1996):

| MOOD metrics | AHF    | AIF    | CF    | MHF   | MIF     | PF |
| --- |--------|--------|-------|-------|---------| --- |
| project | 24.27% | 95.45% | 2.60% | 22.14% | 93.10%  | 1.84% |
| lower-limit | 19.2%  | 63.3%  | 1.3% | 15.4% | 60.6% | 5.3% |
| upper-limit | 35.5%  | 81.5%  | 5.5%  | 38.7% | 77.1% | 10.8% |

### Analysing the Coupling Factor (CF)
Good design means strongly cohesive, weakly couples, therefore a low value is usually better.
The calculated value of 2.60% falls within the confidence interval, suggesting a good value compare to those calculated for the Eiffel libraries.
On the other hand, as seen in the wrongly-scoped table, values can be significantly higher for a given class.

Classes that lower this value are those with narrow focus (which is what is desired), such as the mindustry.entities package
in core/src/mindustry with a Coupling Factor of 9.76% (within its own set of classes). 
In example:
- 15 classes extend the abstract classes Ability in the abilities package within entities, 
- 26 classes extend BulletType in the bullet package of the same parent package.

As such, these classes built en-mass to implement slight variations of some in-game functionality, when brought to project-scale, will further
decrease the overall average (since on a project scale, the denominator should be larger, but, per class, the number of couplings numerator should remain the same).

While the overall Coupling Factor is low due to the sheer number of similar narrow focus classes, the main logic of the game happens in several classes with strong coupling
due to the use of message chains and system convention of using public attributes where possible.

Additionally, from my understanding of the metric, the existence of the Vars allows access to static public variables from anywhere in the project. This indirect access
of variables wouldn't contribute in the same way towards perceived coupling in MOOD. In this specific instance, I believe the 
"Access to Foreign Data" metric in the Lanza-Marinescu Metrics Set from MetricsTree is more helpful in identifying indirect connections between classes.


### Analysing the Attribute Hiding Factor (AHF)

This metric should be as high as possible to guarantee encapsulation of class functionality. The article that uses MOOD to evaluate the Eifel libraries suggests that
"For attributes (AHF) we want this mechanism to be used as much as possible." (Brito e Abreu et al., 1996, p. 13). Despite being within the 90% confidence interval within the Eifel libraries example,
the value of 24.27% is still alarming, as it denotes public access to attributes, which can and do break encapsulation.

The explanation for this metric is present in the contribution rules markdown "CONTRIBUTING.md" within the project directory that specifically points out that
"public or protected fields should suffice for most things". As such, the decision that leads to this metric result is intentional, and the result of it can be seen all across the code.
Attributes are public, and getters are to be avoided unless absolutely necessary according to this same document.

This results in examples such as "control.saves.getCurrent().save();" in line 169 of PauseDialog.java, where control isn't a local variable but instead a
public, static attribute of the class Vars. It is an approach that allows code to change faster, instead of creating many intermediate methods that delegate function
to the next class. However, due to the presence of import * statements in classes, the code can quickly become confusing when trying to understand if a variable in a snippet is local, class-bound, or a global.

On the other hand, subsystems outside of the main gameplay loop have better encapsulation, such as the /server module with a factor of 52.63%
of Attributes hidden. This is a result of being a more isolated functionality, that performs services related to multiplayer functionality instead of storing data variables other classes may need to access.

To conclude, despite the low metric score being a result of a pattern enforced by the programmer to reduce simple getter/setter methods in classes with dozens of attributes,
the decision nonetheless breaks encapsulation and makes extracting meaning out of arbitrary snippets of code more difficult.

### Analysing the Method Inheritance Factor MIF

## Bibliography
Brito e Abreu, F., Goulão, M, Esteves, R.(1996).The Design of Eiffel Programs: Quantitative Evaluation Using the MOOD Metrics. _Proceedings of TOOLS'96, 9 - 13_,
http://ctp.di.fct.unl.pt/~mgoul/papers/Metrics/TOOLS96.pdf

