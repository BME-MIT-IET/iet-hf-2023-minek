package szoftlab.main;

import szoftlab.agent.Agent;
import szoftlab.agent.Effect;
import szoftlab.agent.GeneticCode;
import szoftlab.equipment.Equipment;
import szoftlab.interfaces.Clickable;
import szoftlab.interfaces.Storable;
import szoftlab.interfaces.Touchable;
import szoftlab.map.Field;
import szoftlab.material.Material;
import szoftlab.ui.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;

import java.awt.Component;
import java.awt.event.MouseEvent;

/**
 * Virologist's class.
 */
public class Virologist implements Touchable , Clickable {

    /**
     * Known fields
     */
    public ArrayList<Field> knownFields = new ArrayList<>();
    /**
     * Touched virologists at current field.
     */
    public ArrayList<Virologist> touchedVirologists = new ArrayList<>();
    /**
     * Touched Storable stuff at current field.
     */
    public ArrayList<Storable> touchedStorables = new ArrayList<>();
    /**
     * Equipments currently on the Virologist.
     */
    ArrayList<Equipment> equippedEquipments = new ArrayList<>();
    /**
     * Stored equipments.
     */
    ArrayList<Equipment> equipments = new ArrayList<>();
    /**
     * Stored crafted agents.
     */
    public ArrayList<Agent> craftedAgents = new ArrayList<>();
    /**
     * GeneticCodes already known.
     */
    LinkedHashSet<GeneticCode> knownGeneticCodes = new LinkedHashSet<>();
    /**
     * Stored materials.
     */
    public ArrayList<Material> materials = new ArrayList<>();
    /**Current effects on the virologist.*/
    public ArrayList<Effect> activeEffects = new ArrayList<>();
    /**Current field of the virologist.*/
    public Field currentField;
    /**Virologist maximum carrying capacity.*/
    private final int inventorySize = 50;

    /**
     * Creates a virologist on the given field.
     * @param currentField The field the virologist should be on.
     */
    public Virologist(Field currentField) {
        Log.println("Virologist created on field");
        this.currentField = currentField;
        if(currentField != null)currentField.PlaceVirologist(this);
    }

    /**
     * Adds effect to active effects list.
     * @param e The effect to add.
     */
    void ApplyEffect(Effect e) {
        Log.println("Applying effect");
        activeEffects.add(e);
    }

    /**
     * Removes equipment from equipments list.
     * @param e The equipment to remove.
     */
    Equipment RemoveEquipment(Equipment e) {
        Log.println("Removing equipment");
        equipments.remove(e);
        return e;
    }

    /**
     * Adds equipment to equipped items list.
     * @param e The equipment to add.
     */
    void AddToEquipped(Equipment e) {
        Log.println("Adding equipment");
        equippedEquipments.add(e);
    }

    /**
     * Removes m from materials list.
     *
     * @param m The material to remove.
     */
    void RemoveMaterial(Material m) {
        Log.println("Removing material");
        materials.remove(m);
    }

    /**
     * Clear touched things.
     */
    void ClearTouched() {
        Log.println("Clearing touched");
        touchedVirologists.clear();
        touchedStorables.clear();
    }

    /**
     * Removes e from equipped items list.
     * @param e The equipment to remove.    
     * @return Removed equipment.
     */
    public Equipment RemoveEquipped(Equipment e){
        Log.println("Removing equipment");
        equippedEquipments.remove(e);
        return e;
    }

    /**
     * Removes a from crafted agents list.
     * @param a The agent to remove.
     */
    public void RemoveCraftedAgent(Agent a){
        Log.println("Removing crafted agent");
        craftedAgents.remove(a);
    }

    /**
     * Removes e from active effects list.
     * @param e The effect to remove.
     */
    public void RemoveEffect(Effect e){
        Log.println("Removing effect");
        activeEffects.remove(e);
    }

