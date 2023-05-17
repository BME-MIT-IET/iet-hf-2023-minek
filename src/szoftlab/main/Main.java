package szoftlab.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.swing.JFrame;

import szoftlab.agent.*;
import szoftlab.equipment.*;
import szoftlab.interfaces.Storable;
import szoftlab.map.*;
import szoftlab.material.*;
import szoftlab.ui.GameView;
import szoftlab.ui.MenuView;



public class Main {
    static HashMap<String, Field> fields = new HashMap<>();
    static HashMap<String, Agent> agents = new HashMap<>();
    static HashMap<String, GeneticCode> geneticCodes = new HashMap<>();
    static HashMap<String, Material> materials = new HashMap<>();
    static HashMap<String, Equipment> equipments = new HashMap<>();
    static HashMap<String, Virologist> virologists = new HashMap<>();
    static GameController gc;
    static String gcname;
    static boolean inputFlag = false;
    static Scanner fileScanner; 
    static Scanner sc;
    public static final boolean _GUI = true;
    public static JFrame view;

    public static void main(String[] args) {
        gc = new GameController(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        if(_GUI){
            view = new MenuView();
        }
        else{
            sc = new Scanner(System.in);

            boolean running = true;
            Log.println("Program ready!");
            while (running) {
                String line = sc.nextLine();
                String[] tokens = line.split(" ");
                String command = tokens[0];
                String[] arguments = new String[tokens.length - 1];
                for (int i = 0; i < tokens.length - 1; ++i) {
                    arguments[i] = tokens[i + 1];
                }
                parseCommand(command, arguments);
            }
            sc.close();
        }

    }

    /**
     * Parses the commands
     * @param command the command that it parses
     * @param args extra arguments with the command
     */
    static void parseCommand(String command, String[] args){
        if (command.equals("help")) {
            if (args.length == 0) {
                for (Command c : commands) {
                    c.printHelp();
                }
            } else {
                for (Command c : commands) {
                    if (c.name.equals(args[0])) {
                        c.printHelp();
                        c.printDescription();
                    }
                }
            }
        } else {
            for (Command c : commands) {
                if (c.name.equals(command)) {
                    c.execute(args);
                }
            }
        }

    }

    /**
     * Looks up the objects name in the catalogue. Check if it's available.
     *
     * @param name Lookup name
     * @return false -> Name is in use, true -> Name not in use
     */
    static boolean checkNameAvailability(String name) {
        if (virologists.containsKey(name)) {
            return false;
        } else if (fields.containsKey(name)) {
            return false;
        } else if (materials.containsKey(name)) {
            return false;
        } else if (equipments.containsKey(name)) {
            return false;
        } else if (geneticCodes.containsKey(name)) {
            return false;
        } else if (agents.containsKey(name)) {
            return false;
        } else
            return true;
    }

    /**
     * All of the different commands.
     */
    static Command[] commands = {
            new Command("inputscript", "<forrásfájl>", "A paraméterben megkapott bemeneti nyelv kifejezéseit tartalmazó szövegfájlból beolvasott utasításokat hajtja végre.", (String[] args) -> {
                try {
                    inputFlag = true;
                    File file = new File(args[0]);
                    fileScanner = new Scanner(file);
                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] tokens = line.split(" ");
                        String command = tokens[0];
                        String[] arguments = new String[tokens.length - 1];
                        for (int i = 1; i < tokens.length; i++) {
                            arguments[i - 1] = tokens[i];
                        }
                        parseCommand(command, arguments);
                    }
                    inputFlag = false;
                } catch (Exception e) {
                    Log.println("A megadott fájl nem található!");
                }   
        }),
        new Command("defineoutput","<kimeneti fájl>","A paraméterként megkapott fájlnévvel létrehoz egy fájlt, melybe a standard output-ot fogja lementeni a program futása alatt.", (String[] args) ->{
            Log.outputFile = args[0];
        }),
        new Command("field","<név> <típus>","A parancs létrehoz egy a felhasználó által megadott mezőt a játékban. A mezők típusa lehet: lab,bearlab,warehouse,empty,shelter", (String[] args) -> {
            String name;
            String type;
            try {
                name = args[0];
                type = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            Field f;
            if (!checkNameAvailability(name)) {
                Log.println("Name already in use.");
                return;
            }
            switch (type.toLowerCase()) {
                case "laboratory":
                    f = new Laboratory();
                    break;
                case "warehouse":
                    f = new Warehouse();
                    break;
                case "empty":
                    f = new Empty();
                    break;
                case "shelter":
                    f = new Shelter();
                    break;
                case "bearlab":
                    f = new BearLaboratory();
                    break;
                default:
                    Log.println("A megadott mező típusa nem létezik!");
                    return;
            }

            fields.put(name, f);
            gc.AddField(f);

        }),
        new Command("query","<típus>","A parancs használata lehetővé teszi, hogy lekérdezze a felhasználó, hogy a játékban aktuálisan milyen mezők/virológusok/genetikai kódok szerepelnek.", (String[] args) ->{
            try {
                switch (args[0].toLowerCase()) {
                    case "fields":
                        Log.println("A játékban lévő mezők:");
                        for (String key : fields.keySet()) {
                            Log.println(key);
                        }
                        break;
                    case "virologists":
                        Log.println("A játékban lévő virológusok:");
                        for (String key : virologists.keySet()) {
                            Log.println(key);
                        }
                        break;
                    case "gcodes":
                        Log.println("A játékban lévő genetikai kódok:");
                        for (String key : geneticCodes.keySet()) {
                            Log.println(key);
                        }
                        break;
                    case "agents":
                        Log.println("A játékban lévő ágensek:");
                        for (String key : agents.keySet()) {
                            Log.println(key);
                        }
                        break;
                    default:
                        Log.println("A megadott típus nem létezik!");
                }
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
            }
        }),
        new Command("setneighbor","<mező_név> <mező_név>","A parancs lehetővé teszi, hogy a paraméterként megadott két mező (azonosítója) szomszédja lehessen egymásnak.", (String[] args) -> {
            String name1;
            String name2;
            try {
                name1 = args[0];
                name2 = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (fields.containsKey(name1) && fields.containsKey(name2)) {
                fields.get(name1).AddNeighbor(fields.get(name2));
            } else {
                Log.println("A megadott mezők nem léteznek!");
            }
        }),
        new Command("geneticcode","<név> <aminosav száma> <nukleotid száma> <ágens>","Genetikus kód létrehozása a játékban. Paraméterként a parancs átveszi a genetikus kódból előállítható vakcinához szükséges anyagokat, valamint azt, hogy milyen ágens készíthető ezáltal.", (String[] args) -> {
            String name;
            Agent a;
            int aminos;
            int nukleotides;
            try {
                name = args[0];
                aminos = Integer.parseInt(args[1]);
                nukleotides = Integer.parseInt(args[2]);
                a = agents.get(args[3]);
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }

            if (!checkNameAvailability(name)) {
                Log.println("Name already in use!");
                return;
            }
            ArrayList<Material> materials = new ArrayList<>();
            for (int i = 0; i < aminos; i++) {
                materials.add(new AminoAcid());
            }
            for (int i = 0; i < nukleotides; i++) {
                materials.add(new Nucleotid());
            }

            if (a != null) {
                GeneticCode g = new GeneticCode(materials, a);
                geneticCodes.put(name, g);
                gc.AddGeneticCode(g);
            } else {
                Log.println("Unable to find the correct agent?");
            }
        }),
        new Command("equipment","<név> <típus>","Egy felszerelés létrehozása. Paraméterként megadható, hogy milyen felszerelést hozzunk létre a játékban.", (String[] args) -> {
            String name;
            String type;
            Equipment e;
            try {
                name = args[0];
                type = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }

            if (!checkNameAvailability(name)) {
                Log.println("Name already in use!");
                return;
            }
            switch (type.toLowerCase()) {
                case "axe":
                    e = new Axe();
                    break;
                case "bag":
                    e = new Bag();
                    break;
                case "cape":
                    e = new Cape();
                    break;
                case "glove":
                    e = new Glove();
                    break;
                default:
                    Log.println("A megadott típus nem létezik!");
                    return;                
            }
            equipments.put(name, e);
        }),
        new Command("material","<név> <típus>","Egy anyag létrehozása. Paraméterként megadható az anyag típusa, valamint a neve.", (String[] args) -> {
            String name;
            String type;
            Material m;
            try {
                name = args[0];
                type = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (!checkNameAvailability(name)) {
                Log.println("Name already in use!");
                return;
            }
            switch (type.toLowerCase()) {
                case "aminoacid":
                    m = new AminoAcid();
                    break;
                case "nucleotid":
                    m = new Nucleotid();
                    break;
                default:
                    Log.println("A megadott típus nem létezik!");
                    return;                
            }
            materials.put(name, m);
        }),
        new Command("virologist","<név> <mező>","A parancs kiadásával létrehozhatunk egy virológust. Paraméterként beállításra kerül az a mező, ahol a virológus tartózkodni fog létrehozásával.", (String[] args) -> {
            String name;
            String field;

            try {
                name = args[0];
                field = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (!checkNameAvailability(name)) {
                Log.println("Name already in use!");
                return;
            }
            if (fields.containsKey(field)) {
                Virologist v = new Virologist(fields.get(field));
                virologists.put(name, v);
                gc.AddPlayer(v);
            } else {
                Log.println("A megadott mező nem létezik!");
            }
        }),
        new Command("moveto","<virológus> <mező>","A parancs kiadásával mozgatható egy paraméterként megadott virológus egy paraméterként megadot mezőre.", (String[] args) -> {
            String name;
            String field;
            try {
                name = args[0];
                field = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }

            if (virologists.containsKey(name) && fields.containsKey(field)) {
                virologists.get(name).MoveTo(fields.get(field));
            } else {
                Log.println("A megadott virológus vagy mező nem létezik!");
            }
        }),
        new Command("touchfield","<virológus>","A parancs kiadásával a paraméterként megkapott virológus körbe tud tapogatni az aktuális mezőn.", (String[] args) -> {
            String name;
            try {
                name = args[0];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (virologists.containsKey(name)) {
                virologists.get(name).TouchField();
            } else {
                Log.println("A megadott virológus nem létezik!");
            }
        }),
        new Command("interactwithfield","<virológus>","A parancs kiadásával a paraméterként megkapott virológus interakcióba tud lépni a mezőn lévő lehetséges és már előtte letapogatott dologgal/dolgokkal. Ezzel a paranccsal tud például genetikus kódot tanulni, felszerelést felvenni az óvóhelyról, anyagokat felvenni a raktárból stb.", (String[] args) -> {
            String name;
            try {
                name = args[0];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (virologists.containsKey(name)) {
                Virologist v = virologists.get(name);
                for (Storable s : v.touchedStorables) {
                    v.PickUp(s);
                }
            } else {
                Log.println("A megadott virológus nem létezik!");
            }
        }),
        new Command("equipgear","<virológus> <felszerelés>","A parancs kiadásával a paraméterként megadott virológus raktárából fel tudunk venni felszerelést (ha a virológus raktára tartalmazza az adott felszerelést)", (String[] args) -> {
            String name;
            String equipment;
            try {
                name = args[0];
                equipment = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (virologists.containsKey(name) && equipments.containsKey(equipment)) {
                virologists.get(name).EquipEquipment(equipments.get(equipment));
            } else {
                Log.println("A megadott virológus vagy felszerelés nem létezik!");
            }
        }),
        new Command("addcontenttofield","<mező> <interakciós dolog>","A parancs kiadásával a paraméterként megadott mezőre tehetünk a virológus által interakcióba léphető dolgokat. Például egy laborba genetikai kódot, vagy óvóhelyre felszerelést.", (String[] args) -> {
            String field;
            String storable;
            try {
                field = args[0];
                storable = args[1];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (fields.containsKey(field)) {
                Storable s;
                if (materials.containsKey(storable)) {
                    s = materials.get(storable);
                } else if (equipments.containsKey(storable)) {
                    s = equipments.get(storable);
                } else if (geneticCodes.containsKey(storable)) {
                    s = geneticCodes.get(storable);
                } else {
                    Log.println("A megadott interakciós dolog nem létezik!");
                    return;
                }

                String fclassname = fields.get(field).getClass().getSimpleName();
                String sclassname = s.getClass().getPackageName();
                if (fclassname.equals("Laboratory") && s.getClass().getSimpleName().equals("GeneticCode"))
                    fields.get(field).AddStorable(s);
                else if (fclassname.equals("BearLaboratory") && s.getClass().getSimpleName().equals("GeneticCode"))
                    fields.get(field).AddStorable(s);
                else if (fclassname.equals("Shelter") && sclassname.equals("szoftlab.equipment"))
                    fields.get(field).AddStorable(s);
                else if (fclassname.equals("Warehouse") && sclassname.equals("szoftlab.material"))
                    fields.get(field).AddStorable(s);
                else {
                    Log.println("Non-compatible field&stuff!");
                }
            } else {
                Log.println("A megadott mező vagy interakciós dolog nem létezik!");
            }
        }),
        new Command("expand","<objektum neve>","A parancs a paraméterként megkapott objektum nevét veszi figyelembe és kilistázza az objektum tulajdonságait (pl.: virológusnak a státuszát, laboratórium státuszát, stb.)", (String[] args) -> {
            String name;
            try {
                name = args[0];
            } catch (IndexOutOfBoundsException err) {
                Log.println("Invalid argument(s)!");
                return;
            }
            if (virologists.containsKey(name)) {
                Log.print("Current properties of : " + name);
                virologists.get(name).Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else if (fields.containsKey(name)) {
                Log.print("Current properties of : " + name);
                fields.get(name).Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else if (materials.containsKey(name)) {
                Log.print("Current properties of : " + name);
                materials.get(name).Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else if (equipments.containsKey(name)) {
                Log.print("Current properties of : " + name);
                equipments.get(name).Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else if (agents.containsKey(name)) {
                Log.print("Current properties of : " + name);
                agents.get(name).Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else if (geneticCodes.containsKey(name)) {
                Log.print("Current properties of : " + name);
                geneticCodes.get(name).Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else if (name.equals(gcname)) {
                Log.print("Current properties of : " + name);
                gc.Expand(fields,agents,geneticCodes,materials,equipments,virologists);
            } else {
                Log.println("A megadott objektum nem létezik!");
            }
        }),
            new Command("startgame", "<game_controller_name>", "A parancs kiadásával elindíthatunk egy játékot. Ilyenkor a program azt fogja számításba venni, hogy milyen játékosokat/mezőket/genetikus kódokat adtunk már hozzá a programunkhoz a parancs kiadása előtt. A játékmenetet ezekkel az objektumokkal fogjuk elkezdeni. Paraméterként megadhatjuk a GameController nevét is.", (String[] args) -> {
                try {
                    gcname = args[0];
                } catch (Exception err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                if (gc.players.size() > 0)
                    gc.StartGame();
                else
                    Log.println("U dumb?");
            }),
            new Command("agent", "<ágens neve> <hány körig érvényes> <ágens típusa>", "Új ágens létrehozása. Paraméterként megadható az anyag típusa és a mező, ahova létrehozzuk. ", (String[] args) -> {
                String name;
                String valid_for;
                String type_of_agent;

                try {
                    name = args[0];
                    valid_for = args[1];
                    type_of_agent = args[2];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }
                if (!checkNameAvailability(name)) {
                    Log.println("Name already in use!");
                    return;
                }

                Agent a;
                try {
                    int x = Integer.parseInt(valid_for);
                } catch (Exception err) {
                    Log.println("Invalid remaining time! Check again!");
                    return;
                }

                switch (type_of_agent.toLowerCase()) {
                    case "forget":
                        a = new Agent(Integer.parseInt(valid_for), new Forget());
                        break;
                    case "chorea":
                        a = new Agent(Integer.parseInt(valid_for), new Chorea());
                        break;
                    case "protection":
                        a = new Agent(Integer.parseInt(valid_for), new Protection());
                        break;
                    case "numb":
                        a = new Agent(Integer.parseInt(valid_for), new Numb());
                        break;
                    default:
                        Log.println("Invalid agent type? Check again.");
                        return;
                }

                agents.put(name, a);

            }),
            new Command("endturn", "<virológus>", "A paraméterként megkapott virológus körének a befejezése.", (String[] args) -> {
                String vironame;
                try {
                    vironame = args[0];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }
                if (!checkNameAvailability(vironame)) {
                    if (gc.currentPlayer == virologists.get(vironame)) {
                        GameController.Single.EndTurn();
                        Log.println("Ending " + vironame + "'s turn...");
                    } else
                        Log.println("It's not " + vironame + "'s turn!");
                } else
                    Log.println("Virologist with name: " + vironame + " doesn't exist");

            }),
            new Command("useequipment", "<player> <equipment> <target_player>", "A paraméterként megkapott virológus egyik felszerelésének használata egy másik virológuson.", (String[] args) -> {
                String player;
                String equip;
                String target_player;
                try {
                    player = args[0];
                    equip = args[1];
                    target_player = args[2];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }
                Virologist p = virologists.get(player);
                Virologist tp = virologists.get(target_player);
                Equipment eq = equipments.get(equip);

                if (p == null || tp == null || eq == null)
                    return;
                else {
                    if (gc.currentPlayer == p && p.equippedEquipments.size() > 0) {
                        for (int i = 0; i < p.equippedEquipments.size(); ++i) {
                            if (p.equippedEquipments.get(i).equals(eq)) {
                                p.equippedEquipments.get(i).UseEquipmentOn(p, tp);
                                break;
                            }
                        }
                    }
                }

            }),
            new Command("addagent", "<virológus>  <ágens neve>", "Agens hozzáadása virológus inventoryjához", (String[] args) -> {
                String player;
                String agentType;
                try {
                    player = args[0];
                    agentType = args[1];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                if (virologists.containsKey(player) && agents.containsKey(agentType)) {

                    try {
                        virologists.get(player).craftedAgents.add(agents.get(agentType));
                    } catch (Exception err) {
                        Log.println(Arrays.toString(err.getStackTrace()));
                    }
                }
            }),

            new Command("addmaterial", "<virológus> <anyag típusa> <anyag mennyisége>", "Anyag hozzáadása virológushoz", (String[] args) -> {
                String player;
                String materialType;
                int numOfMaterial;
                try {
                    player = args[0];
                    materialType = args[1];
                    numOfMaterial = Integer.parseInt(args[2]);
                } catch (Exception err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                if (virologists.containsKey(player)) {
                    switch (materialType) {
                        case "aminoacid":
                            for (int i = 0; i < numOfMaterial; i++) {
                                virologists.get(player).materials.add(new AminoAcid());
                            }
                            break;
                        case "nucleotid":
                            for (int i = 0; i < numOfMaterial; i++) {
                                virologists.get(player).materials.add(new Nucleotid());
                            }
                            break;
                        default:
                            Log.println("A megadott típus nem létezik!");
                    }
                } else if (equipments.containsKey(player) && equipments.get(player).getClass().getSimpleName().equals("Bag")) {
                    Bag b = (Bag) equipments.get(player);
                    switch (materialType) {
                        case "aminoacid":
                            for (int i = 0; i < numOfMaterial; i++) {
                                b.StoreMaterial((new AminoAcid()));
                            }
                            break;
                        case "nucleotid":
                            for (int i = 0; i < numOfMaterial; i++) {
                                b.StoreMaterial((new Nucleotid()));
                            }
                            break;
                        default:
                            Log.println("A megadott típus nem létezik!");
                    }
                }
            }),
            new Command("addgear", "<player> <equipment>", "Felszerelés hozzáadása a játékos raktárához.", (String[] args) -> {
                String player;
                String equipment;
                try {
                    player = args[0];
                    equipment = args[1];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                Virologist vp;
                Equipment eq;

                if (!checkNameAvailability(player) && !checkNameAvailability(equipment)) {

                    try {
                        vp = virologists.get(player);
                        eq = equipments.get(equipment);

                        vp.equipments.add(eq);
                    } catch (Exception err) {
                        Log.println("Command raised an exception due to incorrect usage! Please check!");
                    }
                }

            }),
            new Command("useagent", "<virológus>  <target>", "Agens kenése paraméterként megadott virológus által másik paraméterként megadott virológusra", (String[] args) -> {
                String playerinp;
                String targetinp;
                try {
                    playerinp = args[0];
                    targetinp = args[1];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                Virologist player = virologists.get(playerinp);
                Virologist target = virologists.get(targetinp);

                //A világ összes bolondbiztos check-je
                if (player != null && target != null) {
                    if (player.currentField == target.currentField) {
                        if (player.craftedAgents.size() > 0) {
                            Log.println("Select the agent you want to use [0.." + player.craftedAgents.size() + "]");
                            for (int i = 0; i < player.craftedAgents.size(); ++i) {
                                Log.println(i + " - " + player.craftedAgents.get(i).GetEffect().getClass().getSimpleName());
                            }

                            //User-input
                            Scanner scInp;
                            if(!inputFlag)
                               scInp = sc;
                            else
                               scInp = fileScanner;
                            String userInp = scInp.nextLine();
                            int realSelection;
                            try {
                                realSelection = Integer.parseInt(userInp);
                            } catch (NumberFormatException err) {
                                Log.println("Invalid user input!");
                                return;
                            }

                            try {
                                player.ThrowAgent(player.craftedAgents.get(realSelection), target);
                            } catch (IndexOutOfBoundsException err) {
                                Log.println("U dumb? Virologist doesn't have this agent with the ID " + realSelection);
                            }

                        } else {
                            Log.println("Attacker doesn't have any crafted agent!");
                        }
                    } else {
                        Log.println("The two virologist aren't on the same field!");
                    }
                }
            }),
            new Command("steal", "<player> <target>", "A parancs kiadásával a paraméterként megkapott virológus lophat a másik paraméterként megadott virológustól. A parancs kiadása után a felhasználónak választania kell, hogy mit szeretne lopni a megcélzott virológustól (feltéve ha nem béna)", (String[] args) -> {
                String playerinp;
                String targetinp;

                try {
                    playerinp = args[0];
                    targetinp = args[1];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                Virologist player = virologists.get(playerinp);
                Virologist target = virologists.get(targetinp);

                if (player != null && target != null && (player.currentField == target.currentField)) {
                    if (target.materials.size() > 0 || target.equippedEquipments.size() > 0) {

                        Log.println("Select the stuff you want to steal [0.." + target.equippedEquipments.size() + "]");
                        Log.println("0 - Materials");
                        for (int i = 1; i < target.equippedEquipments.size() + 1; ++i) {
                            Log.println(i + " - " + target.equippedEquipments.get(i - 1).getClass().getSimpleName());
                        }

                        Scanner scInp;
                        if(!inputFlag)
                           scInp = sc;
                        else
                           scInp = fileScanner;
                        String inp = scInp.nextLine();
                        int validInp;
                        try {
                            validInp = Integer.parseInt(inp);
                        } catch (NumberFormatException err) {
                            Log.println("Please enter a valid number!");
                            return;
                        }

                        if (validInp == 0) {
                            player.StealMaterial(target);
                        } else if (validInp > 0) {
                            try {
                                player.StealEquipment(target, target.equippedEquipments.get(validInp - 1));
                            } catch (Exception err) {
                                Log.println("Invalid input maybe?");
                            }
                        }

                    } else {
                        Log.println("Target virologist doesn't have anything to steal :c");
                    }

                } else {
                    Log.println("Virologist existance problem or they are not on the same field.");
                }

            }),
            new Command("createagent", "<player_virologist>", "A parancs kiadásával a paraméterként megkapott virológus készíthet dobható ágenst. További lehetősége (hogy milyen ágenst készítsen) a parancs kiadása után válik válaszhatóvá, melyet a felhasználónak kell bevinnie.", (String[] args) -> {
                String pstring;
                try {
                    pstring = args[0];
                } catch (IndexOutOfBoundsException err) {
                    Log.println("Invalid argument(s)!");
                    return;
                }

                Virologist player = virologists.get(pstring);

                if (player != null) {
                    if (player.knownGeneticCodes.size() > 0) {
                        Log.println("Select the agent you want to craft [1.." + player.knownGeneticCodes.size() + "]");
                        for (int i = 0; i < player.knownGeneticCodes.size(); ++i) {
                            Log.println(i + 1 + " - " + player.GetKnownGeneticCodes().get(i).GetAgent().GetEffect().getClass().getSimpleName());
                        }
                        Scanner scInp;
                        if(!inputFlag)
                           scInp = sc;
                        else
                           scInp = fileScanner;
                        int scInpInt = scInp.nextInt();

                        player.CraftAgent(player.GetKnownGeneticCodes().get(scInpInt - 1));

                    } else {
                        Log.println("Virologist doesn't know any genetic codes!");
                    }
                }

            })

    };

    /**
     * In a Map finds the Key which is connected to the Value.
     * @param map The map where it searches
     * @param value The Value which it searches for the Key pair.
     * @return Key that it searched for.
     */
    public static <T> String findKey(HashMap<String,T> map,T value){
        for(var entry : map.entrySet()){
            if(entry.getValue().equals(value)){
                return entry.getKey();
            }
        }
        return null;
    }

}

/**
 * Class that contains the different commands
 */
class Command{
    public String name;
    String parameters;
    String description;
    Consumer<String[]> action;
    Command(String name, String parameters,String description, Consumer<String[]> action){
        this.name = name;
        this.parameters = parameters;
        this.description = description;
        this.action = action;
    }

    /**
     * Prints the help menu.
     */
    public void printHelp(){
        Log.println(name+" "+parameters);
    }

    /**
     * Prints the Description for a command.
     */
    public void printDescription(){
        Log.println(description);
    }

    /**
     * Executes the command with the given arguments.
     * @param args Given arguments.
     */
    public void execute(String[] args){
        action.accept(args);
    }
}
