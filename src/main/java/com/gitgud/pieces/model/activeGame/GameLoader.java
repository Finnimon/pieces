package com.gitgud.pieces.model.activeGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameLoader
{
    
    
    public static final String NEW_GAME_FILENAME = "NEW_GAME.json";
    
    
    private static final String SAVE_FILES_DIR = "";//todo
    
    
    private static final File SAVE_FILE_DIR = new File(SAVE_FILES_DIR);
    
    
    public static final String JSON = ".json";
    
    
    public GameLoader()
    {
        if (SAVE_FILE_DIR.isDirectory()) return;
        
        throw new RuntimeException("Could not find Save File Directory.\n\rPlease restore your copy of the game.");
    }
    
    
    public List<File> getLoadableSaveFiles()
    {
        File[] saveFiles = SAVE_FILE_DIR.listFiles();
        //saveFiles is always a readable Directory as Ensured by the Constructor
        return Arrays.stream(saveFiles).filter(file -> file.getName().endsWith(JSON) || file.canWrite()).toList();
    }
    
    
    public List<String> getLoadableSaveFileNames(File[] saveFiles)
    {
        List<String> names = new ArrayList<>();
        
        for (File saveFile : saveFiles)
        {
            if (!saveFile.canRead()) continue;
            
            String name = saveFile.getName();
            
            if (name.equals(NEW_GAME_FILENAME)) continue;
            
            names.add(name.substring(0, name.length() - JSON.length()));
        }
        
        
        return names;
    }
}
