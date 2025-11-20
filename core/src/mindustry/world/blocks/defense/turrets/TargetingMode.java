package mindustry.world.blocks.defense.turrets;

/** Used for changing the target mode of a turret **/
public enum TargetingMode {
    CLOSEST_FIRST,      // The closest enemies around the turret. This is the default mode.
    FARTHEST_FIRST,     // The farthest enemies from the turret, in its range (or other one)
    STRONGEST_FIRST,    // The strongest enemies (with higher maxHealth) around the turret.
    WEAKEST_FIRST,      // The weakest enemies (with lower maxHealth) around the turret
    FASTEST_FIRST,      // The fastest enemies around the turret (highest u.type.speed * u.speedMultiplier)
    SLOWEST_FIRST,      // The slowest enemies around the turret (lowest u.type.speed * u.speedMultiplier)
}
