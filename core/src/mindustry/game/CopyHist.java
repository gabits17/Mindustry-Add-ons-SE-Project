package mindustry.game;

import java.util.Iterator;

public interface CopyHist {

    /**
     * Add a copied schematic to the history
     * @param scheme: the selected schematic
     */
    void copy(Schematic scheme);

    /**
     * Gets the schematic of the last visited position
     */
    Schematic getCurrent();

    /**
     * Gets the next Schematic stored in the history
     */
    Schematic getNext();

    /**
     * Gets the previous Schematic stored in the history
     */
    Schematic getPrevious();

    /**
     * Checks if the history is empty
     */
    boolean isEmpty();
}
