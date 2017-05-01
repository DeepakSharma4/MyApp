package com.example.hp.myapp;

/**
 * Created by hp on 4/9/2017.
 */

public class student {
    String uniqueid;
    String stdname;
    String stdcollege;

   public student(){  //to read values

   }
//Constructor intialize values of string delecared

    public student(String uniqueid, String stdname, String stdcollege) {
        this.uniqueid = uniqueid;
        this.stdname = stdname;
        this.stdcollege = stdcollege;
    }

    //Getter Funcation to get values

    public String getUniqueid() {
        return uniqueid;
    }

    public String getStdname() {
        return stdname;
    }

    public String getStdcollege() {
        return stdcollege;
    }



}
