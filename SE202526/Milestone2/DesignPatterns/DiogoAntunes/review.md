# Design Pattern Review
## Reviewers
- Gabriel Matias (67775)
- Gabriela Silva (67286)
- #REVIEWER NAME (#STUDENT ID)

## Change log
- Gabriel Matias (67775) 03/11/2025
- Gabriela Silva (67286) 04/11/2025 12:10

# Review

## Review of Memento Design Pattern
- Gabriel Matias (67775)

The diagram has dependency arrows, with a \<\<use\>\> attribute. I believe those are
only used in a _Package Diagram_, _Class Diagrams_ do indeed have dependency arrows,
but I could not find any source that used them with \<\<use\>\>. Maybe I'm wrong about this.

About the pattern, it was expected that a game would have at least an implementation of **Memento**, even if it is not entirely correct due to encapsulation issues.

Apart from the dependency arrow "\<\<use\>\>", I don't believe there is anything else to point out. The report is well structured and easy to read.

## Review of Composite Design Pattern
- Gabriela Silva (67286)

The lack of interfaces in the source code makes several patterns hard to detect, but this one is a great example of the *Composite* pattern. 

The hierarchy is clear to understand, since ``DrawPart`` is an abstract class and every class that extends it are either the *Composite* role or the *Leaf* role, which are enough to justify the pattern. This way, it can be possible to treat individual parts (``EffectSpawnerPart``, ``FlarePart``, ``HaloPart``, ``HoverPart``, etc) and groups of parts (``RegionPart``) uniformly.

For the purpose of the report, while the technical content is well written and pointed out, adopting a clearer format would enhance readability. I recomend reviewing the *enters* in the mid of paragraphs and perhaps some bolds to emphasize important details.
