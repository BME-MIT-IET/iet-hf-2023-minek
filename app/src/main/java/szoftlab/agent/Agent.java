package szoftlab.agent;

import java.util.HashMap;

import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Storable;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Agent class
 */
public class Agent implements Storable {

    /**
     * Time remaining in rounds
     */
    private int timeRemaining;
    /**
     * Effect
     */
    private final Effect effect;

    /**
     * Create a new agent.
     *
     * @param timeRemaining The time remaining of the agent.
     * @param effect        The effect of the agent.
     */
    public Agent(int timeRemaining, Effect effect) {
        Log.println("Creating new agent");
        this.timeRemaining = timeRemaining;
        this.effect = effect;
    }

    /**
     * Decreases the time remaining of the effect.
     *
     * @return The rest of the time remaining.
     */
    private int DecreaseTimeRemaining(){
        Log.println("Decreasing time remaining");
        timeRemaining--;
        return timeRemaining;
    }

    /**
     * Invoked on the beginning of the virologist's turn.
     *
     * @param v The virologist.
     * @return True if the turn can be started.
     */
    public boolean OnRoundBegin(Virologist v){
        Log.blockStart("-> Agent on round begin");
        if(DecreaseTimeRemaining() == 0){
            v.RemoveCraftedAgent(this);
        }
        Log.blockEnd("<- Returning true");
        return true;
    }

    /**
     * @return The effect of the agent.
     */
    public Effect GetEffect(){return effect;}

    /**
     * Stores the agent in the given virologist and removes it from the field.
     * @param v The virologist to store the agent in.
     * @param from The field the agent is taken from.
     */
    @Override
    public void StoreIn(Virologist v, Field from) {
        Log.blockStart("-> Agent store in");
        v.AddAgent(this);
        Log.blockEnd("<- Agent stored");
    }

    /**
     * Adds this to the given virologist's touched list.
     * @param v The virologist who touched the touchable.
     */
    @Override
    public void TouchedBy(Virologist v) {
    }

    public void Expand(HashMap<String, Field> fields, HashMap<String, Agent> agents,
            HashMap<String, GeneticCode> geneticCodes, HashMap<String, Material> materials,
            HashMap<String, Equipment> equipments, HashMap<String, Virologist> virologists) {
        Log.println(" <Agent>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("effect : " + this.effect.getClass().getSimpleName());
    }
}