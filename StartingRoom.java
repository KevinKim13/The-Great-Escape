/**
 * Class: StartingRoom.java
 * @author Kevin Kim
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: April 30, 2025
 * 
 * Purpose: Initializes a the first room with a description, exits, lockedExits,
 *          and walls.
 */

public class StartingRoom {
    public static Room initRoom() {
        Room room1 = new Room("You are in a ramshackle jail cell. To the north is a brick wall.\nTo the east is the cell door.\nOn the south wall, laying ont the gorund is an old stained mattress and a plastic tray with a rusty metal spoon.");
        Item key = new Item("Makeshift Key", "Key", 0.1);
        Item spoon = new Item("Rusty Metal Spoon", "Utensil", 0.15);
        Wall[] r1Walls = new Wall[4];
        r1Walls[0] = new Wall("It's a brick wall with a loose brick.", "The brick needs something thin to pry it out.");
        r1Walls[0].setItem(key);
        r1Walls[1] = new Wall("It's a cell door with a rusty keyhole.", "It requires a key.");
        r1Walls[2] = new Wall("It's a wall with a mattress laying on the ground next to a plastic tray and rusty metal spoon", "The spoon might be useful.");
        r1Walls[2].setItem(spoon);
        room1.setWalls(r1Walls);
 
        return room1;
    }
}
