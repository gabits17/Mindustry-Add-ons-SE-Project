# Design Patterns Report
## Author
- Gabriela Silva (67286)

## 1. Template Method

Template Method is a behavioural design pattern that defines the skeleton of an algorithm in the superclass, but lets subclasses override specific steps of the algorithm without changing its sructure.
(https://refactoring.guru/design-patterns/template-method)

It is present in the ``PlanetGenerator`` class (``core/src/mindustry/maps/generators``) and involves the ``BasicGenerator`` class from the same path, but also the classes ``ErekirPlanetGenerator``, ``SerpuloPlanetGenerator`` and ``TantrosPlanetGenerator`` (``core/src/mindustry/maps/planet``).
The Template Method here allows subclasses to customize a planet with specific-features, while having a well defined, consistent and unchangeable algorithm for generating a map.

The following method (``generate(Tiles, Sector, WorldParams)`` from ``PlanetGenerator``) is the **Template Method** method - it defines "the *how*" of a map generation.

````Java
public void generate(Tiles tiles, Sector sec, WorldParams params){
    this.tiles = tiles;
    this.seed = params.seedOffset + baseSeed;
    this.sector = sec;
    this.width = tiles.width;
    this.height = tiles.height;
    this.rand.setSeed(sec.id + params.seedOffset + baseSeed);

    TileGen gen = new TileGen();
    for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
            gen.reset();
            Vec3 position = sector.rect.project(x / (float)tiles.width, y / (float)tiles.height);

            genTile(position, gen);
            tiles.set(x, y, new Tile(x, y, gen.floor, gen.overlay, gen.block));
        }
    }

    generate(tiles, params);
}
````

The ``PlanetGenerator`` class has a few more methods that can be the *customizable hooks* for other generators to specify its generation details. For instance, the ``ErekirPlanerGenerator`` class has its own `genTile()` method, the ``SerpuloPlanetGenerator`` class has its own ``allowLanding()`` method and the ``TantrosPlanetGenerator`` has its own ``addWeather()`` method. All these three generators also have their own ``generate()`` operation (which is inherited from ``BasicGenerator`` class), that is executed in the Template Method.

### UML Diagram
The following UML diagram translates the structure of the **Template Method** design as explained. It includes only the relevant information for this discussion. The methods that are customized are not abstract in the source code, but they are over writen by the concrete classes ``ErekirPlanetGenerator``, ``SerpuloPlanetGenerator`` and ``TantrosPlanetGenerator``, thus giving the main idea of the design pattern. 

![Template Method UML](Assets/template-method.svg)


## 2. Facade

The Facade design pattern hides the complexity of a subsystem by providing a clear interface for the client to communicate with.    

This pattern is evident in the ``World`` class (``core/src/mindustry/core``), which can be seen as the **central Facade** for all map geometry and tile data access.

````Java
public Tile tileWorld(float x, float y){
    return tile(Math.round(x / tilesize), Math.round(y / tilesize));
}
````
For example, the method above (``tileWorld(float, float)``) converts **world coordinates** (coordinates that an extern class has) to **tile grid coordinates**, which are required for the internal ``Tile`` subsystem.

Additionally, the internal class ``FilterContext`` handles the execution of all map filters, omitting the complex process and the details of map generation:
````Java
public class FilterContext extends Context{
    final Map map;
    // (...)
    public void applyFilters(){
        Seq<GenerateFilter> filters = map.filters();
        if(!filters.isEmpty()){
            GenerateInput input = new GenerateInput();
            for(GenerateFilter filter : filters){
                filter.randomize();
                input.begin(width(), height(), (x, y) -> tiles.getn(x, y));
                filter.apply(tiles, input);
            }
        }
    }
}
````
By exposing a single method ``end()``, the Facade ensures that the map filtering and generation process runs correctly without forcing other non-trivial callings, making the map loading subsystem easy to use.

### UML Diagram
This pattern can be described with the following UML diagram, linking several subclasses to a central interface, as the Facade pattern does.
For clarification, not all methods are specified in the diagram.

