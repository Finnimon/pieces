package com.gitgud.pieces.control;

import com.gitgud.engine.control.Startable;
import com.gitgud.engine.utility.Strings;
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
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * <p>Static structural control class for the game.
 * <p>Houses the main functionality and logic of the game.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 25.07.2024
 * @Version: 3.0
 * @see Flow
 * @see Loader
 * @see Saver
 * @see New
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
    
    
    /**
     * <p>Initialize the game.
     * <p>Controls the flow of the game and Scene Changes.
     *
     * @author Finn L.
     * @Owner: Finn L.
     * @Since: 25.07.2024
     * @Version: 3.0
     * @see Startable
     */
    public static class Flow
    {
        /**
         * Private Constructor to stop instantiation.
         */
        private Flow()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        /**
         * <p>Starts the game.
         * <p>Call in {@link com.gitgud.pieces.App#start(Stage)}
         *
         * @param stage The main stage of the application.
         */
        public static void initializeGame(@NotNull Stage stage)
        {
        }
        
        
        /**
         * Creates a Task that:
         * <p>Initializes the singleton instances and styles the {@code stage}.
         * <p>Initializes the {@link StageController} and calls  {@link #showNextScene()} on success.
         *
         * @param stage The main stage of the application.
         * @return The created task.
         */
        private static Task<Stage> initializeTask(@NotNull Stage stage)
        {
            return new Task<>()
            {
                @Override
                protected Stage call()
                {
                    initializeSingletons();
                    return stage;
                }
                
                
                @Override
                protected void succeeded()
                {
                    StageController.initialize(stage);
                    showNextScene();
                }
            };
        }
        
        
        /**
         * Initializes the singleton instances and styles the {@code stage}.
         */
        private static void initializeSingletons()
        {
            JsonParser.getInstance();
            GameSettings.getInstance();
            Translator.getInstance();
        }
        
        
        /**
         * Starts the next scene as determined by the current game state.
         */
        public static void showNextScene()
        {
            Utility.setStageToLoadScreen();
            Task<Startable> task = nextSceneControllerTask();
            Executors.newSingleThreadExecutor().execute(task);
        }
        
        
        /**
         * Creates the Task for starting the next scene.
         *
         * @return The task for starting the next scene.
         */
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
        
        
        /**
         * <p>Gets the next scene controller for the current game state.
         * <p>If called before the game state has changed it will still return a Startable with the same data.
         *
         * @return The next scene controller.
         */
        private static Startable getNextSceneController()
        {
            GameState gameState = ActiveGameController.getGameState();
            return switch (gameState)
            {
                case NOT_LOADED ->
                        throw new RuntimeException("Not implemented");//MainMenuController.getInstance();//todo not
                // yet implemented
                case CITY -> new CityController(getActiveGame().getCity());//todo not yet implemented
                case MISSION -> new MissionController(getActiveGame().getMission());
                case MISSION_FIGHT -> new FightController(getActiveGame().getFight());
                case ARENA_FIGHT -> throw new RuntimeException(); //todo not yet implemented
            };
        }
        
        
        /**
         * Utility Method to avoid having to type getActiveGame().get() multiple times in the above.
         *
         * @return The current ActiveGame.
         * @Precondition: {@link ActiveGameController#getGameState()}!={@link GameState#NOT_LOADED}
         * @Postcondition: The returned ActiveGame is not null and no Exceptions will be thrown.
         */
        private static @NotNull ActiveGame getActiveGame()
        {
            return ActiveGameController.getInstance().get();
        }
    }
    
    
    /**
     * Loads SaveFiles into memory and creates {@link ActiveGame} objects.
     *
     * @author Finn L.
     * @Owner: Finn L.
     * @Since: 25.07.2024
     * @Version: 3.0
     */
    public static class Loader
    {
        /**
         * Private Constructor to stop instantiation.
         */
        private Loader()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        /**
         * <p>Gets all the save files that can be loaded from.
         * <p>Also includes the new game file.
         *
         * @return All the save files that can be loaded from.
         */
        public static List<File> getLoadableSaveFiles()
        {
            File[] saveFiles = Utility.SAVE_FILE_DIR.listFiles();
            return Arrays.stream(saveFiles)
                         .filter(file -> !file.getName().endsWith(JsonParser.DOT_JSON) || !file.canRead())
                         .toList();
        }
        
        
        /**
         * <p>Gets all the save file names that can be loaded from directly.
         * <p>Filters out the new game file.
         *
         * @param saveFiles All the save files that can be loaded from.
         * @return All the save file names that can be loaded from directly.
         * @Precondition: All the save files must be readable and be of the correct format as well as intact SaveFiles.
         * @Postcondition: All returned saveFileNames are names of loadable save files.
         */
        public static List<String> getLoadableSaveFileNames(@NotNull File[] saveFiles)
        {
            List<String> names = Arrays.stream(saveFiles)
                                       .map(x -> x.getName()//format the name and remove suffix
                                                  .substring(0,
                                                             x.getName().length() -
                                                             JsonParser.DOT_JSON.length()))
                                       .filter(name -> !name.equals(Utility.NEW_GAME_FILENAME)) //filter out new game
                                       .collect(Collectors.toList());
            return names;
        }
        
        
        /**
         * Loads a save file into memory.
         *
         * @param saveFileName The name of the save file without suffix in {@link Utility#SAVE_FILE_DIR} to load.
         * @Precondition: The save file must be readable and be of the correct format as well as intact an SaveFile.
         * @Postcondition: The save file will be loaded into memory.
         * <p>The next Scene will be started, depending on the game state of the save file.
         * @see Game.Flow#showNextScene()
         * @see #loadGameTask(String)
         */
        public static void load(@NotNull String saveFileName)
        {
            Utility.setStageToLoadScreen();
            Executors.newSingleThreadExecutor().execute(loadGameTask(saveFileName));
        }
        
        
        /**
         * Creates a Task, that does the following on execution and success:
         * <p>Task for loading the save file into memory.
         * <p>Saves it to the ActiveGameController.
         * <p>The next Scene will be started, depending on the game state of the save file.
         *
         * @param saveFileName The name of the save file without suffix in {@link Utility#SAVE_FILE_DIR} to load.
         * @return The task for loading the save file into memory.
         * @see #loadActiveGame(String)
         */
        private static Task<ActiveGame> loadGameTask(@NotNull String saveFileName)
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
        
        
        /**
         * Parses the save file into an ActiveGame object.
         *
         * @param saveFileName The name of the save file without suffix in {@link Utility#SAVE_FILE_DIR} to load.
         * @return The loaded ActiveGame object.
         */
        private static @NotNull ActiveGame loadActiveGame(@NotNull String saveFileName)
        {
            File saveFile = Utility.getSaveFile(saveFileName);
            return JsonParser.getInstance().deserializeJsonFile(saveFile, ActiveGame.class);
        }
    }
    
    
    /**
     * Writes SaveFiles from the {@link ActiveGameController}.
     *
     * @author Finn L.
     * @Owner: Finn L.
     * @Since: 25.07.2024
     * @Version: 3.0
     */
    public static class Saver
    {
        /**
         * Private Constructor to stop instantiation.
         */
        private Saver()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        /**
         * Calls {@link #saveAs(String)} with the current player name.
         *
         * @see #saveAs(String)
         */
        public static void save()
        {
            saveAs(ActiveGameController.getInstance().get().getPlayer().name());
        }
        
        
        /**
         * Overwrites/creates a save file with the current player name on a worker thread.
         *
         * @param saveFileName The intended name of the save file without suffix in {@link Utility#SAVE_FILE_DIR} to
         *                     overwrite/create.
         * @Precondition: {@link ActiveGameController#getGameState()}!={@link GameState#NOT_LOADED}
         * @Postcondition: The save file will be overwritten/created. No Exception will be thrown.
         * @see #saveUnderFileName(String)
         */
        public static void saveAs(@NotNull String saveFileName)
        {
            Runnable runnable = () ->
            {
                saveUnderFileName(saveFileName);
            };
            Executors.newSingleThreadExecutor().execute(runnable);
        }
        
        
        /**
         * Overwrites/creates a save file with the current player name.
         *
         * @param saveFileName The intended name of the save file without suffix in {@link Utility#SAVE_FILE_DIR} to
         *                     overwrite/create.
         * @throws IllegalStateException If the Precondition is not met.
         * @Precondition: {@link ActiveGameController#getGameState()}!={@link GameState#NOT_LOADED}
         * @Postcondition: The save file will be overwritten/created. No Exception will be thrown.
         */
        private static void saveUnderFileName(@NotNull String saveFileName)
        {
            if (ActiveGameController.getGameState() == GameState.NOT_LOADED)
            {
                throw new IllegalStateException("Game not loaded");
            }
            ActiveGame activeGame = ActiveGameController.getInstance().get();
            String json = activeGameToString(activeGame, saveFileName);
            
            File saveFile = Utility.getSaveFile(saveFileName);
            
            JsonParser.getInstance().replaceFileContent(saveFile, json);
        }
        
        
        /**
         * Parses {@code activeGame} into a String and changes the player name to {@code newPlayerName}.
         *
         * @param activeGame    The ActiveGame object to parse.
         * @param newPlayerName The new player name.
         * @return The parsed String of {@code activeGame}.
         */
        private static @NotNull String activeGameToString(@NotNull ActiveGame activeGame, @NotNull String newPlayerName)
        {
            Gson gson = JsonParser.getInstance().getGson();
            JsonObject jsonElement = gson.toJsonTree(activeGame).getAsJsonObject();
            Utility.changePlayerName(jsonElement, newPlayerName);
            return jsonElement.toString();
        }
    }
    
    
    /**
     * Creates a new game based on the new game file.
     *
     * @author Finn L.
     * @Owner: Finn L.
     * @Since: 25.07.2024
     * @Version: 3.0
     */
    public static class New
    {
        /**
         * Private Constructor to stop instantiation.
         */
        private New()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        /**
         * Starts a new game.
         *
         * @param playerName The name of the new player.
         * @param difficulty The difficulty of the new game.
         * @throws IllegalArgumentException If the precondition is not met.
         * @Precondition: The player name cannot be {@link Utility#NEW_GAME_FILENAME}.
         * @Postcondition: A new game will be started without throwing any Exceptions.
         */
        public static void start(@NotNull String playerName, @NotNull Difficulty difficulty)
        {
            if (playerName.equals(Utility.NEW_GAME_FILENAME))
            {
                throw new IllegalArgumentException("playerName cannot be " + Utility.NEW_GAME_FILENAME);
            }
            
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(newGameTask(playerName, difficulty));
            executorService.shutdown();
        }
        
        
        /**
         * Creates a Task that prepares a new game save file with the given parameters and loads it into memory.
         *
         * @param playerName The name of the new player and save file.
         * @param difficulty The difficulty of the new game.
         * @return The name of the new save file without suffix in {@link Utility#SAVE_FILE_DIR}.
         * @see #prepareNewGameSaveFile(String, Difficulty)
         */
        private static @NotNull Task<String> newGameTask(@NotNull String playerName, @NotNull Difficulty difficulty)
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
        
        
        /**
         * Prepares a new save file with the given parameters.
         *
         * @param playerName The name of the new player and save file.
         * @param difficulty The difficulty of the new game.
         */
        private static void prepareNewGameSaveFile(@NotNull String playerName, @NotNull Difficulty difficulty)
        {
            ActiveGame activeGame = Loader.loadActiveGame(Utility.NEW_GAME_FILENAME);
            Gson gson = JsonParser.getInstance().getGson();
            JsonObject jsonObject = gson.toJsonTree(activeGame).getAsJsonObject();
            JsonObject playerJsonObject = jsonObject.get(Utility.PLAYER_MEMBER_NAME).getAsJsonObject();
            Utility.changePlayerName(jsonObject, playerName);
            changeDifficulty(difficulty, playerJsonObject, gson);
            File saveFile = Utility.getSaveFile(playerName);
            JsonParser.getInstance().replaceFileContent(saveFile, jsonObject.toString());
        }
        
        
        /**
         * Changes the Difficulty of the player in the given JsonObject.
         *
         * @param difficulty       The difficulty of the new game.
         * @param playerJsonObject The JsonObject of the player.
         * @param gson             The Gson instance to serialize the difficulty.
         */
        private static void changeDifficulty(@NotNull Difficulty difficulty, @NotNull JsonObject playerJsonObject,
                                             @NotNull Gson gson)
        {
            playerJsonObject.remove(Utility.DIFFICULTY_MEMBER_NAME);
            playerJsonObject.addProperty(Utility.DIFFICULTY_MEMBER_NAME, gson.toJson(difficulty));
        }
    }
    
    
    /**
     * Offers some Utility Methods that are used across the different {@link Game} classes.
     *
     * @author Finn L.
     * @Owner: Finn L.
     * @Since: 25.07.2024
     * @Version: 3.0
     */
    private static class Utility
    {
        /**
         * Name of the difficulty member in the player member in the save file as jsonTree.
         */
        public static final String DIFFICULTY_MEMBER_NAME = "difficulty";
        
        
        /**
         * Name of the player member in the save file as jsonTree.
         */
        private static final String PLAYER_MEMBER_NAME = "player";
        
        
        /**
         * Name of the name member in the player member in the save file as jsonTree.
         */
        private static final String NAME_MEMBER_NAME = "name";
        
        
        /**
         * File name of the new game resource file.
         */
        private static final String NEW_GAME_FILENAME = "NEW_GAME";
        
        
        /**
         * Directory of the save files and the new game file.
         */
        private static final File SAVE_FILE_DIR = new File(
                "src\\main\\resources\\com\\gitgud\\pieces\\model\\activeGame\\saveFilesDir");
        
        
        /**
         * Private Constructor to stop instantiation.
         */
        private Utility()
        {
            throw new UnsupportedOperationException(this.getClass() + " cannot be instantiated.");
        }
        
        
        /**
         * Changes the players name in the given JsonObject.
         *
         * @param activeGameJson The JsonObject to change.
         *                       <p>A serialized {@link ActiveGame}.
         * @param playerName     The new player name.
         */
        private static void changePlayerName(@NotNull JsonObject activeGameJson, @NotNull String playerName)
        {
            JsonObject player = activeGameJson.get(PLAYER_MEMBER_NAME).getAsJsonObject();
            player.remove(NAME_MEMBER_NAME);
            player.addProperty(NAME_MEMBER_NAME, playerName);
        }
        
        
        /**
         * Returns the save file with the given name and adds the .json and directory prefix.
         *
         * @param fileName The name of the save file without suffix in {@link Utility#SAVE_FILE_DIR}.
         * @return The save file with the given name.
         */
        private static @NotNull File getSaveFile(@NotNull String fileName)
        {
            return new File(SAVE_FILE_DIR.getPath() + Strings.FILE_SEPERATOR + fileName + JsonParser.DOT_JSON);
        }
        
        
        /**
         * Sets the stage to a load screen.
         */
        private static void setStageToLoadScreen()
        {
            Label label = new Label("Loading...");
            label.setFont(new Font(100));
            StackPane pane = new StackPane(label);
            StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);
            StageController stageController = StageController.getInstance();
            stageController.setRoot(pane);
            stageController.show();
        }
    }
}
