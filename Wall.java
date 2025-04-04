/**
 * Initializes a wall with a description, name
 * and interactableArray.
 * @author Kevin Kim
 */
import java.util.*;

public class Wall {

    // Instance Variables

    private String name;
    private String desc;
    private ArrayList<String> interactableArray;

    // Constructors

    /**
     * Default Constructor.
     */
    public Wall() {
        this.name = "";
        this.desc = "";
        this.interactableArray = null;
    }
    
    /**
     * Constructor with parameters supplied for all instance variables.
     * 
     * @param name the name of the wall.
     * @param desc the description of the wall.
     * @param interactableArray a list of all interactable things on that wall. 
     */
    public Wall(String name, String desc, ArrayList<String> interactableArray) {
        this.name = name;
        this.desc = desc;
        this.interactableArray = interactableArray;
    }

    // Methods

    /**
     * Returns the name of the wall.
     * 
     * @return the name of the wall.
     */
    public String getWallName() {
        return name;
    }

    /**
     * Returns the description of the wall.
     * 
     * @return the description of the wall.
     */
    public String getWallDesc() {
        return desc;
    }

    /**
     * Returns an array of interactables for the wall.
     * 
     * @return an array of interactbles for the wall.
     */
    public ArrayList<String> getWallInters() {
        return interactableArray;
    }

    
}