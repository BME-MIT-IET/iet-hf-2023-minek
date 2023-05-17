package szoftlab.map;

import java.util.HashMap;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Storable;
import szoftlab.main.GameController;
import szoftlab.main.Log;
import szoftlab.main.Main;
import szoftlab.main.Virologist;
import szoftlab.material.Material;
import szoftlab.ui.GameView;

/**
 * Shelter field's class.
 */
public class Shelter extends Field {
    /**Equipment at shelter*/
    private Equipment equipment;

    /**
     * Create a new Shelter with the given Equipment.
     * @param equipment The Equipment to be stored in the Shelter.
     */
    public Shelter(Equipment equipment) {
        Log.println("Creating new Shelter");
        this.equipment = equipment;
    }

    /**
     * Default constructor.
     */
    public Shelter() {
        equipment = null;
    }

    /**
     * @return The equipment.
     */
    @Override
    public Storable GetStorable(){
        return equipment;
    }

    /**
     * Removes the equipment from the shelter.
     *
     * @param s The equipment to remove.
     */
    @Override
    public void RemoveFieldContent(Storable s) {
        equipment = null;
    }

    /**
     * Add an equipment to this field (Shelter).
     *
     * @param s
     */
    @Override
    public void AddStorable(Storable s) {
        equipment = (Equipment) s;
    }

    /**
     * Is invoked when the command "expand" is executed from the command line interface.
     * @param fields The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes The GeneticCodes that will be expanded.
     * @param materials The Materials that will be expanded.
     * @param equipments The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    @Override
    public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists) {
        Log.println(" <Shelter>");
        Log.println("equipment : " + Main.findKey(equipments, equipment));
        Log.print("virologistsHere :");
        if(virologistsHere.size()>0) {
            for (int i = 0; i < virologistsHere.size(); ++i)
                Log.print(" " + Main.findKey(virologists, virologistsHere.get(i)));
        } else
            Log.print(" null");
        Log.println("");
        Log.print("neighbors :");
        if(neighbors.size()>0) {
            for (int i = 0; i < neighbors.size(); ++i)
                Log.print(" " + Main.findKey(fields, (Field)neighbors.toArray()[i]));
        } else
            Log.print(" null");
        Log.println("");
    }

    
}
