package mindustry.input;

public interface Command {
    boolean canUndo();
    boolean canRedo();

    void execute();
    void undo();
}
