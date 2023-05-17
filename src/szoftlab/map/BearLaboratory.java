package szoftlab.map;

import java.util.HashMap;

import szoftlab.agent.Agent;
import szoftlab.agent.Bear;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.main.Log;
import szoftlab.main.Main;
import szoftlab.main.Virologist;
import szoftlab.material.Material;

/**
 * Bear laboratory field's class. Inherits from Laboratory base class.
 */
public class BearLaboratory extends Laboratory {

    /**
     * Create a new BearLaboratory.
     */
    public BearLaboratory(GeneticCode code) {
        this.geneticCode = code;
        Log.println("Creating new BearLaboratory");
    }

    /**
     * Default constructor.
     */
    public BearLaboratory() {
        this.geneticCode = null;
    }

    /**
     * Virologist stepped on this field.
     * @param v The virologist who stepped on this field.
     */
    @Override
    public void OnVirologistEntered(Virologist v) {
        Log.blockStart("-> Virologist entered Bear lab");
        Agent a = new Agent(1, new Bear());
        v.AcceptAgent(a, null);
        for (Virologist here : virologistsHere) {
            here.FieldUpdated(virologistsHere);
        }
        Log.blockEnd("<- Finished");
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
        Log.println(" <BearLaboratory>");
        Log.println("geneticCode : " + Main.findKey(geneticCodes, geneticCode));
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
