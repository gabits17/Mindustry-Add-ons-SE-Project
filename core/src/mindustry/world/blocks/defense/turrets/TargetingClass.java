package mindustry.world.blocks.defense.turrets;

/** Used only by turrets that target both air and ground targets **/
public enum TargetingClass {
    ANY,               // Any Air or Ground target enemy
    AIR_FIRST,         // Focus *first* on Air targets enemies
    GROUND_FIRST;      // Focus *first* on Ground targets enemies
}
