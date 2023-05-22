package szoftlab.main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;


class TestCase {
    public String name;
    Consumer<Integer> function;

    public TestCase(String name, Consumer<Integer> function) {
        this.name = name;
        this.function = function;
    }

    public void run() {
        function.accept(0);
    }
}

public class TestMain {
    public static void main(String[] args) {
        GameController.isDebug = true;
        boolean testing = true;
        Scanner sc = new Scanner(System.in);
        Log.indent = 1;
        UnitTest u = new UnitTest();
        int testCase = 0;

        final ArrayList<TestCase> tests = new ArrayList<>();
        tests.add(new TestCase("MoveToNeighborField",(n)->{u.MoveToNeighborField();}));
        tests.add(new TestCase("MoveToFieldWithChorea",(n)->{u.MoveToFieldWithChorea();}));
        tests.add(new TestCase("MoveToNonNeighborField",(n)->{u.MoveToNonNeighborField();}));
        tests.add(new TestCase("LearnGeneticCode",(n)->{u.LearnGeneticCode();}));
        tests.add(new TestCase("TouchCurrentField",(n)->{u.TouchCurrentField();}));
        tests.add(new TestCase("ThrowAgentWhileNumb",(n)->{u.ThrowAgentWhileNumb();}));
        tests.add(new TestCase("ThrowNumbAgent",(n)->{u.ThrowNumbAgent();}));
        tests.add(new TestCase("ThrowNumbAgentOnProtected",(n)->{u.ThrowNumbAgentOnProtected();}));
        tests.add(new TestCase("ThrowNumbAgentOnSelf",(n)->{u.ThrowNumbAgentOnSelf();}));
        tests.add(new TestCase("ThrowProtectionAgent",(n)->{u.ThrowProtectionAgent();}));
        tests.add(new TestCase("ThrowProtectionAgentOnProtected",(n)->{u.ThrowProtectionAgentOnProtected();}));
        tests.add(new TestCase("ThrowProtectionAgentOnSelf",(n)->{u.ThrowProtectionAgentOnSelf();}));
        tests.add(new TestCase("ThrowChoreaAgent",(n)->{u.ThrowChoreaAgent();}));
        tests.add(new TestCase("ThrowChoreaAgentOnProtected",(n)->{u.ThrowChoreaAgentOnProtected();}));
        tests.add(new TestCase("ThrowChoreaAgentOnSelf",(n)->{u.ThrowChoreaAgentOnSelf();}));
        tests.add(new TestCase("ThrowForgetAgent",(n)->{u.ThrowForgetAgent();}));
        tests.add(new TestCase("ThrowForgetAgentOnProtected",(n)->{u.ThrowForgetAgentOnProtected();}));
        tests.add(new TestCase("ThrowForgetAgentOnSelf",(n)->{u.ThrowForgetAgentOnSelf();}));
        tests.add(new TestCase("ThrowAgentOnSelfOnSelfProtected",(n)->{u.ThrowAgentOnSelfOnSelfProtected();}));
        tests.add(new TestCase("ThrowAgentOnGlovedWhileSelfProtected",(n)->{u.ThrowAgentOnGlovedWhileSelfProtected();}));
        tests.add(new TestCase("StealWhileNumb",(n)->{u.StealWhileNumb();}));
        tests.add(new TestCase("StealCapeFromNumb",(n)->{u.StealCapeFromNumb();}));
        tests.add(new TestCase("StealCapeFromNotNumb",(n)->{u.StealCapeFromNotNumb();}));
        tests.add(new TestCase("StealAminoAcidFromNumb",(n)->{u.StealAminoAcidFromNumb();}));
        tests.add(new TestCase("StealAminoAcidFromNotNumb",(n)->{u.StealAminoAcidFromNotNumb();}));
        tests.add(new TestCase("StealNucleotidFromNumb",(n)->{u.StealNucleotidFromNumb();}));
        tests.add(new TestCase("StealNucleotidFromNotNumb",(n)->{u.StealNucleotidFromNotNumb();}));
        tests.add(new TestCase("UnequipStolenEquipment",(n)->{u.UnequipStolenEquipment();}));
        tests.add(new TestCase("StealEmptyBagFromNumb",(n)->{u.StealEmptyBagFromNumb();}));
        tests.add(new TestCase("StealNotEmptyBagFromNumb",(n)->{u.StealNotEmptyBagFromNumb();}));
        tests.add(new TestCase("StealGloveFromNumb",(n)->{u.StealGloveFromNumb();}));
        tests.add(new TestCase("StealOneOfEverythingFromNumb",(n)->{u.StealOneOfEverythingFromNumb();}));
        tests.add(new TestCase("StealNothingFromNumb",(n)->{u.StealNothingFromNumb();}));
        tests.add(new TestCase("StealNucleotidFromNumbWhileFull",(n)->{u.StealNucleotidFromNumbWhileFull();}));
        tests.add(new TestCase("StealBagFromNumbWhileFullOnEquipmentButNotMaterialwise",(n)->{u.StealBagFromNumbWhileFullOnEquipmentButNotMaterialwise();}));
        tests.add(new TestCase("ProtectionAgentCraftingWithEnough",(n)->{u.ProtectionAgentCraftingWithEnough();}));
        tests.add(new TestCase("ProtectionAgentCraftingWithMore",(n)->{u.ProtectionAgentCraftingWithMore();}));
        tests.add(new TestCase("ProtectionAgentCraftingWithLess",(n)->{u.ProtectionAgentCraftingWithLess();}));
        tests.add(new TestCase("ProtectionAgentCraftingFromBag",(n)->{u.ProtectionAgentCraftingFromBag();}));
        tests.add(new TestCase("NumbAgentCraftingWithEnough",(n)->{u.NumbAgentCraftingWithEnough();}));
        tests.add(new TestCase("NumbAgentCraftingWithMore",(n)->{u.NumbAgentCraftingWithMore();}));
        tests.add(new TestCase("NumbAgentCraftingWithLess",(n)->{u.NumbAgentCraftingWithLess();}));
        tests.add(new TestCase("NumbAgentCraftingFromBag",(n)->{u.NumbAgentCraftingFromBag();}));
        tests.add(new TestCase("ForgetAgentCraftingWithEnough",(n)->{u.ForgetAgentCraftingWithEnough();}));
        tests.add(new TestCase("ForgetAgentCraftingWithMore",(n)->{u.ForgetAgentCraftingWithMore();}));
        tests.add(new TestCase("ForgetAgentCraftingWithLess",(n)->{u.ForgetAgentCraftingWithLess();}));
        tests.add(new TestCase("ForgetAgentCraftingFromBag",(n)->{u.ForgetAgentCraftingFromBag();}));
        tests.add(new TestCase("ChoreaAgentCraftingWithEnough",(n)->{u.ChoreaAgentCraftingWithEnough();}));
        tests.add(new TestCase("ChoreaAgentCraftingWithMore",(n)->{u.ChoreaAgentCraftingWithMore();}));
        tests.add(new TestCase("ChoreaAgentCraftingWithLess",(n)->{u.ChoreaAgentCraftingWithLess();}));
        tests.add(new TestCase("ChoreaAgentCraftingFromBag",(n)->{u.ChoreaAgentCraftingFromBag();}));
        tests.add(new TestCase("GloveThrowBackAgent",(n)->{u.GloveThrowBackAgent();}));
        tests.add(new TestCase("PickUpAminoAcid",(n)->{u.PickUpAminoAcid();}));
        tests.add(new TestCase("PickUpNucleotid",(n)->{u.PickUpNucleotid();}));
        tests.add(new TestCase("PickUpCapeEquipment",(n)->{u.PickUpCapeEquipment();}));
        tests.add(new TestCase("PickUpBagEquipment",(n)->{u.PickUpBagEquipment();}));
        tests.add(new TestCase("PickUpGloveEquipment",(n)->{u.PickUpGloveEquipment();}));
        tests.add(new TestCase("ForgetGeneticCodes",(n)->{u.ForgetGeneticCodes();}));
        tests.add(new TestCase("MoveWhileNumb",(n)->{u.MoveWhileNumb();}));
        tests.add(new TestCase("LearnGeneticCodeWhileNumb",(n)->{u.LearnGeneticCodeWhileNumb();}));
        tests.add(new TestCase("LearnLastGeneticCode",(n)->{u.LearnLastGeneticCode();}));
        tests.add(new TestCase("WarehouseEmptyPickup",(n)->{u.WarehouseEmptyPickup();}));
        tests.add(new TestCase("WarehouseNumbPickup",(n)->{u.WarehouseNumbPickup();}));
        tests.add(new TestCase("WarehousePlayerFullPickup",(n)->{u.WarehousePlayerFullPickup();}));
        tests.add(new TestCase("WarehousePlayerFullBackpackPickup",(n)->{u.WarehousePlayerFullBackpackPickup();}));
        tests.add(new TestCase("WarehousePlayerNonfullBackpackPickup",(n)->{u.WarehousePlayerNonfullBackpackPickup();}));
        tests.add(new TestCase("GloveDurabilityTestNonRemove",(n)->{u.GloveDurabilityTestNonRemove();}));
        tests.add(new TestCase("GloveDurabilityTestRemove",(n)->{u.GloveDurabilityTestRemove();}));
        tests.add(new TestCase("HitBearVirologistWAxe",(n)->{u.HitBearVirologistWAxe();}));
        tests.add(new TestCase("VirologistEnterFieldWithBear", (n) -> {u.VirologistEnterFieldWithBear();}));
        tests.add(new TestCase("ShelterEmptyPickup",(n)->{u.ShelterEmptyPickup();}));
        tests.add(new TestCase("ShelterPickupWhileNumb",(n)->{u.SkipTurnWhileNumb();}));


        while (testing) {
            Log.println("Select a test case:", 0);
            int n = 0;
            for (TestCase s : tests) {
                Log.println(n + ": " + s.name);
                n++;
            }
            testCase = Integer.parseInt(sc.nextLine());
            String result = "Completed Successfully.";

            try {
                Log.print("\n" + testCase + ". Starting - ", 0);
                if (testCase >= 0 && testCase < tests.size())
                    tests.get(testCase).run();
                else
                    result = "Invalid Test Case.";
            } catch (Exception e) {
                result = "Failed!";
            } finally {
                Log.println(testCase + ". Test " + result, 0);
            }

            Log.println("\nTry Another Case? (y/n)", 0);
            String answer = sc.nextLine();
            if (answer.contains("n")) {
                testing = false;
            }
        }
        sc.close();
    }
}