package mindustry.game;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.QuadTree;
import arc.math.geom.Rect;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.Tile;
import mindustry.world.blocks.liquid.Conduit;

import java.util.HashSet;

import static mindustry.Vars.*;

/** Class that manages leaks for display.*/
public class Leaks {
    // Constants
    /**
     * Minimum range for which leaks are identified with circles.
     */
    private static final float MIN_RANGE = 10;
    /**
     * Minimum diameter for which leaks are identified with circles. (to avoid multiplying radius by 2 every time diameter is needed)
     */
    private static final float MIN_DIAM = 2 * MIN_RANGE;

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
    }

    public static Leaks getInstance() {
        if (leaks == null) {
            leaks = new Leaks();
        }
        return leaks;
    }
    
    public void addLeak(Tile tile) {
        leakTree.insert(tile);
        leakSet.add(tile);
    }

    public void removeLeak(Tile tile) {
        leakTree.remove(tile);
        leakSet.remove(tile);
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
                renderer.minimap.updatePixel(tile);
        } else { // Stop leak due to lack of liquid to flow
            removeLeak(tile);
            renderer.minimap.updatePixel(tile);
        }
    }

    /** Draw dotted circles around all leaks in a certain player distance. */
    /** Functionality based on Units class use of Quadtree */
    public void drawLocalLeaks(){
        float x = Vars.player.x / Vars.tilesize;
        float y = Vars.player.y / Vars.tilesize;
        for (Tile tile : leakTree.objects) {
            if(Mathf.dst2(tile.x, tile.y, x, y) < MIN_RANGE*MIN_RANGE) {
                Drawf.dashCircle(tile.x * Vars.tilesize, tile.y * Vars.tilesize, MIN_RANGE * Vars.tilesize, Pal.leakingWarn);
            }
        }

        // Intersects with square around player x and y
        this.leakTree.intersect(x - MIN_RANGE , y - MIN_RANGE, MIN_DIAM, MIN_DIAM, tile -> {
            System.out.println("HELLOOOOO2222");
            if(tile.within(x, y, MIN_RANGE)) {
                System.out.println("HELLOOOO3333");
                Drawf.dashCircle(tile.x * Vars.tilesize, tile.y * Vars.tilesize, MIN_RANGE, Pal.leakingWarn);
            }
        });
    }
}
