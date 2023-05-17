package szoftlab.ui;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.jar.Attributes.Name;

import javax.swing.*;
import javax.swing.SwingUtilities;

import szoftlab.agent.Agent;
import szoftlab.agent.Bear;
import szoftlab.agent.GeneticCode;
import szoftlab.agent.Numb;
import szoftlab.agent.Protection;
import szoftlab.equipment.Cape;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Clickable;
import szoftlab.interfaces.Storable;
import szoftlab.main.GameController;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.map.BearLaboratory;
import szoftlab.map.Empty;
import szoftlab.map.Field;
import szoftlab.map.Laboratory;
import szoftlab.map.Shelter;
import szoftlab.map.Warehouse;
import szoftlab.material.AminoAcid;
import szoftlab.material.Material;
import szoftlab.material.Nucleotid;

/**
 * Class that shows the game state/ shows the game itself.
 */
public class GameView extends JFrame {

    JLabel status = new JLabel("");
    Component strut =  Box.createHorizontalStrut(900);
    JLabel aminoCount = new JLabel("0");
    JLabel nucleotideCount = new JLabel("0");
    JLabel codeCount = new JLabel("0");
    JPanel statusPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JPanel mapPanel = new JPanel();
    JPanel content = new JPanel();
    HashMap<Virologist,VirologistButton> virologistMap = new HashMap<>();

