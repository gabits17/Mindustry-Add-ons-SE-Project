package mindustry.input;

import arc.struct.Seq;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Schematic;

import static mindustry.Vars.player;
import static mindustry.Vars.schematics;

public class BuildPlansCommand extends CommandAbstract{

    private Seq<BuildPlan> plans;
    private InputHandler input;

    protected BuildPlansCommand(Seq<BuildPlan> plans, InputHandler input){
        this.plans = new Seq<>(plans.size);
        for (BuildPlan plan : plans) {
            this.plans.add(plan.clone());
        }
        System.out.println(this.plans.get(0).x + " " + this.plans.get(0).y);
        this.input = input;
    }


    @Override
    public void execute() {
        this.input.flushPlans(this.plans);
    }

    @Override
    public void undo() {
        System.out.println(this.plans.get(0).x + " " + this.plans.get(0).y);
        for (BuildPlan plan : this.plans ) {
            this.input.tryBreakBlock(plan.x, plan.y);
        }
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public boolean canRedo() {
        return true;
    }
}
