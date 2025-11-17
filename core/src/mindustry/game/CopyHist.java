package mindustry.game;

import arc.Events;
import arc.struct.Seq;

/**
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
 */

/**
 * TO-DO:
 * - Events listener basically
 * - Schematics is the correct one to use? Or do we need another class
 * - Assure we are following correct processes
 * - Choose the best design pattern to implement this
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
            // to-do
            return;
        }

        history.add(scheme);
    }

    /**
     * Place a held schematic
     * @param pos: the position of the desired schematic
     */
    public void paste(int pos){
        history.get(pos);
    }
}