    /**
     * GameView constructor with list of names.
     * @param names Players names list.
     */
    public GameView(ArrayList<String> names) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);

        InitStatusPanel();
        InitMenuPanel();
       
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        mapPanel.setPreferredSize(new Dimension(1280, 600));
        mapPanel.setLocation(0,120);
        mapPanel.setBounds(0,120, 1280, 600);
        mapPanel.setLayout(null);
        mapPanel.setBackground(new Color(71, 69, 71));


        GameController.Single.NewGame(names.size());
        int i = 0;
        for (Virologist v : GameController.Single.players) {
            CreateButton(names.get(i), v);
            i++;
        }
        CreateMap();

        content.add(statusPanel);
        content.add(mapPanel);
        content.add(menuPanel);
        setTitle("Creative_game_name_goes_here");
        setIconImage(new ImageIcon("/resources/viro1.png").getImage());
        setVisible(true);
        add(content);

        GameController.Single.StartGame();
    }

    /**
     * Initializes the game menu
     */
    void InitMenuPanel() {
        menuPanel.setPreferredSize(new Dimension(1280, 50));
        menuPanel.setLocation(0,670);
        menuPanel.setBackground(new Color(85, 2, 120));
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton craft = new JButton("Craft");
        craft.setForeground(Color.WHITE);
        craft.setContentAreaFilled(false);
        craft.setBorderPainted(false);
        craft.setFont(new Font("Arial", Font.BOLD, 20));
        craft.setFocusPainted(false);
        craft.addActionListener(e -> {
            ShowCodes(GameController.Single.currentPlayer,(code)->{
                if(GameController.Single.currentPlayer.CraftAgent(code))
                    SetStatus("Crafted agent: " + code.GetAgent().GetEffect().getClass().getSimpleName());
                else
                    SetStatus("Not enough resources");
            });
        });

        JButton equipments = new JButton("Equipments");
        equipments.setForeground(Color.WHITE);
        equipments.setContentAreaFilled(false);
        equipments.setBorderPainted(false);
        equipments.setFont(new Font("Arial", Font.BOLD, 20));
        equipments.setFocusPainted(false);
        equipments.addActionListener(e -> {
            ShowInventory(GameController.Single.currentPlayer, (equipment)->{
                GameController.Single.currentPlayer.EquipEquipment(equipment);
            });
        });


        menuPanel.add(craft);
        menuPanel.add(Box.createHorizontalStrut(1000));
        menuPanel.add(equipments);

    }

    /**
     * Initializes the game's status panel
     */
    void InitStatusPanel(){
        statusPanel.setPreferredSize(new Dimension(1280, 70));
        statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(85, 2, 120));
        statusPanel.add(status);
        //amino acid count
        var i = new ImageIcon("resources/amino.png");
        i.setImage(i.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel amino = new JLabel(i);
        amino.setPreferredSize(new Dimension(50,50));

        //nucleotide count
        i = new ImageIcon("resources/nucleotid.png");
        i.setImage(i.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel nucleo = new JLabel(i);
        nucleo.setPreferredSize(new Dimension(50,50));

        //genetic code count
        i = new ImageIcon("resources/dna.png");
        i.setImage(i.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel dna = new JLabel(i);
        dna.setPreferredSize(new Dimension(50,50));

        aminoCount.setForeground(Color.WHITE);
        aminoCount.setFont(new Font("Arial", Font.BOLD, 20));
        nucleotideCount.setForeground(Color.WHITE);
        nucleotideCount.setFont(new Font("Arial", Font.BOLD, 20));
        codeCount.setForeground(Color.WHITE);
        codeCount.setFont(new Font("Arial", Font.BOLD, 20));
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Arial", Font.BOLD, 20));

        statusPanel.add(amino);
        statusPanel.add(aminoCount);
        statusPanel.add(nucleo);
        statusPanel.add(nucleotideCount);
        statusPanel.add(dna);
        statusPanel.add(codeCount);
        statusPanel.add(strut);
        statusPanel.add(status);

    }

    /**
     * Shows the in-game popups.
     * @param player to whom it shows the popup.
     * @param target to who the player can interract.
     */
    public void ShowPopup(Virologist player, Virologist target){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem item2 = new JMenuItem("Virologist: "+virologistMap.get(target).name);
        item2.setEnabled(false);
        popup.add(item2);
        if(player.craftedAgents.size() > 0){
            JMenuItem item = new JMenuItem("Throw an agent");
            item.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ShowAgents(player, (agent) -> {
                        player.ThrowAgent(agent, target);
                        EndTurn();
                    });
                }
            });
            popup.add(item);
        }
        if(target.GetEquipments().size() > 0 && target.activeEffects.stream().anyMatch(effect -> effect instanceof Numb)){
            JMenuItem item = new JMenuItem("Steal an equipment");
            item.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ShowInventory(target, (equipment) -> {
                        player.StealEquipment(target, equipment);
                        EndTurn();
                    });
                }
            });
            popup.add(item);
        }
        var pos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(pos,this);
        popup.show(this,pos.x,pos.y);
    }

    /**
     * Shows the popup field for the player.
     * @param player to whom it shows.
     * @param target to who it can interract the player.
     */
    public void ShowPopupField(Virologist player, Field target){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem item2 = new JMenuItem("Field: " + target.getClass().getSimpleName());
        item2.setEnabled(false);
        popup.add(item2);
        JMenuItem item = new JMenuItem("Touch this field.");
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player.TouchField();
                EndTurn();
                popup.setVisible(false);
            }
        });
        popup.add(item);        
        if(player.touchedStorables.size() > 0){
            JMenuItem item3 = new JMenuItem("Interact with this field.");
            item3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    for (Storable s : player.touchedStorables) {
                        int eqs = player.GetEquipments().size();
                        int codes = player.GetKnownGeneticCodes().size();
                        int mats = player.materials.size();
                        player.PickUp(s);
                        if(player.GetEquipments().size() > eqs)
                            SetStatus("Picked up equipment: " + player.GetEquipments().get(player.GetEquipments().size()-1).getClass().getSimpleName());
                        if(player.GetKnownGeneticCodes().size() > codes)
                            SetStatus("Learned genetic code: " + player.GetKnownGeneticCodes().get(player.GetKnownGeneticCodes().size()-1).GetAgent().GetEffect().getClass().getSimpleName());
                        if(player.materials.size() > mats)
                            SetStatus("Picked up material: " + player.materials.get(player.materials.size()-1).getClass().getSimpleName());
                        EndTurn();
                        popup.setVisible(false);
                    }
                }
            });
            popup.add(item3);      
        }
        var pos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(pos,this);
        popup.show(this,pos.x,pos.y);
    }

    /**
     * Shows a popup for the player.
     * @param player to whom it shows the popup.
     * @param target to which the player can interract with.
     */
    public void ShowPopupNeighbor(Virologist player, Field target){
        JPopupMenu popup = new JPopupMenu();
        String name = target.getClass().getSimpleName();
        JMenuItem item2 = new JMenuItem("Field: " + ((name.equals("BearLaboratory")) ? "Laboratory" : name));
        item2.setEnabled(false);
        popup.add(item2);
        JMenuItem item = new JMenuItem("Move here");
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player.MoveTo(target);
                EndTurn();
                popup.setVisible(false);
            }
        });
        popup.add(item);
        var pos = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(pos,this);
        popup.show(this,pos.x,pos.y);
    }

    /**
     * Shows the players inventory.
     * @param player to whom it shows their inventory
     * @param callback callback for the Equipments.
     */
    public void ShowInventory(Virologist player, Consumer<Equipment> callback){
        if(player.GetEquipments().size() == 0 && player.GetEquipped().size() == 0){
            SetStatus("No equipments in inventory.");
            return;
        }
        JPopupMenu popup = new JPopupMenu();
        JPanel panel = new InventoryPanel();
        int size = 45;
        int i = 0;
        for(Equipment e : player.GetEquipped()){
            var img = new ImageIcon("resources/"+e.getClass().getSimpleName()+"_on.png");
            img.setImage(img.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
            JButton button = new JButton(img);
            button.setPreferredSize(new Dimension(size, size));
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.addActionListener(event -> {
                callback.accept(e);
                popup.setVisible(false);
            });
            panel.add(button);
            i++;
        }
        for(Equipment e : player.GetEquipments()){
            var img = new ImageIcon("resources/"+e.getClass().getSimpleName()+".png");
            img.setImage(img.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
            JButton button = new JButton(img);
            button.setPreferredSize(new Dimension(size, size));
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.addActionListener(event -> {
                callback.accept(e);
                popup.setVisible(false);
            });
            panel.add(button);
            i++;
        }
        

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(i < 5? i*50 : 250, (int)(Math.ceil(i/5.0)*50)));
        popup.add(panel);
        popup.setBorderPainted(false);
        popup.show(this, 1270-(i < 5? i*50 : 250), 657-(int)(Math.ceil(i/5.0)*50));
    }

    /**
     * Shows the learned genetic codes of the player.
     * @param player to whom it shows.
     * @param callback callback for the genetic codes.
     */
    public void ShowCodes(Virologist player, Consumer<GeneticCode> callback){
        if(player.GetKnownGeneticCodes().size() == 0){
            SetStatus("No known codes");
            return;
        }
        JPopupMenu popup = new JPopupMenu();
        JPanel panel = new JPanel();
        for(GeneticCode code : player.GetKnownGeneticCodes()){
            JButton button = new JButton(code.GetAgent().GetEffect().getClass().getSimpleName());
            button.setPreferredSize(new Dimension(250, 60));
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setForeground(Color.WHITE);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            ArrayList<Material> listmat = code.GetRecipe();
            ArrayList<String> listnames = new ArrayList<String>();
            ArrayList<String> materialnames = new ArrayList<String>();
            materialnames.add("AminoAcid");
            materialnames.add("Nucleotid");

            for(int k = 0; k<listmat.size();++k){
                listnames.add(listmat.get(k).getClass().getSimpleName());
            }

            button.addActionListener(e -> {
                callback.accept(code);
                popup.setVisible(false);
            });
            CraftSidePanel buttonpan = new CraftSidePanel();
            buttonpan.setLayout(new FlowLayout());
            buttonpan.add(button);

            JLabel sample = new JLabel(String.valueOf(Collections.frequency(listnames,materialnames.get(0))));
            sample.setFont(new Font("Roboto", Font.PLAIN,25));
            sample.setForeground(Color.GREEN);
            
            JLabel sample2 = new JLabel("Am");
            sample2.setVerticalAlignment(JLabel.BOTTOM);
            JLabel sample3 = new JLabel(String.valueOf(Collections.frequency(listnames,materialnames.get(1))));
            sample3.setFont(new Font("Roboto", Font.PLAIN,25));
            sample3.setForeground(Color.GREEN);
            JLabel sample4 = new JLabel("Nuc");
            sample.setOpaque(false);
            sample2.setOpaque(false);
            sample3.setOpaque(false);
            sample4.setOpaque(false);
            buttonpan.add(sample);
            buttonpan.add(sample2);
            buttonpan.add(sample3);
            buttonpan.add(sample4);

            panel.add(buttonpan);
        }

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        popup.add(panel);
        popup.setPreferredSize(new Dimension(364, player.GetKnownGeneticCodes().size() * 66));
        popup.setBorderPainted(false);
        popup.show(this, 7, 662-player.GetKnownGeneticCodes().size() * 66);
    }

    /**
     * Shows the crafted agents for the player.
     * @param player to whom it shows the crafted agents.
     * @param callback callback for the agents.
     */
    public void ShowAgents(Virologist player, Consumer<Agent> callback){
        if(player.craftedAgents.size() == 0){
            SetStatus("No crafted agents");
            return;
        }
        JPopupMenu popup = new JPopupMenu();
        JPanel panel = new JPanel();
        for(Agent agent : player.craftedAgents){
            var i = new ImageIcon("resources/craft_cell.png");
            JButton button = new JButton(agent.GetEffect().getClass().getSimpleName(),i);
            button.setPreferredSize(new Dimension(364, 60));
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setForeground(Color.WHITE);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.addActionListener(e -> {
                callback.accept(agent);
                popup.setVisible(false);
            });
            panel.add(button);
        }

        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        popup.add(panel);
        popup.setPreferredSize(new Dimension(364, player.GetKnownGeneticCodes().size() * 66));
        popup.setBorderPainted(false);
        popup.show(this, 7, 662-player.GetKnownGeneticCodes().size() * 66);
    }

    /**
     * Updates the Status (setter).
     * @param status to which it updates/sets.
     */
    public void SetStatus(String status){
        this.status.setText(status);
        statusPanel.remove(statusPanel.getComponentCount()-1);
        statusPanel.remove(statusPanel.getComponentCount()-1);
        strut = Box.createHorizontalStrut(1280-180-status.length()*18);
        statusPanel.add(strut);
        statusPanel.add(this.status);
        statusPanel.invalidate();
    }

    /**
     * It's called when a player end their turn.
     */
    void EndTurn(){
        GameController.Single.EndTurn();
        mapPanel.repaint();

        nucleotideCount.setText(""+GameController.Single.currentPlayer.materials.stream().filter(m -> m instanceof Nucleotid).count());
        aminoCount.setText(""+GameController.Single.currentPlayer.materials.stream().filter(m -> m instanceof AminoAcid).count());
        codeCount.setText(""+GameController.Single.currentPlayer.GetKnownGeneticCodes().size());
    }

    /**
     * It creates the map (playing field).
     */
    void CreateMap(){
        String[] types = {
            "laboratory",
            "shelter",
            "warehouse",
            "bearlaboratory",
            "empty"
        };
        final int mapPosX = 280;
        final int mapPosY = 5;
        final int height = 110;
        final int width = 120;
        int offset = 0;
        ArrayList<Field> triangles = new ArrayList<>();
        ArrayList<Field> hexes = new ArrayList<>();

        for(int y = 0; y < 5; y++){
            int ya = 2-Math.abs(2-y); // 1 2 3 2 1
            offset = -60*ya;
            for(int x = 0; x < 6+ya; x++){
                var h = CreateButton(types[(int)(Math.random()*types.length)], "hexagon", mapPosX+x*width+offset, mapPosY+y*height);
                hexes.add(h);
                if(x != 0){
                    triangles.get(triangles.size()-1).AddNeighbor(h);
                    triangles.get(triangles.size()-2).AddNeighbor(h);
                }
                if(x < 5+ya){
                    var td = CreateButton(types[(int)(Math.random()*types.length)], "triangleDown", mapPosX+x*width+offset+90, mapPosY+y*height);
                    var tu = CreateButton(types[(int)(Math.random()*types.length)], "triangleUp", mapPosX+x*width+offset+90, mapPosY+y*height+55);
                    hexes.get(hexes.size()-1).AddNeighbor(td);
                    hexes.get(hexes.size()-1).AddNeighbor(tu);
                    triangles.add(td);
                    triangles.add(tu);
                    if(y != 0){
                        td.AddNeighbor(hexes.get(hexes.size()-(5+ya+(y > 2? 2 : 1))));
                        if(x!=0)triangles.get(triangles.size()-(5+ya+(y > 2? 2 : 1))*2+1).AddNeighbor(h);
                    }
                }
            }
        }
    }

    /**
     * Creates a button on the screen.
     * @param name the name of the button.
     * @param v the Virologist it belongs to
     */
    void CreateButton(String name,Virologist v){
        var i = new ImageIcon("resources/viro1.png");
        i.setImage(i.getImage().getScaledInstance(25, 33, Image.SCALE_SMOOTH));
        VirologistButton b = new VirologistButton(name,v,i,mapPanel);
        b.addMouseListener(v.OnPress(this));
        mapPanel.add(b);
        virologistMap.put(v, b);
    }

    /**
     * Creates a button on the screen.
     * @param fieldType type of field
     * @param shape type of shape
     * @param x x coord
     * @param y y coord
     * @return Field which is connected to the button.
     */
    Field CreateButton(String fieldType,String shape,int x,int y){
        FieldButton b = new FieldButton(fieldType, shape);
        b.setLocation(x, y);
        b.addMouseListener(b.field.OnPress(this));
        mapPanel.add(b);
        return b.field;
    }

    /**
     * When a player wins the game ends.
     * @param winner the winner Virologist
     */
    public void EndGame(Virologist winner) {
        dispose();
        new EndView(virologistMap.get(winner).name);
    }

    
}

