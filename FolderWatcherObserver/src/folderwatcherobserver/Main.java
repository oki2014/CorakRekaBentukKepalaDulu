/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderwatcherobserver;

import folderwatcherobserver.FolderWatcher.FolderWatcher;
import folderwatcherobserver.FolderWatcher.LaptopHelmi;
import folderwatcherobserver.FolderWatcher.LaptopKasim;
import folderwatcherobserver.FolderWatcher.LaptopZamry;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;

/**
 *
 * @author MohdSyauqiSalehen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Initialize folder to be watched. Currently need to changed manually in source code.
        Path folder = Paths.get("C:\\Users\\user\\Desktop\\FolderWatcher");
        FolderWatcher folderWatcher = new FolderWatcher(folder);
        LaptopKasim kasim = new LaptopKasim(folderWatcher);
        LaptopHelmi helmi = new LaptopHelmi(folderWatcher);
        LaptopZamry zamry = new LaptopZamry(folderWatcher);
        folderWatcher.startWatching();
    }
}
