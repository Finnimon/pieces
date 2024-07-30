package com.gitgud.pieces.view;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.game.Game;
import com.gitgud.pieces.model.game.GameState;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.function.Predicate;


/**
 * <p>This class is used to style the stage.
 * <p>It adds the menu bar and the inner pane to the stage.
 */
public class StageStyler
{
    private StageStyler()
    {
    }
    
    
    public static void style(Stage stage)
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
    
    
    private static MenuBar createMenu()
    {
        //todo!!!!
        MenuBar menuBar = new MenuBar();
        ObservableList<Menu> menus = menuBar.getMenus();
        menus.add(newGameMenu());
        menus.add(settingsMenu());
        menus.add(saveMenu());
        menus.add(loadMenu());
        return menuBar;
    }
    
    
    private static Menu newGameMenu()
    {
        Menu menu = new Menu("New Game");
        
        MenuItem newGame = createMenuItem("New Game", e ->
        {
            //todo            Game.New.start();
        });
        
        menu.getItems().add(newGame);
        return menu;
    }
    
    
    private static Menu settingsMenu()
    {
        Menu menu = new Menu("Settings");
        //// TODO: 29.07.2024  
        return menu;
    }
    
    
    private static Menu saveMenu()
    {
        Menu menu = new Menu("Save");
        
        Predicate<ActionEvent> predicate = gameStateChecker(GameState.NOT_LOADED);
        
        MenuItem save = createMenuItem("Save", e ->
        {
            if (!predicate.test(e))
            {
                return;
            }
            Game.Saver.save();
        });
        
        MenuItem saveAs = createMenuItem("Save As", e ->
        {
            if (!predicate.test(e))
            {
            }
            //todo Game.Saver.saveAs();
        });
        
        menu.getItems().addAll(save, saveAs);
        
        return menu;
    }
    
    
    private static Menu loadMenu()
    {
        Menu menu = new Menu("Load");
        
        Predicate<ActionEvent> predicate = gameStateChecker(GameState.NOT_LOADED);
        
        MenuItem load = createMenuItem("Load", e ->
        {
            if (!predicate.test(e))
            {
                Game.Saver.save();
            }
            //todo Game.Loader.load();
        });
        
        menu.getItems().add(load);
        return menu;
    }
    
    
    private static MenuItem createMenuItem(String text, EventHandler<ActionEvent> actionEventEventHandler)
    {
        MenuItem menuItem = new MenuItem();
        menuItem.setText(text);
        menuItem.setOnAction(actionEventEventHandler);
        return menuItem;
    }
    
    
    private static Predicate<ActionEvent> gameStateChecker(GameState gameState)
    {
        return e ->
        {
            e.consume();
            return ActiveGameController.getGameState() == gameState;
        };
    }
    
    //    private static MenuImp
}
