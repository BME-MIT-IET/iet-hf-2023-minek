package szoftlab.agent;

import java.util.HashMap;

import szoftlab.equipment.Equipment;
import szoftlab.main.*;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Glove equipment's ThrowBack effect class.
 */
public class ThrowBack extends Effect{
    /**
     * Create a new throwback effect.
     * @param parent The eqipment it is attached to.
     */
    public ThrowBack(Equipment parent) {
        super(parent);
        Log.println("Creating new ThrowBack");
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
        Log.blockStart("-> ThrowBack on agent receive");
        if(attacker != null){
            attacker.RemoveCraftedAgent(a);
            if(victim != attacker){
                //noinspection unused
                int db = relatedToEquipment.DecreaseDurability(victim);
                victim.ThrowAgent(a, attacker);
            }
        }

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
        Log.println(" <ThrowBack>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }

}