/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author antonio
 */
@Singleton
@Startup
public class ConfigurationsSingleton implements InterfaceConfiguration{
    //private int lastId;
    private Map<String, Configuration> configurations;
   
    @PostConstruct
    void init() {
        //lastId = 0;
        configurations = new ConcurrentHashMap<>();
        
        this.addFile("File1", "{\"version\":1, \"init\":0}");
        this.addFile("File2", "{\"version\":2, \"init\":9}");
        
    }
    

    @Override
    public void addFile(String filename, String value) {
        configurations.put(/*lastId,*/filename, new Configuration(/*lastId++,*/ filename, value));
    }

    @Override
    public Configuration getFile(String filename) {
         if (/*id >= lastId*/ filename==null)
            return null;
        return configurations.get(filename);
    }

    @Override
    public List<Configuration> getFiles() {
        return new ArrayList<>(configurations.values()); 
    }

    @Override
    public void updateFile(/*int id,*/ String filename, String value) {
        configurations.put(/*id,*/filename, new Configuration(/*id,*/ filename, value));
    }

    @Override
    public void deleteFile(/*int id*/String filename) {
          configurations.remove(filename);
    }
    
   
}
