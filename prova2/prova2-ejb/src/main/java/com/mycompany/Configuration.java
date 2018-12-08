/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author antonio
 */
public class Configuration {
    //private int id;
    private String filename;
    private String value; //dovrebbe essere JSON
    
    
    
    
    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } */

    public String getFileName() {
        return filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return value;
    }

    public String setValue(String value) {
        return this.value;
    }

    

    public Configuration(/*int id,*/ String filename, String value) {
        //this.id = id;
        this.filename = filename;
        this.value = value;
    }
    

   /* public Configuration(
            @JsonProperty("name") String filename, 
            @JsonProperty("value") String value){
        this(-1, filename, value);
    }*/
    
    
}
