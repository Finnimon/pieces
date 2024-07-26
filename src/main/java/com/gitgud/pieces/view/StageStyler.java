package com.gitgud.pieces.view;

import com.gitgud.pieces.model.activeGame.GameLoader;
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

import java.util.ArrayList;


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
        MenuItem menuItem = new Menu("Save");
        
        menuItem.setOnAction(e ->
                             {
                                 e.consume();
                                 System.out.println("save");
                                 new GameLoader().save();
                             });
        
        ArrayList<Menu> menuArrayList = new ArrayList<>();
        Menu menu = new Menu("File");
        menu.getItems().add(menuItem);
        menuBar.getMenus().add(menu);
        return menuBar;
    }
    
    
    private static MenuItem createMenuItem(String text, EventHandler<ActionEvent> actionEventEventHandler)
    {
        MenuItem menuItem = new MenuItem();
        menuItem.setText(text);
        menuItem.setOnAction(actionEventEventHandler);
        return menuItem;
    }
    
    //    private static MenuImp
}
