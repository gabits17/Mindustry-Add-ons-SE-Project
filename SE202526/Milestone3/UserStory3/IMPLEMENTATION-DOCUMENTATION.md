
#### Commits

17/11/2025:
![commit1](./assets/commits/commit1.png)

19/11/2025:
![commit2](./assets/commits/commit2.png)

20/11/2025:
![commit3](./assets/commits/commit3.png)

21/11/2025:
![commit4](./assets/commits/commit4.png)

22/11/2025:
![commit5](./assets/commits/commit5.png)
![commit6](./assets/commits/commit6.png)

23/11/2025:
![commit7](./assets/commits/commit7.png)

26/11/2025:
![commit8](./assets/commits/commit8.png)

27/11/2025:
![commit9](./assets/commits/commit9.png)

28/11/2025:
![commit10](./assets/commits/commit10.png)
![commit11](./assets/commits/commit11.png)

30/11/2025:
![commit12](./assets/commits/commit12.png)
![commit13](./assets/commits/commit13.png)

03/12/2025:
![commit14](./assets/commits/commit14.png)


#### Briefing 
The implemenation of this user story took place mostly around the ``Turret`` class, since it was added new configurations to it. It was created one new interface: ``TargetConfig``, which is implemented by the new class ``TargetConfiguration`` and contains two enumerations ``Mode`` and ``Env``. They contain a list of possible modes and environments for the targeting configuration of a turret.

````java
/** Possible target modes **/
public enum Mode {
    CLOSEST_FIRST,      // The closest enemies around the turret. This is the default mode.
    FARTHEST_FIRST,     // The farthest enemies from the turret, in its range (or other one)
    STRONGEST_FIRST,    // The strongest enemies (with higher maxHealth) around the turret.
    WEAKEST_FIRST,      // The weakest enemies (with lower maxHealth) around the turret
    FASTEST_FIRST,      // The fastest enemies around the turret (highest u.type.speed * u.speedMultiplier)
    SLOWEST_FIRST       // The slowest enemies around the turret (lowest u.type.speed * u.speedMultiplier)
};

/** Possible target environments **/
public enum Env {
    ANY_FIRST,          // Any Air or Ground target enemy
    AIR_FIRST,          // Focus *first* on Air targets enemies
    GROUND_FIRST;       // Focus *first* on Ground targets enemies;
};
````

It is possible to **combine** the targeting environment with a mode. For instance, it is possible for a turret to target the *strongest ground* units around it. For targets that target both air and ground units, it is possible to (or not) focus on an environment.

#### Logic
To sort the enemy units that a turret will attack, the ``SortUnits`` class was utilized. There was already four ways for sorting them (``Sortf`` objects): ``closest``, ``farthest``, ``strongest`` and ``weakest``. It was added two new ways: ``fastest`` and ``slowest``.

````java
public class UnitSorts {
  public static Sortf
      fastest = (u,x,y) -> -u.type.speed * u.speedMultiplier + Mathf.dst2(u.x,u.y,x,y) / NEGATIVE_WEIGHT,

      slowest = (u,x,y) -> u.type.speed * u.speedMultiplier + Mathf.dst2(u.x, u.y, x, y) / NEGATIVE_WEIGHT;
}
````

To combine the targeting environment with a targeting mode, it was also implemented two new (static) functions that handle air or ground units first, with the mode parameterized: ``airFirst(mode)`` and ``groundFirst(mode)``.

````java
public class UnitSorts {
    public static Sortf airFirst(Sortf mode) {
        return (u, x, y) -> {
            if(u.type.flying) return mode.cost(u, x, y);
            else return NEGATIVE_WEIGHT + mode.cost(u, x, y);
        };
    }

    public static Sortf groundFirst(Sortf mode) {
        return (u, x, y) -> {
            if(u.type.flying) return NEGATIVE_WEIGHT + mode.cost(u, x, y);
            else return mode.cost(u, x, y);
        };
    }
}
````

