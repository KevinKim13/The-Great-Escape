import java.util.List;
import java.util.Scanner;

/**
 * Class: Puzzle.java
 * 
 * @author Shane Paton
 * @version 1.0
 *          Course: CSE 201 Spring 2025
 *          Written: April 30, 2025
 * 
 *          Purpose: Initializes room 3.
 */
public class Room3 {

    /**
     * Creates and returns a room that holds a miniboss who cant be defeated without
     * treats.
     * 
     * @return The room holding the miniboss and two walls.
     */
    public static Room initRoom() {
        Room room3 = new Room(
                "You glance up and see a staircase heading up. Above you hear footsteps and a TV turn on");
        Wall[] walls = new Wall[4];
        walls[0] = new Wall("There is a wall full of pictures of people in jail cells...",
                "There is nothing behind the pictures");
        walls[0].setAvailableActions(new String[] { "inspect" });
        walls[1] = new Wall("It's a door with a rotating letter lock.", "Nothing to do here.");
        walls[1].setAvailableActions(new String[] { "inspect" });
        return room3;
    }

    /**
     * This will be called before entering the room. It handles the miniboss logic,
     * returning true if the boss is defeated and the player should advance to room
     * 3, and false if the miniboss is not defeated and the player should be sent
     * back to room 2 to continue searching.
     * 
     * @param inventory The Players inventry should be passed to check for treats.
     * @return True to move to room 3 or false to go back to room 2.
     */
    public static boolean enterRoom(List<Item> inventory, Scanner sc) {
        System.out.println(
                "----------------------------------------------------------------------------------------------------");
        System.out.println("You are in a dim hallway with flickering lights.\nThere is a dog snarling at you.");
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Try to tame the dog with what you have found");
        System.out.println("2. Go back and look for something to tame the dog");
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1":
                return fightBoss(inventory);
            case "2":

                return false;
            default: {
                System.out.println("Invalid option. Please enter 1 or 2");
            }
        }
        return false;
    }

    /**
     * A helper method that checks the inventory for dog treats. It returns true
     * when the player has dog treats and false when the player does not.
     * 
     * @param inventory The players inventory of items that is passed
     * @return True if the player has Dog Treats, False if not.
     */
    private static boolean fightBoss(List<Item> inventory) {
        // loop through the inventry and check for Dog Treats item
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals("Dog Treats")) {
                System.out.println(
                        "You pull out your dog treats and all of the sudden the dog looks friendly."
                                + "After some treats and petting the dog curls up and rests in the corner.");
                return true;
            }
        }
        System.out.println(
                "You check you pockets but there is nothing to befriend the dog with so you head back to room 2.");
        return false;
    }
}