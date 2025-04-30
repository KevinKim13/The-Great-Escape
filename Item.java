/**
 * Class: Item.java
 * @author Will Krajcirik
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: April 27, 2025
 * 
 * Purpose: Initializes an Item with a name, type, and chance.
 */

public class Item {
    // Instance Variables -------------------------------------------
    private String name;
    private String type;
    private double chance;


    // Consructors ---------------------------------------------------
    /**
     * Initializes an Item with a name, type, and chance.
     * @param name the item's name
     * @param type the item's type (Key or Weapon)
     * @param chance the chance to defeat the kidnapper
     */
    public Item(String name, String type, double chance) {
        this.name = name;
        this.type = type;
        this.chance = chance;
    }


    // Methods ------------------------------------------------------
    /**
     * Returns the item's name.
     * @return the item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the item's type.
     * @return the item's type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the item's chance to defeat the kidnapper.
     * @return the item's chance
     */
    public double getChance() {
        return chance;
    }
}
