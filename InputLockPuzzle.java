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

public class InputLockPuzzle extends Puzzle {

    // Instance Variables -------------------------------------------
    private String solution;


    // Constructors -------------------------------------------------
    /**
     * Initializes an InputLockPuzzle with a prompt and solution; 
     * isSolved is set to false by default. 
     * @param prompt the puzzle's prompt which tells them what to enter
     * @param solution the correct answer to solve the puzzle
     */
    public InputLockPuzzle(String prompt, String solution) {
        super(prompt, false);
        this.solution = solution.toUpperCase();
    }

    /**
     * Performs an attempt to solve the puzzle and returns isSolved.
     * @param input the player's attempted solution
     * @return whether or not the puzzle was solved
     */
    @Override
    public boolean attempt(String input) {
        if (input.toUpperCase().equals(solution)) {
            this.isSolved = true;
        }
        return isSolved;
    }
}
