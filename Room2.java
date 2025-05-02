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

    public static Room initRoom() {
        Room room2 = new Room(
                "There is another door with a peculiar looking lock.");
        Item wordClue = new Item("Paper in cell with number code to break lock: 2115", "Key", 0.0);

        Wall[] walls = new Wall[4];
        walls[0] = new Wall("A plain wall that houses an antique table with drawers.",
                "There is a note inside one of the drawers.");
        walls[0].setItem(wordClue);
        walls[1] = new Wall("It's a door with a rotating letter lock.", "It requires a 6-letter word.");
        walls[1].setPuzzle(new InputLockPuzzle("Enter 6-letter word:", "PRISON"));
        walls[1].setAvailableActions(new String[] { "inspect", "enter code" });

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
    public static boolean enterRoom(List<Item> inventory) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "----------------------------------------------------------------------------------------------------");
        System.out.println("You enter the cell block outside the cell you were locked in.\n.");
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

    // somone needs to add dog treats
    private static boolean fightBoss(List<Item> inventory) {

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
