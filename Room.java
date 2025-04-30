/**
 * Class: Room.java
 * @author Shane Paton, Kevin Kim
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: April 27, 2025
 * 
 * Purpose: Initializes a Room with a description, exits, lockedExits,
 *          and walls.
 */

public class Room {

    // Instance Variables -------------------------------------------
    private String description;
    private Room[] exits;
    private boolean[] lockedExits;
    private Wall[] walls;


    // Constructors -------------------------------------------------
    /**
     * Initializes a room with a description, exits, lockedExits, and walls.
     * @param description the room's description
     */
    public Room(String description) {
        this.description = description;
        this.exits = new Room[4]; // North, East, South, West
        this.lockedExits = new boolean[4];
        this.walls = new Wall[4];
    }


    // Methods ------------------------------------------------------
    /**
     * Returns the walls of the room.
     * @return the room's walls
     */
    public Wall[] getWalls() {
        return walls;
    }

    /**
     * Sets the walls of the room.
     * @param walls the room's new walls
     */
    public void setWalls(Wall[] walls) {
        this.walls = walls;
    }
    
    /**
     * Returns the room's description
     * @return the room's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a connection between to rooms.
     * @param direction the cardinal direction the exit is located
     * @param neighbor the room being connected
     */
    public void setExit(int direction, Room neighbor) {
        exits[direction] = neighbor;
    }

    /**
     * Returns the room connected by the exit.
     * @param direction the direction of the exit
     * @return the room connected by that exit
     */
    public Room getExit(int direction) {
        return exits[direction];
    }

    /**
     * Locks the exit so the player cannot pass.
     * @param direction the direction of the locked exit
     */
    public void lockExit(int direction) {
        lockedExits[direction] = true;
    }

    /**
     * Unlocks the exit so the player can pass.
     * @param direction the direction of the unlocked exit
     */
    public void unlockExit(int direction) {
        lockedExits[direction] = false;
    }

    /**
     * Returns whether or not the exit is locked.
     * @param direction the direction of the exit
     * @return whether or not the exit locked
     */
    public boolean isExitLocked(int direction) {
        return lockedExits[direction];
    }

}
