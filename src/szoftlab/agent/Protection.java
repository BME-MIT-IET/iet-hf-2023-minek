package szoftlab.agent;

import java.util.HashMap;

import szoftlab.equipment.Equipment;
import szoftlab.main.*;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Protection effect's class.
 */
public class Protection extends Effect{
    /**
     * Create a new protection effect.
     */
    public Protection() {
        super(null);
        Log.println("Creating new Protection");
    }

    /**
     * Is invoked when the victim receives the agent from the attacker.
     * Invoked on every active effect of the victim.
     * @param a The agent that is received.
     * @param victim The victim of the agent.
     * @param attacker The attacker of the agent.
     * @return False
     */
    @Override
    public boolean OnAgentReceive(Agent a, Virologist victim, Virologist attacker) {
        Log.blockStart("-> Protection on agent receive");
        if (victim == attacker) return true;
        Log.blockEnd("<- Returning false");
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
    @Override
    public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists) {
        Log.println(" <Protection>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }
}