![facade](Assets/facade.svg)

Although this pattern is intented to simplify several subsystems with a central and clear interface, the Facade pattern here provides a **parcial abstraction**. This happens because it is dealing with a large and complex system as ``Mindustry`` is. For instance, any method that has a ``Sector`` parameter, such as ``loadSector(Sector)`` and ``setSectorRules(Sector, boolean)``, requires the client to *already possess* a valid ``Sector`` object.

## 3. Builder

When identifying this design pattern for the first time, I understood it as the **Factory Method**. After further research and consideration, Gabriel's review helped me understand that it fits the **Builder** design pattern better, although not talked about in class. 

The **Builder** _GoF_ design pattern lets a _Director_ manage the building of something, transmitting building steps' informations to concrete _Builders_. These _Builders_ produce a _Product_ according to the _Director_'s orders.

In ``core/src/mindustry/content``, there are the classes ``TechNode``, ``TechTree``, ``ErekirTechTree`` and ``SerpuloTechTree``. It is possible to relate these classes according to the **Builder** _GoF_ design pattern definition:
- _Directors_: Classes ``SerpuloTechTree`` and ``ErekirTechTree``.
- _Builder_: Class ``TechTree``.
- _Product_: Also class ``TechTree``, along with its corresponding ``TechNode`` objects.

As Gabriel mentioned in his review, the class ``TechTree`` would run the same role: _Builder_ and _Product_, along with ``TechNode``.

As mentioned on the previous version of this report, the classes ``SerpuloTechTree`` and ``ErekirTechTree`` both repeatedly call the functions ``node()``, ``nodeRoot()`` and ``nodeProduce()`` to _build_ a progression tree for its planet (_Erekir_ and _Serpulo_).

````Java
public class SerpuloTechTree{
    public static void load(){
        Planets.serpulo.techTree = nodeRoot("serpulo", coreShard, () -> {
            node(conveyor, () -> {
                node(junction, () -> {
                    node(router, () -> {
                        node(advancedLaunchPad, Seq.with(new SectorComplete(extractionOutpost)), () -> {
                            node(landingPad, () -> {
                                node(interplanetaryAccelerator, Seq.with(new SectorComplete(planetaryTerminal)), () -> {
        // (...)
    }
}

public class ErekirTechTree{
    public static void load(){
        rebalance();
        (...)
        Planets.erekir.techTree = nodeRoot("erekir", coreBastion, true, () -> {
            context().researchCostMultipliers = costMultipliers;
            node(duct, erekirSector, () -> {
                node(ductRouter, () -> {
                    node(ductBridge, () -> {
                        node(armoredDuct, () -> {
        // (...)
    }
}
````

These classes avoid the use of ``new``, isolating the building from the client. Although the product is always of the same type, it is built from different configurations, which is exactly what the motivation for the _Builder_ design pattern refers to in [Refactoring Guru Builder Pattern](https://refactoring.guru/design-patterns/builder) (the reference Gabriel recommended me to read).

It is important to notice (again) that ``SerpuloTechTree`` and ``ErekirTechTree`` classes **are not** implementations of a ``TechTree`` interface or abstract class, which breaks the encapsulation of both _Factory Method_ and _Builder_ design patterns and makes the UML diagram difficult to illustrate correctly its principles.


### UML Diagram
The following UML diagram translates this pattern with the classes mentioned. ``ErekirTechTree`` and ``SerpuloTechTree`` uses the three specified methods of ``TechTree`` to create ``TechNode`` objects, when calling the ``load()`` function.  

Since it is not consistent with its *GoF* definition as explained above, it has some crucial differences that feel like it is not that well designed, such as that ``SerpuloTechTree`` and ``ErekirTechTree`` classes **are not** implementations of a ``TechTree`` interface or abstract class.

![builder](Assets/builder.svg)

---

## Change log
_Created_: 29/10/2025 00:23

_Last modified before milestone2_: 04/11/2025 00:36

_Improved according to Gabriel's review_: 12/11/2025 20:35