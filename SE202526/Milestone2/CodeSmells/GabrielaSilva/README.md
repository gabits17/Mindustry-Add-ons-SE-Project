# Code Smell Report
## Author
- Gabriela Silva (#STUDENT ID)
# Code Smells
- Attach a picture of the block of code with the smell
- Reference the file in which the smell was found
- Possible solutions for the code smell you found
## 1. Data Clumps

It is evident that in the ``EntityGroup`` class (``core/src/mindustry/entities``), the *Data Clump* code smell is present in the methods ``intersect()`` and ``resize()``:

````Java
public Seq<T> intersect(float x, float y, float width, float height){
    intersectArray.clear();
    if(isEmpty()) return intersectArray;
    tree.intersect(intersectRect.set(x, y, width, height), intersectArray);
    return intersectArray;
}
// (...)
public void resize(float x, float y, float w, float h){
    if(tree != null){
        tree = new QuadTree<>(new Rect(x, y, w, h));
    }
}
````

The four *float* parameters - ``float x``, ``float y``, ``float w/width``, ``float h/height`` - are often together (since there's two more ``intersect()`` implementations with the same four *floats*, there is more evidence of their togetherness).

The code smell is strongly identified here, since the class itself already knows about the correct abstraction:

````Java
private final Rect viewport = new Rect();
private final Rect intersectRect = new Rect();
````
This way, it is inconsistently implementing logic for a concept that *is already known as an abstraction* in the class' code base.

#### How to fix?

Instead of repeating the same parameters over and over, it should be refactored: change the method signature to accept a single ``Rect`` object. This way, the method's signature becomes shorter (thus easier to read and comprehend) and it is safer, since it is impossible to pass the parameters unordered.

## (Code Smell 2 Name)
## (Code Smell 3 Name)
