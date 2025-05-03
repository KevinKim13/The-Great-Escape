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

public class Room2 {
    
    /**
     * Initializes Room 2 (the cell block)
     * @return the initialized Room 2
     */
    public static Room initRoom() {
        Room room2 = new Room("You escape your cell and enter the cell block. Right in front of you is a number of cells, some open and some locked.\n"
                + "To the east is the cell block exit. You hear an animal snoring on the other side.\n"
                + "On the south wall is even more cells, maybe more clues are in there.");
        
        Item dogTreats = new Item("Box of dog treats", "Treats", 0.1);
        Item comboNote = new Item("Note with 4-digit code: 2115", "Key", 0.0);
        
        Wall[] r2Walls = new Wall[4];
        
        // North Wall - Front cells (when you first enter)
        r2Walls[0] = new Wall("A row of three cells lines the north wall. One is open, one is locked, and one has food scraps inside.",
                             "The open cell appears empty but has scratch marks on the walls, as if someone was counting days.\n"
                             + "The locked cell has a skeleton inside - a grim reminder of your potential fate.\n"
                             + "The third cell has various food scraps on the floor, including what looks like a box of dog treats.");
        r2Walls[0].setItem(dogTreats);
        
        // East Wall - Exit door with keypad
        r2Walls[1] = new Wall("It's a heavy metal door with a keypad lock.", 
                             "It requires a 4-digit code. You hear something moving on the other side.");
        r2Walls[1].setPuzzle(new InputLockPuzzle("Enter 4-digit code:", "2115"));
        
        // South Wall - More cells
        r2Walls[2] = new Wall("It's another row of cells along the south wall. You can see a scrap of paper in one.",
                             "Most of the cells are empty, but one contains a note just within reach between the bars.\n"
                             + "Another cell has dried bloodstains on the floor.");
        r2Walls[2].setItem(comboNote);
        
        // West Wall - Door back to your cell
        r2Walls[3] = new Wall("The door back to your cell, now unlocked.", 
                             "You have no reason to go back to your cell now that you're free.");
        
        // Set available actions for each wall
        String[] actionSet1 = {"Inspect", "Try Puzzle", "Go Back"};
        String[] actionSet2 = {"Inspect", "Take Item", "Go Back"};
        String[] actionSet3 = {"Inspect", "Go Back"};
        
        r2Walls[0].setAvailableActions(actionSet2); // North wall - has dog treats
        r2Walls[1].setAvailableActions(actionSet1); // East wall - has code puzzle
        r2Walls[2].setAvailableActions(actionSet2); // South wall - has note
        r2Walls[3].setAvailableActions(actionSet3); // West wall - back to cell
        
        room2.setWalls(r2Walls);
        
        return room2;
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
