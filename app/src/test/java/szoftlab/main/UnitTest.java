package szoftlab.main;

import szoftlab.agent.*;
import szoftlab.equipment.*;
import szoftlab.map.*;
import szoftlab.material.AminoAcid;
import szoftlab.material.Material;
import szoftlab.material.Nucleotid;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Teszteléshez használt osztály (Skeleton)
 */
public class UnitTest {
    static int testCount = 1;

    /**
     * Saját assertEquals gradle féle compiling miatt.
     * JUnit-os dolgokat csak a teszthez buildel de nekünk main-ben is kell, manuális tesztekhez.
     *
     * @param expected elvárt objektum
     * @param actual   jelenlegi objektum
     */
    private static void assertEquals(Object expected, Object actual) {
        if (expected != null && actual != null) {
            if (expected.hashCode() != actual.hashCode()) {
                throw new AssertionError("Expected: " + expected + " | Actual: " + actual);
            }
        }
        if (expected == null && actual != null)
            throw new AssertionError("Expected null: " + expected + "| Actual: " + actual);
        else if (actual == null && expected != null)
            throw new AssertionError("Expected: " + expected + "| Actual null: " + actual);
    }

    /**
     * Saját assertNotEquals gradle féle compiling miatt.
     * JUnit-os dolgokat csak a teszthez buildel de nekünk main-ben is kell, manuális tesztekhez.
     *
     * @param expected elvárt objektum
     * @param actual   jelenlegi objektum
     */
    private static void assertNotEquals(Object expected, Object actual) {
        if (expected != null && actual != null) {
            if (expected.hashCode() == actual.hashCode()) {
                throw new AssertionError("Expected: " + expected + " | Actual: " + actual);
            }
        }
    }

    /**
     * Tesztesetek előtt futó függvény, az indentáláshoz és kiiratáshoz nyújt segítséget.
     */
    @BeforeAll
    public static void TestingStart() {
        Log.indent = 2;
        Log.println("Starting tests...", 0);
    }

    /**
     * Tesztesetek után futó függvény, az indentáláshoz és kiiratáshoz nyújt segítséget.
     */
    @AfterAll
    public static void TestingFinish() {
        Log.indent = 0;
        Log.println("Tests finished.", 0);
    }

    /**
     * Minden teszteset előtt futó függvény, a tesztek sorszámozását segít kiírni.
     */
    @BeforeEach
    public void TestStart() {
        Log.print(testCount + ". Test - ", 1);
    }

    /**
     * Minden teszteset után futó függvény, a tesztek végét segít kiírni.
     */
    @AfterEach
    public void TestFinished() {
        Log.println(testCount + ". Test Completed\n", 1);
        testCount++;
    }


    /**
     * Virológus mozgatása Laboratóriumba (nem lebénult állapotban).
     */
    @Test
    public void MoveToNeighborField() {
        Log.println("Move To Specific Field", 0);
        Field target = new Laboratory(null);
        Field source = new Empty();
        target.AddNeighbor(source);
        Virologist player = new Virologist(source);
        player.MoveTo(target);
        assertEquals(target, player.currentField);
    }

    /**
     * Virológus mozgatása egy véletlenszerű szomszédos mezőre vitustánc ágens hatása alatt.
     */
    @Test
    public void MoveToFieldWithChorea() {
        Log.println("Move To Field With Chorea Effect", 0);
        Field invalidField = new Empty();
        Field neighbor = new Empty();
        Field source = new Empty();
        neighbor.AddNeighbor(source);
        Virologist player = new Virologist(source);
        player.ApplyEffect(new Chorea());
        player.MoveTo(invalidField);
        assertNotEquals(invalidField, player.currentField);
    }

    /**
     * Mozgás nem szomszédos mezőre.
     */
    @Test
    public void MoveToNonNeighborField() {
        Log.println("Move to non-neighbor field", 0);
        Field current = new Empty();
        Field neighbor = new Empty();
        Field nonNeigh = new Empty();
        current.AddNeighbor(neighbor);

        Virologist player = new Virologist(current);
        player.MoveTo(nonNeigh);
        assertNotEquals(nonNeigh, player.currentField);
        assertEquals(current, player.currentField);
    }

