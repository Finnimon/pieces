package com.gitgud.pieces;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.action.FromToAction;
import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.graph.Edge;
import com.gitgud.graph.Vertex;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.gameObjects.AssetLocator;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.testing.TestStuff;
import com.gitgud.pieces.utility.gsonSerialization.*;
import com.gitgud.pieces.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.DepthTest;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;


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
        System.out.println(spell.description());
        System.out.println(spell.name());
        System.out.println(spell.getModifier());
        System.out.println(spell.doesSucceed());
        System.out.println(spell.getManaCost());
        System.out.println(spell.getSpriteFilePath());
        System.out.println(spell.getSpriteUrl());
        System.out.println(spell.getClass());
        
        String fighterJson = gson.toJson(fighter, FightAgent.class);
        
        FileOutputStream writableFile = new FileOutputStream(
                "src/main/java/com/gitgud/utility/gsonSerialization/test.json");
        writableFile.write(spellJson.getBytes());
        writableFile.close();
        
        
    }
    
    
    public static void finnTest()
    {
        GridMap<GridMappable> testMap = TestStuff.getTestMap(4, 5);
        Tile problemChild=testMap.getVertex(5);
        
        for (Tile tile : testMap.verticeSet())
        {
            System.out.println("x"+tile.getX()+"y"+ tile.getY()+"\n\r");
            
            for (Edge<Tile> edge : testMap.getEdges(tile))
            {
                System.out.println("x"+edge.getTo().getX()+ "y"+edge.getTo().getY());
            }
            System.out.println("---------");
        }
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        setTitleAndIcon(stage);
        finnTest();
        delfiMain(stage);
        stage.show();
    }
}