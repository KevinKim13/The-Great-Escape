/**
 * Class: Room.java
 * @author Landen Burns
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 2, 2025
 * 
 * Purpose: Initializes a Room with description, walls, roomInventory,
 *          lockType, and hasNPC.
 */
import java.util.ArrayList;

public class Room {

    // Instance Variables -----------------------------------------
    private String description;
    private Wall[] walls;
    private ArrayList<Item> roomInventory;
    private String lockType;
    private boolean hasNPC;


    // Constructors -----------------------------------------------

    /**
     * Workhorse Constructor. Initializes a Room object with all
     * instance variables specified.
     * @param description the Room's main description
     * @param walls the Room's walls
     * @param items the Items in the Room
     * @param lock the type of lock in the Room
     * @param npc whether or not an NPC is in the Room
     */
    public Room(String description, Wall[] walls, ArrayList<Item> 
    items, String lock, boolean npc) {
        this.description = description;
        this.walls = walls;
        this.roomInventory = items;
        this.lockType = lock;
        this.hasNPC = npc;
    }

    /**
     * Empty Constructor. Initailizes a Room object with all
     * instance variables set to zero or null values.
     */
    public Room() {
        this.description = "";
        this.walls = null;
        this.roomInventory = null;
        this.lockType = "";
        this.hasNPC = false;
    }


    // Methods ----------------------------------------------------

    /**
     * Return the specified Wall.
     * @param wall the specified Wall's number
     * @return the specified wall
     */
    public Wall getWall(int wall) {
        return walls[wall];
    }

    /**
     * Returns a detailed description of the Room.
     * @return the Room's main description
     */
    public String getMainDescription() {
        return description;
    }

    /**
     * Returns the list of Items in the Room.
     * @return the Room's inventory
     */
    public ArrayList<Item> getRoomInventory() {
        return this.roomInventory;
    }

    /**
     * Returns the type of lock in the Room.
     * @return the lock type
     */
    public String getLockType() {
        return this.lockType;
    }

    /**
     * Returns whether or not the Room contains an NPC.
     * @return whether or not an NPC is present
     */
    public boolean getHasNPC() {
        return this.hasNPC;
    }

    /**
     * Sets the Room's description.
     * @param des the new description of the Room
     */
    public void setDescription(String des) {
        this.description = des;
    }

    /**
     * Sets the Room's inventory.
     * @param inventory the Items in the Room
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.roomInventory = inventory;
    }

}
