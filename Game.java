/**
 * Class: Game.java
 * @author Landen Burns
 * @version 1.0
 * Course: CSE 201 Spring 2025
 * Written: April 5, 2025
 * 
 * Purpose: A Prototype of our game that demonstrates the use of our classes.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Starting Menu
        System.out.println("--- Welcome to The Great Escape! ---");
        System.out.print("Would you like to play? (y/n): ");
        String usr_ansr = in.next().toLowerCase();

        if (usr_ansr.equals("y")) {

            // Creating Game Entities
            Room cell = new Room();
            Item cellKey = new Item("The key for the jail cell.", "Key Item", 4.3);
            cell.setDescription("You are in a dark musky jail cell with a key on the ground.");
            ArrayList<Item> cellInventory = new ArrayList<>();
            ArrayList<Item> mainCharIn = new ArrayList<>();
            cellInventory.add(cellKey);
            cell.setInventory(cellInventory);
            Player mainChar = new Player(mainCharIn, cell, 0);
            

            // Game Start
            System.out.println("You have been kidnapped and awaken in a basement with jail cells.");
            System.out.println(mainChar.getRoom().getMainDescription());
            System.out.println("Would you like to pick up the key? (y/n): ");
            String usr_choice_one = in.next().toLowerCase();
            if (usr_choice_one.equals("y")) {
                mainChar.addItem(mainChar.getRoom().getRoomInventory().getFirst());
                System.out.println("The \"cell key\" was added to your inventory.");
            } else {
                System.out.println("You don't pick up the key. Instead you flush it down the toilet and are trapped forever.\nYOU LOSE!");
                System.exit(0);
            }

            // If key is picked up...
            System.out.println("To open this cell and successfully escape you need a key.");
            System.out.println("Will you use the \"cell key\" to open the cell? (y/n): ");
            String usr_choice_two = in.next();
            if (usr_choice_two.equals("y")) {
                System.out.println("You use the \"cell key\" to open the cell. You successfully escape the kidapper!\nYOU WIN!");
                System.exit(0);
            } else {
                System.out.println("You choose to not open the cell and instead obediently wait for your kidnapper.\nYOU LOSE!");
                System.exit(0);
            }

        } else {
            System.out.println("Goodbye!");
            System.exit(0);
        }

        in.close();
    }

}
