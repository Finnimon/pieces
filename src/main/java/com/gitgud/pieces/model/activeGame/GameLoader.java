package com.gitgud.pieces.model.activeGame;

import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.GameFlow;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.concurrent.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class GameLoader
{
    
    
    public static final String NEW_GAME_FILENAME = "NEW_GAME.json";
    
    
    private static final String SAVE_FILES_DIR = "src/main/resources/com/gitgud/pieces/model/activeGame/saveFilesDir";//todo
    
    
    private static final File SAVE_FILE_DIR = new File(SAVE_FILES_DIR);
    
    
    public GameLoader()
    {
        if (SAVE_FILE_DIR.isDirectory()) return;
        
        throw new RuntimeException("Could not find Save File Directory.\n\rPlease restore your copy of the game.");
    }
    
    
    public List<File> getLoadableSaveFiles()
    {
        File[] saveFiles = SAVE_FILE_DIR.listFiles();
        //saveFiles is always a readable Directory as Ensured by the Constructor
        return Arrays.stream(saveFiles)
                     .filter(file -> file.getName().endsWith(JsonParser.DOT_JSON) || file.canWrite())
                     .toList();
    }
    
    
    public List<String> getLoadableSaveFileNames(File[] saveFiles)
    {
        List<String> names = Arrays.stream(saveFiles)
                                   .filter(File::canRead)
                                   .map(File::getName)
                                   .filter(name -> !name.equals(NEW_GAME_FILENAME))
                                   .map(name -> name.substring(0, name.length() - JsonParser.DOT_JSON.length()))
                                   .collect(Collectors.toList());
        
        return names;
    }
    
    
    public void loadSaveFile(String saveFileName)
    {
        Executors.newSingleThreadExecutor().execute(loadGameTask(saveFileName));
    }
    
    
    private Task<ActiveGame> loadGameTask(String saveFileName)
    {
        return new Task<>()
        {
            @Override
            protected ActiveGame call() throws Exception
            {
                return loadActiveGame(saveFileName);
            }
            
            
            @Override
            protected void succeeded()
            {
                ActiveGameController.reset();
                ActiveGameController.initialize(getValue());
                GameFlow.showNextScene();
            }
        };
    }
    
    
    private ActiveGame loadActiveGame(String saveFileName)
    {
        File saveFile = getSaveFile(saveFileName);
        return JsonParser.getInstance().parseJson(saveFile, ActiveGame.class);
    }
    
    
    public void save()
    {
        saveAs(ActiveGameController.getInstance().get().getPlayer().name());
    }
    
    
    public void saveAs(String saveFileName)
    {
        Runnable runnable = () ->
        {
            saveUnderFileName(saveFileName);
        };
        Executors.newSingleThreadExecutor().execute(runnable);
    }
    
    
    private void saveUnderFileName(String saveFileName)
    {
        if (ActiveGameController.getGameState() == GameState.NOT_LOADED)
        {
            throw new IllegalStateException("Game not loaded");
        }
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        
        File saveFile = getSaveFile(saveFileName);
        saveFile.delete();
        
        
        String json = activeGameToString(activeGame, saveFileName);
        //        jsonElement.get()
        FileWriter writer;
        try
        {
            saveFile.createNewFile();
            writer = new FileWriter(saveFile);
            writer.write(json);
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    
    private String activeGameToString(ActiveGame activeGame, String newPlayerName)
    {
        String playerName = activeGame.getPlayer().name();
        Gson gson = JsonParser.getInstance().getGson();
        JsonObject jsonElement = gson.toJsonTree(activeGame).getAsJsonObject();
        changePlayerName(jsonElement, playerName);
        return jsonElement.toString();
    }
    
    
    private void changePlayerName(JsonObject jsonElement, String playerName)
    {
        JsonObject player = jsonElement.get("player").getAsJsonObject();
        player.remove("name");
        player.addProperty("name", playerName);
    }
    
    
    private File getSaveFile(String fileName)
    {
        return new File(SAVE_FILES_DIR + Strings.FILE_SEPERATOR + fileName + JsonParser.DOT_JSON);
    }
}