    /**
     * Removes g from known genetic codes list.
     * @param g The genetic code to remove.
     */
    public void RemoveGeneticCode(GeneticCode g){
        Log.println("Removing genetic code");
        knownGeneticCodes.remove(g);
    }

    /**
     * Invokes TouchedBy on every Touchable on the current field.
     */
    public void TouchField(){
        Log.blockStart("-> Touching current field with a virologist");
        ClearTouched();
        currentField.VirologistTouch(this);
        knownFields.addAll(currentField.GetNeighbours());
        Log.blockEnd("<- Finished touching current field.");
    }

    /**
     * Invokes StoreIn on s.
     * @param s The storable to invoke StoreIn on.
     */
    public void PickUp(Storable s){
        Log.blockStart("-> Picking up");
        s.StoreIn(this,currentField);
        Log.blockEnd("<- Finished picking up ");
    }

    /**
     * Creates an agent from g if virologist has enough materials, and adds it to craftedAgents list.
     * @param g The genetic code to create an agent from.
     */
    public boolean CraftAgent(GeneticCode g){
        Log.blockStart("-> Crafting agent from genetic code");
        ArrayList<Material> recipe = new ArrayList<>(g.GetRecipe());
        ArrayList<Supplier<Boolean>> foundMaterials = new ArrayList<>(); //Store functions to remove the materials ticked off from the recipe.
        ArrayList<Material> matcopy = new ArrayList<>(materials);

        //Check if virologist has enough materials
        checks:
        for(int i = recipe.size()-1; i >= 0; i--){
            Material m = recipe.get(i);
            for(int n = matcopy.size()-1; n >= 0; n--){
            //Check virologist inventory
                if(matcopy.get(n).CheckIngredient(m)){
                    recipe.remove(i);
                    final Material material = matcopy.get(n);
                    matcopy.remove(material);
                    foundMaterials.add(()->{
                        materials.remove(material);
                        return true;
                    });
                    continue checks;
                }
            }
            //Check equipments inventory
            for(Equipment e : equippedEquipments){
                for(int n = e.GetMaterialCount()-1; n >= 0; n--){
                    if(e.GetMaterial(n).CheckIngredient(m)){
                        recipe.remove(i);
                        final Equipment equipment = e;
                        final Material material = e.GetMaterial(n);
                        foundMaterials.add(()->{
                            equipment.RemoveMaterial(material);
                            return true;
                        });
                        continue checks;
                    }
                }
            }
        }

        if(recipe.size() == 0){
            //Remove all the found materials ticked off from the recipe
            for(Supplier<Boolean> s : foundMaterials){
                s.get();
            }
            AddAgent(g.GetAgent());
        }
        Log.blockEnd("<- Finished crafting agent.");
        return recipe.size() == 0;
    }

    /**
     * Steals e from target, if target has effects that allow stealing.
     * @param target The target to steal from.
     * @param e The equipment to steal.
     */
    public void StealEquipment(Virologist target,Equipment e){
        Log.blockStart("-> Stealing equipment from virologist");
        Equipment stolen = target.OnRobbed(e);
        if(stolen != null){
            stolen.StoreIn(this,null);
            //target.RemoveEquipment(e);
        }
        Log.blockEnd("<- Finished stealing equipment.");
    }

    /**
     * Applies an agents effects on the virologist, if the active effects allow that.
     * Invokes OnEffectApplied on the agent and OnAgentReceived on active effects.
     * @param a The agent to apply on this virologist.
     * @param from The other virologist the agent came from.
     * @return Agent was accepted by virologist or not
     */
    public boolean AcceptAgent(Agent a,Virologist from){
        Log.blockStart("-> Accepting agent");
        boolean canBeApplied = true;
        for (Effect f : activeEffects) {
            if(!f.OnAgentReceive(a,this,from)){
                canBeApplied = false;
                break;
            }
        }
        Log.println("Can the agent be applied? (Y/N)");
        if (canBeApplied) {
            Log.println("\tY - The agent can be applied.");
            Effect e = a.GetEffect();
            ApplyEffect(e);
            e.OnEffectApplied(this);

        } else {
            Log.println("\tN - The agent can't be applied.");
        }
        Log.blockEnd("<- Finished accepting agent");
        return canBeApplied;
    }

