package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 * Edited by Ryan on 11/6/2018
 */

public class FoundReport {
    /*FoundReport attributes*/
    private Item myItem;
    private String myTime;//report's timestamp
    private String myLocation;//location found
    private String myLeftLoc;//location where item was left/can be retrieved

    /*Constructor*/
    public FoundReport(String name, String type, String desc, String time, String loc, String leftLoc) {
        this.myItem = new Item(name, type, desc);
        this.myTime = time;
        this.myLocation = loc;
        this.myLeftLoc = leftLoc;
    }

    /**                 Accessors               **/
    public String getName() {
        return this.myItem.getName();
    }
    public String getType() {
        return this.myItem.getType();
    }
    public String getDescription() {
        return this.myItem.getDescription();
    }
    public String getTime() {
        return this.myTime;
    }
    public String getFoundLocation() {
        return this.myLocation;
    }
    public String getLeftLocation() {
        return this.myLeftLoc;
    }

    /**              Mutators                   **/
    public void setName(String n) {
        this.myItem.setName(n);
    }
    public void setType(String t) {
        this.myItem.setType(t);
    }
    public void setDescription(String d) {
        this.myItem.setDescription(d);
    }
    public void setTime(String t) {
        this.myTime = t;
    }
    public void setLocation(String l) {
        this.myLocation = l;
    }
    public void setLeftLocation(String l) {
        this.myLeftLoc = l;
    }
}
