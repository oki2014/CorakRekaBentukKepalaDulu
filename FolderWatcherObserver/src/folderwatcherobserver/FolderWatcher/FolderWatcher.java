/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folderwatcherobserver.FolderWatcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MohdSyauqiSalehen
 */
public class FolderWatcher extends Observable {

    private final WatchService watcher;
    private final Path folder;
    private String message;

    public FolderWatcher(Path folder) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.folder = folder;
        this.message = "UNKNOWN MESSAGE";
    }

    public void startWatching() throws IOException {
        checkFolderExistence();
        folder.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        while (true) {
            WatchKey key = null;
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(FolderWatcher.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                Kind kind = event.kind();

                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

//                System.out.println(kind.name() + ": " + fileName);
                if (kind == OVERFLOW) {
                    message = "What happen " + fileName + "???!!!";
                    continue;
                } else if (kind == ENTRY_CREATE) {
                    message = fileName + " has been created.";
                    folderActivityDetected();
                } else if (kind == ENTRY_DELETE) {
                    message = fileName + " has been deleted.";
                    folderActivityDetected();
                } else if (kind == ENTRY_MODIFY) {
                    message = fileName + " has been modified.";
                    folderActivityDetected();
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                System.out.println(folder.getFileName() + " directory has been deleted. Terminating the program..");
                break;
            }
        }
    }

    private void checkFolderExistence() {
        if (!Files.exists(folder)) {
            System.out.println(folder.toFile().getAbsolutePath() + " does not exist. Creating folder...");
            if (folder.toFile().mkdirs()) {
                System.out.println(folder.toFile().getAbsolutePath() + " created.");
            }
        }
    }

    public void folderActivityDetected() {
        System.out.println("=================================================================================");
        System.out.println("MACHINE NOTIFICATION: " + getMessage());
        System.out.println("=================================================================================");
        setChanged();
        notifyObservers();
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
