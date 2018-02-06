package com.ahmed.ateeqsrpicar;

/**
 * Created by ateeq-ahmed on 8/2/16.
 */
public class DataClass {

    //private variables
    int id;
    String ipHost;
    int port;
    int lPorta;



    // Empty constructor
    public DataClass(){

    }
    // constructor
    public DataClass(String ipHost,int lPorta, int port){

        this.lPorta = lPorta;
        this.ipHost = ipHost;
        this.port = port;

    }

    public int getID()
    {
        return this.id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    // getting ID
    public int getlPorta(){
        return this.lPorta;
    }

    // setting id
    public void setlPorta(int lPorta){
        this.lPorta = lPorta;
    }

    public int getPort(){
        return this.port;
    }

    // setting id
    public void setPort(int port){
        this.port = port;
    }

    // getting name
    public String getIpHost(){
        return this.ipHost;
    }

    // setting name
    public void setIpHost(String ipHost){
        this.ipHost = ipHost;
    }


}