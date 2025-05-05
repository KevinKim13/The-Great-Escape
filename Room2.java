/**
 * Class: Room2.java
 * 
 * @author Will Krajcirik
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: May 4, 2025
 * 
 * Purpose: Initializes room 2 (cell block)
 */
import java.util.Scanner;

public class Room2 {
    
    /**
     * Initializes Room 2 (the cell block)
     * @return the initialized Room 2
     */
    public static Room initRoom() {
        // Create the room with a detailed description
        Room room2 = new Room("You escape your cell and enter the cell block. Right in front of you is a number of cells, some open and some locked.\n" +
                             "To the east is the cell block exit. You hear an animal snoring on the other side.\n" +
                             "On the south wall is even more cells, maybe more clues are in there. A rickety wooden chair sits in the corner.");
        
        // Create items for the room
        Item dogTreats = new Item("Box of Dog Treats", "Treats", 0.1);
        Item woodenBat = new Item("Broken Chair Leg", "Weapon", 0.4);
    
        // Initialize the walls array
        Wall[] r2Walls = new Wall[4];
        
        // North Wall - Contains cells with dog treats
        r2Walls[0] = new Wall("A row of cells lines the north wall. One appears to have food scraps on the floor.",
                             "You examine the cells closer. Most are empty, but one contains various food scraps including what looks like a box of dog treats.\n" +
                             "You can just reach them through the bars if you try.");
        r2Walls[0].setItem(dogTreats);
        
        // East Wall - Exit door with keypad
        r2Walls[1] = new Wall("It's a heavy metal door with a keypad lock.", 
                             "It requires a 4-digit code. You can hear faint snoring coming from the other side.");
        r2Walls[1].setPuzzle(new InputLockPuzzle("Enter 4-digit code:", "2115"));
        
        // South Wall - Contains cells with the code note description
        r2Walls[2] = new Wall("It's another row of cells along the south wall. One cell has a scrap of paper on the floor.",
                             "You peer into the cells. One contains a piece of paper just within reach between the bars.\n" +
                             "You stretch your arm and grab it. The note reads: \"4-digit code: 2115\"\n" +
                             "Another cell has dried bloodstains on the floor, and a third has tallies scratched into the wall.");
        
        // West Wall - Door back to your cell and the wooden chair
        r2Walls[3] = new Wall("An old rickety wooden chair sits against the west wall near the door back to your cell.", 
                             "The chair is barely holding together. One of the legs is loose and could be broken off to use as a weapon.");
        r2Walls[3].setItem(woodenBat);
        
        // Set available actions for each wall
        String[] actionSet1 = {"Inspect", "Try Puzzle", "Go Back"};
        String[] actionSet2 = {"Inspect", "Take Item", "Go Back"};
        
        r2Walls[0].setAvailableActions(actionSet2); // North wall - has dog treats
        r2Walls[1].setAvailableActions(actionSet1); // East wall - has code puzzle
        r2Walls[2].setAvailableActions(new String[] {"Inspect", "Go Back"}); // South wall - just description with code
        r2Walls[3].setAvailableActions(actionSet2); // West wall - has chair leg
        
        // Set the walls to the room
        room2.setWalls(r2Walls);
        
        return room2;
    }
    
    /**
     * Handles interaction with a wall in Room 2
     * @param action the action to perform
     * @param wall the wall to interact with
     * @param room the current room
     * @param scanner for user input
     * @param player the game player
     */
    public static void wallInteraction(String action, Wall wall, Room room, Scanner scanner, Player player) {
        if (action.equalsIgnoreCase("inspect")) {
            System.out.println(wall.getInspectText());
        } else if (action.equalsIgnoreCase("Try Puzzle")) {
            tryLock(wall, room, scanner);
        } else if (action.equalsIgnoreCase("Take Item")) {
            takeItem(wall, player);
        } else if (action.equalsIgnoreCase("Go Back")) {
            // Do nothing
        } else {
            System.out.println("You can't do that here.");
        }
    }

    /**
     * Handle attempting the door lock puzzle
     * @param wall the wall with the puzzle
     * @param room the current room
     * @param scanner for user input
     */
    private static void tryLock(Wall wall, Room room, Scanner scanner) {
        if (wall.hasPuzzle() && wall.getPuzzle() instanceof InputLockPuzzle) {
            InputLockPuzzle puzzle = (InputLockPuzzle) wall.getPuzzle();
            
            if (puzzle.isSolved()) {
                System.out.println("This lock is already solved.");
                return;
            }
            
            System.out.println(puzzle.getPrompt());
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (puzzle.attempt(input)) {
                System.out.println("Correct! The keypad beeps and the door unlocks.");
                for (int i = 0; i < 4; i++) {
                    if (room.getWalls()[i] == wall && room.isExitLocked(i)) {
                        room.unlockExit(i);
                        System.out.println("The east door is now unlocked. You hear snoring from the other side.");
                    }
                }
            } else {
                System.out.println("Wrong code. The keypad flashes red.");
            }
        } else {
            System.out.println("There's no puzzle to solve here.");
        }
    }
    
    /**
     * Helper method to handle take item action.
     * @param wall the wall with the item
     * @param player the player to add the item to
     */
    private static void takeItem(Wall wall, Player player) {
        if (wall.getItem() != null) {
            Item item = wall.getItem();
            player.addItem(item);
            
            // Custom messages based on the item
            if (item.getName().contains("Dog Treats")) {
                System.out.println("You reach through the bars and grab the box of dog treats.");
                System.out.println("These might come in handy if there's a guard dog nearby.");
            } else if (item.getName().contains("Chair Leg")) {
                System.out.println("You break off one of the chair legs to use as a makeshift weapon.");
                System.out.println("It's not very sturdy, but it's better than nothing.");
            } else {
                System.out.println("Picked up: " + item.getName());
            }
            
            wall.setItem(null);
        } else {
            System.out.println("There's nothing here to take.");
        }
    }
}
