/**
 * Class: Puzzle.java
 * @author Joseph Edwards
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 27, 2025
 * 
 * Purpose: Initializes a Puzzle with a prompt.
 */

public abstract class Puzzle {

    // Instance Variables -------------------------------------------
    private String prompt;


    // Constructors -------------------------------------------------
    /**
     * Initializes a puzzle with a prompt.
     * @param prompt the puzzle's prompt
     */
    public Puzzle(String prompt) {
        this.prompt = prompt;
    }


    // Methods ------------------------------------------------------
    /**
     * Returns the puzzle's prompt.
     * @return the puzzle's prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Allows the player to make an attempt at solving the puzzle.
     * @param input the player's attempted solution
     * @return whether or not the puzzle was solved
     */
    public abstract boolean attempt(String input);
}
