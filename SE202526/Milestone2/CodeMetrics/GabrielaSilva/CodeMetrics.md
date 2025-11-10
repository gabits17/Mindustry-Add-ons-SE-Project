# Code metrics Report
## Author
- Gabriela Silva (67286)

# Complexity Metrics Set
This metrics set is composed by four essential method metrics:
- **Cognitive Complexity** (*CogC*)
- **Cyclomatic Complexity** (*v(G)*)
- **Design Complexity** (i*v(G)*)
- **Essential Cyclomatic Complexity** (e*v(G)*)

This report will analyse the project by the metrics **Cognitive** and **Cyclomatic** complexities.

---

## CogC Metric

The **Cognitive Complexity** metric accounts for the *human cognitive load* while exploring the logic of a program. The mental effort for understanding and debugging are evaluated here.

It is obvious that a *Long Method* or a *Large Class* elevates the cognitive effort of a human needed to understand it. That's why they are considered *code smells*.

The ``build()`` method in ``FragmentPlacement`` class (``core/src/mindustry/ui/fragments``) is the method with the **highest **CogC**** metric value in the whole project code base: **461**. Since the *MetricsReloaded* plugin gives a warning for when this metric is greater than 15, it is pretty clear why this is an extreme case. A **CogC** value this high indicates that the method is extremely difficult for a human to understand, safely modify, refactor or debug. **This method should be refactored massively.**

---

## *v(G)* Metric

The **Cyclomatic Complexity** metric reflects the number of independent paths that a source code goes through. If *v(G)* has a higher value, it means the code has more execution paths and results in higher complexity. This behaviour is prone to errors to test and maintain.

The formula to calculate *v(G)* is the following:
**$$v(G) = E - N + 2P$$**
Where:
- $E$ is the number of edges in the program's control-flow graph
- $N$ is the number of nodes in the program's control flow graph
- $P$ is the number of connected components in the program's control flow graph

This value translates the minimum number of paths that are needed to test to make sure that each *decision point* is executed at least one time.

The most common statements that elevates this complexity are ``if``, ``while``, ``for`` and ``switch``, since they most times (if not always) introduce a *new path* in the flow of the program.

When analysing the code base of this project, the method that has the higher *v(G)* value is ``registerEvents()`` in ``core/src/mindustry/service``. By understanding this metric, it is clear why does this happen: *it has **80** (!) ``if`` statements*. 

As already stated, to solutionate this problem, the method should be refactored *massively* too.

This methods are some examples in a lot of existing *high CogC* and *high v(G)* methods. Looking at package level, the package with the highest *average cyclomatic complexity* (*v(G)avg*) is ``core/src/mindustry/maps/planet``. It has an average of 10.58.

When exporting the data from this package, excel suggested to show the data via a *Parelo Chart*. A Parelo Chart is most known for the 80/20 Rule (*The Pareto Principle*). It states that roughly 80% of effects result from 20% of causes.

![parelo-chart1](Assets/v(G)-planets-pareto-chart.png)

 In this context, we can conclude that **11% of the methods in the package are the *extreme causes* of its elevated *v(G)avg* metric value** (11% of the methods in this package have a *v(G)* value bigger than 10). To be more precise, the ``generate()`` methods in each class are the ones where *v(G)* (and *CogC*) is really high (since three of them are top-three).


However, still in package level, when analysing with respect to the *total cyclomatic complexity* (*v(G)tot*), the highest value belongs to ``core/src/mindustry/entities/comp``. Its total cyclomatic complexity is 2049.

![parelo-chart2](Assets/v(G)-comp-pareto.png)

Here, **only 6% of the methods in the package are the extreme causes of its total *v(G)* metric value** (again, 6% of the methods in this package have a *v(G)* value higher than 10). To be more precise, the method ``update()`` in the ``UnitComp`` class has the highest value (71), containing 38 ``if`` statements, but more methods are contributing to the total value.

The main difference here is that the ``comp`` package handles components behavioral complexity, spreading accross many components, while the ``maps`` one focus on generators algorithmic complexity, having massive heavy methods (``generate()`` as pointed out). 

As stated when analysing design patterns and code smells by the entire group work, some design principles where not respected in every spot in the code, which can happen since we're talking about an entire game's code logic. We, as a group, can be considered a good example of how a high ***CogC*** value can be exaustive, since we had to work and analyse this code base.

The main solution for these values to decrease is to **refactor** the code blocks that increase the human cognitive effort to understand and work on. To do that, developers should follow some design principles, emphasizing the *SRP* - **Single Responsibility Principle**.

---

To produce this report and analyse this data, I learned from these two web materials that explain Cognitive Complexity and Cyclomatic Complexity:
- https://getdx.com/blog/cognitive-complexity/
- https://www.sonarsource.com/resources/library/cyclomatic-complexity/