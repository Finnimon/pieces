package com.gitgud.pieces;


import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.pieces.utility.Strings;
import com.gitgud.pieces.utility.services.AssetParser;
import com.gitgud.pieces.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.util.Arrays;


public class App extends Application
{


    public static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\sprites\\agents\\blackAndWhite\\black_king.png";

    public static final String APP_TITLE = "Pieces";


    private static void setTitleAndIcon(Stage stage) throws MalformedURLException
    {
        stage.setTitle(APP_TITLE);
        stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException
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
    
    
    
    public static void jsonTestFinnK (String[] args) throws IOException, ClassNotFoundException
    {
        // FightAgent --- OK
        // Modifier --- OK
        // Spell --- OK

        // Insert resources here
        JsonArray fightAgentTypes = AssetParser.parseJsonArray(Strings.FIGHT_AGENT_TYPES);
        // Insert Json Objects here

        // Register type adapters
        GsonBuilder gsonBuilder = new GsonBuilder();
        // insert type adapters here

        Gson gson = gsonBuilder.create();

        // Deserialization

        // Serialization

        // Test output


        // Write Json file
        FileOutputStream writableFile = new FileOutputStream("src/main/resources/com/gitgud/gameObjectTypes/test.json");
        // Insert Json objects to write here
        // writableFile.write(spellToJson.getBytes());
        writableFile.close();
    }
}