package mindustry.input;

public interface BlockOperation {
    boolean canUndo();
    boolean canRedo();
    void undo();
    void redo();
}
