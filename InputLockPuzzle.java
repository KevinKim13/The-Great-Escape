/**
 * Class: InputLockPuzzle.java
 * @author Joseph Edwards
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 27, 2025
 * 
 * Purpose: Initializes an InputLockPuzzle with a prompt, solution,
 *          and keeps track of its status with isSolved.
 */

public class InputLockPuzzle {

    // Instance Variables -------------------------------------------
    private String prompt;
    private String solution;
    private boolean isSolved;


    // Constructors -------------------------------------------------
    /**
     * Initializes an InputLockPuzzle with a prompt and solution; 
     * isSolved is set to false by default. 
     * @param prompt the puzzle's prompt which tells them what to enter
     * @param solution the correct answer to solve the puzzle
     */
    public InputLockPuzzle(String prompt, String solution) {
        this.prompt = prompt;
        this.solution = solution.toUpperCase();
        this.isSolved = false;
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
     * Performs an attempt to solve the puzzle and returns isSolved.
     * @param input the player's attempted solution
     * @return whether or not the puzzle was solved
     */
    public boolean attempt(String input) {
        if (input.toUpperCase().equals(solution)) {
            isSolved = true;
        }
        return isSolved;
    }

    /**
     * Returns whether or not the puzzle is solved.
     * @return isSolved
     */
    public boolean isSolved() {
        return isSolved;
    }
}
