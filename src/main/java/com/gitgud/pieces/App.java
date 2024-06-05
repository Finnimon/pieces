package com.gitgud.pieces;

import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.Sprite;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.gsonSerialization.AssetParser;
import com.gitgud.utility.gsonSerialization.FightAgentDeserializer;
import com.gitgud.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;


public class App extends Application
{
    
    
    public static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\sprites\\agents\\blackAndWhite\\black_king.png";
    
    
    public static final String APP_TITLE = "Pieces";
    
    private static void setTitleAndIcon(Stage stage) throws MalformedURLException
    {
        stage.setTitle(APP_TITLE);
        stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
    }
    
    public static void main(String[] args)
    {
        launch();
    }
    
    
    public static void delfiMain(Stage stage)
    {
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setScene(SMainMenue.createMainMenueScene(stage));
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        setTitleAndIcon(stage);
        delfiMain(stage);
        stage.show();
    }
    
    
    
    public static void jsonTestFinnK(String[] args)
    {
        JsonArray jsonArray = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES);
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        
        Gson gson = new GsonBuilder().registerTypeAdapter(FightAgent.class, new FightAgentDeserializer()).create();
        
        FightAgent fighter = gson.fromJson(jsonObject, FightAgent.class);
        
        System.out.println(fighter.getInitiative());
    }
}