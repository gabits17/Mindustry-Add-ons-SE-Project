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

The following UML diagram translates the structure of the **Template Method** design as explained. It includes only the relevant information for this discussion.
The methods that are customized are not abstract in the source code, but they are over writen by the concrete classes ``ErekirPlanetGenerator``, ``SerpuloPlanetGenerator`` and ``TantrosPlanetGenerator``, thus giving the main idea of the desgin pattern. 

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

This pattern can be described with the following UML diagram, linking several subclasses to a central interface, as the Facade pattern does.
For clarification, not all methods are specified in the diagram.

![facade](Assets/facade.drawio.svg)

Although this pattern is intented to simplify several subsystems with a central and clear interface, the Facade pattern here provides a **parcial abstraction**. This happens because it is dealing with a large and complex system as ``Mindustry`` is. For instance, any method that has a ``Sector`` parameter, such as ``loadSector(Sector)`` and ``setSectorRules(Sector, boolean)``, requires the client to *already possess* a valid ``Sector`` object.

## 3. Composite (?)

```Java
private void updatePlanet(Planet planet){
    planet.position.setZero();
    planet.addParentOffset(planet.position);
    if(planet.parent != null){
        planet.position.add(planet.parent.position);
    }
    for(Planet child : planet.children){
        updatePlanet(child);
    }
}
```
```Java
public void updateTotalRadius(){
    totalRadius = radius;
    for(Planet planet : children){
        //max with highest outer bound planet
        totalRadius = Math.max(totalRadius, planet.orbitRadius + planet.totalRadius);
    }
}
```

This was my first analysis, so this document is still a **draft**.

One design pattern that i might have identified is ``Composite``.

I was analizying the ``Universe`` class in ``core/src/game/Universe`` and found the class **``Planet``** a potential ``Composite`` implementation: each ``Planet`` object has children and each children is also a ``Planet`` object. 

**``Planet``** can surely be the ``component`` role of the pattern, **its children** can be the ``composite`` role and **planets with no children** might