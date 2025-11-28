package mindustry.world.blocks.defense.turrets;

/** Used only by turrets that target both air and ground targets **/
public enum TargetEnv {
    ANY,               // Any Air or Ground target enemy
    AIR_FIRST,         // Focus *first* on Air targets enemies
    GROUND_FIRST;      // Focus *first* on Ground targets enemies

    public static String toString(TargetEnv e) {
        return e.toString().split("_")[0];
    }
}

