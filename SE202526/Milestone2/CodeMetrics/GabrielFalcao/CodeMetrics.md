# Code metrics Report
## Author
- Gabriel Falcao (67775)
# Dependency Metrics

## Analysis of collected metrics
The dependency metrics reveal a great deal about how this project is structured and confirm some of the suspicions and code smells the group identified in the codebase. The most notable outlier in the dataset ([[CodeMetrics.xlsx]]) is the `Vars.java` class, which is used extensively throughout the project to provide and store values for many other classes. Both **Dpt** and **Dcy** indicate several issues with this class—specifically, that it creates excessive **coupling** and represents a clear case of a **Large Class**.  

Unfortunately, the **Cyclic**, **Dcy\***, and **Dpt\*** metrics don’t provide much useful data, as most of their values are extremely high. This is primarily because many classes import `Vars.java`, which itself depends on numerous other classes—creating transitive dependencies across much of the codebase.

The observations above can also be corroborated by examining the **PDcy** and **PDpt** metrics (particularly **PDpt**) provided in the dataset. These values show that the package containing `Vars` (`mindustry`) once again exhibits very high dependency values, indicating that this package depends on far too many others.

Several other cases can also be identified, such as `Blocks.java`, `Tile.java`, `UnlockableContent.java`, and others, which can be easily found by sorting the table by either the **Dpt** or **Dcy** metrics. Some of these high values are expected—such as in `UnlockableContent.java`, which is naturally used by many items and buildings, throughout the game.

## Relevant graphs
Relevant graphs for the collected metrics are shown in the provided data sheet, so that it may be easier to visualise values while sorting them.
Although some relevant ones are:
### Dpt Metric
Bar Chart sorted by highest **Dpt** metric value.
Here we see that Vars is the class most dependent on others.

![DptSortHighLow.png](Data/DptSortHighLow.png)
### PDpt Metric
Bar Chart sorted by highest **PDpt** metric value.
Here we see that Vars is once again on top.

![PDptSortHighLow.png](Data/PDptSortHighLow.png)

