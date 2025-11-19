package mindustry.input;

import mindustry.gen.Building;
import mindustry.gen.Call;

import static mindustry.Vars.player;

/**
 * Command used to rotate a building
 */
public class BlockRotateCommand implements Command {

    private Building cursorBuild;
    private boolean direction;

    public BlockRotateCommand(Building cursorBuild, boolean direction){
        this.cursorBuild = cursorBuild;
        this.direction = direction;
    }


    @Override
    public void execute() {
        Call.rotateBlock(player, this.cursorBuild, this.direction);
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public boolean canRedo() {
        return true;
    }

    @Override
    public void undo() {
        Call.rotateBlock(player, this.cursorBuild, !this.direction);
    }
}