/**
 * A class that represents a field that is a button too.
 */
class FieldButton extends JButton{
    private static final boolean DEBUG = false;
    Field field;
    Consumer<Graphics> draw;
    Color c;

    /**
     * Constructor for the FieldButton
     * @param type type of field
     * @param shape type of field shape
     */
    FieldButton(String type,String shape){
        switch(type){
            case "laboratory":
                field = new Laboratory();
                field.AddStorable(GameController.Single.GetGeneticCodes().get((int)(Math.random()*GameController.Single.GetGeneticCodes().size())));
                c = new Color(85, 2, 120);
                break;
            case "shelter":
                field = new Shelter();
                field.AddStorable(GameController.Single.GetEquipments().get((int)(Math.random()*GameController.Single.GetEquipments().size())));
                c = new Color(230,230,250);
                break;
            case "warehouse":
                field = new Warehouse();

                if(Math.random()>0.5)
                    field.AddStorable(new AminoAcid());
                else
                    field.AddStorable(new Nucleotid());

                c = new Color(156, 103, 168);
                break;
            case "bearlaboratory":
                field = new BearLaboratory();
                c = new Color(85, 2, 120);
                break;
            default:
                field = new Empty();
                c = new Color(195, 185, 199);
                break;
        }
        Dimension size = getPreferredSize();

        switch(shape){
            case "triangleUp":
                draw = ((Graphics g) -> {
                    g.fillPolygon(new int[]{0,60,30}, new int[]{55,55,0}, 3);
                    g.setColor(Color.black);
                    g.drawPolygon(new int[]{0,60,30,0}, new int[]{55,55,0,55}, 4);
                });
                size = new Dimension(60,55);
                break;
            case "triangleDown":
                draw = ((Graphics g) -> {
                    g.fillPolygon(new int[]{0,60,30}, new int[]{0,0,55}, 3);
                    g.setColor(Color.black);
                    g.drawPolygon(new int[]{0,60,30,0}, new int[]{0,0,55,0}, 4);
                });
                size = new Dimension(60,55);
                break;
            case "hexagon":
                draw = ((Graphics g) -> {
                    g.fillPolygon(new int[]{30,90,120,90,30,0}, new int[]{0,0,55,110,110,55}, 6);
                    g.setColor(Color.black);
                    g.drawPolygon(new int[]{30,90,120,90,30,0,30}, new int[]{0,0,55,110,110,55,0}, 7);
                });
                size = new Dimension(120,110);
                break;
        }
        setPreferredSize(size);
        setSize(size);
        setContentAreaFilled(false);
        setBorderPainted(DEBUG);
        GameController.Single.AddField(field);
    }

