package mindustry.game;

import arc.Events;
import arc.struct.Seq;
import java.util.Iterator;
// import mindustry.input.DesktopInput;

/**
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 */

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
public class CopyHist {

    /** Maximum size of the Seq. */
    private static final int MAX_SIZE = 5;

    private static final int INIT_POS = 0;

    // history of copies
    private final Seq<Schematic> history;

    private int lastPos;

    public CopyHist() {
        // check if arrayList, LinkedList or Seq
        this.history = new Seq<>(MAX_SIZE);
        this.lastPos = INIT_POS;

        Events.on(EventType.ClientLoadEvent.class, event -> {
            // to-do
        });
    }

    /**
     * Add a copied schematic to the history
     * @param scheme: the selected schematic
     */
    public void copy(Schematic scheme){
        if (history.size == MAX_SIZE ){
            /** is this logic correct?
            for(int i = 0; i < MAX_SIZE - 1; i++){
                Schematic iScheme = history.get(i + 1);
                history.set(i, iScheme);
            }*/
            history.remove(INIT_POS);
        }

        history.addUnique(scheme);
    }

    /**
     * Returns an Iterator of the schematics saved
     */
    public Iterator<Schematic> Iterator(){
        return history.iterator();
    }

    /**
     * Gets the schematic in a given position
     * @param pos: the position of the desired schematic
     */
    public Schematic get(int pos) {
        lastPos = (pos + lastPos) % MAX_SIZE;
        return history.get(lastPos);
    }

    /**
     * Gets the schematic of the last visited position
     */
    public Schematic get(){
        return history.get(lastPos);
    }
}
