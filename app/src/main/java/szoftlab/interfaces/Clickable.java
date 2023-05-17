package szoftlab.interfaces;

import javax.swing.event.MouseInputAdapter;
import szoftlab.ui.GameView;
import java.awt.event.MouseEvent;

/**
 * Needed for GUI these are the Objects that you can click on the screen.
 */
public interface Clickable {
    /**
     * Called when the mouse is clicked.
     * @param view GameView
     * @return
     */
    public MouseInputAdapter OnPress(GameView view);
}
