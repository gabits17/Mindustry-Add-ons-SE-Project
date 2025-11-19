package mindustry.world.blocks.defense.turrets;

public enum TargetingMode {
    // Already done, need testing and review
    CLOSEST_FIRST,      // The closest enemies around the turret. This is the default mode.
    FARTHEST_FIRST,     // The farthest enemies from the turret, in its range (or other one)
    STRONGEST_FIRST,    // The strongest enemies (with higher maxHealth) around the turret.
    WEAKEST_FIRST,      // The weakest enemies (with lower maxHealth) around the turret
    FASTEST_FIRST,      // The fastest enemies around the turret (highest u.type.speed * u.speedMultiplier)
    SLOWEST_FIRST,      // The slowest enemies around the turret (lowest u.type.speed * u.speedMultiplier)

    // still thinking about if we are implementing these:
    GROUNDERS_FIRST,    // Turrets should attack Grounder Units in this mode; but need to make sure it can attack ground targets!
    FLYERS_FIRST,       // Turrets should attack Flying Units in this mode; but need to make sure it can attack air targets!
    NAVAL_FIRST,        // Turrets should attack Naval Units in this mode; but need to make sure it can attack naval targets!
    RANDOM              // Turrets attack random enemies around it; need to make sure it attacks random targets in the possible ones it can attack!
}
