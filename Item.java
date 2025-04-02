/**
 * Initalizes an Item object with a description, itemType, and
 * itemSuccess.
 * @author Landen Burns
 */

public class Item {

    // Instance Variables ------------------------------------------
    private String description;
    private String itemType;
    private double itemSuccess;


    // Constructors ------------------------------------------------

    /**
     * Workhorse Constructor. Initializes an Item with all instance
     * variables specified.
     * @param description a detailed description of the Item
     * @param type the Item's type
     * @param success the Item's probability to defeat the kidnapper
     */
    public Item(String description, String type, double success) {
        this.description = description;
        this.itemType = type;
        this.itemSuccess = success;
    }

    /**
     * Empty Constructor. Initializes an Item with all instance
     * variables set to zero or null values.
     */
    public Item() {
        this.description = "";
        this.itemType = "";
        this.itemSuccess = 0.0;
    }


    // Methods -----------------------------------------------------
    
    /**
     * Returns a detailed description of the Item.
     * @return the Item's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a the Item's type.
     * @return the Item's type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Returns the Item's success chance to defeat kidnapper.
     * @return the Item's percent probability
     */
    public double getItemSuccess() {
        return itemSuccess;
    }

}