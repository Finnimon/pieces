package com.gitgud.engine.view;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;


public class GridMapRender<GridMappableType extends GridMappable> extends Group implements Render<GridMap<GridMappableType>>
{
    
    
    public static final double HIGHLIGHT_OPACITY = 0.3;
    
    
    private final int tileSize;
    
    
    private final Group tileGroup;
    
    
    private final HashMap<Tile, Rectangle> tileRectangles = new HashMap<>();
    
    
    private final Group gridMappableGroup;
    
    
    private final HashMap<GridMappableType, Rectangle> gridMappableRectangles = new HashMap<>();
    
    
    private final Group highLightGroup = new Group();
    
    
    private final HashMap<Tile, Rectangle> highLightRectangles = new HashMap<>();
    
    
    private GridMapRender(GridMap<GridMappableType> gridMap, int tileSize)
    {
        super();
        this.tileSize = tileSize;
        
        tileGroup = new Group();
        gridMappableGroup = new Group();
        
        
        render(gridMap);
        getChildren().add(tileGroup);
        getChildren().add(gridMappableGroup);
        getChildren().add(highLightGroup);
    }
    
    
    private void renderTiles(GridMap<GridMappableType> gridMap)
    {
        ObservableList<Node> children = this.tileGroup.getChildren();
        HashMap<Tile, ImagePattern> gridMapTiles = SpriteHelper.loadImagePatterns(gridMap.verticeSet());
        
        for (Tile tile : gridMap.verticeSet())
        {
            ImagePattern currentPattern = gridMapTiles.get(tile);
            
            Rectangle rectangle = SpriteHelper.createRectangle(currentPattern, tile, tileSize);
            tileRectangles.put(tile, rectangle);
            
            children.add(rectangle);
        }
    }
    
    
    private void renderGridMappables(GridMap<GridMappableType> gridMap)
    {
        ObservableList<Node> children = this.gridMappableGroup.getChildren();
        HashMap<GridMappableType, ImagePattern> gridMappablePatterns = SpriteHelper.loadImagePatterns(
                gridMap.elements());
        
        children.clear();
        
        for (Tile tile : gridMap.verticeSet())
        {
            GridMappableType gridMappable = gridMap.get(tile);
            
            if (gridMappable == null)
            {
                continue;
            }
            
            ImagePattern currentPattern = gridMappablePatterns.get(gridMappable);
            
            Rectangle rectangle = SpriteHelper.createRectangle(currentPattern, tile, tileSize);
            
            children.add(rectangle);
        }
        
    }
    
    
    @Override
    public void render(GridMap<GridMappableType> gridMap)
    {
        renderTiles(gridMap);
        renderGridMappables(gridMap);
    }
    
    
    public void relocateGridMappable(GridMappableType gridMappable, Tile next)
    {
        Rectangle rectangle = gridMappableRectangles.get(gridMappable);
        rectangle.setX(next.getX() * tileSize);
        rectangle.setY(next.getY() * tileSize);
    }
    
    
    public void removeGridMappable(GridMappableType gridMappable)
    {
        Rectangle rectangle = gridMappableRectangles.get(gridMappable);
        gridMappableGroup.getChildren().remove(rectangle);
        gridMappableRectangles.remove(gridMappable);
    }
    
    
    public void addGridMappable(GridMappableType gridMappable, Tile tile)
    {
        ImagePattern pattern = new ImagePattern(new Image(gridMappable.getSpriteUrl()));
        
        
        Rectangle rectangle = SpriteHelper.createRectangle(pattern, tile, tileSize);
        gridMappableGroup.getChildren().add(rectangle);
        gridMappableRectangles.put(gridMappable, rectangle);
    }
    
    
    public void addHighLight(Tile tile, Color color)
    {
        Rectangle rectangle = SpriteHelper.createRectangle(color, tile, tileSize);
        rectangle.setOpacity(HIGHLIGHT_OPACITY);
        
        highLightGroup.getChildren().add(rectangle);
        highLightRectangles.put(tile, rectangle);
    }
    
    
    public void removeHighLight(Tile tile)
    {
        Rectangle rectangle = highLightRectangles.get(tile);
        highLightGroup.getChildren().remove(rectangle);
        highLightRectangles.remove(tile);
    }
    
    
    public void removeAllHighLights()
    {
        highLightGroup.getChildren().clear();
        highLightRectangles.clear();
    }
}
