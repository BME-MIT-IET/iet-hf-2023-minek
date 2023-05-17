package szoftlab.map;

import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Clickable;
import szoftlab.interfaces.Storable;
import szoftlab.main.Virologist;
import szoftlab.material.Material;
import szoftlab.ui.GameView;
import szoftlab.main.GameController;
import szoftlab.main.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;

/**
 * Abstract class for implementing Fields.
 */
public abstract class Field implements Clickable{
    /**
     * Virologists in this field.
     */
    public ArrayList<Virologist> virologistsHere = new ArrayList<>();
    /**Neighbors of this field.*/
    LinkedHashSet<Field> neighbors = new LinkedHashSet<>();

    /**
     * Connects the given field to this field.
     * @param other The field to connect to this field.
     */
    public void AddNeighbor(Field other){
        Log.println("Connecting neighbors");
        neighbors.add(other);
        other.neighbors.add(this);
    }

    /**
     * Adds every touchable on this field to the virologists touched list.
     * @param v The virologist to add the touchable to.
     */
    public void VirologistTouch(Virologist v) {
        Log.blockStart("-> Virologist touching field");
        Storable t = GetStorable();
        if (t != null) {
            Log.println("\tY - Touching thing on field.");
            t.TouchedBy(v);
        } else {
            Log.println("\tN - There is no Storable object on current field to touch.");
        }
        for (Virologist here : virologistsHere) {
            Log.println("Touching other virologists on field.");
            here.TouchedBy(v);
        }
        Log.blockEnd("<- Touch ended");
    }

    /**
     * @return Virologists on the field.
     */
    public ArrayList<Virologist> GetVirologists(){return virologistsHere;}

    /**
     * @return The neighbors of this field.
     */
    public ArrayList<Field> GetNeighbours(){return new ArrayList<Field>(neighbors);}

    /**
     * Removes the given virologist from the list of virologists on this field.
     * @param v The virologist to remove.
     */
    public void RemoveVirologist(Virologist v){
        Log.println("Removing virologist from field.");
        virologistsHere.remove(v);
    }

    /**
     * Adds the given virologist to the list of virologists on this field.
     * @param v The virologist to add.
     */
    public void PlaceVirologist(Virologist v){
        Log.blockStart("-> Placing virologist on field.");
        virologistsHere.add(v);
        this.OnVirologistEntered(v);
        Log.blockEnd("<- Virologist placed.");
    }

    /** 
     * Virologist stepped on this field.
     * @param v The virologist who stepped on this field.
     */
    protected void OnVirologistEntered(Virologist v) {
        Log.blockStart("-> Virologist entered field");
        //signal every virologist on field that an update has occured
        for (Virologist here : virologistsHere) {
            here.FieldUpdated(virologistsHere);
        }
        Log.blockEnd("<- Finished");
    }

    /**
     * @return The Storable object on this field.
     */
    public Storable GetStorable(){
        return null;
    }

    /**
     * Removes the Storable object on this field.
     *
     * @param s The Storable object to remove.
     */
    public void RemoveFieldContent(Storable s) {
    }

    /**
     * Abstract function to add Storable stuff to Fields
     */
    abstract public void AddStorable(Storable s);

    /**
     * Is invoked when the command "expand" is executed from the command line interface.
     * @param fields The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes The GeneticCodes that will be expanded.
     * @param materials The Materials that will be expanded.
     * @param equipments The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    abstract public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists);

    /**
     * Called on mouse press.
     * @param view GameView
     * @return
     */
    @Override
    public MouseInputAdapter OnPress(GameView view) {
        var field = this;
        return new MouseInputAdapter() {
            /**
             * Called on mouse click.
             * @param e Mouse Event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if(GameController.Single.currentPlayer.currentField == field){
                    view.ShowPopupField(GameController.Single.currentPlayer, field);
                }
                else if(GameController.Single.currentPlayer.currentField.neighbors.contains(field) && GameController.Single.currentPlayer.knownFields.contains(field)){
                    view.ShowPopupNeighbor(GameController.Single.currentPlayer, field);
                }
                super.mouseClicked(e);
            }
        };
    }


}
