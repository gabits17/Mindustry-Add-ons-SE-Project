package mindustry.game;
import arc.Events;
import arc.graphics.Color;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Tile;
import mindustry.world.blocks.liquid.Conduit;

import java.util.HashSet;

import static mindustry.Vars.world;

/** Class that manages leaks for display.*/
public class Leaks {
    // Constants
    /**
     * Minimum range for which leaks are identified with circles.
     */
    private static final float MIN_RANGE = 10 * Vars.tilesize;
    /**
     * Minimum diameter for which leaks are identified with circles. (to avoid multiplying radius by 2 every time diameter is needed)
     */
    private static final float MIN_DIAM = 2 * MIN_RANGE;

    /**
     * Color for leaking related display.
     */
    private static final Color LEAK_COLOR = Pal.leakingWarn;

    // Static Vars
    /**
     * The singleton variable.
     */
    private static Leaks leaks;

    // Vars
    /**
     * Var that stores leaking tiles - can be used to find leaks close to the player more efficiently.
     */
    private final QuadTree<Tile> leakTree;
    /**
     * HashSet for quickly checking whether a tile is leaking (if it exists in the set, it is leaking).
     */
    private final HashSet<Tile> leakSet;

    public Leaks() {
        this.leakTree = new QuadTree<>(Vars.world.getQuadBounds(new Rect()));
        this.leakSet = new HashSet<>();

        //Clear leaks when an event occurs (entering/leaving a map)
        Events.on(EventType.ResetEvent.class, e -> {
            leakTree.clear();
            leakSet.clear();
        });
    }

    public static Leaks getInstance() {
        if (leaks == null) {
            leaks = new Leaks();
        }
        return leaks;
    }

    public static int getLeakColorValue() {
        return Pal.leakingWarn.rgba();
    }
    
    public void addLeak(Tile tile) {
        if (leakSet.add(tile)) {
            //only adds to QuadTree if removing from the HashSet was a success.
            leakTree.insert(tile);
        }
    }

    /**
     * Removes a leaking tile from the data structures if it is there.
     * @param tile the tile indicated to be leaking
     */
    public void removeLeak(Tile tile) {
        if (leakSet.remove(tile)) {
            //only removes from the QuadTree if removing from the HashSet was a success.
            leakTree.remove(tile);
        }
    }
    
    public boolean isLeaking(Tile tile) {
        return leakSet.contains(tile);
    }

    /**
     * Checks that a building that can leak (Conduit class) has started/stopped leaking and requests a minimap update if true.
     * @param building the Building undergoing a leak update.
     */
    public void checkLeak(Conduit.ConduitBuild building) {
        // Don't check for leaks unless the building belongs to the player
        if (building.team != Vars.player.team()) return;
        Tile tile = building.tile;
        if(building.liquids.currentAmount() > 0.0001f) {
            Tile next = tile.nearby(building.rotation);
            // Knows the building is currently leaking if it's contained in the set of leaking buildings
            boolean wasLeaking = this.isLeaking(tile);
            // Checks if the leak has been plugged by a block, or passed on to another block that carries liquids
            boolean isLeaking = !next.block().solid && !next.block().hasLiquids;

            // Transition from not leaking to leaking (start leak) and vice-versa (stop leak)
            if (!wasLeaking && isLeaking) {
                addLeak(tile);
            } else if (wasLeaking && !isLeaking) {
                removeLeak(tile);
            }
            // Either transition needs a minimap update
            if (isLeaking ^ wasLeaking)
                Vars.renderer.minimap.updatePixel(tile);
        } else { // Stop leak due to lack of liquid to flow
            removeLeak(tile);
            Vars.renderer.minimap.updatePixel(tile);
        }
    }

    /** Draw dotted circles around all leaks in a certain player distance. */
    public void drawLocalLeaks(){
        float x = Vars.player.x;
        float y = Vars.player.y;
        // Intersects with square around player x and y
        this.leakTree.intersect(x - MIN_RANGE , y - MIN_RANGE, MIN_DIAM, MIN_DIAM, tile -> {
            //Ensure the tile really is leaking (if the block was broken, it shouldn't be registered as a leak)
            if(!(world.tile(tile.x, tile.y).build instanceof Conduit.ConduitBuild)){
                removeLeak(tile);
            } else if(tile.within(x, y, MIN_RANGE)) {
                Drawf.dashCircle(tile.getX(), tile.getY(), MIN_RANGE, LEAK_COLOR);
            }
        });
    }
}
