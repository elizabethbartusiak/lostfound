package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 * Edited by Ryan on 11/6/2018
 */

public class FoundReport implements Report {
    /*FoundReport attributes*/
//    private Item myItem;
    private String mName;
    private String mDescription;
    private String mType;
    private String mTime;//report's timestamp
    private String mLocation;//location found
//    private String myLeftLoc;//location where item was left/can be retrieved

    /*Constructors*/
    public FoundReport(){
        //empty constructor, used by FirebaseUI
    }

    public FoundReport(String name, String type, String desc, String time, String loc) {
//        this.myItem = new Item(name, type, desc);
        this.mName = name;
        this.mType = type;
        this.mDescription = desc;
        this.mTime = time;
        this.mLocation = loc;
    }

    /**                 Accessors               **/
//    public String getName() {
//        return this.myItem.getName();
//    }
//    public String getType() {
//        return this.myItem.getType();
//    }
//    public String getDescription() {
//        return this.myItem.getDescription();
//    }
    public String getTime() {
        return this.mTime;
    }
    public String getLocation() {
        return this.mLocation;
    }
    public String getName(){
        return this.mName;
    }
    public String getType(){
        return this.mType;
    }
    public String getDescription(){
        return this.mDescription;
    }
//    public String getLeftLocation() {
//        return this.myLeftLoc;
//    }

    /**              Mutators                   **/
//    public void setName(String n) {
//        this.myItem.setName(n);
//    }
//    public void setType(String t) {
//        this.myItem.setType(t);
//    }
//    public void setDescription(String d) {
//        this.myItem.setDescription(d);
//    }
    public void setName(String n) {
        this.mName = n;
    }
    public void setType(String t) {
        this.mType = t;
    }
    public void setDescription(String d) {
        this.mDescription = d;
    }
    public void setTime(String t) {
        this.mTime = t;
    }
    public void setLocation(String l) {
        this.mLocation = l;
    }

//    public void setLeftLocation(String l) {
//        this.myLeftLoc = l;
//    }






}
