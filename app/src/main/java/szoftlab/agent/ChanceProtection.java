package szoftlab.agent;

import java.util.HashMap;

import szoftlab.main.GameController;
import szoftlab.main.Log;
import szoftlab.main.Main;
import szoftlab.equipment.Equipment;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Random chance to protect from agents. Created by equipping a cape.
 */
public class ChanceProtection extends Effect{


    /**
     * Create a new chance protection effect.
     * @param parent The equipment this effect is attached to.
     */
    public ChanceProtection(Equipment parent) {
        super(parent);
        Log.println("Creating new ChanceProtection");
    }

    /**
     * Is invoked when the victim receives the agent from the attacker.
     * @param a The agent that is received.
     * @param victim The victim of the agent.
     * @param attacker The attacker of the agent.
     * @return True if a random chance is hit.
     */
    @Override
    public boolean OnAgentReceive(Agent a, Virologist victim,Virologist attacker){
        Log.blockStart("-> ChanceProtection on agent receive");
        if (victim == attacker) {
            Log.blockEnd("<- Returning " + true);
            return true;
        }
        boolean result = Math.random() <= 0.823;
        if (GameController.isDebug) {
            result = GameController.randomTestResult;
        }
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
        Log.println(" <ChanceProtection>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }
}