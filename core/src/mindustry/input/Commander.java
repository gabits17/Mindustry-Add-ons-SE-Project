package mindustry.input;

import arc.struct.Seq;


public class Commander {

    private static final int maxDone = 10;
    private static final int maxUndone = 10;

    //Holds the commands that have been executed
    private Seq<Command> doneCommands;
    //Holds the commands that have been undone
    private Seq<Command> undoneCommands;

    protected  Commander(){
        doneCommands = new Seq<>(true, maxDone);
        undoneCommands = new Seq<>(true, maxUndone);
    }

    /**
     * Adds a command to the top of the done stack
     * @param command command to add
     */
    private void addCommand(Command command){
        if(doneCommands.size == maxDone){
            doneCommands.remove(0);
        }
        doneCommands.add(command);
    }

    /**
     * Adds a command to the top of the undone stack
     * @param command command to add
     */
    private void addUndone(Command command){
        if(undoneCommands.size == maxUndone){
            undoneCommands.remove(0);
        }
        undoneCommands.add(command);
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
     * @param command command to execute
     */
    protected void execute(Command command) {
        addCommand(command);
        executeTop();
    }

    /**
     *
     * @return True if the undone stack is not empty
     */
    protected boolean hasUndone(){
        return !undoneCommands.isEmpty();
    }

    /**
     *
     * @return True if the done stack is not empty
     */
    protected boolean hasDone(){
        return !doneCommands.isEmpty();
    }

    /**
     * Undoes the topmost done command, and adds it to the undone stack
     */
    protected void undoTop(){
        try {
            if (doneCommands.peek().canUndo()) {
                doneCommands.peek().undo();
                addUndone(doneCommands.pop());
            } else {
                doneCommands.pop();
                //call the undo again probably until an undoable is found?
            }
        } catch (IllegalStateException e) {
            System.out.println("Nothing to undo");
        }
    }

    /**
     * Redoes the topmost command, and adds it to the done stack
     */
    protected void redoTop(){
        try {
            if (undoneCommands.peek().canRedo()) {
                undoneCommands.peek().execute();
                addCommand(undoneCommands.pop());
            } else {
                undoneCommands.pop();
                //call the redo again probably until an redoable is found?
            }
        } catch (IllegalStateException e) {
            System.out.println("Nothing to redo");
        }
    }

}
