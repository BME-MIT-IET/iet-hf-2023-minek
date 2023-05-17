package szoftlab.material;

import java.util.HashMap;

import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Storable;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.map.Field;

/**
 * Abstract Material class to implement Materials.
 */
public abstract class Material implements Storable {

    /**
     * @param m The material to check.
     * @return true if this material can fill the role of m.
     */
    public boolean CheckIngredient(Material m){return false;}

    /**
     * Stores the material in the given virologist and removes it from the field.
     * @param v The virologist to store the material in.
     * @param from The field the material is taken from.
     */
    @Override
    public void StoreIn(Virologist v, Field from) {
        Log.blockStart("-> Material store in");
        if(v.StoreMaterial(this) && from != null){
            from.RemoveFieldContent(this);
        }
        Log.blockEnd("<- Material stored");
    }

    /**
     * Add this to the given virologist's touched list.
     * @param v The virologist who touched the material.
     */
    @Override
    public void TouchedBy(Virologist v) {
        Log.blockStart("-> Material touched by virologist");
        v.DiscoverStorable(this);
        Log.blockEnd("<- Discovered");
    }


    protected boolean CheckMaterial(AminoAcid a){
        return false;
    }

    protected boolean CheckMaterial(Nucleotid a){
        return false;
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
    abstract public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists);

}