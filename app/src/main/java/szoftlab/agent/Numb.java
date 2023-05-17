package szoftlab.agent;

import szoftlab.interfaces.Storable;
import szoftlab.equipment.Equipment;
import szoftlab.main.*;
import szoftlab.map.Field;
import szoftlab.material.Material;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Numbing agent's effect class.
 */
public class Numb extends Effect{
    /**
     * Create a new numb effect.
     */
    public Numb() {
        super(null);
        Log.println("Creating new Numb");
    }

    /**
     * Invoked when the victim tries to move to field d.
     * @param d The destination field.
     * @param neighbors The neighbors of the destination field.
     * @return null, victim cannot move under this effect.
     */
    @Override
    public Field DestinationChange(Field d, ArrayList<Field> neighbors){
        Log.blockStart("-> Numb destination change");
        Log.blockEnd("<- Returning null");
        return null;
    }

    /**
     * Invoked when the effect is applied on v.
     * @param v The virologist who is affected by the effect.
     */
    @Override
    public void OnEffectApplied(Virologist v){
        Log.blockStart("-> Numb on effect applied");
        GameController.Single.EndTurn();
        Log.blockEnd("<- Effect applied");
    }

    /**
     * Invoked when s is stolen from the victim.
     * @param s The stolen storable.
     * @return s, under this effect the storable is stolen.
     */
    @Override
    public Storable StealStorable(Storable s){
        Log.blockStart("-> Numb steal storable");
        Log.blockEnd("<- Returning storable");
        return s;
    }

    /**
     * Decreases the time remaining of the effect.
     * @param v The virologist the effect is applied on.
     * @return false, can't start the round under this effect.
     */
    @Override
    public boolean OnRoundBegin(Virologist v) {
        Log.blockStart("-> Numb on round begin");
        if (DecreaseTimeRemaining() == 0) {
            v.RemoveEffect(this);
        }
        Log.blockEnd("<- returning false");
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
        Log.println(" <Numb>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }
}