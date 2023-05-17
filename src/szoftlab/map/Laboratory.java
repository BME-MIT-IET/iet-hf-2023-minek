package szoftlab.map;

import java.util.HashMap;

import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Storable;
import szoftlab.main.Log;
import szoftlab.main.Main;
import szoftlab.main.Virologist;
import szoftlab.material.Material;

/**
 * Laboratory field's class.
 */
public class Laboratory extends Field {
    /**Genetic code at the laboratory*/
    protected GeneticCode geneticCode;

    /**
     * Create a new Laboratory with the given GeneticCode.
     *
     * @param geneticCode The GeneticCode to be stored in the Laboratory.
     */
    public Laboratory(GeneticCode geneticCode) {
        Log.println("Creating new Laboratory");
        this.geneticCode = geneticCode;
    }

    /**
     * Default constructor.
     */
    public Laboratory() {
        geneticCode = null;
    }

    /**
     * @return Genetic code.
     */
    @Override
    public Storable GetStorable() {
        return geneticCode;
    }

    /**
     * Removes the genetic code from the field.
     *
     * @param s The genetic code to remove.
     */
    @Override
    public void RemoveFieldContent(Storable s) {
        geneticCode = null;
    }

    /**
     * Add genetic code to the laboratory
     */
    @Override
    public void AddStorable(Storable s) {
        geneticCode = (GeneticCode) s;
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
        Log.println(" <Laboratory>");
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
