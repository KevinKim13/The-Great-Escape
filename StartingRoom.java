/**
 * Class: StartingRoom.java
 * @author Kevin Kim
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: April 30, 2025
 * 
 * Purpose: Initializes a the first room with a description, exits, lockedExits,
 *          and walls.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class StartingRoom {
    /**
     * A method to initialize all the room details.
     * @return Room with all the information added.
     */
    public static Room initRoom() {
        Room room1 = new Room("You are in a ramshackle jail cell. To the north is a brick wall.\nTo the east is the cell door.\nOn the south wall, laying on the ground is an old stained mattress and a plastic tray with a rusty metal spoon.");
        Item key = new Item("Makeshift Three-prong Key", "Key", 0.1);
        Item spoon = new Item("Rusty Metal Spoon", "Utensil", 0.15);
        Wall[] r1Walls = new Wall[4];
        r1Walls[0] = new Wall("It's a brick wall with a loose brick.", "The brick needs something thin to pry it out.");
        r1Walls[0].setItem(key);
        r1Walls[0].setPuzzle(new ItemLockPuzzle("Needs a thin sturdy object.", spoon));
        r1Walls[1] = new Wall("It's a cell door with a rusty keyhole.", "It requires a three-prong key.");
        r1Walls[1].setPuzzle(new ItemLockPuzzle("Needs a three-prong key.", key));
        r1Walls[2] = new Wall("It's a wall with a mattress laying on the ground next to a plastic tray and rusty metal spoon.", "You examine the spoon and although it isn't useful as a digging tool because of the hole in the center, the handle is still sturdy.");
        r1Walls[2].setItem(spoon);
        String[] actionSet1 = {"Inspect", "Try Puzzle", "Go Back"};
        r1Walls[0].setAvailableActions(actionSet1);
        r1Walls[1].setAvailableActions(actionSet1);
        String[] actionSet2 = {"Inspect", "Take Item", "Go Back"};
        r1Walls[2].setAvailableActions(actionSet2);
        room1.setWalls(r1Walls);
        return room1;
    }
    
    /**
     * Handles wall interactions for room 1.
     * @param action the string of action type
     * @param wall the wall being interacted with
     * @param room the room the player is in
     * @param scanner the scanner we are using for the game
     * @param player the player playing the game
     */
    public static void wallInteraction(String action, Wall wall, Room room, Scanner scanner, Player player) {
        if (action.equalsIgnoreCase("inspect")) {
            System.out.println(wall.getInspectText());
        } else if (action.equalsIgnoreCase("Try Puzzle")) {
            tryLock(wall, room, scanner, player);
        } else if (action.equalsIgnoreCase("Take Item")) {
            takeItem(wall, player);
        } else if (action.equalsIgnoreCase("Go Back")) {
            // Do nothing
        } else {
            System.out.println("You can't do that here.");
        }
    }

    /**
     * Function that handles puzzle attempt.
     * @param wall wall puzzle is on
     * @param room current room
     * @param scanner scanner to take user input
     * @param player current player
     */
    private static void tryLock(Wall wall, Room room, Scanner scanner, Player player) {
        Item item = null;
        if (!player.getInventory().isEmpty() && !wall.getPuzzle().isSolved()) {
            System.out.println(wall.getPuzzle().getPrompt());
            System.out.println("You check your inventory for useful items to try.");
            printInventory(player);
            System.out.print("> ");
            int input = -1;
            try {
                input = Integer.parseInt(scanner.nextLine());
                while (input > player.getInventory().size() || input < 1) {
                    System.out.println("Please choose a number between 1-" + player.getInventory().size() + ".");
                    printInventory(player);
                    System.out.print("> ");
                    try {
                        input = Integer.parseInt(scanner.nextLine()); 
                    } catch (Exception d) {
                    // Do Nothing 
                    }
                }
            } catch (Exception e) {
                //Do Nothing
            }
            item = player.getInventory().get(input - 1);
        } else { 
            if (wall.getPuzzle().isSolved()) {
                System.out.println("Puzzle already complete.");
            } else {
                System.out.println("You don't have anything useful. Maybe search around the room?");
            }
            return;               
        }
        if (((ItemLockPuzzle)wall.getPuzzle()).attempt(item)) {
            puzzleSolved(wall, room, player);
        } else {
            System.out.println("Wrong item.");
        }
    }

    /**
     * Helper method that handles when puzzle is successfully solved.
     * @param wall the wall the player is interacting with
     * @param room the room the player is currently in
     * @param player the player playing the game
     */
    private static void puzzleSolved(Wall wall, Room room, Player player) {
        System.out.println("Correct! Puzzle solved.");
        for (int i = 0; i < 4; i++) {
            if (room.getWalls()[i] == wall && room.isExitLocked(i)) {
                room.unlockExit(i);
                System.out.println("Door unlocked.");
            }
        }
        if (wall.getItem() != null) {
            player.addItem(wall.getItem());
            System.out.println("You grab " + wall.getItem().getName());
        }
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

    /**
     * Helper method to handle take item action.
     * @param wall the wall with the item
     * @param player the player to add the item to
     */
    private static void takeItem(Wall wall, Player player) {
        if (wall.getItem() != null) {
            player.getInventory().add(wall.getItem());
            System.out.println("Picked up: " + wall.getItem().getName());
            wall.setItem(null);
        } else {
            System.out.println("Item has already been taken.");
        }
    }
}
