package mindustry.input;

public interface Command {
    /**
     *
     * @return True if the command can undo its action, false if else
     */
    boolean canUndo();

    /**
     *
     * @return True if the command can redo its action, false if else
     */
    boolean canRedo();

    /**
     * Executes the command's implemented action
     */
    void execute();

    /**
     * Undoes the command's implemented action
     */
    void undo();
}
