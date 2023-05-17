package szoftlab.agent;

import java.util.ArrayList;
import java.util.HashMap;

import szoftlab.main.*;
import szoftlab.equipment.Equipment;
import szoftlab.map.Field;
import szoftlab.material.Material;

/**
 * Forget effect class.
 */
public class Forget extends Effect{
    /**
     * Create a new Forget effect. Default constructor.
     */
    public Forget() {
        super(null);
        Log.println("Creating new Forget");
        timeRemaining = 1;
    }

    /**
     * Removes all known genetic codes from the virologist, when the effect is applied.
     * @param v The virologist who is affected by the effect.
     */
    @Override
    public void OnEffectApplied(Virologist v){
        Log.blockStart("-> Forget on effect applied");
        if (v != null) {
            ArrayList<GeneticCode> known = v.GetKnownGeneticCodes();
            for (int i = known.size() - 1; i >= 0; i--) {
                v.RemoveGeneticCode(known.get(i));
            }
        }
        Log.blockEnd("<- Effect applied");
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
        Log.println(" <Forget>");
        Log.println("timeRemaining : " + this.timeRemaining);
        Log.println("relatedToEquipment : "+ Main.findKey(equipments, this.relatedToEquipment));
    }
}