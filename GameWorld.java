/**
 * Class: GameWorld.java
 * @author Shane Paton, Will Krajcirik
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 28, 2025
 * 
 * Purpose: Initializes all the objects to be used in the game.
 */
import java.util.*;

public class GameWorld {

    // Instance Variables -------------------------------------------
    private Player player;
    private Room[] rooms;


    // Methods ------------------------------------------------------
    /**
     * Initializes all of the game's objects.
     */
    public void initialize() {

        // Creating the rooms and adding them to array
        Room room1 = StartingRoom.initRoom();
        Room room2 = Room2.initRoom();
        Room room3 = Room3.initRoom();
        Room room4 = new Room("You go upstairs and are now in a dingy kitchen.\nThe kidnapper is preoccupied with watching tv and eating.");

        rooms = new Room[] { room1, room2, room3, room4 };

        // Setting up the exits
        room1.setExit(1, room2); // East
        room2.setExit(3, room1); // West
        room2.setExit(1, room3); // East
        room3.setExit(3, room2); // West
        room3.setExit(1, room4); // East
        room4.setExit(3, room3); // West

        // Locking all of the exits
        room1.lockExit(1);
        room2.lockExit(1);

        // Creating all items
        // Item comboNote = new Item("Note with 4-digit code: 2115", "Key", 0.0);
        // Item wordClue = new Item("Paper with 6-letter word: PRISON", "Key", 0.0);
        // Item woodBat = new Item("Unreliable Wooden Bat", "Weapon", 0.6);
        // Item peanuts = new Item("Bag of Peanuts", "Secret", 1.0);
        // Item knife = new Item("Kitchen Knife", "Weapon", 0.8);

        // Creating the player
        player = new Player(new ArrayList<>(), room1, 0);
        System.out.println("Game loading complete.");
    }

    /**
     * Returns the game's player.
     * @return the game's player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns an array of the rooms in the game.
     * @return the game's rooms
     */
    public Room[] getRooms() {
        return rooms;
    }
}
