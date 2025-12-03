package mindustry.world.blocks.defense.turrets;

public class TargetConfiguration implements TargetConfig {

    /** Target Mode configuration **/
    private Mode mode;

    /** Target Environment configuration **/
    private Env env;

    /**
     * Target configurations constructor
     * @param mode Mode target configuration
     * @param env Environment target configuration
     */
    public TargetConfiguration(Mode mode, Env env) {
        this.mode = mode;
        this.env = env;
    }

    @Override
    public void swapMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public void swapEnv(Env env) {
        this.env = env;
    }

    @Override
    public Mode getMode() {
        return mode;
    }

    @Override
    public Env getEnv() {
        return env;
    }

    @Override
    public Mode findMode(String modeStr) {
        for (Mode m : Mode.values())
            if (getConfigText(m, null).equalsIgnoreCase(modeStr))
                return m;
        return null; // Should never happen
    }

    @Override
    public Env findEnv(String envStr) {
        for (Env e : Env.values())
            if(getConfigText(null, e).equalsIgnoreCase(envStr))
                return e;
        return null; // Should never happen
    }

    @Override
    public String getConfigText(Mode mode, Env env) {
        if (mode == null) return env.toString().split("_")[0];
        if(env == null) return mode.toString().split("_")[0];
        return "."; // Should never happen
    }
}
