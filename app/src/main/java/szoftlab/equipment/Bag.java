package szoftlab.equipment;

import szoftlab.main.Log;
import szoftlab.material.Material;

import java.util.ArrayList;
import java.util.HashMap;
import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.main.Virologist;
import szoftlab.map.Field;

/**
 * Bag equipment's class.
 */
public class Bag extends Equipment {
    /**
     * Materials that are stored in the Bag.
     */
    private ArrayList<Material> materials = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Bag() {
        Log.println("Creating new Bag");
    }

    /**
     * Store the given Material in the Bag.
     * @param //material The Material to be stored in the Bag.
     * @return true if the Material was stored, false if the Bag is full.
     */
    @Override
    public boolean StoreMaterial(Material m) {
        Log.println("Storing material");
        if (materials.size() < 10) {
            materials.add(m);
            return true;
        }
        return false;
    }

    /**
     * Gets the material from the equipment, if it can store them.
     * @param i The index of the material to get.
     * @return The material at index i or null.
     */
    @Override
    public Material GetMaterial(int i){
        return materials.get(i);
    }

    /**
     * Removes the material m if it is stored in the equipment.
     * @param m The material to remove.
     */
    @Override
    public void RemoveMaterial(Material m){
        Log.println("Removing material");
        materials.remove(m);
    }

    /**
     * Returns the number of materials stored in the equipment.
     *
     * @return The number of materials stored in the equipment.
     */
    @Override
    public int GetMaterialCount() {
        return materials.size();
    }

    /**
     * Is invoked when the command "expand" is executed from the command line interface.
     * @param fields The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes The GeneticCodes that will be expanded.
     * @param materials2 The Materials that will be expanded.
     * @param equipments The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    @Override
    public void Expand(HashMap<String, Field> fields, HashMap<String, Agent> agents, HashMap<String, GeneticCode> geneticCodes, HashMap<String, Material> materials2, HashMap<String, Equipment> equipments, HashMap<String, Virologist> virologists) {
        Log.println(" <Bag>");
        Log.print("materials :");
        if (materials.size() > 0) {
            for (int i = 0; i < materials.size(); ++i) {
                Log.print(" " + materials.get(i).getClass().getSimpleName());
            }
        } else {
            Log.println(" null");
        }
    }

}
