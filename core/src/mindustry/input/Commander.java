package mindustry.input;

import arc.struct.Seq;
import mindustry.Vars;


public class Commander {

    private static final int maxDone = 10;
    private static final int maxUndone = 10;

    //Holds the commands that have been executed
    private Seq<Command> doneCommands;
    //Holds the commands that have been undone
    private Seq<Command> undoneCommands;

    protected Commander() {
        doneCommands = new Seq<>(true, maxDone);
        undoneCommands = new Seq<>(true, maxUndone);
    }

    /**
     * Adds a command to the top of the done stack
     *
     * @param command command to add
     */
    private void addCommand(Command command) {
        if (isDoneFull()) {
            doneCommands.remove(0);
        }
        doneCommands.add(command);
    }

    private boolean isDoneFull() {
        return doneCommands.size == maxDone;
    }

    /**
     * Adds a command to the top of the undone stack
     *
     * @param command command to add
     */
    private void addUndone(Command command) {
        if (isUndoneFull()) {
            undoneCommands.remove(0);
        }
        undoneCommands.add(command);
    }

    private boolean isUndoneFull() {
        return undoneCommands.size == maxUndone;
    }

    /**
     * Clears the command history
     */
    public void clear() {
        doneCommands.clear();
        undoneCommands.clear();
    }

    /**
     * Executes the topmost command in the done stack
     */
    private void executeTop() {
        doneCommands.peek().execute();
    }

    /**
     * Used to record a command and execute it
     *
     * @param command command to execute
     */
    protected void execute(Command command) {
        addCommand(command);
        executeTop();
    }

    /**
     * Undoes the topmost done command, and adds it to the undone stack
     */
    protected void undoTop() {
        if (doneCommands.peek().canUndo()) {
            doneCommands.peek().undo();
            addUndone(removeTopDone());
        } else {
            removeTopDone();
        }
    }

    private Command removeTopDone() {
        return doneCommands.pop();
    }

    /**
     * Redoes the topmost command, and adds it to the done stack
     */
    protected void redoTop() {
        if (undoneCommands.peek().canRedo()) {
            undoneCommands.peek().execute();
            addCommand(removeTopUndone());
        } else {
            removeTopUndone();
        }
    }

    private Command removeTopUndone() {
        return undoneCommands.pop();
    }

}
