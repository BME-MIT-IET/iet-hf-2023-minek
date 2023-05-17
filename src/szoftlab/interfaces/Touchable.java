package szoftlab.interfaces;

import szoftlab.main.Virologist;

/**
 * Touchable interface. Used to create Touchable stuff.
 */
public interface Touchable {
    /**
     * Adds this to the given virologist's touched list.
     * @param v The virologist who touched the touchable.
     */
    public void TouchedBy(Virologist v);
}