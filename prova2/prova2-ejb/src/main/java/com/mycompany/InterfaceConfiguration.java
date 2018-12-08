/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author antonio
 */

@Local
public interface InterfaceConfiguration {



    public void addFile(String filename, String value);

    public Configuration getFile(/*int id*/String filename);

    public List<Configuration> getFiles();

    public void updateFile(/*int id,*/ String filename, String value);

    public void deleteFile(/*int id*/String filename);
    

}
