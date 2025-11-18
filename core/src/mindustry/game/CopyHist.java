package mindustry.game;

import arc.Events;
import arc.struct.Seq;
import java.util.Iterator;
import mindustry.input.DesktopInput;

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

    // history of copies
    private final Seq<Schematic> history;

    public CopyHist() {
        // check if arrayList or LinkedList or Seq
        this.history = new Seq<>(MAX_SIZE);

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
            for(int i = 0; i < MAX_SIZE - 1; i++){
                Schematic iScheme = history.get(i + 1);
                history.set(i, iScheme);
            }
            history.remove(MAX_SIZE);
        }

        history.add(scheme);
    }


    /**
     * I don't think this function should exist, because this class probably
     * shouldn't concern itself with pasting, and actually building anything
     * At least I thought it was just to save the copied history, and maybe it passes that
     * responsibility to whoever accesses the history, maybe in core/src/mindustry/input/DesktopInput.java
     */
    /**
     * Place a held schematic
     * @param pos: the position of the desired schematic
     */
    public void paste(int pos){
        history.get(pos);
    }


    /**
     * Returns an Iterator of the schematics saved
     */
    public Iterator<Schematic> Iterator(){
        return history.iterator();
    }

    // Now that I see it this is basically paste
    /**
     * Gets the schematic in a given position
     * @param pos: the position of the desired schematic
     */
    public Schematic get(int pos) {
        return history.get((pos%MAX_SIZE));
    }
}
