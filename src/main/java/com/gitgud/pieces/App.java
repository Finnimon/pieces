package com.gitgud.pieces;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.graph.Graph;
import com.gitgud.graph.WeightedEdge;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.gameObjects.AssetLocator;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.gameObjects.agents.PlayerAgent;
import com.gitgud.pieces.model.gameObjects.interactable.collectibles.FightAgentCollectable;
import com.gitgud.pieces.testing.TestStuff;
import com.gitgud.pieces.utility.gsonSerialization.*;
import com.gitgud.pieces.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


public class App extends Application
{
    
    
    public static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\sprites\\agents\\blackAndWhite\\black_king.png";
    
    
    public static final String APP_TITLE = "Pieces";
    
    
    public static void main(String[] args) throws IOException
    {
        launch();
        
        //        jsonTestFinnK(args);
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
        gsonBuilder.registerTypeAdapter(FightAgentCollectable.class, new FightAgentCollectableSerializer());
        gsonBuilder.registerTypeAdapter(GridMap.class, new GridMapSerializer());
        Gson gson = gsonBuilder.create();
        
        FightAgent fighter = gson.fromJson(jsonObject, FightAgent.class);
        Spell spell = gson.fromJson(jsonSpell, Spell.class);
        String spellJson = gson.toJson(spell, Spell.class);
        FightAgentCollectable fightAgentCollectable = gson.fromJson(jsonObject, FightAgentCollectable.class);
        String fightAgentCollectableToJson = gson.toJson(fightAgentCollectable, FightAgentCollectable.class);
        GridMap<?> map = gson.fromJson(spellJson, GridMap.class);
        
        System.out.println(spell.getType());
        System.out.println(spell.description());
        System.out.println(spell.name());
        System.out.println(spell.getModifier());
        System.out.println(spell.doesSucceed());
        System.out.println(spell.getManaCost());
        System.out.println(spell.getSpriteFilePath());
        System.out.println(spell.getSpriteUrl());
        System.out.println(spell.getClass());
        
        GridMap<GridMappable> mapTest = TestStuff.getTestMap(5, 5);
        
        System.out.println(gson.toJson(mapTest, GridMap.class));
        
        String fighterJson = gson.toJson(fighter, FightAgent.class);
        
        FileOutputStream writableFile = new FileOutputStream("src/main/resources/com/gitgud/gameObjectTypes/test.json");
        writableFile.write(fightAgentCollectableToJson.getBytes());
        writableFile.close();
    }
    
    
    public static void finnTest(Stage stage)
    {
        addTestGridMapRenderToStage(stage);
        
    }
    
    
    private static void addTestGridMapRenderToStage(Stage stage)
    {
        GridMap<GridMappable> testMap = TestStuff.getTestMap(12, 12);
        
        GridMapRender<GridMappable> gridMapRender = new GridMapRender<>(testMap, 90);
        
        stage.setResizable(false);
        
        Group group = new Group();
        PlayerAgent playerAgent = new PlayerAgent();
        gridMapRender.addGridMappable(playerAgent, testMap.getVertex(0));
        ScrollPane scrollPane = new ScrollPane(gridMapRender);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        group.getChildren().add(scrollPane);
        Scene scene = new Scene(group);
        
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        gridMapRender.relocateGridMappable(playerAgent, testMap.getVertex(1));
        
        group.getChildren().clear();
        scrollPane = new ScrollPane(new GridMapRender<GridMappable>(TestStuff.getTestMap(12, 12), 40));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        group.getChildren().add(scrollPane);
        stage.hide();
        
        stage.sizeToScene();
        
        //        gridMapRender.removeGridMappable(playerAgent);
    }
    
    
    private static void printGridMap(Graph<Tile, GridMappable, WeightedEdge<Tile>> testMap)
    {
        for (Tile tile : testMap.verticeSet())
        {
            System.out.println("x" + tile.getX() + "y" + tile.getY() + "\n\r");
            
            System.out.println(testMap.getEdges(tile).size() + "\n\r");
            for (WeightedEdge<Tile> edge : testMap.getEdges(tile))
            {
                System.out.println(
                        "x" + edge.getTo().getX() + "y" + edge.getTo().getY() + "\tlength\t" + edge.getWeight());
            }
            System.out.println("---------");
        }
    }
    
    
    private void initialize(Stage stage)
    {
        StageController.initialize(stage);
        setTitleAndIcon();
    }
    
    
    private void setTitleAndIcon()
    {
        Stage stage = StageController.getInstance().getStage();
        stage.setTitle(APP_TITLE);
        try
        {
            stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
        }
        catch (MalformedURLException ignored) //can be ignored because worst case there is no Icon
        {
        }
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        initialize(stage);
        finnTest(stage);
        //        delfiMain(stage);
        stage.show();
    }
}