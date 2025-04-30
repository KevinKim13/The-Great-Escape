/**
 * Class: Wall.java
 * @author Kevin Kim, Shane Paton
 * @version 2.0
 * Course: CSE 201 Spring 2025
 * Written: April 27, 2025
 * 
 * Purpose: Initializes a wall with a description, inspectText,
 *          puzzle, item, and availableActions.
 */

public class Wall {

    // Instance Variables -------------------------------------------
    private String desc;
    private String inspectText;
    private InputLockPuzzle puzzle;
    private Item item;
    private String[] availableActions;


    // Constructors -------------------------------------------------
    /**
     * Initializes a wall with a desc, inspectText, puzzle, item, and 
     * availableActions.
     * @param desc
     * @param inspectText
     */
    public Wall(String desc, String inspectText) {
        this.desc = desc;
        this.inspectText = inspectText;
        this.puzzle = null;
        this.item = null;
        this.availableActions = new String[0];
    }

    
    // Methods ------------------------------------------------------
    /**
     * Returns the wall's description.
     * @return the wall's description
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Returns the text to be displayed when inspecting the wall.
     * @return the wall's inspectText
     */
    public String getInspectText() {
        return inspectText;
    }

    /**
     * Returns the wall's puzzle.
     * @return the wall's puzzle
     */
    public InputLockPuzzle getPuzzle() {
        return puzzle;
    }

    /**
     * Sets a wall with a puzzle.
     * @param puzzle the wall's puzzle
     */
    public void setPuzzle(InputLockPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Returns whether or not a wall has a puzzle.
     * @return whether or not the wall has a puzzle
     */
    public boolean hasPuzzle() {
        return puzzle != null;
    }

    /**
     * Returns the wall's item.
     * @return the wall's item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets an item to a wall.
     * @param item the wall's new item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the available actions to be performed on the wall.
     * @return the wall's availableActions
     */
    public String[] getAvailableActions() {
        return availableActions;
    }

    /**
     * Sets the available actions that can be performed on the wall.
     * @param actions the wall's new availableActions
     */
    public void setAvailableActions(String[] actions) {
        this.availableActions = actions;
    }
}
