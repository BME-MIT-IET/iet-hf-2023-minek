package szoftlab.agent;

import szoftlab.interfaces.Storable;
import szoftlab.main.Log;
import szoftlab.equipment.Equipment;
import szoftlab.main.Virologist;
import szoftlab.map.Field;
import szoftlab.material.Material;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Genetic code class. Implements Storable base class.
 */
public class GeneticCode implements Storable {
    /**
     * Materials that are required to craft an Agent from this GeneticCode.
     */
    private final ArrayList<Material> recipe;
    /**
     * Agent
     */
    private final Agent agent;

    /**
     * Create a new GeneticCode.
     *
     * @param recipe The recipe of the GeneticCode.
     * @param agent  The agent that can be created with the GeneticCode.
     */
    public GeneticCode(ArrayList<Material> recipe, Agent agent) {
        Log.println("Creating new GeneticCode");
        this.recipe = recipe;
        this.agent = agent;
    }

    /**
     * @return The recipe of the genetic code.
     */
    public ArrayList<Material> GetRecipe(){return recipe;}

    /**
     * @return The agent that can be created by this genetic code.
     */
    public Agent GetAgent(){return agent;}

    /**
     * Stores the genetic code in the given virologist and removes it from the field.
     * @param v The virologist to store the genetic code in.
     * @param from The field the genetic code is taken from.
     */
    @Override
    public void StoreIn(Virologist v, Field from) {
        Log.blockStart("-> GeneticCode store in");
        v.LearnGenCode(this);
        Log.blockEnd("<- GeneticCode stored");
    }

    /**
     * Adds this to the given virologist's touched list.
     *
     * @param v The virologist who touched the touchable.
     */
    @Override
    public void TouchedBy(Virologist v) {
        Log.blockStart("-> GeneticCode touched by virologist");
        v.DiscoverStorable(this);
        Log.blockEnd("<- Discovered");
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
    public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists) {
        Log.println(" <GeneticCode>");
        Log.print("recipe: ");
        for (int i = 0; i < recipe.size(); ++i) {
            Log.println("Material " + i + " " + recipe.get(i));
        }
        Log.println("agent: " + agent.GetEffect());
    }
}
