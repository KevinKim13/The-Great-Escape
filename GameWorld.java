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
        Room room1 = new Room("You are in a ramshackle jail cell. To the north is a brick wall.\nTo the east is the cell door.");
        Room room2 = new Room("You are in a cell block with many cells and a heavy metal door to your east.");
        Room room3 = new Room("You are in a dim hallway with flickering lights.\nThere is another door with a peculiar looking lock.");
        Room room4 = new Room("You are in a dingy kitchen. The kidnapper is watching tv while eating.");

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
        room3.lockExit(1);

        // Creating all items
        Item key = new Item("Makeshift Key", "Key", 0.1);
        Item comboNote = new Item("Note with 4-digit code: 2115", "Key", 0.0);
        Item wordClue = new Item("Paper with 6-letter word: PRISON", "Key", 0.0);

        // Assigning items to their walls
        Wall[] r1Walls = new Wall[4];
        r1Walls[0] = new Wall("It's a brick wall with a loose brick.", "You find a key behind the brick.");
        r1Walls[0].setItem(key);
        r1Walls[1] = new Wall("It's a cell door with a rusty keyhole.", "It requires a key.");

        Wall[] r2Walls = new Wall[4];
        r2Walls[1] = new Wall("It's a heavy metal door with a keypad lock.", "It requires a 4-digit code.");
        r2Walls[1].setPuzzle(new InputLockPuzzle("Enter 4-digit code:", "2115"));
        r2Walls[1].setAvailableActions(new String[] { "inspect", "enter code" });
        r2Walls[2] = new Wall("It's another locked cell with a scrap of paper just in reach between the bars.", "The cell contains a note.");
        r2Walls[2].setItem(comboNote);

        Wall[] r3Walls = new Wall[4];
        r3Walls[0] = new Wall("A plain wall that houses an antique table with drawers.", "There is a note inside one of the drawers.");
        r3Walls[0].setItem(wordClue);
        r3Walls[1] = new Wall("It's a door with a rotating letter lock.", "It requires a 6-letter word.");
        r3Walls[1].setPuzzle(new InputLockPuzzle("Enter 6-letter word:", "PRISON"));
        r3Walls[1].setAvailableActions(new String[] { "inspect", "enter code" });

        Wall[] r4Walls = new Wall[4];
        // NEED TO FINISH FINAL ROOM - ADD BOSS, ENDING SCREEN

        // Setting walls to their rooms
        room1.setWalls(r1Walls);
        room2.setWalls(r2Walls);
        room3.setWalls(r3Walls);
        room4.setWalls(r4Walls);

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