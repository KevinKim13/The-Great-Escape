
/**
 * Class: GameMain.java
 * @author Landen Burns, Kevin Kim, Shane Paton, Joseph Edwards, Will Krajcirik
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 29, 2025
 * 
 * Purpose: Runs "The Great Escape" and allows for player input
 *          to play.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class GameMain {

    // Instance Variables -------------------------------------------
    private static final String[] directions = { "North", "East", "South", "West" };
    private static final GameWorld world = new GameWorld();

    // Main Method --------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        world.initialize();
        Player player = world.getPlayer();

        while (true) {
            Room currentRoom = player.getCurrentRoom();
            if (currentRoom == world.getRooms()[2]) {
                if (!Room3.enterRoom(player.getInventory(), scanner)) {
                    player.setCurrentRoom(world.getRooms()[1]);
                    currentRoom = player.getCurrentRoom();
                }
            }
            System.out.println(
                    "----------------------------------------------------------------------------------------------------");
            System.out.println("\nLocation: " + currentRoom.getDescription());

            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleWallListing(scanner, currentRoom, player);
                    break;
                case "2":
                    interactWall(scanner, currentRoom, player);
                    break;
                case "3":
                    handleMovement(scanner, currentRoom, player);
                    break;
                case "4":
                    printInventory(player);
                    break;
                case "5":
                    System.out.println("Thank you for playing! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
            }
        }
    }

    // Methods ------------------------------------------------------
    /**
     * Prints the menu of the game and the player input line.
     */
    private static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Look at walls");
        System.out.println("2. Interact with wall"); 
        System.out.println("3. Move");
        System.out.println("4. Check inventory");
        System.out.println("5. Quit");
        System.out.print("> ");
    }

    /**
     * Lists available walls in the player's current room.
     * 
     * @param room the player's current room
     */
    private static void handleWallListing(Scanner scanner, Room room, Player player) {
        for (int i = 0; i < 4; i++) {
            Wall wall = room.getWalls()[i];
            if (wall != null) {
                System.out.println(directions[i] + ": " + wall.getDescription());
            }
        }
        interactWall(scanner, room, player);
    }

    /**
     * Handle initial user input to direct wall interactions to correct methods.
     * @param scanner the scanner taking user input
     * @param room the current room
     * @param player the player
     */
    private static void interactWall(Scanner scanner, Room room, Player player) {
        System.out.println("Which wall would you like to examine further? (0-N, 1-E, 2-S, 3-W)");
        System.out.print("> ");
        int index = Integer.parseInt(scanner.nextLine());
        while (!(index < 4 && index >= 0)) {
            System.out.println("Invalid input. Enter a number 0-3");
            System.out.println("Which wall would you like to examine further? (0-N, 1-E, 2-S, 3-W)");
            System.out.print("> ");
            index = Integer.parseInt(scanner.nextLine());
        }
        Wall wall = room.getWalls()[index];
        if (wall == null) {
            System.out.println("Nothing interesting here.");
            return;
        }
        String[] actions = wall.getAvailableActions();
        if (actions == null || actions.length == 0) {
            System.out.println("Nothing to do here.");
            return;
        }
        splitRoomInteractions(wall, room, scanner, player, actions);
    }

    /**
     * Handles action input and sends to room specified wall interactions.
     * @param wall the wall being interacted with
     * @param room the current room
     * @param scanner the scanner taking user input
     * @param player current player
     * @param actions list of actions possible
     */
    private static void splitRoomInteractions(Wall wall, Room room, Scanner scanner, Player player, String[] actions) {
        String action = "";
        while (!action.equalsIgnoreCase("Go Back")) {
            for (int i = 0; i < actions.length; i++) {
                System.out.println((i + 1) + ". " + actions[i]);
            }
            System.out.print("> ");
            int actionIndex = Integer.parseInt(scanner.nextLine()) - 1;
            while (!(actionIndex < actions.length && actionIndex >= 0 )) {
                System.out.println("Invalid input. Enter a number 1-" + actions.length + ".");
                for (int i = 0; i < actions.length; i++) {
                    System.out.println((i + 1) + ". " + actions[i]);
                }
                System.out.print("> ");
                actionIndex = Integer.parseInt(scanner.nextLine());
            }
            action = actions[actionIndex];
            if (room == world.getRooms()[0]) {
                StartingRoom.wallInteraction(action, wall, room, scanner, player);
            } else if (room == world.getRooms()[1]) {
                Room2.wallInteraction(action, wall, room, scanner, player);
            } else if (room == world.getRooms()[2]) {
                Room3.wallInteraction(action, wall, room, scanner, player);
            }
            
        }
    }

    /**
     * Lists the available directions to go and attempts to move player to inputted
     * direction.
     * 
     * @param scanner the game's scanner
     * @param room    the player's current room
     * @param player  the game's player
     */
    private static void handleMovement(Scanner scanner, Room room, Player player) {
        String room4Desc = "You go upstairs and are now in a dingy kitchen.\nThe kidnapper is preoccupied with watching tv and eating.";
        for (int i = 0; i < 4; i++) {
            System.out.println(i + ". Go " + directions[i]);
        }
        System.out.print("> ");
        int dir = Integer.parseInt(scanner.nextLine());
        Room next = room.getExit(dir);
        if (player.getCurrentRoom().getDescription().equals(room4Desc) && dir == 1) {
            System.out.println("You ran out the backdoor and escaped! YOU WIN!");
            System.exit(0);
        } else if (next != null) {
            if (room.isExitLocked(dir)) {
                Wall door = room.getWalls()[dir];
                if (door != null && door.hasPuzzle()) {
                    System.out.println("This door's puzzle must be solved.");
                }
            } else {
                player.setCurrentRoom(next);
                System.out.println("You move " + directions[dir].toLowerCase() + "...");
            }
        } else {
            System.out.println("You can't go that way.");
        }
    }

    /**
     * Prints the player's inventory to the terminal.
     * 
     * @param player the game's player
     */
    private static void printInventory(Player player) {
        ArrayList<Item> inv = (ArrayList<Item>) player.getInventory();
        if (inv.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < inv.size(); i++) {
                Item item = inv.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " (" + item.getType() + ")");
            }
        }
    }
}