These configurations are defined and depend on the ``Turret``'s ``targetConfig`` new instance variable. The sort occurs in the ``unitSorter()`` new method in the ``TurretBuild`` class (inner class from ``Turret``). This method is called in the function ``findEnemy()`` in the same way the default sort was before this functionality was added.

````java
protected Sortf unitSorter() {
    Sortf mode = getSortf();
    TargetConfig.Env env = getTargetEnv();
    
    if(env == TargetConfig.Env.AIR_FIRST)
        return UnitSorts.airFirst(mode);

    else if(env == TargetConfig.Env.GROUND_FIRST)
        return UnitSorts.groundFirst(mode);

    return mode;
}
````

To display this configuration, the ``Turret`` block became configurable (``configurable = true`` in its constructor), which means it can be configured by the player, by clicking on it. Two configurations were added in the ``Turret``'s constructor. The method ``configure()`` is responsible to update the turret's configurations. Every time a configuration option is pressed, the method is called and updates the variable that matches the configuration being swapped (``targetMode`` or ``targetEnv``)-

````java
public Turret(String name) {
  // ...
  configurable = true;

  targetConfig = new TargetConfiguration(TargetConfig.Mode.CLOSEST_FIRST, TargetConfig.Env.ANY_FIRST);

  config(TargetConfig.Mode.class, (TurretBuild build, TargetConfig.Mode mode) ->{
      if(!configurable) return;
      build.swapTargetMode(mode);
  });

  config(TargetConfig.Env.class, (TurretBuild build, TargetConfig.Env env) ->{
      if(!configurable) return;
      build.swapTargetEnv(env);
  });

  configClear((TurretBuild build) ->{
      new TargetConfiguration(TargetConfig.Mode.CLOSEST_FIRST, build.setTargetEnv());
  });
}
````


#### Display
The current target configurations were added to the turret's information table. This table is shown at the right bottom corner, above the building menu, when the mouse cursor is hovering over the placed turret.

The configurations are also shown when the player clicks on the turret, by showing two buttons with the current target mode (left one) and environment (right one).

#### UI interaction

Having the two buttons shown, the player can choose to swap the turret's target configurations or to not do anything. Every time one of the buttons is pressed, the system displays the possible options for that target configuration, unless the turret can not target both environments. In that case, the environment button is disabled and can not be pressed on; the mode button is always enabled.

When having the options displayed, if one is chosen and it is different from the current configuration, the configuration is swapped to that option. Otherwise, an error message appears, informing that the option chosen is the same as the current one. The player can eventually do nothing, by unselecting the turret.

#### Class diagram for modifications
![modif-class-diagram](./assets/class-diagrams/modifications-cd.png)

Since this class diagram only shows modifications on the codebase, the represented ``UnitSorts`` attributes are just the ones that were added: ``fastest`` and ``slowest``.

Part of the new private methods are helper functions for the ``buildConfiguration()`` method, which is an *@override* method from the ``Building`` class, as ``display()`` and ``onConfigureClosed()`` are. The other part are helper functions that come from the ``TargetConfiguration`` class.

The three methods ``read()``, ``write()`` and ``version()`` were modified for saving and loading target configurations.


##### Note
Color code meaning:
- <mark style="background: #32e556ff;"> Green</mark>: Classes that were *created* 
- <mark style="background: #fbff02f0;">Yellow</mark>: Classes that were *modified*

### Implementation summary
- Main class affected: ``TurretBuild`` inner class from ``Turret``
- Took advantage of the sorting modes from the ``UnitSorts`` class for defining target modes
- Most dispendious part of the implementation: Handling the UI with several text buttons
- Main problem: Maps that in its initially launch have turrets placed on some tile in the grid. These maps have problems on saves and loads. However, saving and loading target configurations are working, except for that particular case.
- It was needed to handle (a lot of) corrupted old save files because of the loading and saving part.