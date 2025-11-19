package mindustry.game;

import arc.Events;

import java.util.Iterator;
// import mindustry.input.DesktopInput;

import java.util.ArrayList;
import java.util.List;

/**
 * TO-DO:
 * - Events listener basically -> the event listeners are all
     *      handled in th core/src/mindustry/input/DesktopInput.java class, so
     *      maybe we just make methods that are then called in that class?
 * - Schematics is the correct one to use? Or do we need another class -> I think so
 *          because in the event handles for the desktop inputs, schematics are also used
 *          to represent what is being selected
 * - KeyBinding: core/src/mindustry/input/Binding.java
 *              Useful to define the keys we want to use to implement the functionality and other funny things
 * - Assure we are following correct processes
 * - Choose the best design pattern to implement this
 *
 *
 * ...
 * - Change core/src/mindustry/input/DesktopInput.java, to call the CopyHist functions upon proper input
 */
public class CopyHistClass implements CopyHist {

    /** Maximum size of the Seq. */
    private static final int MAX_SIZE = 5;

    private static final int INIT_POS = 0;

    private static final int LAST_POS = 4;

    private static final int PLUS = 1;

    // history of copies
    private final List<Schematic> history;

    private int size;

    private int lastPos;

    public CopyHistClass() {
        // check if arrayList, LinkedList or Seq
        this.history = new ArrayList<>(MAX_SIZE);
        this.size = 0;
        this.lastPos = INIT_POS;

        Events.on(EventType.ClientLoadEvent.class, event -> {
            // to-do
        });
    }


    @Override
    public void copy(Schematic scheme){
        if (this.size != INIT_POS)
            queueIt();

        if (!history.contains(scheme)){
            history.add(INIT_POS, scheme);
            this.size++;
        }
    }

    private void queueIt(){
        for (int i = INIT_POS; i == this.size - 1; i++){
            history.add(i + PLUS, history.get(i));
        }
    }


    @Override
    public Iterator<Schematic> Iterator(){
        return history.iterator();
    }

    @Override
    public Schematic get(int pos) {
        lastPos = (pos + lastPos) % MAX_SIZE;
        return history.get(lastPos);
    }

    @Override
    public Schematic get(){
        return history.get(lastPos);
    }

    @Override
    public Schematic getNext(){
        lastPos++;
        if(lastPos == size){
            lastPos = 0;
        }
        return history.get(lastPos);
    }

    @Override
    public Schematic getPrevious(){
        lastPos--;
        if(lastPos < 0){
            lastPos = size - 1;
        }
        return history.get(lastPos);
    }

    @Override
    public boolean isEmpty(){
        return history.isEmpty();
    }
}
