package mindustry.input;

import arc.struct.Seq;
import mindustry.entities.units.BuildPlan;

import static mindustry.Vars.schematics;

public class RemoveSelectionCommand extends CommandAbstract {

    private InputHandler input;
    private int selectX, selectY, cursorX, cursorY, maxSize;
    private Seq<BuildPlan> removed;

    protected RemoveSelectionCommand(int selectX,
                                     int selectY,
                                     int cursorX,
                                     int cursorY,
                                     int maxSize,
                                     InputHandler input) {

        this.removed = new Seq<>();
        var schematic = schematics.create(selectX, selectY, cursorX, cursorY);
        double d1 = selectX + cursorX;
        d1 /= 2;
        double d2 = selectY + cursorY;
        d2 /= 2;
        this.removed.addAll(schematics.toPlans(schematic, (int) Math.ceil(d1), (int) Math.ceil(d2)));
        this.selectX = selectX;
        this.selectY = selectY;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.maxSize = maxSize;
        this.input = input;
    }

    @Override
    public void execute() {
        this.input.removeSelection(this.selectX, this.selectY, this.cursorX, this.cursorY, this.maxSize);
    }

    @Override
    public void undo() {
        this.input.flushPlans(removed);
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
