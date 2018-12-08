/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import static com.sun.org.apache.regexp.internal.RETest.test;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import static jdk.nashorn.internal.objects.NativeRegExp.test;

/**
 * REST Web Service
 *
 * @author antonio
 */
@Path("files")
@RequestScoped
public class FilesResource {
    com.mycompany.InterfaceConfiguration configurationsSingleton = lookupConfigurationsSingleton();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FilesResource
     */
    public FilesResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.FilesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
        //TODO return proper representation object
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(configurationsSingleton.getFiles());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(FilesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "[]";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("{fileName: [a-zA-Z][a-zA-Z_0-9]*}")
    public String getXml(@PathParam("fileName") String fileName) throws IOException {
       
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(configurationsSingleton.getFile(fileName).getValue(), Object.class);	
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        //return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(configurationsSingleton.getFile(fileName).getValue());
        //return mapper.writeValueAsString(configurationsSingleton.getFile(fileName).getValue());
       
    }
    

    /**
     * PUT method for updating or creating an instance of FilesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{fileName: [a-zA-Z][a-zA-Z_0-9]*}")
    public String put(String content, @PathParam("fileName") String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Configuration c = mapper.readValue(content, Configuration.class);
        //mapper.readTree(content.toString())
        
        configurationsSingleton.updateFile(fileName, mapper.readTree(content).toString());
        return "Entry Aggiornata";
        
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{fileName: [a-zA-Z][a-zA-Z_0-9]*}")
    public String post(String content, @PathParam("fileName") String fileName) {
        //ObjectMapper mapper = new ObjectMapper();
        //Configuration c = mapper.readValue(content, Configuration.class);
        
        //if(isJSONValid(c.getValue())==true)//{
        
        
        //return "Inserimento non riuscito"+isJSONValid(c.getValue());
        
        configurationsSingleton.addFile(fileName, content);
        return content +"\nInserimento Effettuato";
        //}
         //return "Inserimento non riuscitoNO";
        
    }
    
    
  
    
    @DELETE
    @Path("{fileName: [a-zA-Z][a-zA-Z_0-9]*}") 
    public void delete(@PathParam("fileName") String fileName) {
        configurationsSingleton.deleteFile((fileName));
        
    }
    
    
    
     private com.mycompany.InterfaceConfiguration lookupConfigurationsSingleton() {
        try {
            javax.naming.Context c = new InitialContext();
            return (com.mycompany.InterfaceConfiguration) c.lookup("java:global/prova2-ear/prova2-ejb-1.0-SNAPSHOT/ConfigurationsSingleton!com.mycompany.InterfaceConfiguration");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    } 
    
    
    
}
