package szoftlab.ui;

import javax.swing.*;

import szoftlab.main.Main;

import java.awt.*;
import java.util.ArrayList;

/**
 * A class for the menu.
 */
public class MenuView extends JFrame{
    JPanel panel = new JPanel();

    /**
     * Constructor for the MenuView.
     */
    public MenuView(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        ImageIcon img = new ImageIcon("resources/menu.png");
        JPanel background = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                g.drawImage(img.getImage(), 0, 0, null);
            }
        };
        JPanel panel = new JPanel();
        JButton start = new JButton("Start");
        JButton exit = new JButton("Exit");
        JTextField playerName = new JTextField("Player Name");
        playerName.setAlignmentX(CENTER_ALIGNMENT);
        JButton addPlayer = new JButton("Add Player");
        DefaultListModel<String> list = new DefaultListModel<String>();  
        JList<String> playerList = new JList<String>(list);

        start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(list.size() > 0){
                    ArrayList<String> names = new ArrayList<>();
                    for(int i = 0; i < list.size(); i++){
                        names.add(list.get(i));
                    }
                    Main.view = new GameView(names);
                    dispose();
                }
            }
        });
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });
        addPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list.addElement(playerName.getText());
            }
        });
        //panel.add(Box.createVerticalStrut(100));
        panel.add(start);
        panel.add(exit);
        panel.add(playerName);
        panel.add(addPlayer);
        panel.add(playerList);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setPreferredSize(new Dimension(100, 400));
        panel.setMaximumSize(new Dimension(100, 400));
        panel.setOpaque(false);
        background.add(panel);
        //REEEE
        //background.setLayout(new BorderLayout());
        panel.setLocation(150, 200);

        getContentPane().add(background);
        setSize(1280, 720);
        setVisible(true);
        setResizable(false);
        setTitle("Creative_menu_name_goes_here");
        setIconImage(new ImageIcon("/resources/viro1.png").getImage());
    }
}