    /**
     * Decrements agents and effects age,
     * and invokes their OnRoundBegin on the virologist.
     */
    public void RoundBegin(){
        Log.blockStart("-> Virologist - " + hashCode() + " Starting Round.");
        boolean canStartRound = true;
        for (int i = activeEffects.size() - 1; i >= 0; --i) {
            canStartRound = canStartRound & activeEffects.get(i).OnRoundBegin(this);
        }
        for (int i = craftedAgents.size() - 1; i >= 0; --i) {
            canStartRound = canStartRound & craftedAgents.get(i).OnRoundBegin(this);
        }
        if (!canStartRound) {
            GameController.Single.EndTurn();
        }
        Log.blockEnd("<- Finished Round.");
    }

    /**
     * Applies an agents effects on the target virologist, if the active effects allow that.
     * @param a The agent to apply on the target.
     * @param target The target virologist.
     */
    public void ThrowAgent(Agent a,Virologist target){
        Log.blockStart("-> Throwing agent");
        target.AcceptAgent(a,this);
        RemoveCraftedAgent(a);
        Log.blockEnd("<- Finished throwing agent");
    }

    /**
     * Places the virologist on the destination field.
     * @param dest The destination field.
     */
    public void MoveTo(Field dest){
        Field accepted = dest;
        Log.blockStart("-> Moving virologist");
        for(Effect e : activeEffects){
            //if(e != null) {
            Log.blockStart("-> e.DestinationChange(accepted, currentField, GetNeighbors())");
            accepted = e.DestinationChange(accepted, currentField.GetNeighbours());
            Log.blockEnd("<- " + accepted != null ? "Accepted" : "Rejected");
            //}
        }
        Log.println("Can the virologist move from the current field? (Y/N)");
        if (accepted != null && currentField.GetNeighbours().contains(accepted)) {
            Log.println("\tY - Virologist moves to an other field.");
            currentField.RemoveVirologist(this);
            accepted.PlaceVirologist(this);
            currentField = accepted;
            ClearTouched();
        } else
            Log.println("\tN - Virologist stays on the current field.");
        Log.blockEnd("<- Finished moving virologist");
    }

    /**
     * Adds an equipment to the equipments list.
     * @param e The equipment to add.
     */
    public void AddEquipment(Equipment e){
        Log.println("Adding equipment");
        equipments.add(e);
    }

    /**
     * Adds an agent to the crafted agents list.
     * @param a The agent to add.
     */
    public void AddAgent(Agent a){
        Log.println("Adding agent");
        craftedAgents.add(a);
    }

    /**
     * Equips an equipment from the inventory and applies it's effect, removes that equipment from the equipments list.
     * @param e The equipment to equip.
     */
    public void EquipEquipment(Equipment e){
        Log.blockStart("-> Equipping equipment");
        if(equippedEquipments.size() < 3) {
            AddToEquipped(e);
            if (e.GetEffect() != null) {
                ApplyEffect(e.GetEffect());
            }
            RemoveEquipment(e);
        }
        Log.blockEnd("<- Finished equipping equipment");
    }

    /**
     * Removes e from the equipments list and returns it,
     * if active effects allow stealing the equipment.
     * @param e The equipment to remove.
     * @return Stolen equipment or null.
     */
    public Equipment OnRobbed(Equipment e){
        Log.blockStart("-> OnRobbed");
        if(activeEffects.size() == 0){
            Log.blockEnd("<- Return null");
            return null;
        }
        Equipment s = e;
        for(Effect f : activeEffects){
            s = (Equipment) f.StealStorable(e);
            if(s != null){
                break;
            }
        }
        if(s != null){
            RemoveEffect(s.GetEffect());
            RemoveEquipment(s);
            RemoveEquipped(s);
        }
        Log.blockEnd("<- Return stolen equipment");
        return s;
    }

