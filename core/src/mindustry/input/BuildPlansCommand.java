package mindustry.input;

import arc.struct.Seq;
import mindustry.entities.units.BuildPlan;

import static mindustry.Vars.player;

public class BuildPlansCommand implements Command{

    private Seq<BuildPlan> plans;
    private InputHandler input;

    protected BuildPlansCommand(Seq<BuildPlan> plans, InputHandler input){
        this.plans = new Seq<>(plans.size);
        for (BuildPlan plan : plans) {
            this.plans.add(plan.copy());
        }
        this.input = input;
    }


    @Override
    public void execute() {
        this.plans = this.input.flushPlans(this.plans);
    }

    @Override
    public void undo() {
        for (BuildPlan plan : this.plans ) {
            this.input.tryBreakBlock(plan.x, plan.y);
            player.unit().removeBuild(plan.x, plan.y, false);
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
