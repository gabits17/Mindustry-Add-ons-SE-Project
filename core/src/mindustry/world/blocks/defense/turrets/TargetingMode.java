package mindustry.world.blocks.defense.turrets;

public enum TargetingMode {
    CLOSEST_FIRST,      // The closest enemies around the turret. This is the default mode, I suppose.
    FARTHEST_FIRST,     // The farthest enemies from the turret, in its range (or other one)
    STRONGEST_FIRST,    // The strongest enemies around the turret (probably usually bosses?), maybe combine health and armor
    WEAKEST_FIRST,      // The weakest enemies around the turret
    FASTEST_FIRST,      // The fastest enemies around the turret
    SLOWEST_FIRST,      // The slowest enemies around the turret
    GROUNDERS_FIRST,    // Turrets only attack Grounder Units in this mode; but need to make sure it can attack ground targets!
    FLYERS_FIRST,       // Turrets only attack Flying Units in this mode; but need to make sure it can attack air targets!
    NAVAL_FIRST,        // Turrets only attack Naval Units in this mode; but need to make sure it can attack naval targets!
    RANDOM              // Turrets attack random enemies around it; need to make sure it attacks random targets in the possible ones it can attack!
}
