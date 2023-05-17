package szoftlab.agent;

import java.util.ArrayList;
import java.util.HashMap;

import szoftlab.main.Log;
import szoftlab.main.Main;
import szoftlab.equipment.Equipment;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Bear effect class. Inherits from Chorea.
 */
public class Bear extends Chorea {
    /**
     * Create a new bear effect.
     */
    public Bear() {
        Log.println("Creating new Bear");
    }

    /**
     * An update happened on the field where notified is standing.
     * @param notified The virologist who is notified.
     * @param virologistsHere The virologists on the current field.
     */
    @Override
    public void FieldUpdated(Virologist notified,ArrayList<Virologist> virologistsHere ){
        Log.blockStart("-> Effect on field updated");
        for(Virologist v : virologistsHere) {
            if(v != notified) {
                v.AcceptAgent(new Agent(1,this), null);
            }
        }
        Log.blockEnd("<- Bear applied");
    }


    /**
     * Is invoked when the victim receives the agent from the attacker.
     * Invoked on every active effect of the victim.
     * @param a The agent that is received.
     * @param victim The victim of the agent.
     * @param attacker The attacker of the agent.
     * @return False if there is no attacker (it means that the effect is a bear effect)
     */
    @Override
    public boolean OnAgentReceive(Agent a, Virologist victim, Virologist attacker) {
        Log.blockStart("-> ThrowBack on agent receive");
        if (attacker != null)
            Log.blockEnd("<- Returning true");
        else Log.blockEnd("<- Returning false");
        return attacker != null;
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
        Log.println(" <Bear>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }

}
