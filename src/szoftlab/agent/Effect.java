package szoftlab.agent;

import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Storable;
import szoftlab.main.Log;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract class for implementing Effects.
 */
public abstract class Effect {
    /**
     * Time remaining in rounds.
     */
    protected int timeRemaining = 3;
    /**
     * If the effect is related to an equipment then it's connected/stored here
     */
    protected Equipment relatedToEquipment; //kell glove-hoz

    /**
     * Decreases the time remaining of the effect.
     * @return The rest of the time remaining.
     */
    protected int DecreaseTimeRemaining() {
        Log.println("Decreasing time remaining");
        timeRemaining--;
        return timeRemaining;
    }

    /**
     * Create a new Effect.
     * @param parent The equipment the effect is attached to.
     */
    public Effect(Equipment parent) {
        Log.println("Creating new Effect");
        relatedToEquipment = parent;
    }

    /**
     * Is invoked when the victim receives the agent from the attacker.
     * Invoked on every active effect of the victim.
     * @param a        The agent that is received.
     * @param victim   The victim of the agent.
     * @param attacker The attacker of the agent.
     * @return True if the agent was applied.
     */
    public boolean OnAgentReceive(Agent a, Virologist victim, Virologist attacker) {
        Log.blockStart("-> Effect on agent receive");
        Log.blockEnd("<- Returning true");
        return true;
    }

    /**
     * Is invoked when the victim tries to move to field d.
     * @param d         The destination field.
     * @param neighbors The neighbors of the destination field.
     * @return The field the victim should move to.
     */
    public Field DestinationChange(Field d, ArrayList<Field> neighbors) {
        Log.blockStart("-> Effect destination change");
        Log.blockEnd("<- Returning destination");
        return d;
    }

    

    /**
     * Invoked on the beginning of the virologist's turn.
     * @param v The virologist.
     * @return True if the turn can be started.
     */
    public boolean OnRoundBegin(Virologist v) {
        Log.blockStart("-> Effect on round begin");
        if (DecreaseTimeRemaining() == 0) {
            v.RemoveEffect(this);
        }
        Log.blockEnd("<- Returning true");
        return true;
    }

    /**
     * Invoked when the effect is applied on v.
     * @param v The virologist who is affected by the effect.
     */
    public void OnEffectApplied(Virologist v) {
        Log.blockStart("-> Effect on effect applied");
        Log.blockEnd("<- Effect applied");
    }

    /**
     * Invoked when s is stolen from the victim.
     * @param s The stolen storable.
     * @return s if the storable can be stolen, null otherwise.
     */
    public Storable StealStorable(Storable s) {
        Log.blockStart("-> Effect steal storable");
        Log.blockEnd("<- Returning null");
        return null;
    }

    /**
     * Getter function for timeRemaining property.
     * Only used in testing.
     *
     * @return timeRemaining
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * An update happened on the field where notified is standing.
     *
     * @param notified        The virologist who is notified.
     * @param virologistsHere The virologists on the current field.
     */
    public void FieldUpdated(Virologist notified, ArrayList<Virologist> virologistsHere) {
        Log.blockStart("-> Effect on field updated");
        Log.blockEnd("<- Nothing to update");
    }

    /**
     * Is invoked when the command "expand" is executed from the command line interface. Unimplemented in abstract class.
     * @param fields The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes The GeneticCodes that will be expanded.
     * @param materials The Materials that will be expanded.
     * @param equipments The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    abstract public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists);

}