    /**
     * Removes all materials from the virologist and returns it,
     * if active effects allow stealing the materials.
     * @return Stolen materials or null.
     */
    public ArrayList<Material> OnRobbed(){
        Log.blockStart("-> OnRobbed");
        if(activeEffects.size() == 0){
            Log.blockEnd("<- Return null");
            return null;
        }
        ArrayList<Material> stolen = new ArrayList<>();
        for(int i = materials.size()-1; i >= 0; i--){
            Material s = materials.get(i);
            for(Effect f : activeEffects){
                s = (Material) f.StealStorable(materials.get(i));
                if(s != null){
                    break;
                }
            }
            if(s != null){
                RemoveMaterial(s);
                stolen.add(s);
            }
        }
        if(stolen == null || stolen.size() == 0){
            Log.blockEnd("<- Returning no materials");
        }
        else Log.blockEnd("<- Return stolen materials");
        return stolen;
    }

    /**
     * Steals and stores materials from the target virologist, if active effects allow stealing.
     * @param target The target virologist.
     */
    public void StealMaterial(Virologist target){
        Log.blockStart("-> Stealing material");
        ArrayList<Material> stolen = target.OnRobbed();
        if(stolen != null){
            for(Material m : stolen){
                if(this.StoreMaterial(m) == false)
                    target.StoreMaterial(m);
            }
        }
        Log.blockEnd("<- Finished stealing material");
    }

    /**
     * Adds genetic code to known genetic codes list.
     * If virologist knows all genetic codes, a winner is declared.
     * @param g The genetic code to add.
     */
    public void LearnGenCode(GeneticCode g){
        Log.blockStart("-> Learning genetic code");
        Boolean doiknowthis = false;
        for (GeneticCode ge : knownGeneticCodes) {
            if (ge == g) {
                doiknowthis = true;
                break;
            }
        }
        if (!doiknowthis)
            knownGeneticCodes.add(g);
        if (knownGeneticCodes.size() >= GameController.Single.GetGeneticCodes().size()) {
            GameController.Single.EndGame(this);
        }
        Log.blockEnd("<- Finished learning genetic code");
    }

    /**
     * @return Known genetic codes.
     */
    public ArrayList<GeneticCode> GetKnownGeneticCodes() {
        return new ArrayList<GeneticCode>(knownGeneticCodes);
    }

    

    /**
     * Stores material in virologist or in an equipment capable of storing it.
     * @param m The material to store.
     * @return True if material was stored, false if not.
     */
    public boolean StoreMaterial(Material m) {
        Log.blockStart("-> Storing material");
        if(materials.size() < inventorySize){
            materials.add(m);
            Log.blockEnd("<- Returning true");
            return true;
        } else{
            for(Equipment e : equippedEquipments){
                if(e.StoreMaterial(m)){
                    Log.blockEnd("<- Returning true");
                    return true;
                }
            }
        }
        Log.blockEnd("<- Returning false");
        return false;
    }


    /**
     * Adds virologist to the other virologists touched list.
     * @param other The other virologist who touched this virologist.
     */
    @Override
    public void TouchedBy(Virologist other) {
        Log.blockStart("-> Virologist touched by other virologist");
        if(other != this)
            other.touchedVirologists.add(this);
        Log.blockEnd("<- Discovered");
    }

    /**
     * Adds the storable to the touched storables list.
     * @param s The storable to add to touched list.
     */
    public void DiscoverStorable(Storable s) { 
        Log.println("Discovering storable");
        touchedStorables.add(s);
    }

    /**
     * An update happened on the current field
     * @param virologistsHere The virologists on the current field.
     */
    public void FieldUpdated(ArrayList<Virologist> virologistsHere) {
        Log.blockStart("-> Field updated");
        for (Effect e : activeEffects) {
            e.FieldUpdated(this,virologistsHere);
        }
        Log.blockEnd("<- Finished");
    }

