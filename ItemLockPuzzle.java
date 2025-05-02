/**
 * Class: ItemLockPuzzle.java
 * @author Kevin Kim
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: May 2, 2025
 * 
 * Purpose: Initializes an ItemLockPuzzle with a prompt, solution,
 *          and keeps track of its status with isSolved.
 */

public class ItemLockPuzzle extends Puzzle {

    // Instance Variables -------------------------------------------
    private Item solution;


    // Constructors -------------------------------------------------
    /**
     * Initializes an ItemLockPuzzle with a prompt and solution; 
     * isSolved is set to false by default. 
     * @param prompt the puzzle's prompt which tells them what to enter
     * @param solution the correct answer to solve the puzzle
     */
    public ItemLockPuzzle(String prompt, Item solution) {
        super(prompt, false);
        this.solution = solution;
    }

    /**
     * Performs an attempt to solve the puzzle and returns isSolved.
     * @param input the player's attempted solution
     * @return whether or not the puzzle was solved
     */
    public boolean attempt(Item input) {
        if (input.equals(solution)) {
            this.isSolved = true;
        }
        return isSolved;
    }

    /**
     * Purposefully fails if players attempts string solution with item puzzle.
     * @param input the player's attempted solution
     * @return whether or not the puzzle was solved
     */
    @Override
    public boolean attempt(String input) {
        return false;
    }
}
