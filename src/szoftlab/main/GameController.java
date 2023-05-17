package szoftlab.main;

import szoftlab.agent.Agent;
import szoftlab.agent.Chorea;
import szoftlab.agent.Forget;
import szoftlab.agent.GeneticCode;
import szoftlab.agent.Numb;
import szoftlab.agent.Protection;
import szoftlab.equipment.Axe;
import szoftlab.equipment.Bag;
import szoftlab.equipment.Cape;
import szoftlab.equipment.Equipment;
import szoftlab.equipment.Glove;
import szoftlab.map.Field;
import szoftlab.material.AminoAcid;
import szoftlab.material.Material;
import szoftlab.material.Nucleotid;
import szoftlab.ui.EndView;
import szoftlab.ui.GameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * GameController Singleton class
 */
public class GameController{
    /**
     * The singleton.
     */
    public static GameController Single = null;

    /**
     * Players in the game.
     */
    public final ArrayList<Virologist> players;
    /**Fields in the game.*/
    final ArrayList<Field> fields;
    /**Genetic codes in the game.*/
    final ArrayList<GeneticCode> geneticCodes;
    /**Equipment in the game.*/
    final ArrayList<Equipment> equipments;
    /**The current player that's doing stuff this round.*/
    public Virologist currentPlayer;
    /**GameOver*/
    boolean gameOver = true;
    /**Toggle debug mode*/
    public static boolean isDebug = false;
    /**Related to debugging [toggling random to predictable random].*/
    public static boolean randomTestResult = false;
    

    /**
     * Creates a new GameController singleton.
     * @param players The players in the game.
     * @param fields The fields in the game.
     * @param codes The genetic codes in the game.
     * @param equipments The equipments in the game.
     */
    public GameController(ArrayList<Virologist> players, ArrayList<Field> fields, ArrayList<GeneticCode> codes, ArrayList<Equipment> equipments) {
        if (Single == null) {
            Single = this;
        }
        this.players = players;
        this.fields = fields;
        this.geneticCodes = codes;
        this.equipments = equipments;
        if (players.size() > 0)
            currentPlayer = players.get(0);
    }
    /**
     * Creates a new GameController singleton.
     * @param players The players in the game.
     * @param fields The fields in the game.
     * @param codes The genetic codes in the game.
     */
    public GameController(ArrayList<Virologist> players, ArrayList<Field> fields, ArrayList<GeneticCode> codes) {
        if (Single == null) {
            Single = this;
        }
        this.players = players;
        this.fields = fields;
        this.geneticCodes = codes;
        this.equipments = null;
        if (players.size() > 0)
            currentPlayer = players.get(0);
    }

    /**
     * Creates a new GameController singleton.
     *
     * @param players The players in the game.
     * @param fields  The fields in the game.
     * @param codes   The genetic codes in the game.
     * @param isDebug Currently debugging?
     */
    public GameController(ArrayList<Virologist> players, ArrayList<Field> fields, ArrayList<GeneticCode> codes, ArrayList<Equipment> equipments, boolean isDebug) {
        if (Single == null) {
            Single = this;
        }
        this.players = players;
        this.fields = fields;
        this.geneticCodes = codes;
        this.equipments = equipments;
        currentPlayer = players.get(0);
        GameController.isDebug = isDebug;
    }

    /**
     * Starts the game and player 1's turn.
     */
    public void StartGame(){
        if(fields.size() == 0)throw new RuntimeException("No playing field defined.");
        for(var v : players){
            v.currentField = fields.get(0);
            v.currentField.PlaceVirologist(v);
            v.knownFields.add(fields.get(0));
        }
        if(gameOver){
            gameOver = false;
            currentPlayer = players.get(0);
            StartTurn();
        }
    }

    /**
     * Declares the winner and ends the game.
     * @param winner The winner of the game.
     */
    public void EndGame(Virologist winner){
        gameOver = true;
        Log.println("Yay you won.");
        if(Main._GUI && Main.view != null){
            //We have to pass it forward because of scope issues
            ((GameView)Main.view).EndGame(winner);
        }
    }

    /**
     * Finishes the current player's turn and starts the next player's turn.
     */
    public void EndTurn(){
        for (int i = 0; i < players.size(); i++){
            if(players.get(i) == currentPlayer){
                i++;
                currentPlayer = players.get(i< players.size()?i:0);
                break;
            }
        }
        StartTurn();
    }

