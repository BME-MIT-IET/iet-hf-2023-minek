package szoftlab.equipment;

import java.util.HashMap;

import szoftlab.agent.ChanceProtection;
import szoftlab.agent.Effect;
import szoftlab.main.Log;
import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Cape equipment's class.
 */
public class Cape extends Equipment {

    private Effect effect;

    /**
     * Default constructor.
     */
    public Cape() {
        Log.println("Creating new Cape");
        effect = new ChanceProtection(this);
    }

    /**
     * @return A chance protection effect.
     */
    @Override
    public Effect GetEffect() {
        return effect;
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
        Log.println(" <Cape>");
        Log.println("durability : " + this.durability);
    }
}
