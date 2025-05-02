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
    protected String prompt;
    protected boolean isSolved;


    // Constructors -------------------------------------------------
    /**
     * Initializes a puzzle with a prompt.
     * @param prompt the puzzle's prompt
     */
    public Puzzle(String prompt, boolean isSolved) {
        this.prompt = prompt;
        this.isSolved = isSolved;
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

    /**
     * Checks if the puzzle was already solved or not.
     * @return whether puzzle is already solved.
     */
    public boolean isSolved() {
        return isSolved;
    }
}
