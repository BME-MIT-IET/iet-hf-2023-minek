package szoftlab.ui;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlayerNameTextField extends JTextField {
    private final String placeholderText;

    PlayerNameTextField(String name) {
        this.placeholderText = name;
        this.reset();
        this.addFocusListener(new PlayerNameTextFieldFocusListener());
    }

    public void reset() {
        this.setText(placeholderText);
    }

    private class PlayerNameTextFieldFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            if (PlayerNameTextField.this.getText().equals(placeholderText))
                PlayerNameTextField.this.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (PlayerNameTextField.this.getText().equals(""))
                PlayerNameTextField.this.reset();
        }
    }
}
