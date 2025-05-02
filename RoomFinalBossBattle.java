import java.util.List;
import java.util.Scanner;

/**
 * Class: RoomFinalBossBattle.java
 * @author [Your Name]
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: [Insert Date]
 * 
 * Purpose: Manages the final boss encounter in the game, combining Room setup and combat logic.
 */
public class RoomFinalBossBattle extends Room {

    private Player player;

    /**
     * Constructor for RoomFinalBossBattle. Calls the super constructor of Room.
     * @param player the player object
     * @param description the description of the final room
     */
    public RoomFinalBossBattle(Player player, String description) {
        // Call the Room constructor with the description argument
        super(description);
        this.player = player;
    }

    /**
     * Builds the final room and sets up the boss battle.
     * @param player the player object
     * @return the Room representing the final scene with boss
     */
    public static Room buildRoom(Player player) {
        RoomFinalBossBattle room4 = new RoomFinalBossBattle(player, "You go upstairs and are now in a dingy kitchen.\n"
                + "The kidnapper is preoccupied with watching TV and eating.");

        // Walls and items setup
        Wall[] r4Walls = new Wall[4];
        Item knife = new Item("Kitchen Knife", "Weapon", 0.8);

        r4Walls[0] = new Wall("The passageway to the living room where the kidnapper sits.",
                              "It might be possible to take him down.");
        r4Walls[1] = new Wall("The main area of the kitchen with a fridge, oven, and sink.",
                              "You spot a knife in the kitchen sink.");
        r4Walls[1].setItem(knife);
        r4Walls[2] = new Wall("The backdoor of the kidnapper's house",
                              "The only feasible escape route in sight.");
        r4Walls[3] = new Wall("A door to the basement.",
                              "The basement you just escaped from.");

        room4.setWalls(r4Walls);

        // Lock the exit to the backdoor (escape route)
        room4.lockExit(2);  // Lock the backdoor exit (3rd direction, south)

        return room4;
    }

    /**
     * Starts the final boss battle, where the player confronts the kidnapper.
     * @param inventory the player's inventory
     * @param player the player object
     * @return true if the player defeats the kidnapper, false otherwise
     */
    public static boolean startBossBattle(List<Item> inventory, Player player) {
        Scanner sc = new Scanner(System.in);

        System.out.println("====================================================================================================");
        System.out.println("You step into a dark kitchen. The kidnapper is sitting at a table, distracted by TV and eating.");
        System.out.println("The kidnapper looks up at you, a malicious grin appearing on his face.");
        System.out.println("\nKidnapper: 'So, you found your way here. But this is the end of the line.'");

        // Battle loop
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

    /**
     * Try to reason with the kidnapper (will fail).
     * @return false (the kidnapper doesn't listen)
     */
    private static boolean tryTalkHimDown() {
        System.out.println("\nYou try to talk to the kidnapper, but he isn't interested.");
        System.out.println("Kidnapper: 'I have nothing to say to you.'");
        System.out.println("The kidnapper attacks you in response. You fail to talk him down.");
        return false;
    }

    /**
     * Use an item from the player's inventory.
     * @param inventory the player's inventory
     * @param player the player object
     * @return true if the player defeats the kidnapper using an item, false otherwise
     */
    private static boolean useItem(List<Item> inventory, Player player) {
        Scanner sc = new Scanner(System.in);

        if (inventory.isEmpty()) {
            System.out.println("You have nothing in your inventory.");
            return false;
        }

        System.out.println("\nWhich item do you want to use? (Type the item name exactly)");
        for (Item item : inventory) {
            System.out.println("- " + item.getName() + " (Type: " + item.getType() + ")");
        }

        String itemName = sc.nextLine().trim();
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {

                if (item.getType().equalsIgnoreCase("Weapon")) {
                    double roll = Math.random(); // 0.0 to 1.0
                    System.out.printf("You use the %s. Rolling for success... (Need ≤ %.2f)%n", item.getName(), item.getChance());
                    if (roll <= item.getChance()) {
                        System.out.println("Success! You defeat the kidnapper with the " + item.getName() + ".");
                        return true;
                    } else {
                        System.out.println("You tried to use the " + item.getName() + ", but it failed. The kidnapper overpowers you.");
                        return false;
                    }

                } else if (item.getName().equalsIgnoreCase("Evidence Folder")) {
                    System.out.println("You present the Evidence Folder.");
                    System.out.println("Kidnapper: 'You... weren’t supposed to find that...'");
                    System.out.println("He surrenders, realizing the truth will come out.");
                    return true;

                } else {
                    System.out.println("The " + item.getName() + " doesn't help you here.");
                    return false;
                }
            }
        }

        System.out.println("You don't have that item.");
        return false;
    }

    /**
     * Directly attack the kidnapper (will fail).
     * @return false
     */
    private static boolean attackBoss() {
        System.out.println("\nYou charge at him, fists flying.");
        System.out.println("The kidnapper fights back fiercely. You struggle, but he overpowers you.");
        System.out.println("You are forced to retreat. A more strategic approach is needed.");
        return false;
    }
}
