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

Another unusual aspect of this implementation is that the **MapEditor** class appears to serve as both the _Invoker_ and the _Receiver_. Although some operations are indeed invoked by external elements such as `EditorTile`, the `MapEditor` is responsible for invoking operations through the `addTileOp` method, which adds operations to the current one. It also applies the operation’s changes via the `updateStatic` (`EditorRender` this class could also be considered a *Receiver*) method (acting as the Invoker) directly on itself (acting as the Receiver). Finally, the resulting operation is added to the `OperationStack` through the `flushOp` method, which is invoked either by the `MapEditor` itself (in the seemingly unused `addClifs` method) or by the `MapView`, which appears to handle editor input.

The `MapEditor` uses the `undo`, `canUndo`, `redo` and `canRedo` methods to control the flow of the operations, giving the user the ability to *undo* or *redo* operations made in the editor.

It’s worth noting that the `EditorRender` class was not discussed here, as most of the operations relevant to the pattern occur within `MapEditor`, `OperationStack`, or `DrawOperation`. Though `EditorRender` class also functions as a Receiver of operations through the `updateStatic` method.
### Relevant Code Snippets
#### addTileOp Method
In the `MapEditor` class
```java
public void addTileOp(long data){  
    if(loading) return;  
  
    if(currentOp == null) currentOp = new DrawOperation();  
    currentOp.addOperation(data);  
  
    renderer.updateStatic(TileOp.x(data), TileOp.y(data));  
}
```
#### flushOp Method
In the `MapEditor` class
``` java
public void flushOp(){  
    if(currentOp == null || currentOp.isEmpty()) return;  
    stack.add(currentOp);  
    currentOp = null;  
}
```
#### OperationStack Class
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



## Visitor
The **Visitor** pattern can be found in the `LExecuter` class in `core/src/mindustry/logic`.
The pattern is, once again, not immediately obvious, as its implementation differs from the typical form where a single Visitor traverses multiple classes to add functionality. In this case, the situation is reversed: there are multiple _Visitors_, the instruction, and only one _Visited_ element, the executor itself.
### Relevant Code Snippets
#### runOnce Method
This method is implemented by `LExecuter`
```java
/** Runs a single instruction. */  
public void runOnce(){  
    //reset to start  
    if(counter.numval >= instructions.length || counter.numval < 0){  
        counter.numval = 0;  
    }  
  
    if(counter.numval < instructions.length){  
        counter.isobj = false;  
        instructions[(int)(counter.numval++)].run(this);  
    }  
}
```
#### LInstruction Interface
```java
public interface LInstruction{  
    void run(LExecutor exec);  
}
```
#### UnitBind
One of the many *instructions* that implement `LInstrucion`, as one may note the run method receives an instance of `LExecuter` and uses it, visits it.
```java
/** Binds the processor to a unit based on some filters. */  
public static class UnitBindI implements LInstruction{  
    public LVar type;  
  
    public UnitBindI(LVar type){  
        this.type = type;  
    }  
  
    public UnitBindI(){  
    }  
    @Override  
    public void run(LExecutor exec){  
        if(exec.binds == null || exec.binds.length != content.units().size){  
            exec.binds = new int[content.units().size];  
        }  
  
        //binding to `null` was previously possible, but was too powerful and exploitable  
        if(type.obj() instanceof UnitType type && type.logicControllable){  
            Seq<Unit> seq = exec.team.data().unitCache(type);  
  
            if(seq != null && seq.any()){  
                exec.binds[type.id] %= seq.size;  
                if(exec.binds[type.id] < seq.size){  
                    //bind to the next unit  
                    exec.unit.setconst(seq.get(exec.binds[type.id]));  
                }  
                exec.binds[type.id] ++;  
            }else{  
                //no units of this type found  
                exec.unit.setconst(null);  
            }  
        }else if(type.obj() instanceof Unit u && (u.team == exec.team || exec.privileged) && u.type.logicControllable){  
            //bind to specific unit object  
            exec.unit.setconst(u);  
        }else{  
            exec.unit.setconst(null);  
        }  
    }  
}
```
### Diagram

![VisitorPattern](Assets/VisitorPattern.svg)

## Strategy
The `UnitSorts` class ,in `core/src/mindustry/entities`, according to the reference https://refactoring.guru/design-patterns/strategy, uses a Strategy design pattern where a single algorithm is attributed to a unit (a Turret in this case). The algorithm is responsible for deciding what targets the Turret prioritises, based on properties like distance and strength. Named turrets like foreshadow, malign, lustre use an algorithm that sorts the target units by strength.

Note: This class will be extremely important upon the implementation of the third user story, it combined with some sort of *UI* will be needed to implement the desired functionalities.
### Relevant Code Snippets
#### UnitSorts
```java
package mindustry.entities;  
  
import arc.math.*;  
import mindustry.content.*;  
import mindustry.entities.Units.*;  
import mindustry.gen.*;  
  
public class UnitSorts{  
    public static Sortf  
  
    closest = Unit::dst2,  
    farthest = (u, x, y) -> -u.dst2(x, y),  
    strongest = (u, x, y) -> -u.maxHealth + Mathf.dst2(u.x, u.y, x, y) / 6400f,  
    weakest = (u, x, y) -> u.maxHealth + Mathf.dst2(u.x, u.y, x, y) / 6400f;  
  
    public static BuildingPriorityf  
  
    buildingDefault = b -> b.block.priority,  
    buildingWater = b -> b.block.priority + (b.liquids != null && b.liquids.get(Liquids.water) > 5f ? 10f : 0f);  
}
```
#### Example Turret Creation (Foreshadow)
The *foreshadow* is a late-game *Serpulo* only turret that prioritises stronger enemies.
```java
foreshadow = new ItemTurret("foreshadow"){{  
    float brange = range = 500f;  
  
    requirements(Category.turret, with(Items.copper, 1000, Items.metaglass, 600, Items.surgeAlloy, 300, Items.plastanium, 200, Items.silicon, 600));  
    ammo(  
        Items.surgeAlloy, new RailBulletType(){{  
            shootEffect = Fx.instShoot;  
            hitEffect = Fx.instHit;  
            pierceEffect = Fx.railHit;  
            smokeEffect = Fx.smokeCloud;  
            pointEffect = Fx.instTrail;  
            despawnEffect = Fx.instBomb;  
            pointEffectSpace = 20f;  
            damage = 1350;  
            buildingDamageMultiplier = 0.2f;  
            maxDamageFraction = 0.6f;  
            pierceDamageFactor = 1f;  
            length = brange;  
            hitShake = 6f;  
            ammoMultiplier = 1f;  
        }}    );  
    maxAmmo = 40;  
    ammoPerShot = 5;  
    rotateSpeed = 2f;  
    reload = 200f;  
    ammoUseEffect = Fx.casing3Double;  
    recoil = 5f;  
    cooldownTime = reload;  
    shake = 4f;  
    size = 4;  
    shootCone = 2f;  
    shootSound = Sounds.railgun;  
    unitSort = UnitSorts.strongest; //The used startegy

    envEnabled |= Env.space;  
  
    coolantMultiplier = 0.4f;  
    scaledHealth = 150;  
  
    coolant = consumeCoolant(1f);  
    consumePower(10f);  
}};
```
### Diagram
![StrategyPattern.svg](Assets/StrategyPattern.svg)
