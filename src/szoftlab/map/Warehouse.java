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
 * Warehouse field's class.
 */
public class Warehouse extends Field {
    /**Material at warehouse*/
    private Material material;

    /**
     * Create a new Warehouse with the given Material.
     * @param material The Material to be stored in the Warehouse.
     */
    public Warehouse(Material material) {
        Log.println("Creating new Warehouse");
        this.material = material;
    }

    /**
     * Default constructor.
     */
    public Warehouse() {
        material = null;
    }

    /**
     * @return The material.
     */
    @Override
    public Storable GetStorable(){
        return material;
    }

    /**
     * Doesn't do anything since materials shouldn't be removed.
     *
     * @param s The material here.
     */
    @Override
    public void RemoveFieldContent(Storable s) {
        //material = null;
    }

    /**
     * Add material to the current field (Warehouse).
     *
     * @param s The material to add.
     */
    @Override
    public void AddStorable(Storable s) {
        material = (Material) s;
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
        Log.println(" <Warehouse>");
        Log.println("material : " + this.material.getClass().getSimpleName());
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
