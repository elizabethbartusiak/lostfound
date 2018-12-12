package edu.duke.compsci316.lostitfoundit;

import java.io.Serializable;

/**
 * Created by Elizabeth on 11/2/2018.
 * Edited by Ryan on 11/6/2018
 */

public class FoundReport implements Report, Serializable {
    /*FoundReport attributes*/
    private String mName;
    private String mType;
    private String mDescription;
    private String mLocation;//location found
    private String mImageName;
    private String mContact;

    /*Constructor*/
    public FoundReport(String name, String type, String desc, String loc, String img, String contact) {
        this.mName = name;
        this.mType = type;
        this.mDescription = desc;
        this.mLocation = loc;
        this.mImageName = img;
        this.mContact = contact;
    }

    /*Constructors*/
    public FoundReport(){
        //empty constructor, used by FirebaseUI
    }

    /**                 Accessors               **/
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
    public String getImageName() {
        return this.mImageName;
    }
    public String getContact() { return this.mContact; }
//    public String getLeftLocation() {
//        return this.myLeftLoc;
//    }

    /**              Mutators                   **/
    public void setName(String n) {
        this.mName = n;
    }
    public void setType(String t) {
        this.mType = t;
    }
    public void setDescription(String d) {
        this.mDescription = d;
    }
    public void setLocation(String l) {
        this.mLocation = l;
    }
    public void setImageName(String str) {this.mImageName = str;}
    public void setContact(String c) { this.mContact = c;}
}
