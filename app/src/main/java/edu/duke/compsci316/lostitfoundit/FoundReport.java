package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 */

public class FoundReport {
    /*FoundReport attributes*/
    private String myName;//name of item
    private String myType;//item's type
    private String myDesc;//user's description of item
    private String myTime;//report's timestamp
    private String myLocation;//location found
    private String myLeftLoc;//location where item was left/can be retrieved

    /*Constructor*/
    public FoundReport(String name, String type, String desc, String time, String loc, String leftLoc) {
        this.myName = name;
        this.myType = type;
        this.myDesc = desc;
        this.myTime = time;
        this.myLocation = loc;
        this.myLeftLoc = leftLoc;
    }

    /**                 Accessors               **/
    public String getName() {
        return this.myName;
    }
    public String getType() {
        return this.myType;
    }
    public String getDescription() {
        return this.myDesc;
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
        this.myName = n;
    }
    public void setType(String t) {
        this.myType = t;
    }
    public void setDescription(String d) {
        this.myDesc = d;
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
