package edu.duke.compsci316.lostitfoundit;

/**
 * Created by Elizabeth on 11/2/2018.
 * Edited by Ryan on 11/15/2018.
 */

public interface Report {
    /**                 Accessors               **/
    public String getName();
    public String getType();
    public String getDescription();
    public String getLocation();

    /**              Mutators                   **/
    public void setName(String n);
    public void setType(String t);
    public void setDescription(String d);
    public void setLocation(String l);
}
