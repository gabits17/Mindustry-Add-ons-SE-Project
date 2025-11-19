package mindustry.entities;

import arc.math.*;
import mindustry.content.*;
import mindustry.entities.Units.*;
import mindustry.gen.*;

public class UnitSorts{
    // Units sorted; slower score means they are the first in line in the sort.
    public static Sortf
    // Closest dst2 (closest unit) is ordered as the first in the sort
    closest = Unit::dst2,
    // Farthest dst2 (farthest unit) is ordered as the first in the sort
    farthest = (u, x, y) -> -u.dst2(x, y),
    // Combines strongest (maxHealth) and distance (prioritizes closest among the strongest ones)
    strongest = (u, x, y) -> -u.maxHealth + Mathf.dst2(u.x, u.y, x, y) / 6400f,
    // Combines weakest (miHealth) and distance (prioritizes closest among the weakest ones)
    weakest = (u, x, y) -> u.maxHealth + Mathf.dst2(u.x, u.y, x, y) / 6400f,
    //****************************** NEW ONES FOR US3 IMPLEMENTATION **************************************//
    // Combines fastest (speed * speedMultiplier) and distance (prioritizes closest among the fastest ones)
    fastest = (u,x,y) -> -u.type.speed * u.speedMultiplier + Mathf.dst2(u.x,u.y,x,y) / 6400f,
    // Combines slowest (speed * speedMultiplier) and distance (prioritizes closest among the slowest ones)
    slowest = (u,x,y) -> u.type.speed * u.speedMultiplier + Mathf.dst2(u.x, u.y, x, y) / 6400f;

    public static BuildingPriorityf

    buildingDefault = b -> b.block.priority,
    buildingWater = b -> b.block.priority + (b.liquids != null && b.liquids.get(Liquids.water) > 5f ? 10f : 0f);
}