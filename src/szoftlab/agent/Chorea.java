package szoftlab.agent;

import szoftlab.main.*;
import szoftlab.equipment.Equipment;
import szoftlab.map.Field;
import szoftlab.material.Material;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Chorea effect class.
 */
public class Chorea extends Effect{
    /**
     * Create a new chorea effect.
     */
    public Chorea() {
        super(null);
        Log.println("Creating new Chorea");
    }

    /**
     * Choose a random field from the neighbors.
     * @param neighbors The neighbors of the field.
     * @return A random field from the neighbors.
     */
    private Field RandomField(ArrayList<Field> neighbors){
        int n = (int)(Math.random()*neighbors.size());
        return neighbors.get(n);
    }

    /**
     * Invoked when the victim tries to move to field d.
     * @param d The destination field.
     * @param neighbors The neighbors of the destination field.
     * @return A random field from the neighbors.
     */
    @Override
    public Field DestinationChange(Field d, ArrayList<Field> neighbors) {
        Log.blockStart("-> Chorea destination change");
        Field result = RandomField(neighbors);
        Log.blockEnd("<- Returning " + result);
        return result;
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
        Log.println(" <Chorea>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }
}