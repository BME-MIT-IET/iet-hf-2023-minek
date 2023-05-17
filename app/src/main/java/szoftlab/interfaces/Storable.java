package szoftlab.interfaces;

import szoftlab.main.Virologist;
import szoftlab.map.Field;

/**
 * Storable interface. Used to create Storable stuff. Implements the touchable interface.
 */
public interface Storable extends Touchable {
    /**
     * Stores the storable in the given virologist and removes it from the field.
     * @param v The virologist to store the storable in.
     * @param from The field the storable is taken from.
     */
    public void StoreIn(Virologist v,Field from);
}