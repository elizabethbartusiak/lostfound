package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 * Edited by Ryan on 11/6/2018
 */

public class FoundReport implements Report {
    /*FoundReport attributes*/
    private String myName;
    private String myType;
    private String myDescription;
    private String myTime;//report's timestamp
    private String myLocation;//location found
    private String myImgName;

    /*Constructor*/
    public FoundReport(String name, String type, String desc, String time, String loc, String img) {
        this.myName = name;
        this.myType = type;
        this.myDescription = desc;
        this.myTime = time;
        this.myLocation = loc;
        this.myImgName = img;
    }

    /*Constructors*/
    public FoundReport(){
        //empty constructor, used by FirebaseUI
    }

    /**                 Accessors               **/
    public String getTime() {
        return this.myTime;
    }
    public String getLocation() {
        return this.myLocation;
    }
    public String getName(){
        return this.myName;
    }
    public String getType(){
        return this.myType;
    }
    public String getDescription(){
        return this.myDescription;
    }
    public String getImageName() {
        return this.myImgName;
    }
//    public String getLeftLocation() {
//        return this.myLeftLoc;
//    }

    /**              Mutators                   **/
    public void setName(String n) {
        this.myName = n;
    }
    public void setType(String t) {
        this.myType = t;
    }
    public void setDescription(String d) {
        this.myDescription = d;
    }
    public void setTime(String t) {
        this.myTime = t;
    }
    public void setLocation(String l) {
        this.myLocation = l;
    }
    public void setImageName(String str) {this.myImgName = str;}
}
