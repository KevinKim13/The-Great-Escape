/**
 * Class: Player.java
 * @author Landen Burns
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 2, 2025
 * 
 * Purpose: Initializes a Player with inventory, room, and soundLevel.
 */
import java.util.ArrayList;

public class Player {

    // Instance Variables -----------------------------------------
    private ArrayList<Item> inventory;
    private Room room;
    private int soundLevel;


    // Constructors -----------------------------------------------

    /**
     * Workhorse Constructor. Initializes a Player with all instance
     * variables specified.
     * @param inventory the Player's list of acquired Items
     * @param room the current location of the Player
     * @param lvl the Player's sound level
     */
    public Player(ArrayList<Item> inventory, Room room, int lvl) {
        this.inventory = inventory;
        this.room = room;
        this.soundLevel = lvl;
    }

    /**
     * Empty Constructor. Intializes a Player with all instance
     * variables set to zero or null values.
     */
    public Player() {
        this.inventory = null;
        this.room = null;
        this.soundLevel = 0;
    }


    // Methods ----------------------------------------------------

    /**
     * Return the Player's current location.
     * @return the Player's current Room
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Sets the Player's current location.
     * @param room the Player's new Room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns the Player's inventory of Items.
     * @return the Player's inventory
     */
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    /**
     * Adds an Item to the Player's inventory.
     * @param item the new Item being added
     * @return whether or not the addition was successful
     */
    public boolean addItem(Item item) {
        this.inventory.add(item);
    }

    /**
     * Removes an Item from the Player's inventory.
     * @param item the Item being removed
     * @return whether or not the removal was successful
     */
    public boolean removeItem(Item item) {
        this.inventory.remove(item);
    }

    /**
     * Returns the Player's current sound level.
     * @return the Player's sound level
     */
    public int getSoundLevel() {
        return soundLevel;
    }

    /**
     * Sets the Player's sound level.
     * @param lvl the Player's new sound level
     */
    public void setSoundLevel(int lvl) {
        this.soundLevel = lvl;
    }

}
