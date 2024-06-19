package com.gitgud.engine.view;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;


public class GridMapRender<GridMappableType extends GridMappable> extends Group implements Render<GridMap<GridMappableType>>
{
    private final int tileSize;
    
    
    private final GridMap<GridMappableType> gridMap;
    
    
    private final Group tileGroup;
    
    
    private final Group gridMappableGroup;
    
    private final HashMap<GridMappableType, ImagePattern> gridMappablePatterns;
    
    
    private GridMapRender(GridMap<GridMappableType> gridMap,int tileSize)
    {
        super();
        this.gridMap = gridMap;
        this.tileSize = tileSize;
        
        tileGroup = new Group();
        gridMappableGroup = new Group();
        
        gridMappablePatterns=SpriteHelper.loadImagePattern(gridMap.elements());
        
        initialRender();
        
        getChildren().add(tileGroup);
        getChildren().add(gridMappableGroup);
    }
    
    
    private void renderTiles()
    {
        ObservableList<Node> children = this.tileGroup.getChildren();
        HashMap<Tile, ImagePattern> gridMapTiles =SpriteHelper.loadImagePattern(gridMap.verticeSet());
        
        for (Tile tile : gridMap.verticeSet())
        {
            ImagePattern currentPattern = gridMapTiles.get(tile);
            Rectangle rectangle = SpriteHelper.createRectangle(currentPattern, tile, tileSize);
            children.add(rectangle);
        }
    }
    
    
    private void renderGridMappables()
    {
        ObservableList<Node> children = this.gridMappableGroup.getChildren();
        
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
    public void initialRender()
    {
        renderTiles();
        renderGridMappables();
    }
    
    
    @Override
    public void reRender()
    {
        renderGridMappables();
    }
    
}
