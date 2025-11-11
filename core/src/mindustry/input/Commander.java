package mindustry.input;

import arc.struct.Seq;

public class Commander {
    private Seq<Command> commands;
    private Seq<Command> undone;

    protected  Commander(){
        commands = new Seq<>();
        undone = new Seq<>();
    }

    private void addCommand(Command command){
        commands.add(command);
    }

    private void executeTop() {
        commands.peek().execute();
    }

    protected void execute(Command command) {
        addCommand(command);
        executeTop();
    }

    protected void undoTop(){
        try {
            if (commands.peek().canUndo()) {
                commands.peek().undo();
                addUndone(commands.pop());
                System.out.println("Undid");
            } else {
                commands.pop();
                System.out.println("Command does not undo");
            }
        } catch (IllegalStateException e) {
            System.out.println("Nothing to undo");
        }
    }

    protected void redoTop(){
        try {
            if (undone.peek().canRedo()) {
                undone.peek().execute();
                addCommand(undone.pop());
            } else {
                undone.pop();
            }
        } catch (IllegalStateException e) {
            System.out.println("Nothing to redo");
        }
    }

    private void addUndone(Command command){
        undone.add(command);
    }


}