    /**
     * Genetikus kód megtanulása.
     */
    @Test
    public void LearnGeneticCode() {
        Log.println("Learn Genetic Code", 0);
        GameController.Delete();
        GeneticCode code = new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        );
        Field labor = new Laboratory(code);
        Virologist player = new Virologist(labor);
        new GameController(new ArrayList<>(Arrays.asList(player)), null, new ArrayList<>(Arrays.asList(code)));
        player.PickUp(labor.GetStorable());
        assertEquals(code, player.GetKnownGeneticCodes().get(0));
    }

    /**
     * Jelenlegi mezőn tapogatás virológusként.
     */
    @Test
    public void TouchCurrentField() {
        Log.println("Touch Current Field", 0);
        Cape cape = new Cape();
        Shelter shelter = new Shelter(cape);
        Virologist toucher = new Virologist(shelter);
        Virologist touched = new Virologist(shelter);
        toucher.TouchField();
        assertEquals(touched, toucher.touchedVirologists.get(0));
        assertEquals(cape, toucher.touchedStorables.get(0));
    }


    // THROWS ----------------------------------------------------------------------------------------------------------------------------------

    /**
     * Agens dobasa lebenult virologuskent
     */
    @Test
    public void ThrowAgentWhileNumb() {
        Log.println("Throw Agent while Thrower is numb", 0);
        GameController.Delete();
        Effect f = new Forget();
        Virologist thrower = new Virologist(null);
        Virologist target = new Virologist(null);
        new GameController(new ArrayList<>(Arrays.asList(thrower, target)), null, null);
        Numb n = new Numb();
        thrower.ApplyEffect(n);
        n.OnEffectApplied(thrower);
        //Turn has ended, you can't throw.

        assertEquals(target, GameController.Single.currentPlayer);
    }

    /**
     * Bénító ágens dobása egy kiválasztott virológusra.
     */
    @Test
    public void ThrowNumbAgent() {
        Log.println("Throw Numb Agent", 0);
        GameController.Delete();
        Effect f = new Numb();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        new GameController(new ArrayList<>(Arrays.asList(player, target)), null, null);
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        GameController.Single.EndTurn();
        //Endturn will start the turn of player 1, who is numb, so skip that and back to player 0
        assertEquals(player, GameController.Single.currentPlayer);
    }

    /**
     * Bénító ágens dobása egy kiválasztott Védelmező ágens hatása alatt álló virológusra.
     */
    @Test
    public void ThrowNumbAgentOnProtected() {
        Log.println("Throw Numb Agent On Protected Virologist", 0);
        GameController.Delete();
        Effect f = new Numb();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.ApplyEffect(new Protection());
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        new GameController(new ArrayList<>(Arrays.asList(player, target)), null, null);
        GameController.Single.EndTurn();
        //Endturn will start the turn of player 1, who is not numb
        assertEquals(target, GameController.Single.currentPlayer);
    }

    /**
     * Bénító ágens dobása a játékos által irányított virológusra (saját magunkra).
     */
    @Test
    public void ThrowNumbAgentOnSelf() {
        Log.println("Throw Numb Agent On Self", 0);
        Effect f = new Numb();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, player);
        assertEquals(f, player.activeEffects.get(0));
    }

    /**
     * Védelmező ágens dobása egy kiválasztott virológusra.
     */
    @Test
    public void ThrowProtectionAgent() {
        Log.println("Throw Protection Agent", 0);
        Effect f = new Protection();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        assertEquals(f, target.activeEffects.get(0));
    }

    /**
     * Védelmező ágens dobása egy kiválasztott virológusra, aki már Védelmező ágens hatása alatt áll.
     */
    @Test
    public void ThrowProtectionAgentOnProtected() {
        Log.println("Throw Protection Agent On Protected Virologist", 0);
        Effect f = new Protection();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.ApplyEffect(new Protection());
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        assertEquals(1, target.activeEffects.size());
    }

    /**
     * Védelmező ágens dobása a játékos által irányított virológusra (saját magunkra).
     */
    @Test
    public void ThrowProtectionAgentOnSelf() {
        Log.println("Throw Protection Agent On Self", 0);
        Effect f = new Protection();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, player);
        assertEquals(f, player.activeEffects.get(0));
    }

    /**
     * Vitustánc ágens dobása egy kiválasztott virológusra.
     */
    @Test
    public void ThrowChoreaAgent() {
        Log.println("Throw Chorea Agent", 0);
        Effect f = new Chorea();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        assertEquals(f, target.activeEffects.get(0));
    }

    /**
     * Vitustánc ágens dobása egy védelmező ágens hatása alatt álló virológusra.
     */
    @Test
    public void ThrowChoreaAgentOnProtected() {
        Log.println("Throw Chorea Agent On Protected Virologist", 0);
        Effect f = new Chorea();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.ApplyEffect(new Protection());
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        assertEquals(1, target.activeEffects.size());
    }

    /**
     * Vitustánc ágens dobása a játékos által irányított virológusra (saját magunkra).
     */
    @Test
    public void ThrowChoreaAgentOnSelf() {
        Log.println("Throw Chorea Agent On Self", 0);
        Effect f = new Chorea();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, player);
        assertEquals(f, player.activeEffects.get(0));
    }

    /**
     * Felejtő ágens dobása egy kiválasztott virológusra.
     */
    @Test
    public void ThrowForgetAgent() {
        Log.println("Throw Forget Agent", 0);
        Effect f = new Forget();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        assertEquals(f, target.activeEffects.get(0));
    }

    /**
     * Felejtő ágens dobása egy kiválasztott virológusra, aki Védelmező ágens hatása alatt áll.
     */
    @Test
    public void ThrowForgetAgentOnProtected() {
        Log.println("Throw Forget Agent On Protected Virologist", 0);
        Effect f = new Forget();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.ApplyEffect(new Protection());
        player.AddAgent(a);
        player.ThrowAgent(a, target);
        assertEquals(1, target.activeEffects.size());
    }

    /**
     * Felejtő ágens dobása a játékos által irányított virológusra (saját magunkra).
     */
    @Test
    public void ThrowForgetAgentOnSelf() {
        Log.println("Throw Forget Agent On Self", 0);
        Effect f = new Forget();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        player.AddAgent(a);
        player.ThrowAgent(a, player);
        assertEquals(f, player.activeEffects.get(0));
    }

    /**
     * Bármely ágens dobása a játékos által irányított virológusra (saját magunkra).
     */
    @Test
    public void ThrowAgentOnSelfOnSelfProtected() {
        Log.println("Throw Any Agent On Self while Self Protected", 0);
        Effect f = new Chorea();
        Agent a = new Agent(3, f);
        Virologist self = new Virologist(null);
        self.ApplyEffect(new Protection());
        self.AddAgent(a);
        self.ThrowAgent(a, self);
        assertEquals(2, self.activeEffects.size());
    }

    @Test
    public void ThrowAgentOnGlovedWhileSelfProtected(){
        Log.println("Throw Agent", 0);
        Effect f = new Numb();
        Effect p = new Protection();
        Agent a = new Agent(3, f);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        player.AddAgent(a);
        player.ApplyEffect(p);
        target.EquipEquipment(new Glove());
        player.ThrowAgent(a, target);
        assertEquals(1, target.activeEffects.size());
        assertEquals(p, player.activeEffects.get(0));
        assertEquals(1, player.activeEffects.size());
    }

    // STEALS -----------------------------------------------------------------

    /**
     * Lopás  lebénult virológusként
     */
    @Test
    public void StealWhileNumb() {
        Log.println("Steal while stealer is numb", 0);
        GameController.Delete();
        Equipment cape = new Cape();
        Virologist stealer = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(cape);
        new GameController(new ArrayList<>(Arrays.asList(stealer, target)), null, null);
        Numb n = new Numb();
        stealer.ApplyEffect(n);
        n.OnEffectApplied(stealer);

        assertEquals(1, target.equipments.size());
        assertEquals(target, GameController.Single.currentPlayer);
    }

    /**
     * Köpeny lopása lebénult virológustól
     */
    @Test
    public void StealCapeFromNumb() {
        Log.println("Steal Cape From Numb Target", 0);
        Equipment e = new Cape();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.EquipEquipment(e);
        target.ApplyEffect(new Numb());
        player.StealEquipment(target, e);
        assertEquals(e, player.equipments.get(0));
        assertEquals(0, target.equipments.size());
        assertEquals(1, target.activeEffects.size());
    }

    /**
     * Köpeny lopása nem lebénult virológustól
     */
    @Test
    public void StealCapeFromNotNumb() {
        Log.println("Steal Cape From Not Numb Target", 0);
        Equipment e = new Cape();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        player.StealEquipment(target, e);
        assertEquals(e, target.equipments.get(0));
        assertEquals(0, player.equipments.size());
    }

    /**
     * Aminosav lopása lebénult virológustól
     */
    @Test
    public void StealAminoAcidFromNumb() {
        Log.println("Steal AminoAcid From Numb Target", 0);
        Material e = new AminoAcid();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.ApplyEffect(new Numb());
        player.StealMaterial(target);
        assertEquals(e, player.materials.get(0));
        assertEquals(0, target.materials.size());
    }

    /**
     * Aminosav lopása nem lebénult virológustól
     */
    @Test
    public void StealAminoAcidFromNotNumb() {
        Log.println("Steal AminoAcid From Not Numb Target", 0);
        Material e = new AminoAcid();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        player.StealMaterial(target);
        assertEquals(e, target.materials.get(0));
        assertEquals(0, player.materials.size());
    }

    /**
     * Nukleotid lopása lebénult virológustól.
     */
    @Test
    public void StealNucleotidFromNumb() {
        Log.println("Steal Nucleotid From Numb Target", 0);
        Material e = new Nucleotid();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.ApplyEffect(new Numb());
        player.StealMaterial(target);
        assertEquals(e, player.materials.get(0));
        assertEquals(0, target.materials.size());
    }

    /**
     * Nukleotid lopása nem bénult virológustól.
     */
    @Test
    public void StealNucleotidFromNotNumb() {
        Log.println("Steal Nucleotid From Not Numb Target", 0);
        Material e = new Nucleotid();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        player.StealMaterial(target);
        assertEquals(e, target.materials.get(0));
        assertEquals(0, player.materials.size());
    }

    /**
     * Lopott felszerelés levétele a lopást elszenvedő játékosról.
     */
    @Test
    public void UnequipStolenEquipment() {
        Log.println("Unequip Stolen Equipment", 0);
        Equipment e = new Cape();
        Virologist player = new Virologist(null);
        player.ApplyEffect(new Numb());
        player.PickUp(e);
        player.EquipEquipment(e);
        Equipment stolen = player.OnRobbed(e);
        
        assertEquals(0, player.equipments.size());
        assertEquals(0, player.equippedEquipments.size());
        assertEquals(e, stolen);
    }

    /**
     * Üres zsák lopása bénult virológustól.
     */
    @Test
    public void StealEmptyBagFromNumb() {
        Log.println("Steal Empty Bag From Numb Target", 0);
        Equipment e = new Bag();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.ApplyEffect(new Numb());
        player.StealEquipment(target, e);
        assertEquals(e, player.equipments.get(0));
        assertEquals(0, target.equipments.size());
    }

    /**
     * Megpakolt zsák lopása bénult virológustól.
     */
    @Test
    public void StealNotEmptyBagFromNumb() {
        Log.println("Steal Not Empty Bag From Numb Target", 0);
        Equipment e = new Bag();
        Material amino = new AminoAcid();
        Material nucle = new Nucleotid();
        e.StoreMaterial(amino);
        e.StoreMaterial(nucle);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.ApplyEffect(new Numb());
        player.StealEquipment(target, e);
        assertEquals(e, player.equipments.get(0));
        assertEquals(0, target.equipments.size());
        assertEquals(amino, player.equipments.get(0).GetMaterial(0));
        assertEquals(nucle, player.equipments.get(0).GetMaterial(1));
    }

    /**
     * Kesztyű lopása bénult virológustól.
     */
    @Test
    public void StealGloveFromNumb() {
        Log.println("Steal Glove From Numb Target", 0);
        Equipment e = new Glove();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.ApplyEffect(new Numb());
        player.StealEquipment(target, e);
        assertEquals(e, player.equipments.get(0));
        assertEquals(0, target.equipments.size());
    }

    /**
     * Mindenféle felszerelésből 1-1 darab lopása a kiválasztott virológustól.
     */
    @Test
    public void StealOneOfEverythingFromNumb() {
        Log.println("Steal 1 of Everything From Numb Target", 0);
        Equipment g = new Glove();
        Equipment c = new Cape();
        Equipment b = new Bag();
        Material n = new Nucleotid();
        Material a = new AminoAcid();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(g);
        target.PickUp(c);
        target.PickUp(b);
        target.PickUp(n);
        target.PickUp(a);
        target.ApplyEffect(new Numb());
        player.StealEquipment(target, g);
        player.StealEquipment(target, c);
        player.StealEquipment(target, b);
        player.StealMaterial(target);
        assertEquals(g,player.equipments.get(0));
        assertEquals(c, player.equipments.get(1));
        assertEquals(b, player.equipments.get(2));
        assertEquals(a, player.materials.get(0));
        assertEquals(n, player.materials.get(1));
        assertEquals(3, player.equipments.size());
        assertEquals(2, player.materials.size());
        assertEquals(0, target.equipments.size());
        assertEquals(0, target.materials.size());
    }

    /**
     * Semmi rabolása bénult virológustól.
     */
    @Test
    public void StealNothingFromNumb() {
        Log.println("Steal Nothing From Numb Target", 0);
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.ApplyEffect(new Numb());
        player.StealEquipment(target, null);
        player.StealMaterial(target);
        assertEquals(0, player.equipments.size());
        assertEquals(0, target.equipments.size());
    }

    @Test
    public void StealNucleotidFromNumbWhileFull(){
        Log.println("Steal Nucleotid From Numb Target While Full", 0);
        Material m = new Nucleotid();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        for(int i = 0; i < 50; i++)
            player.PickUp(new Nucleotid());
        target.PickUp(m);
        target.ApplyEffect(new Numb());
        player.StealMaterial(target);
        assertEquals(1, target.materials.size());
        assertEquals(m, target.materials.get(0));
    }

    @Test
    public void StealBagFromNumbWhileFullOnEquipmentButNotMaterialwise(){
        Log.println("Steal Cape From Numb Target", 0);
        Equipment e = new Bag();
        e.StoreMaterial(new AminoAcid());
        e.StoreMaterial(new Nucleotid());
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        target.PickUp(e);
        target.ApplyEffect(new Numb());
        player.EquipEquipment(new Cape());
        player.EquipEquipment(new Cape());
        player.EquipEquipment(new Glove());
        player.StealEquipment(target, e);
        assertEquals(e, player.equipments.get(0));
        assertEquals(0, target.equipments.size());
    }

    // CRAFTING -----------------------------------------------------------------

    /**
     * Védelmező ágens készítése éppen elegendő anyagból.
     */
    @Test
    public void ProtectionAgentCraftingWithEnough() {
        Log.println("Protection Agent Crafting With Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Védelmező ágens készítése több mint elegendő anyagból.
     */
    @Test
    public void ProtectionAgentCraftingWithMore() {
        Log.println("Protection Agent Crafting With More Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Védelmező ágens készítése nem elegendő anyagból.
     */
    @Test
    public void ProtectionAgentCraftingWithLess() {
        Log.println("Protection Agent Crafting With Less Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(0, player.craftedAgents.size());
    }

    /**
     * Bénító ágens készítése a virológusnál lévő táskában található anyagokból.
     */
    @Test
    public void ProtectionAgentCraftingFromBag() {
        Log.println("Protection Agent Crafting With Materials From Bag", 0);
        Virologist player = new Virologist(null);
        Bag bag = new Bag();
        bag.StoreMaterial(new Nucleotid());
        bag.StoreMaterial(new AminoAcid());
        player.AddToEquipped(bag);
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(0, player.equippedEquipments.get(0).GetMaterialCount());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Bénító ágens készítése éppen elegendő anyagból.
     */
    @Test
    public void NumbAgentCraftingWithEnough() {
        Log.println("Numb Agent Crafting With Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Numb())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Bénító ágens készítése több mint elegendő anyagból.
     */
    @Test
    public void NumbAgentCraftingWithMore() {
        Log.println("Numb Agent Crafting With More Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Numb())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Bénító ágens készítése nem elegendő anyagból.
     */
    @Test
    public void NumbAgentCraftingWithLess() {
        Log.println("Numb Agent Crafting With Less Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Numb())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(0, player.craftedAgents.size());
    }

    /**
     * Felejtő ágens készítése a virológusnál lévő táskában található anyagokból.
     */
    @Test
    public void NumbAgentCraftingFromBag() {
        Log.println("Numb Agent Crafting With Materials From Bag", 0);
        Virologist player = new Virologist(null);
        Bag bag = new Bag();
        bag.StoreMaterial(new Nucleotid());
        bag.StoreMaterial(new AminoAcid());
        player.AddToEquipped(bag);
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Numb())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(0, player.equippedEquipments.get(0).GetMaterialCount());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Felejtő ágens készítése éppen elegendő anyagból.
     */
    @Test
    public void ForgetAgentCraftingWithEnough() {
        Log.println("Forget Agent Crafting With Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Forget())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Felejtő ágens készítése több mint elegendő anyagból.
     */
    @Test
    public void ForgetAgentCraftingWithMore() {
        Log.println("Forget Agent Crafting With More Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Forget())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Felejtő ágens készítése nem elegendő anyagból.
     */
    @Test
    public void ForgetAgentCraftingWithLess() {
        Log.println("Forget Agent Crafting With Less Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Forget())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(0, player.craftedAgents.size());
    }

    /**
     * Vitustánc ágens készítése a virológusnál lévő táskában található anyagokból.
     */
    @Test
    public void ForgetAgentCraftingFromBag() {
        Log.println("Forget Agent Crafting With Materials From Bag", 0);
        Virologist player = new Virologist(null);
        Bag bag = new Bag();
        bag.StoreMaterial(new Nucleotid());
        bag.StoreMaterial(new AminoAcid());
        player.AddToEquipped(bag);
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Forget())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(0, player.equippedEquipments.get(0).GetMaterialCount());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Vitustánc ágens készítése éppen elegendő anyagból.
     */
    @Test
    public void ChoreaAgentCraftingWithEnough() {
        Log.println("Chorea Agent Crafting With Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Chorea())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Vitustánc ágens készítése több mint elegendő anyagból.
     */
    @Test
    public void ChoreaAgentCraftingWithMore() {
        Log.println("Chorea Agent Crafting With More Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new Nucleotid(), new Nucleotid(), new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Chorea())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Vitustánc ágens készítése nem elegendő anyagból.
     */
    @Test
    public void ChoreaAgentCraftingWithLess() {
        Log.println("Chorea Agent Crafting With Less Than Enough Materials", 0);
        Virologist player = new Virologist(null);
        player.materials = new ArrayList<>(Arrays.asList(new AminoAcid()));
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Chorea())
        ));
        assertEquals(1, player.materials.size());
        assertEquals(0, player.craftedAgents.size());
    }

    /**
     * Kesztyűvel ágens visszadobása a támadó virológusra.
     */
    @Test
    public void ChoreaAgentCraftingFromBag() {
        Log.println("Chorea Agent Crafting With Materials From Bag", 0);
        Virologist player = new Virologist(null);
        Bag bag = new Bag();
        bag.StoreMaterial(new Nucleotid());
        bag.StoreMaterial(new AminoAcid());
        player.AddToEquipped(bag);
        player.CraftAgent(new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Chorea())
        ));
        assertEquals(0, player.materials.size());
        assertEquals(0, player.equippedEquipments.get(0).GetMaterialCount());
        assertEquals(1, player.craftedAgents.size());
    }

    /**
     * Kesztyűvel ágens visszadobása a támadó virológusnak.
     */
    @Test
    public void GloveThrowBackAgent() {
        Log.println("Glove Throwing Back Agent", 0);
        GameController.Delete();
        Glove g = new Glove();
        Virologist player = new Virologist(null);
        Virologist target = new Virologist(null);
        new GameController(new ArrayList<>(Arrays.asList(player, target)), null, null);
        player.AddEquipment(g);
        player.EquipEquipment(g);
        Agent a = new Agent(3, new Numb());
        Effect f = a.GetEffect();
        target.AddAgent(a); // numb agent
        target.ThrowAgent(a, player);
        assertEquals(0, target.craftedAgents.size());
        assertEquals(f, target.activeEffects.get(0));
    }

    /**
     * Aminosav felvétele a raktárból.
     */
    @Test
    public void PickUpAminoAcid() {
        Log.println("Pick Up AminoAcid", 0);
        Warehouse warehouse = new Warehouse(new AminoAcid());
        Virologist player = new Virologist(warehouse);
        player.TouchField();
        player.PickUp(player.touchedStorables.get(0));
        assertEquals(warehouse.GetStorable(), player.materials.get(0));
    }

    /**
     * Nukleotid felvétele a raktárból.
     */
    @Test
    public void PickUpNucleotid() {
        Log.println("Pick Up Nucleotid", 0);
        Warehouse warehouse = new Warehouse(new Nucleotid());
        Virologist player = new Virologist(warehouse);
        player.TouchField();
        player.PickUp(player.touchedStorables.get(0));
        assertEquals(warehouse.GetStorable(), player.materials.get(0));
    }

    /**
     * Köpeny felvétele az óvóhelyről.
     */
    @Test
    public void PickUpCapeEquipment() {
        Log.println("Pick Up Cape", 0);
        Cape cape = new Cape();
        Shelter shelter = new Shelter(cape);
        Virologist player = new Virologist(shelter);
        player.PickUp(cape);
        cape.StoreIn(player, shelter);
        player.AddEquipment(cape);
        shelter.RemoveFieldContent(cape);
        assertEquals(cape, player.equipments.get(0));

    }

    /**
     * Zsák felvétele az óvóhelyről.
     */
    @Test
    public void PickUpBagEquipment() {
        Log.println("Pick Up Bag", 0);
        Bag bag = new Bag();
        Shelter shelter = new Shelter(bag);
        Virologist player = new Virologist(shelter);
        player.PickUp(bag);
        assertEquals(bag, player.equipments.get(0));

    }

    /**
     * Kesztyű felvétele az óvóhelyről.
     */
    @Test
    public void PickUpGloveEquipment() {
        Log.println("Pick Up Glove", 0);
        Equipment glove = new Glove();
        Shelter shelter = new Shelter(glove);
        Virologist player = new Virologist(shelter);
        player.PickUp(glove);
        assertEquals(glove, player.equipments.get(0));

    }

    /**
     * A megtanult genetikus kódok elfelejtése.
     */
    @Test
    public void ForgetGeneticCodes() {
        Log.println("Forget Genetic Codes", 0);
        GameController.Delete();
        GeneticCode protectionCode = new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        );
        Agent f = new Agent(5, new Forget());
        Field labor = new Laboratory(protectionCode);
        Virologist player = new Virologist(labor);
        Virologist other = new Virologist(labor);
        new GameController(new ArrayList<>(Arrays.asList(player, other)), null, new ArrayList<>(Arrays.asList(protectionCode)));
        player.LearnGenCode(protectionCode);
        other.AddAgent(f);
        other.ThrowAgent(f, player);
        assertEquals(0, player.GetKnownGeneticCodes().size());
    }

    /**
     * Mozgás bénult állapotban lévő virológussal.
     */
    @Test
    public void MoveWhileNumb() {
        Log.println("Move while virologist is numb", 0);
        Field empty = new Empty();
        Field empty_neigh = new Empty();
        empty.AddNeighbor(empty_neigh);

        Virologist player = new Virologist(empty);
        //Virologist other = new Virologist(empty_neigh);
        player.ApplyEffect(new Numb());

        player.MoveTo(empty_neigh);

        assertEquals(player.currentField, empty);
    }

    /**
     * Genetikus kód megtanulása bénult állapotban lévő virológussal.
     */
    @Test
    public void LearnGeneticCodeWhileNumb() {
        Log.println("Learn Genetic code while virologist is numb", 0);
        GameController.Delete();
        GeneticCode code = new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        );
        Field labor = new Laboratory(code);
        Virologist notNumbViro = new Virologist(labor);
        Virologist player = new Virologist(labor);
        player.ApplyEffect(new Numb());
        new GameController(new ArrayList<>(Arrays.asList(notNumbViro, player)), null, new ArrayList<>(Arrays.asList(code)));
        //Ending turn for other virologist and preparing the numb virologist -> because he is numb he skips the turn -> other virologists turn
        GameController.Single.EndTurn();
        assertEquals(notNumbViro, GameController.Single.currentPlayer);

    }

    /**
     * Az utolsó genetikus kód megtanulása, valamint ezáltal a játék megnyerése.
     */
    @Test
    public void LearnLastGeneticCode() {
        Log.println("Learn last Genetic code in the game", 0);
        GameController.Delete();
        GeneticCode code1 = new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Protection())
        );
        GeneticCode code2 = new GeneticCode(
                new ArrayList<Material>(Arrays.asList(new AminoAcid(), new Nucleotid())),
                new Agent(5, new Numb())
        );
        Field labor = new Laboratory(code1);
        Virologist player = new Virologist(labor);
        Field labor2 = new Laboratory(code2);
        labor.AddNeighbor(labor2);
        new GameController(new ArrayList<>(Arrays.asList(player)), new ArrayList<>(Arrays.asList(labor, labor2)), new ArrayList<>(Arrays.asList(code1, code2)));

        player.TouchField();
        player.PickUp(player.touchedStorables.get(0));
        player.MoveTo(labor2);
        player.TouchField();
        player.PickUp(player.touchedStorables.get(0));

        assertEquals(player.knownGeneticCodes.size(), 2);

    }

    /**
     * Üres raktárból anyag felvétele.
     */
    @Test
    public void WarehouseEmptyPickup() {
        Log.println("Pick up stuff from Empty Warehouse", 0);
        Warehouse warehouse = new Warehouse(null);
        Virologist player = new Virologist(warehouse);
        player.TouchField();
        try {
            player.PickUp(player.touchedStorables.get(0));
        } catch (IndexOutOfBoundsException iobex) {
            assertEquals(null, warehouse.GetStorable());

            assertEquals(0, player.materials.size());

        }

    }

    /**
     * Raktárból anyagfelvétel bénultan.
     */
    @Test
    public void WarehouseNumbPickup() {
        Log.println("Pick up material from warehouse while virologist is numb ", 0);
        GameController.Delete();
        Nucleotid nu = new Nucleotid();
        Field warehouse = new Warehouse(nu);
        Virologist player = new Virologist(warehouse);
        Virologist otherPlayer = new Virologist(warehouse);
        Agent a = new Agent(3, new Numb());

        new GameController(new ArrayList<>(Arrays.asList(player, otherPlayer)), new ArrayList<>(Arrays.asList(warehouse)), null);

        player.AcceptAgent(a, null);
        GameController.Single.EndTurn();

        assertNotEquals(player, GameController.Single.currentPlayer);

    }


    /**
     * Raktárból anyag felvétele úgy, hogy a játékos megtelt.
     */
    @Test
    public void WarehousePlayerFullPickup() {
        Log.println("Pick up stuff from non-empty Warehouse - Player is full", 0);
        Nucleotid material_on_field = new Nucleotid();
        Warehouse warehouse = new Warehouse(material_on_field);
        Virologist player = new Virologist(warehouse);
        player.TouchField();
        for (int i = 0; i < 50; ++i) {
            player.materials.add(new Nucleotid());
        }

        player.PickUp(player.touchedStorables.get(0));
        assertEquals(50, player.materials.size());
        assertEquals(material_on_field, warehouse.GetStorable());
    }

    /**
     * Raktárból anyag felvétele úgy, hogy a játékosnál lévő zsákban sincs már több hely, valamint a játékos raktárkészlete is megtelt.
     */
    @Test
    public void WarehousePlayerFullBackpackPickup() {
        Log.println("Pick up stuff from non-empty Warehouse - Player is full", 0);
        Nucleotid material_on_field = new Nucleotid();
        Warehouse warehouse = new Warehouse(material_on_field);
        Virologist player = new Virologist(warehouse);
        player.TouchField();
        player.PickUp(new Bag());
        player.EquipEquipment(player.equipments.get(0));
        for (int i = 0; i < 50; ++i) {
            player.materials.add(new Nucleotid());
        }

        ///A raktárból már nem tudja felvenni ezt az anyagot.
        player.PickUp(player.touchedStorables.get(0));

        assertEquals(50, player.materials.size());
        assertEquals(1, player.equippedEquipments.size());
        assertEquals(material_on_field, warehouse.GetStorable());
    }

    /**
     * Raktárból anyag felvétele úgy, hogy a játékos raktára már megtelt, viszont még van zsákja amelybe még tud raktározni anyagot.
     */
    @Test
    public void WarehousePlayerNonfullBackpackPickup() {
        Log.println("Pick up stuff from non-empty Warehouse - Player is full, but equipped backpack is not", 0);
        Nucleotid material_on_field = new Nucleotid();
        Warehouse warehouse = new Warehouse(material_on_field);
        Virologist player = new Virologist(warehouse);
        player.TouchField();
        player.PickUp(new Bag());
        player.EquipEquipment(player.equipments.get(0));
        for (int i = 0; i < 50; ++i) {
            player.materials.add(new Nucleotid());
        }

        player.PickUp(player.touchedStorables.get(0));

        assertEquals(50, player.materials.size());
        assertEquals(1, player.equippedEquipments.size());
    }


    /**
     * Kesztyű még nem kopott el
     */
    @Test
    public void GloveDurabilityTestNonRemove() {

        Field empty = new Empty();
        Virologist v1 = new Virologist(empty);
        Virologist v2 = new Virologist(empty);

        v2.AddEquipment(new Glove());
        v2.EquipEquipment(v2.equipments.get(0));

        Agent a = new Agent(3, new Forget());

        v1.AddAgent(a);
        v1.ThrowAgent(a, v2);
        assertEquals(2, v2.equippedEquipments.get(0).GetDurability());
    }

    /**
    * Kesztyű elkopott
    */
    @Test
    public void GloveDurabilityTestRemove() {
        Field empty = new Empty();
        Virologist v1 = new Virologist(empty);
        Virologist v2 = new Virologist(empty);

        v1.AddEquipment(new Glove());
        v2.AddEquipment(new Glove());
        v1.EquipEquipment(v1.equipments.get(0));
        v2.EquipEquipment(v2.equipments.get(0));

        Agent a = new Agent(3, new Forget());
        v1.AddAgent(a);
        v1.ThrowAgent(a, v2);

        assertEquals(0, v2.equippedEquipments.size());
        assertEquals(0, v1.equippedEquipments.size());
    }

    /**
    * Medve vírussal fertőzött virológus megcsapása baltával.
    */
    @Test
    public void HitBearVirologistWAxe() {
        GameController.Delete();

        Field empty = new Empty();
        Field bearlab = new BearLaboratory(null);
        Virologist bear = new Virologist(empty);
        Virologist nonbear = new Virologist(empty);
        Axe axe = new Axe();

        new GameController(new ArrayList<>(Arrays.asList(bear, nonbear)), new ArrayList<>(Arrays.asList(empty, bearlab)), null);

        nonbear.AddEquipment(axe);
        nonbear.EquipEquipment(axe);

        empty.AddNeighbor(bearlab);

        bear.MoveTo(bearlab);
        bear.MoveTo(empty);
        nonbear.equippedEquipments.get(0).UseEquipmentOn(nonbear, bear);

        assertEquals(1, GameController.Single.players.size());
        assertEquals(0, nonbear.equippedEquipments.size());

    }

    /**
    * Virológus megfertőzése medve vírussal.
    */
    @Test
    public void VirologistEnterFieldWithBear() {
        Field emptyStartField = new Empty();
        Field emptyOtherField = new Empty();
        Field bearlab = new BearLaboratory(null);
        emptyStartField.AddNeighbor(emptyOtherField);
        bearlab.AddNeighbor(emptyStartField);

        Virologist player = new Virologist(emptyStartField);
        player.MoveTo(bearlab);

        player.MoveTo(emptyStartField);
        Virologist other1 = new Virologist(emptyOtherField);

        other1.MoveTo(emptyStartField);


        assertEquals(1, other1.activeEffects.size());
        assertEquals(1, player.activeEffects.size());
    }

    /**
     * Kör átugrása bénulva
     */
    @Test
    public void SkipTurnWhileNumb() {
        GameController.Delete();
        Equipment eq = new Cape();
        Field shelter = new Shelter(eq);
        Virologist player = new Virologist(shelter);
        Virologist otherPlayer = new Virologist(shelter);
        Agent a = new Agent(3, new Numb());

        new GameController(new ArrayList<>(Arrays.asList(player, otherPlayer)), new ArrayList<>(Arrays.asList(shelter)), null);

        player.AcceptAgent(a, null);
        GameController.Single.EndTurn();

        assertNotEquals(player, GameController.Single.currentPlayer);
    }

    /**
     * Üres óvóhelyről való anyagfelvétel.
     */
    @Test
    public void ShelterEmptyPickup() {
        Shelter shelter = new Shelter(null);
        Virologist player = new Virologist(shelter);
        player.TouchField();
        try {
            player.PickUp(player.touchedStorables.get(0));
        } catch (IndexOutOfBoundsException e) {
            assertEquals(0, player.materials.size());
            assertEquals(null, shelter.GetStorable());
        }
    }



}
