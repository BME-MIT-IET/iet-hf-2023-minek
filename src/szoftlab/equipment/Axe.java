package szoftlab.equipment;

import java.util.HashMap;

import szoftlab.main.GameController;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Axe equipment's class.
 */
public class Axe extends Equipment {

    /**
     * Creating new Axe Equipment. Default constructor.
     */
    public Axe() {
        Log.println("Creating new Axe");
        durability = 1;
    }

    /**
     * Use this equipment on a virologist.
     * @param attacker The virologist who used.
     * @param target The virologist to use the equipment on.
     */
    @Override
    public void UseEquipmentOn(Virologist attacker, Virologist target) {
        Log.println("Using Axe on Virologist");
        DecreaseDurability(attacker);
        GameController.Single.PlayerDeath(target);
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
        Log.println(" <Axe>");
        Log.println("durability : " + this.durability);
    }
}
