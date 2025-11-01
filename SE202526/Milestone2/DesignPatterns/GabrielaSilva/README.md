# Design Patterns Report
## Author
<<<<<<< HEAD
- Gabriela Silva (67286)

## Template Method

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
![Template Method UML](template-method.svg)

=======
- Gabriela Silva (#STUDENT IF)
# Design Patterns
- Attach a picture of the block of code with the Design Pattern
- Reference the file in which the Pattern was found
- Possible improvements you found
- Possible blocks of code you believe a Pattern could be implemented
    - Picture of said blocks
>>>>>>> master

## (Design Pattern 2 Name)


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