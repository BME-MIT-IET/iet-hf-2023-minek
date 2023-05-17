package szoftlab.map;

import java.util.HashMap;

import szoftlab.agent.Agent;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Storable;
import szoftlab.main.GameController;
import szoftlab.main.Log;
import szoftlab.main.Main;
import szoftlab.main.Virologist;
import szoftlab.material.Material;
import szoftlab.ui.GameView;

import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;
/**
 * Empty field's class.
 */
public class Empty extends Field {

    /**
     * Default constructor.
     */
    public Empty() {
        Log.println("Creating new Empty");
    }

    /**
     * Unused function -> Empty field can't store Storable objects.
     *
     * @param s Storable
     */
    @Override
    public void AddStorable(Storable s) {
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
        Log.println(" <Empty>");
        Log.print("virologistsHere :");
        if(virologistsHere.size()>0) {
            for (int i = 0; i < virologistsHere.size(); ++i)
                Log.print(" " + Main.findKey(virologists, virologistsHere.get(i)));
        } else
            Log.print(" null");
        Log.println("");
        Log.print("neighbors :");
        if(neighbors.size()>0) {
            for (int i = 0; i < neighbors.size(); ++i)
                Log.print(" " + Main.findKey(fields, (Field)neighbors.toArray()[i]));
        } else
            Log.print(" null");
        Log.println("");
    }

}
