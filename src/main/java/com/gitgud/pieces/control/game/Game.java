package com.gitgud.pieces.control.game;

import com.gitgud.engine.control.Startable;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.*;
import com.gitgud.pieces.model.game.ActiveGame;
import com.gitgud.pieces.model.game.GameState;
import com.gitgud.pieces.model.player.Difficulty;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Game
{
    public static final String NEW_GAME_FILENAME = "NEW_GAME";
    
    
    private static final String SAVE_FILES_DIR =
            "src/main/resources/com/gitgud/pieces/model/activeGame/saveFilesDir";//todo
    
    
    private static final File SAVE_FILE_DIR = new File(SAVE_FILES_DIR);
    
    
    private static final String PLAYER = "player";
    
    
    private static final String NAME = "name";
    
    
    private Game()
    {
    }
    
    
    private static void changePlayerName(JsonObject jsonElement, String playerName)
    {
        JsonObject player = jsonElement.get(PLAYER).getAsJsonObject();
        player.remove(NAME);
        player.addProperty(NAME, playerName);
    }
    
    
    public static class Flow
    {
        private Flow()
        {
        }
        
        
        public static void initializeGame(Stage stage)
        {
            setStageToLoadScreen();
            JsonParser.getInstance();
            StageController.initialize(stage);
            
            showNextScene();
        }
        
        
        public static void setStageToLoadScreen()
        {
            Label label = new Label("Loading...");
            label.setFont(new Font(100));
            StackPane pane = new StackPane(label);
            StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);
            StageController stageController = StageController.getInstance();
            stageController.setRoot(pane);
            stageController.show();
        }
        
        
        public static void showNextScene()
        {
            setStageToLoadScreen();
            Task<Startable> task = nextSceneControllerTask();
            Executors.newSingleThreadExecutor().execute(task);
        }
        
        
        private static Task<Startable> nextSceneControllerTask()
        {
            return new Task<>()
            {
                @Override
                protected Startable call()
                {
                    return getNextSceneController();
                }
                
                
                @Override
                protected void succeeded()
                {
                    getValue().start();
                }
            };
        }
        
        
        public static Startable getNextSceneController()
        {
            GameState gameState = ActiveGameController.getGameState();
            return switch (gameState)
            {
                case NOT_LOADED -> throw new RuntimeException("Not implemented");
                case CITY -> new CityController(getActiveGame().getCity());
                case MISSION -> new MissionController(getActiveGame().getMission());
                case MISSION_FIGHT -> new FightController(getActiveGame().getFight());
                case ARENA_FIGHT -> throw new RuntimeException();
            };
        }
        
        
        private static ActiveGame getActiveGame()
        {
            return ActiveGameController.getInstance().get();
        }
    }
    
    
    public static class Loader
    {
        private Loader()
        {
        }
        
        
        public static List<File> getLoadableSaveFiles()
        {
            File[] saveFiles = SAVE_FILE_DIR.listFiles();
            //saveFiles is always a readable Directory as Ensured by the Constructor
            return Arrays.stream(saveFiles)
                         .filter(file -> file.getName().endsWith(JsonParser.DOT_JSON) || file.canWrite())
                         .toList();
        }
        
        
        public static List<String> getLoadableSaveFileNames(File[] saveFiles)
        {
            List<String> names = Arrays.stream(saveFiles)
                                       .filter(File::canRead)
                                       .map(x -> x.getName()
                                                  .substring(0, x.getName().length() - JsonParser.DOT_JSON.length()))
                                       .filter(name -> !name.equals(NEW_GAME_FILENAME))
                                       .collect(Collectors.toList());
            return names;
        }
        
        
        public static void load(String saveFileName)
        {
            Executors.newSingleThreadExecutor().execute(loadGameTask(saveFileName));
        }
        
        
        private static Task<ActiveGame> loadGameTask(String saveFileName)
        {
            return new Task<>()
            {
                @Override
                protected ActiveGame call()
                {
                    return loadActiveGame(saveFileName);
                }
                
                
                @Override
                protected void succeeded()
                {
                    ActiveGameController.reset();
                    ActiveGameController.initialize(getValue());
                    Flow.showNextScene();
                }
            };
        }
        
        
        private static ActiveGame loadActiveGame(String saveFileName)
        {
            File saveFile = Saver.getSaveFile(saveFileName);
            return JsonParser.getInstance().parseJson(saveFile, ActiveGame.class);
        }
    }
    
    
    public static class Saver
    {
        private Saver()
        {
        }
        
        
        public static void save()
        {
            saveAs(ActiveGameController.getInstance().get().getPlayer().name());
        }
        
        
        public static void saveAs(String saveFileName)
        {
            Runnable runnable = () ->
            {
                saveUnderFileName(saveFileName);
            };
            Executors.newSingleThreadExecutor().execute(runnable);
        }
        
        
        private static void saveUnderFileName(String saveFileName)
        {
            if (ActiveGameController.getGameState() == GameState.NOT_LOADED)
            {
                throw new IllegalStateException("Game not loaded");
            }
            ActiveGame activeGame = ActiveGameController.getInstance().get();
            String json = activeGameToString(activeGame, saveFileName);
            
            File saveFile = getSaveFile(saveFileName);
            
            clearFileAndWriteString(saveFile, json);
        }
        
        
        private static String activeGameToString(ActiveGame activeGame, String newPlayerName)
        {
            String playerName = activeGame.getPlayer().name();
            Gson gson = JsonParser.getInstance().getGson();
            JsonObject jsonElement = gson.toJsonTree(activeGame).getAsJsonObject();
            changePlayerName(jsonElement, playerName);
            return jsonElement.toString();
        }
        
        
        private static File getSaveFile(String fileName)
        {
            return new File(SAVE_FILES_DIR + Strings.FILE_SEPERATOR + fileName + JsonParser.DOT_JSON);
        }
        
        
        private static void clearFileAndWriteString(File saveFile, String json)
        {
            saveFile.delete();
            try
            {
                saveFile.createNewFile();
                FileWriter writer = new FileWriter(saveFile);
                writer.write(json);
                writer.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    
    
    public static class New
    {
        public static void start(String playerName, Difficulty difficulty)
        {
            if (playerName.equals(NEW_GAME_FILENAME))
            {
                throw new IllegalArgumentException("playerName cannot be " + NEW_GAME_FILENAME);
            }
            
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(newGameTask(playerName, difficulty));
            executorService.shutdown();
        }
        
        
        private static Task<String> newGameTask(String playerName, Difficulty difficulty)
        {
            return new Task<String>()
            {
                @Override
                protected String call() throws Exception
                {
                    prepareNewGameSaveFile(playerName, difficulty);
                    return playerName;
                }
                
                
                @Override
                protected void succeeded()
                {
                    Loader.load(playerName);
                }
            };
        }
        
        
        private static void prepareNewGameSaveFile(String playerName, Difficulty difficulty)
        {
            ActiveGame activeGame = Loader.loadActiveGame(NEW_GAME_FILENAME);
            Gson gson = JsonParser.getInstance().getGson();
            JsonObject jsonObject = gson.toJsonTree(activeGame).getAsJsonObject();
            JsonObject playerJsonObject = jsonObject.get(PLAYER).getAsJsonObject();
            changePlayerName(jsonObject, playerName);
            changeDifficulty(difficulty, playerJsonObject, gson);
            File saveFile = Saver.getSaveFile(playerName);
            Saver.clearFileAndWriteString(saveFile, jsonObject.toString());
        }
        
        
        private static void changeDifficulty(Difficulty difficulty, JsonObject playerJsonObject, Gson gson)
        {
            String DIFFICULTY = "difficulty";
            playerJsonObject.remove(DIFFICULTY);
            playerJsonObject.addProperty(DIFFICULTY, gson.toJson(difficulty));
        }
    }
    
    
}
