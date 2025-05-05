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

    /**
     * Creates and returns a room that holds the final boss encounter.
     * 
     * @return The room holding the final boss and its interactive elements.
     */
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
     * Handles the final boss encounter. If the player defeats the kidnapper, they win. 
     * If not, they must retreat.
     * 
     * @param inventory The player's inventory.
     * @param sc The Scanner for user input.
     * @return true if the boss is defeated and the player escapes; false otherwise.
     */
    public static boolean enterRoom(List<Item> inventory, Scanner sc) {
        System.out.println("====================================================================================================");
        System.out.println("You step into a dark kitchen. The kidnapper is sitting at a table, distracted by TV and eating.");
        System.out.println("The kidnapper looks up at you, a malicious grin appearing on his face.");
        System.out.println("\nKidnapper: 'So, you found your way here. But this is the end of the line.'");

        while (true) {
            System.out.println("\nWhat do you do?");
            System.out.println("1. Try to talk him down");
            System.out.println("2. Use an item from your inventory");
            System.out.println("3. Attack him");
            System.out.println("4. Retreat");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    return tryTalkHimDown();
                case "2":
                    return useItem(inventory, sc);
                case "3":
                    return attackBoss();
                case "4":
                    System.out.println("You retreat and head back to regroup.");
                    return false;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
            }
        }
    }

    /**
     * Attempts to talk down the kidnapper. Always fails.
     */
    private static boolean tryTalkHimDown() {
        System.out.println("\nYou try to talk to the kidnapper, but he isn't interested.");
        System.out.println("Kidnapper: 'I have nothing to say to you.'");
        System.out.println("The kidnapper attacks you in response. You fail to talk him down.");
        return false;
    }

    /**
     * Lets the player select and use an item from their inventory.
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
                    System.out.println("Success! You defeat the kidnapper with the " + selectedItem.getName() + ".");
                    return true;
                } else {
                    System.out.println("You tried to use the " + selectedItem.getName() + ", but it failed. The kidnapper overpowers you.");
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

    /**
     * Player attacks without a weapon — always fails.
     */
    private static boolean attackBoss() {
        System.out.println("\nYou charge at him, fists flying.");
        System.out.println("The kidnapper fights back fiercely. You struggle, but he overpowers you.");
        System.out.println("You are forced to retreat. A more strategic approach is needed.");
        return false;
    }
}
