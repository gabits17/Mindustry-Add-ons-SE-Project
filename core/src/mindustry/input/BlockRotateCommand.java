package mindustry.input;

import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Player;
public class BlockRotateCommand extends CommandAbstract{

    private Player player;
    private Building cursorBuild;
    private boolean direction;

    public BlockRotateCommand(Player player, Building cursorBuild, boolean direction){
        this.player = player;
        this.cursorBuild = cursorBuild;
        this.direction = direction;
    }


    @Override
    public void execute() {
        Call.rotateBlock(this.player, this.cursorBuild, this.direction);
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public void undo() {
        Call.rotateBlock(this.player, this.cursorBuild, !this.direction);
    }
}