    /**
     * Paints the screen
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        
        g.setColor(Color.GRAY);
        if(GameController.Single.currentPlayer.knownFields.contains(field) || DEBUG){
            g.setColor(c);
        }
        draw.accept(g);
    }
    
}

/**
 * A class that represents a virologist that's a button too.
 */
class VirologistButton extends JButton{
    private static final boolean DEBUG = false;
    Virologist v;
    String name;
    JPanel parent;

    /**
     * Constructor for VirologistButton.
     * @param name name of the virologist.
     * @param v the virologist it belongs to
     * @param img image of the virologist
     * @param parent Jpanel
     */
    VirologistButton(String name,Virologist v,ImageIcon img,JPanel parent){
        super(img);
        this.parent = parent;
        this.v = v;
        this.name = name;
        setPreferredSize(new Dimension(25,33));
        setSize(new Dimension(25,33));
        setContentAreaFilled(false);
        setBorderPainted(DEBUG);
    }

    /**
     * Paints the screen.
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        var cp = GameController.Single.currentPlayer;
        if(cp == v || (cp.touchedVirologists.contains(v) && cp.currentField.virologistsHere.contains(v)) || DEBUG){
            super.paintComponent(g);
            //find component of parent with same field
            for(Component c : parent.getComponents()){
                if(c instanceof FieldButton){
                    if(((FieldButton)c).field == v.currentField){
                        int phere = Math.min(cp.touchedVirologists.size(), cp.currentField.virologistsHere.size());
                        int[] offsets = new int[phere+1];
                        for(int i = 0; i < phere; i++){
                            offsets[i] = 13*phere-26*phere+i*26;
                        }
                        offsets[phere] = 13*phere;
                        int index = cp.touchedVirologists.indexOf(v)+1;
                        setLocation(c.getX()+c.getWidth()/2-getWidth()/2+offsets[index], c.getY()+c.getHeight()/2-getHeight()/2);

                        break;
                    }
                }
            }
            var gn = g.create();
            gn.setClip(-50, -50, 200, 200);
            gn.setColor(cp == v ? new Color(204, 0, 255) : Color.black);
            gn.drawString(name, -5, 0); 
        }
    }

}

/**
 * A class for the inventory panel.
 */
class InventoryPanel extends JPanel {

    private Image tile;

    /**
     * Constructor for the InventoryPanel.
     */
    public InventoryPanel() {
        var img = new ImageIcon("resources/square.png");
        tile = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }

    /**
     * Paints the screen.
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileWidth = 50;
        int tileHeight = 50;
        for (int y = 0; y < getHeight(); y += tileHeight) {
            for (int x = 0; x < getWidth(); x += tileWidth) {
                g.drawImage(tile, x, y, this);
            }
        }
    }
}

/**
 * A class for the crafting side panel.
 */
class CraftSidePanel extends JPanel{

    ImageIcon img = new ImageIcon("resources/craft_cell.png");

    /**
     * Paints on the screen.
     * @param g Graphics.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img.getImage(),0,0,null);
    }
}