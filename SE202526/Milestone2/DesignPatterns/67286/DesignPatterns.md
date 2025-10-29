# Gof Design Patterns on Mindustry
*Identified by student 67286*


This was my first analysis, so this document is still a **draft**.

One design pattern that i might have identified is ``Composite``.

I was analizying the ``Universe`` class in ``core/src/game/Universe`` and found the class **``Planet``** a potential ``Composite`` implementation: each ``Planet`` object has children and each children is also a ``Planet`` object. 

**``Planet``** can surely be the ``component`` role of the pattern, **its children** can be the ``composite`` role and **planets with no children** might be the ``leaf`` role.
