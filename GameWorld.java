import java.util.*;

/**
 * Class: GameWorld.java
 * @author Shane Paton, Will Krajcirik
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 28, 2025
 * 
 * Purpose: Initializes all of the game's objects.
 */
public class GameWorld {

    // Instance Variables -------------------------------------------
    private Player player;
    private Room[] rooms;

    // Methods ------------------------------------------------------
    /**
     * Initializes all of the game's objects.
     */
    public void initialize() {

        // Creating the rooms and adding them to the array
        Room room1 = StartingRoom.initRoom();
        Room room2 = new Room("You are in a cell block with many cells and a heavy metal door to your east.");
        Room room3 = new Room("You are in a dim hallway with flickering lights.\nThere is another door with a peculiar looking lock.");
        RoomFinalBossBattle room4 = new RoomFinalBossBattle(player, "You go upstairs and are now in a dingy kitchen.\nThe kidnapper is preoccupied with watching TV and eating.");

        rooms = new Room[] { room1, room2, room3, room4 };

        // Setting up the exits
        room1.setExit(1, room2); // East
        room2.setExit(3, room1); // West
        room2.setExit(1, room3); // East
        room3.setExit(3, room2); // West
        room3.setExit(1, room4); // East
        room4.setExit(3, room3); // West

        // Locking all of the exits
        room1.lockExit(1); // Lock east exit of room1
        room2.lockExit(1); // Lock east exit of room2
        room3.lockExit(1); // Lock east exit of room3

        // Creating all items
        Item comboNote = new Item("Note with 4-digit code: 2115", "Key", 0.0);
        Item wordClue = new Item("Paper with 6-letter word: PRISON", "Key", 0.0);
        Item woodBat = new Item("Unreliable Wooden Bat", "Weapon", 0.6);
        Item peanuts = new Item("Bag of Peanuts", "Secret", 1.0);
        Item knife = new Item("Kitchen Knife", "Weapon", 0.8);

        // Walls and Items Setup for Room 2
        Wall[] r2Walls = new Wall[4];
        r2Walls[0] = new Wall("There's an old rickety wooden chair.", "You could break off a leg to use as an improvised bat.");
        r2Walls[0].setItem(woodBat);
        r2Walls[1] = new Wall("It's a heavy metal door with a keypad lock.", "It requires a 4-digit code.");
        r2Walls[1].setPuzzle(new InputLockPuzzle("Enter 4-digit code:", "2115"));
        r2Walls[1].setAvailableActions(new String[] { "inspect", "enter code" });
        r2Walls[2] = new Wall("It's another locked cell with a scrap of paper just\nin reach between the bars.", "The cell contains a note.");
        r2Walls[2].setItem(comboNote);
        r2Walls[3] = new Wall("An unlocked door to a cell.", "The cell you just escaped from.");

        // Walls and Items Setup for Room 3
        Wall[] r3Walls = new Wall[4];
        r3Walls[0] = new Wall("A plain wall that houses an antique table with drawers.", "There is a note inside one of the drawers.");
        r3Walls[0].setItem(wordClue);
        r3Walls[1] = new Wall("It's a door with a rotating letter lock.", "It requires a 6-letter word.");
        r3Walls[1].setPuzzle(new InputLockPuzzle("Enter 6-letter word:", "PRISON"));
        r3Walls[1].setAvailableActions(new String[] { "inspect", "enter code" });
        r3Walls[2] = new Wall("A suspiciously pristine backpack leaning against the wall.", "There's not much of anything besides a rolled up\nhalf-eaten bag of peanuts.");
        r3Walls[2].setItem(peanuts);
        r3Walls[3] = new Wall("A door to a cell block.", "The cell block you just came from.");

        // Walls and Items Setup for Room 4
        Wall[] r4Walls = new Wall[4];
        r4Walls[0] = new Wall("The passageway to the living room where the kidnapper sits.", "It might be possible to take him down.");
        r4Walls[1] = new Wall("The main area of the kitchen with a fridge, oven, and sink.", "You spot a knife in the kitchen sink.");
        r4Walls[1].setItem(knife);
        r4Walls[2] = new Wall("The backdoor of the kidnapper's house", "The only feasible escape route in sight.");
        r4Walls[3] = new Wall("A door to the basement.", "The basement you just escaped from.");

        // Setting walls to their rooms
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
