package szoftlab.equipment;

import java.util.HashMap;

import szoftlab.agent.Agent;
import szoftlab.agent.Effect;
import szoftlab.agent.GeneticCode;
import szoftlab.interfaces.Storable;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Abstract class for implementing Equipments.
 */
public abstract class Equipment implements Storable {

    /**
     * The equipment's durability (number in usages).
     */
    protected int durability = -1;

    /**
     * Stores the material if the equipment can store it.
     *
     * @param m The material to store.
     * @return True if the material was stored, false otherwise.
     */
    public boolean StoreMaterial(Material m) {
        Log.println("Can't store material");
        return false;
    }

    /**
     * Gets the material from the equipment, if it can store them.
     * @param i The index of the material to get.
     * @return The material at index i or null.
     */
    public Material GetMaterial(int i){return null;}

    /**
     * Removes the material m if it is stored in the equipment.
     * @param m The material to remove.
     */
    public void RemoveMaterial(Material m){
        Log.println("Can't remove material");
    }

    /**
     * Returns the number of materials stored in the equipment.
     * @return The number of materials stored in the equipment.
     */
    public int GetMaterialCount(){return 0;}

    /**
     * @return The effect of the equipment or null.
     */
    public Effect GetEffect(){return null;}

    /**
     * Stores the equipment in the given virologist and removes it from the field.
     * @param v The virologist to store the equipment in.
     * @param from The field the equipment is taken from.
     */
    @Override
    public void StoreIn(Virologist v,Field from) {
        Log.blockStart("-> Equipment store in");
        v.AddEquipment(this);
        if(from != null)from.RemoveFieldContent(this);
        Log.blockEnd("<- Equipment stored");
    }

    /**
     * Adds this to the given virologist's touched list.
     *
     * @param v The virologist who touched the touchable.
     */
    @Override
    public void TouchedBy(Virologist v) {
        Log.blockStart("-> Equipment touched by virologist");
        v.DiscoverStorable(this);
        Log.blockEnd("<- Discovered");
    }

    public int DecreaseDurability(Virologist owner) {
        Log.blockStart("-> Decreasing equipment durability");
        Log.println("Equipment has limited usages? (Y/N)");
        if (durability != -1) {
            Log.println("\tY - Decreasing equipment durability.");
            durability -= 1;

            if (durability == 0) {
                owner.RemoveEquipped(this);
                owner.RemoveEffect(this.GetEffect());
            }

        } else
            Log.println("\tN - Equipment durability not decreased.");
        Log.blockEnd("<- Returning equipment durability");
        return durability;
    }

    /**
     * Durability getter, teszteleshez hasznalatos fuggveny.
     *
     * @return Durability
     */
    public int GetDurability() {
        return durability;
    }

    /**
     * Use this equipment on a virologist.
     * @param attacker The virologist who used.
     * @param target The virologist to use the equipment on.
     */
    public void UseEquipmentOn(Virologist attacker, Virologist target) {
        Log.println("Not usable equipment.");
    }

    /**
     * Abstract command "expand" is executed from the command line interface.
     * @param fields The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes The GeneticCodes that will be expanded.
     * @param materials The Materials that will be expanded.
     * @param equipments The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    abstract public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists);

}
