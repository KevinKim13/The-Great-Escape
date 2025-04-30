/**
 * Class: Player.java
 * @author Kevin Kim, Landen Burns
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: April 28, 2025
 * 
 * Purpose: Initializes a Player with inventory, currentRoom, and directionFacing.
 */
import java.util.*;

public class Player {

    // Instance Variables -------------------------------------------
    private List<Item> inventory;
    private Room currentRoom;
    private int directionFacing; // 0 = North, 1 = East, 2 = South, 3 = West


    // Constructors -------------------------------------------------
    /**
     * Initializes a Player with an inventory, startRoom, and directionFacing.
     * @param inventory the player's inventory
     * @param startRoom the player's starting room
     * @param directionFacing the the direction (N,E,S,W) the player is facing
     */
    public Player(List<Item> inventory, Room startRoom, int directionFacing) {
        this.inventory = inventory;
        this.currentRoom = startRoom;
        this.directionFacing = directionFacing;
    }


    // Methods ------------------------------------------------------
    /**
     * Returns the room the player is currently in.
     * @return the player's current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the room the player is currently in.
     * @param room the player's new room
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Returns the cardinal direction the player is facing.
     * @return the direction the player is facing
     */
    public int getDirectionFacing() {
        return directionFacing;
    }

    /**
     * Returns the player's inventory of items.
     * @return the player's inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Adds an item to the player's inventory.
     * @param item item to be added
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Removes an item from the player's inventory.
     * @param item
     */
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    /**
     * Checks the player inventory for an item of a certain type.
     * @param type the type being looked for
     * @return whether or not the player has an item of that type
     */
    public boolean hasItemOfType(String type) {
        for (Item item : inventory) {
            if (item.getType().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}