    /**
     * Starts the current player's turn.
     */
    public void StartTurn(){
        currentPlayer.RoundBegin();
    }

    /**
     * Deletes the singleton instance.
     */
    public static void Delete() {
        if (Single != null) Single = null;
    }

    /**
     * @return Array of genetic codes in game.
     */
    public ArrayList<GeneticCode> GetGeneticCodes() {
        return geneticCodes;
    }
    /**
     * @return Array of equipments in game.
     */
    public ArrayList<Equipment> GetEquipments(){
        return equipments;
    }

    /**
     * Function that's invoked when a player dies.
     *
     * @param player The dying player.
     */
    public void PlayerDeath(Virologist player) {
        Log.println("Removing " + player + " from the game.");
        player.currentField.RemoveVirologist(player);
        player.currentField = null;
        players.remove(player);
    }

    /**
     * Add a field to the GameController instance.
     *
     * @param field Field to add.
     */
    public void AddField(Field field) {
        fields.add(field);
    }

    /**
     * Add a player to the GameController instance.
     *
     * @param player Player to add.
     */
    public void AddPlayer(Virologist player) {
        players.add(player);
    }

    /**
     * Add a genetic code to the GameController instance.
     *
     * @param geneticCode Genetic code to add.
     */
    public void AddGeneticCode(GeneticCode geneticCode) {
        geneticCodes.add(geneticCode);
    }

    /**
     * Is invoked when the command "expand" is executed from the command line interface.
     * @param fields2 The Fields that will be expanded.
     * @param agents The Agents that will be expanded.
     * @param geneticCodes2 The GeneticCodes that will be expanded.
     * @param materials The Materials that will be expanded.
     * @param equipments The Equipments that will be expanded.
     * @param virologists The Virologists that will be expanded.
     */
    public void Expand(HashMap<String, Field> fields2,HashMap<String, Agent> agents,HashMap<String, GeneticCode> geneticCodes2,HashMap<String, Material> materials,HashMap<String, Equipment> equipments,HashMap<String, Virologist> virologists) {
        Log.println(" <GameController>");
        Log.print("players :");
        if(players.size()>0) {
            for (int i = 0; i < players.size(); ++i)
                Log.print(" " + Main.findKey(virologists, players.get(i)));
        } else
            Log.print(" null");
        Log.println("");

        Log.print("fields :");
        if(fields.size()>0) {
            for (int i = 0; i < fields.size(); ++i)
                Log.print(" " + Main.findKey(fields2, (Field)fields.toArray()[i]));
        } else
            Log.print(" null");
        Log.println("");

        Log.print("geneticcodes :");
        if(geneticCodes.size()>0) {
            for (int i = 0; i < geneticCodes.size(); ++i)
                Log.print(" " + Main.findKey(geneticCodes2, geneticCodes.get(i)));
        } else
            Log.print(" null");
        Log.println("");
        
        Log.println("currentPlayer : "+Main.findKey(virologists, currentPlayer));
    }

    /**
     * Creates a new game.
     * @param playerCount How many players there are in the game.
     */
    public void NewGame(int playerCount) {
        players.clear();
        fields.clear();
        geneticCodes.clear();
        for (int i = 0; i < playerCount; i++) {
            players.add(new Virologist(null));
        }
        geneticCodes.addAll(Arrays.asList(
            new GeneticCode(new ArrayList<Material>(Arrays.asList(new AminoAcid(),new Nucleotid())), 
                            new Agent(5, new Protection())),
            new GeneticCode(new ArrayList<Material>(Arrays.asList(new AminoAcid(),new Nucleotid())), 
                            new Agent(5, new Numb())),
            new GeneticCode(new ArrayList<Material>(Arrays.asList(new AminoAcid(),new Nucleotid())), 
                            new Agent(3, new Chorea())),
            new GeneticCode(new ArrayList<Material>(Arrays.asList(new AminoAcid(),new Nucleotid())), 
                            new Agent(5, new Forget()))
        ));
        equipments.addAll(Arrays.asList(
            new Cape(),
            new Axe(),
            new Glove(),
            new Bag()
        ));

    }

}