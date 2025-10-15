package mindustry.input;

import arc.struct.*;

public class BlockOperationStack {
    private static final int maxStackSize = 10;
    private Seq<BlockOperation> doneStack;
    private Seq<BlockOperation> undoneStack;

    protected BlockOperationStack(){
        doneStack = new Seq<>(maxStackSize);
        undoneStack = new Seq<>(maxStackSize);
    }

    protected void add(BlockOperation op) {
        if (!op.canUndo()) return;

        doneStack.add(op);

        if (doneStack.size >= maxStackSize) {
            doneStack.remove(0);
        }

    }

    protected boolean canUndo(){
        return !doneStack.isEmpty();
    }

    protected boolean canRedo(){
        return  !undoneStack.isEmpty();
    }

    protected void undo() {
        if (!canUndo()) return;
        doneStack.pop().undo();
    }

    protected void redo() {
        if (!canRedo()) return;

        undoneStack.pop().redo();
    }

    private void addRedo(BlockOperation op){
        if (!op.canRedo()) return;

        undoneStack.add(op);

        if (doneStack.size >= maxStackSize) {
            doneStack.remove(0);
        }
    }



}
