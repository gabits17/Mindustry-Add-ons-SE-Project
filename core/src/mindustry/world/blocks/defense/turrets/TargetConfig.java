package mindustry.world.blocks.defense.turrets;

/**
 * Target configurations.
 */
public interface TargetConfig {
    /** Possible target modes **/
    public enum Mode {
        CLOSEST_FIRST,      // The closest enemies around the turret. This is the default mode.
        FARTHEST_FIRST,     // The farthest enemies from the turret, in its range (or other one)
        STRONGEST_FIRST,    // The strongest enemies (with higher maxHealth) around the turret.
        WEAKEST_FIRST,      // The weakest enemies (with lower maxHealth) around the turret
        FASTEST_FIRST,      // The fastest enemies around the turret (highest u.type.speed * u.speedMultiplier)
        SLOWEST_FIRST       // The slowest enemies around the turret (lowest u.type.speed * u.speedMultiplier)
    };

    /** Possible target environments **/
    public enum Env {
        ANY_FIRST,          // Any Air or Ground target enemy
        AIR_FIRST,          // Focus *first* on Air targets enemies
        GROUND_FIRST;       // Focus *first* on Ground targets enemies;
    };

    /**
     * Swaps mode configuration to the mode specified.
     * @param mode Mode being set
     */
    void swapMode(Mode mode);

    /**
     * Swaps environment configuration to the environment specified.
     * @param env Environment being set
     */
    void swapEnv(Env env);

    /**
     * @return Current target config mode
     */
    Mode getMode();

    /**
     * @return Current target config environment
     */
    Env getEnv();

    /**
     * Finds the mode that matches the specified string
     * @param str Mode string being searched
     * @return Mode that matches; or null (should never happen)
     */
    Mode findMode(String str);

    /**
     * Finds the environment that matches the specified string
     * @param str Environment string being searched
     * @return Environment that matches; or null (should never happen)
     */
    Env findEnv(String str);

    /**
     * Returns the specific configuration String
     * @param m Mode configuration
     * @param e Env configuration
     * @return Mode configuration to string if e is null; Env configuration to string if m is null
     */
    String getConfigText(Mode m, Env e);
}
