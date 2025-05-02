import java.util.List;
import java.util.Scanner;

/**
 * Class: Room2.java
 * 
 * @author Will Krajcirik
 * @version 1.0
 *          Course: CSE 201 Spring 2025
 *          Written: April 30, 2025
 * 
 *          Purpose: Initializes room 2
 */
public static Room initRoom() {
        Room room2 = new Room("You escape your cell and enter the cell block. right in front of you is a number of cells, some open and some locked.\nTo the east is the cell block exit. You hear an animal snoring on the other side.\nOn the south wall is even more cells, maybe more clues are in there");
        Item dogTreats = new Item("Box of dog treats", "Treats", 0.1);
        Wall[] r2Walls = new Wall[4];
        r2Walls[0] = new Wall("It's a bunch of cells.");
        r2Walls[0].setItem(key);
        r2Walls[0].setPuzzle(new NumPuzzle("Needs a thin sturdy object.", spoon));
        r2Walls[1] = new Wall("It's a cell door with a rusty keyhole.", "It requires a three-prong key.");
        r2Walls[1].setPuzzle(new NumPuzzle("Needs a three-prong key.", key));
        r2Walls[2] = new Wall("It's a wall with a mattress laying on the ground next to a plastic tray and rusty metal spoon.", "You examine the spoon and although it isn't useful as a digging tool because of the hole in the center, the handle is still sturdy.");
        String[] actionSet1 = {"Inspect", "Try Puzzle", "Go Back"};
        r2Walls[0].setAvailableActions(actionSet1);
        r2Walls[1].setAvailableActions(actionSet1);
        String[] actionSet2 = {"Inspect", "Take Item", "Go Back"};
        r2Walls[2].setAvailableActions(actionSet2);
        room2.setWalls(r2Walls);
        return room1;
    }
    

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

    private static void tryLock(Wall wall, Room room, Scanner scanner, Player player) {
        Item item = null;
        if (!player.getInventory().isEmpty() && !wall.getPuzzle().isSolved()) {
            System.out.println(wall.getPuzzle().getPrompt());
            System.out.println("You check your inventory for useful items to try.");
            printInventory(player);
            System.out.print("> ");
            int input = Integer.parseInt(scanner.nextLine());
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
        } else {
            System.out.println("Wrong item.");
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