    /**
     * Use an equipment on a virologist.
     *
     * @param e      The equipment to use.
     * @param target The virologist to use the equipment on.
     */
    public void UseEquipmentOn(Equipment e, Virologist target) {
        Log.blockStart("-> Using equipment on virologist");
        e.UseEquipmentOn(this, target);
        Log.blockEnd("<- Finished");
    }

    /**
     * Is invoked when the command "expand" is executed from the command line interface.
     * @param fields The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes The GeneticCodes that will be expanded.
     * @param materials2 The Materials that will be expanded.
     * @param equipments2 The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    public void Expand(HashMap<String, Field> fields,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes,HashMap<String, Material> materials2,HashMap<String, Equipment> equipments2,HashMap<String, Virologist> virologists) {
        Log.println(" <Virologist>");
        Log.print("touchedVirologists :");
        if(touchedVirologists.size()>0) {
            for (int i = 0; i < touchedVirologists.size(); ++i)
                Log.print(" " + Main.findKey(virologists, touchedVirologists.get(i)));
        } else
            Log.print(" null");
        Log.println("");
        Log.print("touchedStorables :" );
        if(touchedStorables.size()>0){
            for(int i = 0; i<touchedStorables.size();++i)
                Log.print(" " + touchedStorables.get(i).getClass().getSimpleName());
        } else
            Log.print(" null");
        Log.println("");
        Log.print("equippedEquipments :");
        if(equippedEquipments.size()>0) {
            for (int i = 0; i < equippedEquipments.size(); ++i)
                Log.print(" " + Main.findKey(equipments2, equippedEquipments.get(i)));
        } else
            Log.print(" null");
        Log.println("");
        Log.print("equipments :");
        if(equipments.size()>0) {
            for (int i = 0; i < equipments.size(); ++i)
                Log.print(" " + Main.findKey(equipments2, equipments.get(i)));
        } else
            Log.print(" null");
        Log.println("");
        Log.print("craftedAgents :");
        if(craftedAgents.size()>0) {
            for (int i = 0; i < craftedAgents.size(); ++i)
                Log.print(" " + Main.findKey(agents, craftedAgents.get(i)));
        } else
            Log.print(" null");
        Log.println("");

        Log.print("knownGeneticCodes :");
        if(knownGeneticCodes.size()>0) {
            for (int i = 0; i < knownGeneticCodes.size(); ++i)
                Log.print(" " + Main.findKey(geneticCodes, (GeneticCode)knownGeneticCodes.toArray()[i]));
        } else
            Log.print(" null");
        Log.println("");
        
        Log.print("materials :");
        if(materials.size()>0) {
            for (int i = 0; i < materials.size(); ++i)
                Log.print(" <" + materials.get(i).getClass().getSimpleName()+">");
        } else
            Log.print(" null");
        Log.println("");
        Log.print("activeEffects :");
        if(activeEffects.size()>0) {
            for (int i = 0; i < activeEffects.size(); ++i)
                Log.print(" <" + activeEffects.get(i).getClass().getSimpleName() + ">");
        } else
            Log.print(" null");
        Log.println("");

        Log.print("currentField : " );
        Log.println(Main.findKey(fields,currentField));
    }

    /**
     * Called on mouse press.
     * @param view GameView
     * @return
     */
    @Override
    public MouseInputAdapter OnPress(GameView view) {
        return new MouseInputAdapter() {
            /**
             * Called on mouse click.
             * @param e MouseEvent
             */
            @Override
            public void mouseClicked(MouseEvent e) {            
                view.ShowPopup(GameController.Single.currentPlayer, Virologist.this);
                super.mouseClicked(e);
            }
        };
    }

    /**
     * @return Equipments in the virologists inventory.
     */
    public ArrayList<Equipment> GetEquipments() {
        return equipments;
    }
    /**
     * @return Equipped equipments.
     */
    public ArrayList<Equipment> GetEquipped() {
        return equippedEquipments;
    }

}
