package com.gitgud.pieces;

import com.gitgud.model.fight.Spell;
import com.gitgud.model.fight.SpellType;
import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.Sprite;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.gsonSerialization.*;
import com.gitgud.utility.modification.fightAgent.FightAgentAttackModifier;
import com.gitgud.utility.modification.fightAgent.FightAgentModifier;
import com.gitgud.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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
    
    
    
    public static void jsonTestFinnK(String[] args) throws IOException
    {
        JsonArray jsonArray = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES);
        JsonArray jsonSpells = AssetParser.parseJsonArray(AssetLocator.SPELL_TYPES);
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        JsonObject jsonSpell = jsonSpells.get(0).getAsJsonObject();
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FightAgent.class, new FightAgentDeserializer()).create();
        gsonBuilder.registerTypeAdapter(FightAgent.class, new FightAgentSerializer());
        gsonBuilder.registerTypeAdapter(Spell.class, new SpellDeserializer());
        gsonBuilder.registerTypeAdapter(Spell.class, new SpellSerializer());
        Gson gson = gsonBuilder.create();

        FightAgent fighter = gson.fromJson(jsonObject, FightAgent.class);
        Spell spell = gson.fromJson(jsonSpell, Spell.class);
        String spellJson = gson.toJson(spell, Spell.class);

        System.out.println(spell.getType());
        System.out.println(spell.getDescription());
        System.out.println(spell.getName());
        System.out.println(spell.getModifier());
        System.out.println(spell.doesSucceed());
        System.out.println(spell.getManaCost());
        System.out.println(spell.getSpriteFilePath());
        System.out.println(spell.getSpriteUrl());
        System.out.println(spell.getClass());

        String fighterJson = gson.toJson(fighter, FightAgent.class);

        FileOutputStream writableFile = new FileOutputStream("src/main/java/com/gitgud/utility/gsonSerialization/test.json");
        writableFile.write(spellJson.getBytes());
        writableFile.close();



    }
}