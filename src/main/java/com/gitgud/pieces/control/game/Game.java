package com.gitgud.pieces.control.game;

import com.gitgud.engine.control.Startable;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.*;
import com.gitgud.pieces.model.game.ActiveGame;
import com.gitgud.pieces.model.game.GameState;
import com.gitgud.pieces.model.player.Difficulty;
import com.gitgud.pieces.utility.JsonParser;
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


/**
 * <p>Static structural control class for the game.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 25.07.2024
 * @Version: 3.0
 */
public class Game
{
    /**
     * Private Constructor to stop instantiation.
     */
    private Game()
    {
        throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
    }
    
    
    public static class Flow
    {
        /**
         * Private Constructor to stop instantiation.
         */
        private Flow()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
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
        
        
        private static Startable getNextSceneController()
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
        /**
         * Private Constructor to stop instantiation.
         */
        private Loader()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        public static List<File> getLoadableSaveFiles()
        {
            File[] saveFiles = Utility.SAVE_FILE_DIR.listFiles();
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
                                       .filter(name -> !name.equals(Utility.NEW_GAME_FILENAME))
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
            File saveFile = Utility.getSaveFile(saveFileName);
            return JsonParser.getInstance().deserializeJsonFile(saveFile, ActiveGame.class);
        }
    }
    
    
    public static class Saver
    {
        /**
         * Private Constructor to stop instantiation.
         */
        private Saver()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
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
            
            File saveFile = Utility.getSaveFile(saveFileName);
            
            clearFileAndWriteString(saveFile, json);
        }
        
        
        private static String activeGameToString(ActiveGame activeGame, String newPlayerName)
        {
            String playerName = activeGame.getPlayer().name();
            Gson gson = JsonParser.getInstance().getGson();
            JsonObject jsonElement = gson.toJsonTree(activeGame).getAsJsonObject();
            Utility.changePlayerName(jsonElement, playerName);
            return jsonElement.toString();
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
        /**
         * Private Constructor to stop instantiation.
         */
        private New()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        public static void start(String playerName, Difficulty difficulty)
        {
            if (playerName.equals(Utility.NEW_GAME_FILENAME))
            {
                throw new IllegalArgumentException("playerName cannot be " + Utility.NEW_GAME_FILENAME);
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
            ActiveGame activeGame = Loader.loadActiveGame(Utility.NEW_GAME_FILENAME);
            Gson gson = JsonParser.getInstance().getGson();
            JsonObject jsonObject = gson.toJsonTree(activeGame).getAsJsonObject();
            JsonObject playerJsonObject = jsonObject.get(Utility.PLAYER).getAsJsonObject();
            Utility.changePlayerName(jsonObject, playerName);
            changeDifficulty(difficulty, playerJsonObject, gson);
            File saveFile = Utility.getSaveFile(playerName);
            Saver.clearFileAndWriteString(saveFile, jsonObject.toString());
        }
        
        
        private static void changeDifficulty(Difficulty difficulty, JsonObject playerJsonObject, Gson gson)
        {
            String DIFFICULTY = "difficulty";
            playerJsonObject.remove(DIFFICULTY);
            playerJsonObject.addProperty(DIFFICULTY, gson.toJson(difficulty));
        }
    }
    
    
    private static class Utility
    {
        private static final String NEW_GAME_FILENAME = "NEW_GAME";
        
        
        private static final File SAVE_FILE_DIR = new File(
                "src\\main\\resources\\com\\gitgud\\pieces\\model\\activeGame\\saveFilesDir");
        
        
        private static final String PLAYER = "player";
        
        
        private static final String NAME = "name";
        
        
        /**
         * Private Constructor to stop instantiation.
         */
        private Utility()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        private static void changePlayerName(JsonObject jsonElement, String playerName)
        {
            JsonObject player = jsonElement.get(PLAYER).getAsJsonObject();
            player.remove(NAME);
            player.addProperty(NAME, playerName);
        }
        
        
        private static File getSaveFile(String fileName)
        {
            return new File(SAVE_FILE_DIR.getPath() + Strings.FILE_SEPERATOR + fileName + JsonParser.DOT_JSON);
        }
    }
}
