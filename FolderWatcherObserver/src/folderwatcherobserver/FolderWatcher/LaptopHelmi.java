/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderwatcherobserver.FolderWatcher;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author MohdSyauqiSalehen
 */
public class LaptopHelmi implements Observer, DisplayMessage {

    Observable observable;
    private String machineUserName = "Helmi";
    private String message = "";

    public LaptopHelmi(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof FolderWatcher) {
            FolderWatcher watcher = (FolderWatcher) observable;
            message = watcher.getMessage();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println(machineUserName + ": \"" + message + "\"....aik? Sape pnye keje nih?");
    }

}
