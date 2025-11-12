package mindustry.input;

import arc.struct.Seq;
import mindustry.entities.units.BuildPlan;

import static mindustry.Vars.schematics;

public class RemoveSelectionCommand extends CommandAbstract {

    private InputHandler input;
    private int selectX, selectY, cursorX, cursorY, maxSize;
    private boolean flush;
    private Seq<BuildPlan> removed;

    protected RemoveSelectionCommand(int selectX,
                                     int selectY,
                                     int cursorX,
                                     int cursorY,
                                     int maxSize,
                                     boolean flush,
                                     InputHandler input) {

        System.out.println(selectX + " " + selectY);
        System.out.println(cursorX + " " + cursorY);
        this.selectX = selectX;
        this.selectY = selectY;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.maxSize = maxSize;
        this.flush = flush;
        this.input = input;
    }

    @Override
    public void execute() {
        //Should this take into account the first removal??? should it take into account the last removal??? This is probably is not relevant as thr commands are a stack
        //This is important as it wont take non built plans into account maybe this should either see a little more about remove selection
        //Ok this now takes non built things into account
        this.removed = this.input.removeSelection(this.selectX, this.selectY, this.cursorX, this.cursorY, this.flush, this.maxSize);
        removed.reverse();
    }

    @Override
    public void undo() {
        this.input.flushPlans(removed);
    }

    @Override
    public boolean canUndo() {
        return this.removed != null;
    }

    @Override
    public boolean canRedo() {
        return true;
    }
}
