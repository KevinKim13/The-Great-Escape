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
    private static final String[] directions = {"North", "East", "South", "West"};


    // Main Method --------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameWorld world = new GameWorld();
        world.initialize();
        Player player = world.getPlayer();

        while (true) {
            Room currentRoom = player.getCurrentRoom();
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("\nLocation: " + currentRoom.getDescription());

            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleWallInspection(currentRoom);
                    break;
                case "2":
                    handleWallInteraction(scanner, currentRoom);
                    break;
                case "3":
                    handleItemPickup(scanner, currentRoom, player);
                    break;
                case "4":
                    handleMovement(scanner, currentRoom, player);
                    break;
                case "5":
                    printInventory(player);
                    break;
                case "6":
                    System.out.println("Thank you for playing! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
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
        System.out.println("2. Interact with a wall");
        System.out.println("3. Pick up an item");
        System.out.println("4. Move");
        System.out.println("5. Check inventory");
        System.out.println("6. Quit");
        System.out.print("> ");
    }

    /**
     * Lists available walls in the player's current room.
     * @param room the player's current room
     */
    private static void handleWallInspection(Room room) {
        for (int i = 0; i < 4; i++) {
            Wall wall = room.getWalls()[i];
            if (wall != null) {
                System.out.println(directions[i] + ": " + wall.getDescription());
            }
        }
    }

    /**
     * Allows player to choose a wall to interact with. Displays wall info if available.
     * @param scanner the game's scanner
     * @param room the player's current room
     */
    private static void handleWallInteraction(Scanner scanner, Room room) {
        System.out.println("Which wall? (0-N, 1-E, 2-S, 3-W)");
        int index = Integer.parseInt(scanner.nextLine());
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
        for (int i = 0; i < actions.length; i++) {
            System.out.println((i + 1) + ". " + actions[i]);
        }
        int actionIndex = Integer.parseInt(scanner.nextLine()) - 1;
        String action = actions[actionIndex];
        if (action.equalsIgnoreCase("inspect")) {
            System.out.println(wall.getInspectText());
        } else if (action.equalsIgnoreCase("enter code") && wall.hasPuzzle()) {
            System.out.println(wall.getPuzzle().getPrompt());
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (wall.getPuzzle().attempt(input)) {
                System.out.println("Correct! Puzzle solved.");
                for (int i = 0; i < 4; i++) {
                    if (room.getWalls()[i] == wall && room.isExitLocked(i)) {
                        room.unlockExit(i);
                        System.out.println("A door has unlocked to the " + directions[i].toLowerCase() + ".");
                    }
                }
            } else {
                System.out.println("Wrong code.");
            }
        } else {
            System.out.println("You can't do that here.");
        }
    }

    /**
     * Gives player the option to take item. Adds item to inventory if taken.
     * @param scanner the game's scanner
     * @param room the player's current room
     * @param player the game's player
     */
    private static void handleItemPickup(Scanner scanner, Room room, Player player) {
        for (int i = 0; i < 4; i++) {
            Wall w = room.getWalls()[i];
            if (w != null && w.getItem() != null) {
                System.out.println(i + ". Take item from " + directions[i] + " wall: " + w.getItem().getName());
            }
        }
        int index = Integer.parseInt(scanner.nextLine());
        Wall wall = room.getWalls()[index];
        if (wall != null && wall.getItem() != null) {
            player.getInventory().add(wall.getItem());
            System.out.println("Picked up: " + wall.getItem().getName());
            wall.setItem(null);
        } else {
            System.out.println("Nothing picked up.");
        }
    }

    /**
     * Lists the available directions to go and attempts to move player to inputted direction.
     * @param scanner the game's scanner
     * @param room the player's current room
     * @param player the game's player
     */
    private static void handleMovement(Scanner scanner, Room room, Player player) {
        for (int i = 0; i < 4; i++) {
            System.out.println(i + ". Go " + directions[i]);
        }
        int dir = Integer.parseInt(scanner.nextLine());
        Room next = room.getExit(dir);
        if (next != null) {
            if (room.isExitLocked(dir)) {
                Wall door = room.getWalls()[dir];
                if (door != null && door.hasPuzzle()) {
                    System.out.println("This door's puzzle must be solved.");
                } else {
                    boolean hasKey = player.getInventory().stream().anyMatch(i -> i.getType().equals("Key"));
                    if (hasKey) {
                        room.unlockExit(dir);
                        System.out.println("You used a key to unlock the door.");
                        player.setCurrentRoom(next);
                    } else {
                        System.out.println("The door is locked and requires a key.");
                    }
                }
            } else {
                player.setCurrentRoom(next);
                System.out.println("You move " + directions[dir].toLowerCase());
            }
        } else {
            System.out.println("You can't go that way.");
        }

        // ADD FINISH PATH + BOSS - IF THE PLAYER TRIES TO MOVE FROM ROOM 4 EAST AFTER BEATING KIDNAPPER, THEN THEY WIN
    }

    /**
     * Prints the player's inventory to the terminal.
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