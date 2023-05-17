package szoftlab.ui;

import szoftlab.main.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndView extends JFrame {

    EndGamePanel endpanel = new EndGamePanel();
    JButton exitButton = new JButton("Exit!");
    JLabel winnerNameLabel;

    /**
     * The Game Over view, shows the winner
     * @param name The winner's name.
     */
    public EndView(String name) {

        winnerNameLabel = new JLabel(name);
        winnerNameLabel.setFont(new Font("Calibri", Font.BOLD, 75));
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        endpanel.setLayout(new BoxLayout(endpanel, BoxLayout.Y_AXIS));
        endpanel.add(Box.createRigidArea(new Dimension(0, 2)));
        endpanel.add(Box.createVerticalStrut(350));
        endpanel.add(Box.createHorizontalStrut(310));
        endpanel.add(winnerNameLabel);
        endpanel.add(Box.createVerticalStrut(200));
        endpanel.add(exitButton);

        endpanel.add(Box.createVerticalStrut(125));
        endpanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(endpanel);
        setResizable(false);
        setSize(1280, 720);
        setVisible(true);

    }


}

/**
 * Panel which it gets painted on the EndView.
 */
class EndGamePanel extends JPanel {

    private final ImageIcon img = new ImageIcon("resources/score.png");

    /**
     * Responsible for painting the screen.
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img.getImage(), 0, 0, null);
    }
}