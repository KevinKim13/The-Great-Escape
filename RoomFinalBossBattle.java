import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class: RoomFinalBossBattle.java
 * Author: Joseph Edwards
 * Version: 1.0
 * Course: CSE 201 Spring 2025
 * 
 * Purpose: Manages the final boss encounter in the game, combining Room setup and combat logic.
 */
public class RoomFinalBossBattle extends Room {

    public Player player;

    public RoomFinalBossBattle(Player player, String description) {
        super(description);
        this.player = player;
    }
    public static Wall[] buildRoom(Player player) {
        // Create a new instance of RoomFinalBossBattle (which is a type of Room)
        RoomFinalBossBattle room4 = new RoomFinalBossBattle(player,
            "You go upstairs and are now in a dingy kitchen.\n" +
            "The kidnapper is preoccupied with watching TV and eating.");
    
        // Setup walls and items for the room
        Wall[] r4Walls = new Wall[4];
        Item knife = new Item("Kitchen Knife", "Weapon", 0.8);
    
        r4Walls[0] = new Wall("The passageway to the living room where the kidnapper sits.",
                              "It might be possible to take him down.");
    
        r4Walls[1] = new Wall("The main area of the kitchen with a fridge, oven, and sink.",
                              "You spot a knife in the kitchen sink.");
        r4Walls[1].setItem(knife);
    
        r4Walls[2] = new Wall("The backdoor of the kidnapper's house",
                              "The only feasible escape route in sight.");
        r4Walls[2].setAvailableActions(new String[] { "Fight", "Leave" });  // <-- This triggers the fight
    
        r4Walls[3] = new Wall("A door to the basement.",
                              "The basement you just escaped from.");
    
        // Lock the backdoor exit (index 2 for the backdoor wall)
        room4.lockExit(2);
    
        // Return the walls array to be used by the room
        return r4Walls;
    }
    

    /**
     * Triggers the boss fight when the player selects "Fight" on the backdoor wall.
     * @return true if the player defeats the kidnapper
     */
    public boolean triggerFightFromWall() {
        return startBossBattle(player.getInventory(), player);
    }

    public static boolean startBossBattle(List<Item> inventory, Player player) {
        Scanner sc = new Scanner(System.in);

        System.out.println("====================================================================================================");
        System.out.println("You step into a dark kitchen. The kidnapper is sitting at a table, distracted by TV and eating.");
        System.out.println("The kidnapper looks up at you, a malicious grin appearing on his face.");
        System.out.println("\nKidnapper: 'So, you found your way here. But this is the end of the line.'");

        while (true) {
            System.out.println("\nWhat do you do?");
            System.out.println("1. Try to talk him down");
            System.out.println("2. Use an item from your inventory");
            System.out.println("3. Attack him");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    return tryTalkHimDown();
                case "2":
                    return useItem(inventory, player);
                case "3":
                    return attackBoss();
                default:
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
            }
        }
    }

    private static boolean tryTalkHimDown() {
        System.out.println("\nYou try to talk to the kidnapper, but he isn't interested.");
        System.out.println("Kidnapper: 'I have nothing to say to you.'");
        System.out.println("The kidnapper attacks you in response. You fail to talk him down.");
        return false;
    }

    private static boolean useItem(List<Item> inventory, Player player) {
        Scanner sc = new Scanner(System.in);
    
        if (inventory.isEmpty()) {
            System.out.println("You have nothing in your inventory.");
            return false;
        }
    
        System.out.println("\nWhich item do you want to use? Enter the number:");
    
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.printf("%d. %s (Type: %s)%n", i + 1, item.getName(), item.getType());
        }
    
        int choice = -1;
        try {
            System.out.print("Your choice: ");
            choice = Integer.parseInt(sc.nextLine().trim()) - 1;
    
            if (choice < 0 || choice >= inventory.size()) {
                System.out.println("Invalid number.");
                return false;
            }
    
            Item selectedItem = inventory.get(choice);
    
            if (selectedItem.getType().equalsIgnoreCase("Weapon")) {
                double roll = Math.random();
                System.out.printf("You use the %s. Rolling for success... (Need ≤ %.2f)%n", selectedItem.getName(), selectedItem.getChance());
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

    private static boolean attackBoss() {
        System.out.println("\nYou charge at him, fists flying.");
        System.out.println("The kidnapper fights back fiercely. You struggle, but he overpowers you.");
        System.out.println("You are forced to retreat. A more strategic approach is needed.");
        return false;
    }
}
