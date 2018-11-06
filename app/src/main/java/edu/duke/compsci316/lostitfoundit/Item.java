package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 */

public class Item {
    private String myName;//name of item
    private String myType;//item's type
    private String myDesc;//user's description of item

    /*Constructor*/
    public Item(String name, String type, String desc) {
        this.myName = name;
        this.myType = type;
        this.myDesc = desc;
    }

    /**             Accessors               **/
    public String getName() {
        return this.myName;
    }
    public String getType() {
        return this.myType;
    }
    public String getDescription() {
        return this.myDesc;
    }
    /**             Mutators                **/
    public void setName(String n) {
        this.myName = n;
    }
    public void setType(String t) {
        this.myType = t;
    }
    public void setDescription(String d) {
        this.myDesc = d;
    }
}
