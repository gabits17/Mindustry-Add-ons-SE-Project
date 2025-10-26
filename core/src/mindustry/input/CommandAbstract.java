package mindustry.input;

public abstract class CommandAbstract implements Command {
    public boolean canUndo() {
        return false;
    }

    public boolean canRedo() {
        return false;
    }

    public void undo() {
        //Do nothing if not override
    }
}
