package mindustry.input;

import arc.struct.Seq;


public class Commander {

    private static final int maxDone = 10;
    private static final int maxUndone = 10;

    private Seq<Command> doneCommands;
    private Seq<Command> undoneCommands;

    protected  Commander(){
        doneCommands = new Seq<>(true, maxDone);
        undoneCommands = new Seq<>(true, maxUndone);
    }

    private void addCommand(Command command){
        if(doneCommands.size == maxDone){
            doneCommands.remove(0);
        }
        doneCommands.add(command);
    }

    private void addUndone(Command command){
        if(undoneCommands.size == maxUndone){
            undoneCommands.remove(0);
        }
        undoneCommands.add(command);
    }

    public void clear() {
        doneCommands.clear();
        undoneCommands.clear();
    }

    private void executeTop() {
        doneCommands.peek().execute();
    }

    protected void execute(Command command) {
        addCommand(command);
        executeTop();
    }

    protected boolean hasUndone(){
        return undoneCommands.size != 0;
    }
    protected boolean hasDone(){
        return doneCommands.size != 0;
    }

    protected void undoTop(){
        try {
            if (doneCommands.peek().canUndo()) {
                doneCommands.peek().undo();
                addUndone(doneCommands.pop());
            } else {
                doneCommands.pop();
            }
        } catch (IllegalStateException e) {
            System.out.println("Nothing to undo");
        }
    }

    protected void redoTop(){
        try {
            if (undoneCommands.peek().canRedo()) {
                undoneCommands.peek().execute();
                addCommand(undoneCommands.pop());
            } else {
                undoneCommands.pop();
            }
        } catch (IllegalStateException e) {
            System.out.println("Nothing to redo");
        }
    }

}
