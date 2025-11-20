package mindustry.entities;

import arc.math.*;
import mindustry.content.*;
import mindustry.entities.Units.*;
import mindustry.gen.*;
import mindustry.world.blocks.defense.turrets.TargetingClass;

public class UnitSorts{
    private static final float NEGATIVE_WEIGHT = 6400f;

    /** Units sorted; slower score means they are the first in line in the sort. **/
    public static Sortf
    // Closest dst2 (closest unit) is ordered as the first in the sort
    closest = Unit::dst2,

    // Farthest dst2 (farthest unit) is ordered as the first in the sort
    farthest = (u, x, y) -> -u.dst2(x, y),

    // Combines strongest (maxHealth) and distance (prioritizes closest among the strongest ones)
    strongest = (u, x, y) -> -u.maxHealth + Mathf.dst2(u.x, u.y, x, y) / NEGATIVE_WEIGHT,

    // Combines weakest (miHealth) and distance (prioritizes closest among the weakest ones)
    weakest = (u, x, y) -> u.maxHealth + Mathf.dst2(u.x, u.y, x, y) / NEGATIVE_WEIGHT,

    //****************************** NEW ONES FOR US3 IMPLEMENTATION **************************************//
    // Combines fastest (speed * speedMultiplier) and distance (prioritizes closest among the fastest ones)
    fastest = (u,x,y) -> -u.type.speed * u.speedMultiplier + Mathf.dst2(u.x,u.y,x,y) / NEGATIVE_WEIGHT,

    // Combines slowest (speed * speedMultiplier) and distance (prioritizes closest among the slowest ones)
    slowest = (u,x,y) -> u.type.speed * u.speedMultiplier + Mathf.dst2(u.x, u.y, x, y) / NEGATIVE_WEIGHT;

    /** Returns a unit sort with air units first, untied by one of the Sortf modes  **/
    public static Sortf airFirst(Sortf mode) {
        return (u, x, y) -> {
            if(u.type.flying) return mode.cost(u, x, y);
            else return NEGATIVE_WEIGHT + mode.cost(u, x, y);
        };
    }

    /** Returns a unit sort with ground units first, untied by one of the Sortf modes above **/
    public static Sortf groundFirst(Sortf mode) {
        return (u, x, y) -> {
            if(u.type.flying) return NEGATIVE_WEIGHT + mode.cost(u, x, y);
            else return mode.cost(u, x, y);
        };
    }

    public static BuildingPriorityf

    buildingDefault = b -> b.block.priority,
    buildingWater = b -> b.block.priority + (b.liquids != null && b.liquids.get(Liquids.water) > 5f ? 10f : 0f);
}