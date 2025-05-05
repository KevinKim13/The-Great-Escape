import java.util.List;
import java.util.Scanner;

/**
 * Class: RoomFinalBossBattle.java
 * Author: Joseph Edwards
 * Version: 2.0
 * Course: CSE 201 Spring 2025
 * 
 * Purpose: Manages the final boss encounter in the game, combining Room setup and combat logic.
 */
public class RoomFinalBossBattle {

    public static Room initRoom() {
        Room room4 = new Room(
                "You go upstairs and are now in a dingy kitchen.\nThe kidnapper is preoccupied with watching TV and eating.");
        
        Wall[] walls = new Wall[4];
        Item knife = new Item("Kitchen Knife", "Weapon", 0.8);

        walls[0] = new Wall("The passageway to the living room where the kidnapper sits.",
                            "It might be possible to take him down.");
        
        walls[1] = new Wall("The main area of the kitchen with a fridge, oven, and sink.",
                            "You spot a knife in the kitchen sink.");
        walls[1].setItem(knife);

        walls[2] = new Wall("The backdoor of the kidnapper's house",
                            "The only feasible escape route in sight.");
        walls[2].setAvailableActions(new String[] { "Fight", "Leave" });

        walls[3] = new Wall("A door to the basement.",
                            "The basement you just escaped from.");

        room4.setWalls(walls);
        return room4;
    }

    /**
     * Handles the final boss encounter, but only interacts with the inventory and scanner for user input.
     * 
     * @param inventory The player's inventory.
     * @param sc The Scanner for user input.
     * @return true if an item was used successfully, false otherwise.
     */
    public static boolean enterRoom(List<Item> inventory, Scanner sc) {
        System.out.println("====================================================================================================");
        System.out.println("You step into a dark kitchen. The kidnapper is sitting at a table, distracted by TV and eating.");
        System.out.println("The kidnapper looks up at you, a malicious grin appearing on his face.");
        System.out.println("\nKidnapper: 'So, you found your way here. But this is the end of the line.'");

        while (true) {
            System.out.println("\nWhat do you do?");
            System.out.println("1. Inspect the kidnapper");
            System.out.println("2. Talk to the kidnapper");
            System.out.println("3. Use an item from your inventory");
            System.out.println("4. Fight the kidnapper");
            System.out.println("5. Retreat");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("You inspect the kidnapper closely.");
                    break;
                case "2":
                    System.out.println("You try to talk to the kidnapper, but he isn't interested.");
                    break;
                case "3":
                    return useItem(inventory, sc);
                case "4":
                    System.out.println("You attempt to fight the kidnapper, but it is not effective without a weapon.");
                    break;
                case "5":
                    System.out.println("You retreat and head back to regroup.");
                    return false;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
            }
        }
    }

    /**
     * Lets the player select and use an item from their inventory.
     * 
     * @param inventory The player's inventory.
     * @param sc The Scanner for user input.
     * @return true if an item was successfully used, false otherwise.
     */
    private static boolean useItem(List<Item> inventory, Scanner sc) {
        if (inventory.isEmpty()) {
            System.out.println("You have nothing in your inventory.");
            return false;
        }

        System.out.println("\nWhich item do you want to use? Enter the number:");

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.printf("%d. %s (Type: %s)%n", i + 1, item.getName(), item.getType());
        }

        try {
            System.out.print("Your choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim()) - 1;

            if (choice < 0 || choice >= inventory.size()) {
                System.out.println("Invalid number.");
                return false;
            }

            Item selectedItem = inventory.get(choice);

            if (selectedItem.getType().equalsIgnoreCase("Weapon")) {
                double roll = Math.random();
                System.out.printf("You use the %s. Rolling for success... (Need ≤ %.2f)%n", 
                                  selectedItem.getName(), selectedItem.getChance());
                if (roll <= selectedItem.getChance()) {
                    System.out.println("Success! You use the " + selectedItem.getName() + " effectively.");
                    return true;
                } else {
                    System.out.println("You tried to use the " + selectedItem.getName() + ", but it failed.");
                    return false;
                }
            } else {
                System.out.println("The " + selectedItem.getName() + " doesn't help you here.");
                return false;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return false;
        }
    }
}
