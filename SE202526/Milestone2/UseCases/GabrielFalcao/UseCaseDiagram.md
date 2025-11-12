# Use Case Diagram Report
## Author
- Gabriel Falcão (67775)

# Use Cases - Map Editing System
- Editing Maps

## Diagram

![TileEditingSystem](./Assets/TileEditingSystem.svg)
**Note**: This Diagram only shows a tiny amount of the use cases offered by the built in editor, Tile Editing is the one shown here, but, the system also offers: 
- Wave Editing
- Map Sharing
- Map Creation
- Automatic Map Generation
These will not be portrayed here as the system would become to large for whats needed for the milestone.

## Choose Tile
### Description
The Map Maker chooses a tile to build with.
## Search Tile (Extending Use Case)
### Description
If the Map Maker clicks the search icon while choosing tiles, he/she may search for tiles by name.
## Change Brush Size
### Description
The Map Maker may increase or decrease the size of the tool being used (pencil, line,...).
## Toggle Grid
### Description
The Map Maker toggles the a grid like reference system, which shows all tiles (squares in world) outlined with grey lines.
## Zoom
### Description
The Map Maker may zoom in or zoom out.
## Draw (abstract)
### Description
The Map Maker draws tiles.
## Fill
### Description
The Map Maker draws with the filling tool (similar to paint's bucket tool).
## Draw Line
### Description
The Map Maker chooses two points and the system draws a line between them.
## Pencil
### Description
The Map Maker draws tiles individually.
## Spray
### Description
The Map Maker draws in a spray pattern (similar to paint's spray tool).
## Erase
### Description
The Map Maker is able to erase blocks/terrain from the world.
**Note**: blocks/terrain are tiles, but the word blocks/terrain is used because the Map Maker may not erase the floor.
## Change Tool Team
### Description
The Map Maker chooses the team of the blocks to be placed.
**Note**: Since only blocks have a team the word tile was not used.
## Pick Tile
### Description
The Map Maker picks a tile in the editor (similar to paint's pipette tool).
## Choose Tile
### Description
The Map Maker chooses a tool to draw with (Pencil, Fill....).
## Rotate
### Description
The Map Maker rotates (ccw) the direction of the tiles to be placed.
## Show Tiles (abstract)
### Description
The Map Maker chooses which subset of tiles to show.
## Toggle Floor
### Description
The Map Maker toggles between showing or not showing the floor.
## Toggle Blocks
### Description
The Map Maker toggles between showing or not showing blocks.
## Toggle Terrain
### Description
The Map Maker toggles between showing or not showing terrain.
## Redo
### Description
The Map Maker *redos* the previously undone action.
**Note**: Some actions may not be *redoable*

## Undo
### Description
The Map Maker *undos* the previously done action.
**Note**: Some actions may not be *undoable*

## Actors
**Note**: Use cases did not include actors as the only actor in this system is the **Map Maker**, taking the role of primary actor in all the use cases.

Map Maker - Someone that makes maps using the build in mindustry map editor

# Change Log
I have updated the report according to the review given by Carolina (67775) (12/11/2025) (18:02)