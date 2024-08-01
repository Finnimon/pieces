package com.gitgud.pieces.view;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.model.game.GameState;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.MalformedURLException;
import java.util.function.Predicate;


/**
 * <p>This class is used to style the stage.
 * <p>It adds the menu bar and the inner pane to the stage.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 15.07.2024
 * @Version: 0.2
 */
public class StageStyler
{
    private static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\agents" +
                                            "\\monochrome\\black_king.png";
    
    
    private static final String APP_TITLE = "Pieces";
    
    
    /**
     * Private Constructor to prevent instantiation.
     */
    private StageStyler()
    {
    }
    
    
    /**
     * Styles the stage according to needs of StageController.
     *
     * @param stage The stage to style.
     */
    public static void style(Stage stage)
    {
        initialize(stage);
        createSceneGraph(stage);
    }
    
    
    private static void initialize(Stage stage)
    {
        stage.initStyle(StageStyle.DECORATED);
        stage.setFullScreen(true);
        setTitleAndIcon(stage);
    }
    
    
    /**
     * Creates the scene graph in the stage.
     *
     * @param stage The stage to create the scene graph in.
     */
    private static void createSceneGraph(Stage stage)
    {
        StackPane stackPane = new StackPane();
        MenuBar menuBar = createMenu();
        StackPane innerPane = new StackPane();
        stackPane.getChildren().addAll(menuBar, innerPane);
        StackPane.setAlignment(menuBar, Pos.TOP_CENTER);
        innerPane.translateYProperty().bind(menuBar.heightProperty());
        innerPane.getChildren().add(new Pane());
        stage.setScene(new Scene((stackPane)));
    }
    
    
    private static void setTitleAndIcon(Stage stage)
    {
        stage.setTitle(APP_TITLE);
        try
        {
            stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
        }
        catch (MalformedURLException ignore)
        {
            //do nothing only sets the icon, so you can ignore the exception and just move on
        }
    }
    
    
    /**
     * Creates the classic menu for the application.
     *
     * @return The created menu.
     */
    private static MenuBar createMenu()
    {
        MenuBar menuBar = new MenuBar();
        ObservableList<Menu> menus = menuBar.getMenus();
        menus.add(newGameMenu());
        menus.add(settingsMenu());
        menus.add(saveMenu());
        menus.add(loadMenu());
        return menuBar;
    }
    
    
    /**
     * Creates the menu for starting a new game.
     *
     * @return The created menu.
     */
    private static Menu newGameMenu()
    {
        Menu menu = new Menu("New Game");
        
        MenuItem newGame = createMenuItem("New Game", e ->
        {
            e.consume();
            //todo show new Screen for Difficulty and Name Selection
            //Game.New.start();
        });
        
        menu.getItems().add(newGame);
        return menu;
    }
    
    
    /**
     * Creates a menu for settings.
     *
     * @return The created menu.
     */
    private static Menu settingsMenu()
    {
        Menu menu = new Menu("GameSettings");
        //todo
        return menu;
    }
    
    
    /**
     * Creates a menu for saving the game.
     *
     * @return The created menu.
     */
    private static Menu saveMenu()
    {
        Menu menu = new Menu("Save");
        
        Predicate<ActionEvent> predicate = gameStateChecker(GameState.NOT_LOADED);
        
        MenuItem save = createMenuItem("Save", e ->
        {
            e.consume();
            if (predicate.test(e))
            {
                //todo show message
                return;
            }
            //todo show message
            Game.Saver.save();
        });
        
        MenuItem saveAs = createMenuItem("Save As", e ->
        {
            if (predicate.test(e))
            {
                //todo show message
            }
            //todo Game.Saver.saveAs();
        });
        
        menu.getItems().addAll(save, saveAs);
        
        return menu;
    }
    
    
    /**
     * Creates a menu for loading the game.
     *
     * @return The created menu.
     */
    private static Menu loadMenu()
    {
        Menu menu = new Menu("Load");
        
        Predicate<ActionEvent> predicate = gameStateChecker(GameState.NOT_LOADED);
        
        MenuItem load = createMenuItem("Load", e ->
        {
            e.consume();
            if (predicate.test(e))
            {
                //todo show message want to save before?, then show Game.Loader.getSaveFileNames();....
            }
            //create context menu for offering Game.Loader.getSaveFileNames(); and calling Game.Loader.load
            // (selectedName);
            //todo Game.Loader.load();
        });
        
        menu.getItems().add(load);
        return menu;
    }
    
    
    /**
     * Creates a menu item.
     *
     * @param text                    Text of the menu item.
     * @param actionEventEventHandler The event handler for the menu item.
     * @return The created menu item.
     */
    private static MenuItem createMenuItem(String text, EventHandler<ActionEvent> actionEventEventHandler)
    {
        MenuItem menuItem = new MenuItem();
        menuItem.setText(text);
        menuItem.setOnAction(actionEventEventHandler);
        return menuItem;
    }
    
    
    /**
     * Predicate for the game state.
     *
     * @param gameState the game state to be checked
     * @return the predicate
     */
    private static Predicate<ActionEvent> gameStateChecker(GameState gameState)
    {
        return e ->
        {
            return ActiveGameController.getGameState() == gameState;
        };
    }
}
