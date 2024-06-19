package com.gitgud.pieces;

import com.gitgud.model.fight.Spell;
import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.Sprite;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.gsonSerialization.*;
import com.gitgud.utility.modification.Modifier;
import com.gitgud.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileOutputStream;
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
    
    public static void main(String[] args) throws IOException
    {
        // launch();

        jsonTestFinnK(args);
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
    
    
    
    public static void jsonTestFinnK (String[] args) throws IOException
    {
        JsonArray jsonFightAgents = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES);
        JsonArray jsonSpells = AssetParser.parseJsonArray(AssetLocator.SPELL_TYPES);
        JsonArray jsonModifiers = AssetParser.parseJsonArray(AssetLocator.MODIFIER_TYPES);
        JsonObject jsonFightAgent = jsonFightAgents.get(0).getAsJsonObject();
        JsonObject jsonSpell = jsonSpells.get(0).getAsJsonObject();
        JsonObject jsonModifier = jsonModifiers.get(1).getAsJsonObject();
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FightAgent.class, new FightAgentSerializer());
        gsonBuilder.registerTypeAdapter(Spell.class, new SpellSerializer());
        gsonBuilder.registerTypeAdapter(Modifier.class, new ModifierSerializer());
        Gson gson = gsonBuilder.create();


        Modifier<?> modifier = gson.fromJson(jsonModifier, Modifier.class);
        String modifierToJson = gson.toJson(modifier, Modifier.class);
        System.out.println(modifier);


        FileOutputStream writableFile = new FileOutputStream("src/main/java/com/gitgud/utility/gsonSerialization/test.json");
        writableFile.write(modifierToJson.getBytes());
        writableFile.close();
    }
}