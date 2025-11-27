## Select Placed Turret
![select-turret-sd](./assets/select-turret-sd.svg)
This sequence diagram represents the message flow between the player and the code base when selecting a placed turret in the grid.
The ``update()`` method triggers the player's interaction of selecting (``Binding.select``) a built build, which is better described by this code snippet:
````java
if (Core.input.keyTap(Binding.select) && !Core.scene.hasMouse()) {  

    Tile selected = world.tileWorld(input.mouseWorldX(), input.mouseWorldY());  
    if (selected != null) {  
        Call.tileTap(player, selected);  
    }  
}
````
The built turret being selected **must be from the same team of the player**, which is checked by the alternative combined fragment. If not the case, the system doesn't allow the player to click on it, returning nothing.

The only interesting action in this sequence diagram is the selection of the turret. The *Configuration Display* is referenced and detailed below in this report. 

### Configuration Display

This sequence diagram doesn't represent a particular use case, but part of the *Select Placed Turret* one. Since it is complex and big enough, it was refactorized, so it is easier to understand.

// TO DO (after changing the display to a menu rather than a live-updatable button)

## Change Turret's Target Environment && Change Turret's Target Mode
// TO DO (after changing the display to a menu rather than a live-updatable button)
These two use cases will be very similar as sequence diagrams, probably.

## Unselect Turret
// still a draft