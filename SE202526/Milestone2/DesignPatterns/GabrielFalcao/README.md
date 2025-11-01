# Design Pattern Report
## Author
- Gabriel Falcão (67775)
# Design Patterns
- Attach a picture of the block of code with the Design Pattern
- Reference the file in which the Pattern was found
- Possible improvements you found
- Possible blocks of code you believe a Pattern could be implemented
    - Picture of said blocks

## Command Pattern
The **Command** pattern isn’t immediately evident here, as its implementation differs somewhat from the version presented in class. Its presence is mainly suggested by the undo and redo functionality, as well as the `OperationStack` (`core/src/mindustry/editor`) class, which stores `DrawOperation`(`core/src/mindustry/editor`)   objects. However, it could also be mistaken for the **Memento** pattern—though that doesn’t seem to be the case, since the state of the `MapEditor` (`core/src/mindustry/editor`) itself is not stored.

Another unusual aspect of this implementation is that the **MapEditor** class appears to serve as both the _Invoker_ and the _Receiver_. Although some operations are indeed performed by external elements such as `EditorTile`, the `MapEditor` is responsible for invoking operations through the `addTileOp` method, which adds operations to the current one. It also applies the operation’s changes via the `updateStatic` (`EditorRender` this class could also be considered a *Receiver*) method (acting as the Invoker) directly on itself (acting as the Receiver). Finally, the resulting operation is added to the `OperationStack` through the `flushOp` method, which is invoked either by the `MapEditor` itself (in the seemingly unused `addClifs` method) or by the `MapView`, which appears to handle editor input.

The `MapEditor` uses the `undo`, `canUndo`, `redo` and `canRedo` methods to control the flow of the operations, giving the user the ability to *undo* or *redo* operations made in the editor.

It’s worth noting that the `EditorRender` class was not discussed here, as most of the operations relevant to the pattern occur within `MapEditor`, `OperationStack`, or `DrawOperation`. Though `EditorRender` class also functions as a Receiver of operations through the `updateStatic` method.
### Relevant Code Snippets
##### addTileOp
In the `MapEditor` class
```java
public void addTileOp(long data){  
    if(loading) return;  
  
    if(currentOp == null) currentOp = new DrawOperation();  
    currentOp.addOperation(data);  
  
    renderer.updateStatic(TileOp.x(data), TileOp.y(data));  
}
```
##### flushOp
In the `MapEditor` class
``` java
public void flushOp(){  
    if(currentOp == null || currentOp.isEmpty()) return;  
    stack.add(currentOp);  
    currentOp = null;  
}
```
##### OperationStack
Acts as an **history** for the performed operations
```java
public OperationStack(){  
  
}  
  
public void clear(){  
    stack.clear();  
    index = 0;  
}  
  
public void add(DrawOperation action){  
    stack.truncate(stack.size + index);  
    index = 0;  
    stack.add(action);  
  
    if(stack.size > maxSize){  
        stack.remove(0);  
    }  
}  
  
public boolean canUndo(){  
    return !(stack.size - 1 + index < 0);  
}  
  
public boolean canRedo(){  
    return !(index > -1 || stack.size + index < 0);  
}  
  
public void undo(){  
    if(!canUndo()) return;  
  
    stack.get(stack.size - 1 + index).undo();  
    index--;  
}  
  
public void redo(){  
    if(!canRedo()) return;  
  
    index++;  
    stack.get(stack.size - 1 + index).redo();  
  
}
```
### Diagram
Some methods in `MapEditor` are not displayed as they are not deemed relevant to the *design pattern*
![Command Pattern Diagram](Assets/CommandPattern.svg)



## (Design Pattern 2 Name)
## (Design Pattern 3 Name)
