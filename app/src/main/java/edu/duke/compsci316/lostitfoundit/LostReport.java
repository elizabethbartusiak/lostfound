package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 * Edited by Ryan on 11/15/2018.
 * currently deprecated - no longer necessary for current workflow, but not necessarily useless for scaling
 */

public class LostReport implements Report {
    /*FoundReport attributes*/
    private Item myItem;
    private String myTime;//report's timestamp
    private String myLocation;//location found

    /*Constructors*/
    public LostReport (String type, String location) {
        myItem = new Item(null,type,null);
        myTime = "";
        myLocation = location;
    }
    public LostReport(String name, String type, String desc, String time, String loc) {
        this.myItem = new Item(name, type, desc);
        this.myTime = time;
        this.myLocation = loc;
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
    public String getLocation() {
        return this.myLocation;
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
}
