package mindustry.input;

public class RemoveSelectionCommand extends CommandAbstract {

    private InputHandler input;
    private int selectX, selectY, cursorX, cursorY, maxSize;

    protected RemoveSelectionCommand(int selectX,
                                     int selectY,
                                     int cursorX,
                                     int cursorY,
                                     int maxSize,
                                     InputHandler input) {
        this.selectX = selectX;
        this.selectY = selectY;
        this.cursorX = cursorX;
        this.cursorY = cursorY;
        this.maxSize = maxSize;
        this.input = input;
    }

    @Override
    public void execute() {
        this.input.removeSelection(this.selectX, this.selectY, this.cursorX, this.cursorY, this.maxSize);
    }
}
