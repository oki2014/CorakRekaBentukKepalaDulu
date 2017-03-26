/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderwatcherobserver.FolderWatcher;

import java.io.File;
import java.util.Observable;

/**
 *
 * @author MohdSyauqiSalehen
 */
public class FolderData extends Observable{

    private File folder;
    public FolderData() {
    }
    
    public void folderActivityDetected(){
        setChanged();
        notifyObservers();
    }
    
    public void setFolder(String path){
        this.folder = new File(path);
    }
    
    public void setFolder(File path){
        this.folder = path;
    }
    
    public File getFolder(){
        return this.folder;
    }
    
    public String getFolderName(){
        return this.folder.getName();
    }
    
}
