package szoftlab.material;

import java.util.HashMap;

import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.map.Field;

/**
 * Nucleotide material's class.
 */
public class Nucleotid extends Material{
    /**
     * @param m The material to check.
     * @return true if this material can fill the role of m.
     */
    @Override
    public boolean CheckIngredient(Material m){
        return m.CheckMaterial(this);
    }

    @Override
    protected boolean CheckMaterial(Nucleotid a){
        return true;
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
    public void Expand(HashMap<String, Field> fields, HashMap<String, Agent> agents,
            HashMap<String, GeneticCode> geneticCodes, HashMap<String, Material> materials,
            HashMap<String, Equipment> equipments, HashMap<String, Virologist> virologists) {
        Log.println(" <Nucleotid>");
        
    }

    
}