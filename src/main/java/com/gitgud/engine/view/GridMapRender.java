package com.gitgud.engine.view;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;


public class GridMapRender<Type extends GridMappable> extends Group implements Render<GridMap<Type>>
{
    public static final double HIGHLIGHT_OPACITY = 0.2;
    
    
    private final int tileSize;
    
    
    private final Group tileGroup;
    
    
    private final Group gridMappableGroup;
    
    
    private final HashMap<Type, GridMappableRender<Type>> gridMappableRenders = new HashMap<>();
    
    
    private final Group highLightGroup;
    
    
    private final HashMap<Tile, Rectangle> highLightRectangles = new HashMap<>();
    
    
    public GridMapRender(GridMap gridMap, int tileSize)
    {
        super();
        this.tileSize = tileSize;
        
        tileGroup = new Group();
        gridMappableGroup = new Group();
        highLightGroup = new Group();
        
        render(gridMap);
        
        
        getChildren().add(tileGroup);
        getChildren().add(gridMappableGroup);
        getChildren().add(highLightGroup);
    }
    
    
    private void renderTiles(GridMap<Type> gridMap)
    {
        ObservableList<Node> children = this.tileGroup.getChildren();
        HashMap<Tile, ImagePattern> gridMapTiles = SpriteHelper.loadImagePatterns(gridMap.verticeSet());
        
        for (Tile tile : gridMap.verticeSet())
        {
            ImagePattern currentPattern = gridMapTiles.get(tile);
            
            Rectangle rectangle = SpriteHelper.createRectangle(currentPattern, tile, tileSize);
            
            children.add(rectangle);
        }
    }
    
    
    private void renderGridMappables(GridMap<Type> gridMap)
    {
        ObservableList<Node> children = this.gridMappableGroup.getChildren();
        children.clear();
        
        for (Tile tile : gridMap.verticeSet())
        {
            Type gridMappable = gridMap.get(tile);
            
            if (gridMappable == null)
            {
                continue;
            }
            
            addGridMappable(gridMappable, tile);
        }
        
    }
    
    
    @Override
    public void render(GridMap<Type> gridMap)
    {
        renderTiles(gridMap);
        renderGridMappables(gridMap);
    }
    
    
    public GridMappableRender<Type> getGridMappableRender(Type gridMappable)
    {
        return gridMappableRenders.get(gridMappable);
    }
    
    
    public void relocateGridMappable(Type gridMappable, Tile next)
    {
        GridMappableRender<?> gridMappableRender = gridMappableRenders.get(gridMappable);
        gridMappableRender.setX(next.getX() * tileSize);
        gridMappableRender.setY(next.getY() * tileSize);
    }
    
    
    public void removeGridMappable(Type gridMappable)
    {
        GridMappableRender<?> gridMappableRender = gridMappableRenders.get(gridMappable);
        gridMappableGroup.getChildren().remove(gridMappableRender);
        gridMappableRenders.remove(gridMappable);
    }
    
    
    public void addGridMappable(Type gridMappable, Tile tile)
    {
        double x = tile.getX() * tileSize;
        double y = tile.getY() * tileSize;
        GridMappableRender<Type> render = new GridMappableRender<>(gridMappable, x, y, tileSize);
        
        gridMappableGroup.getChildren().add(render);
        
        gridMappableRenders.put(gridMappable, render);
    }
    
    
    /**
     * Highlights are transparent rectangles and recieve a {@link MouseEvent#MOUSE_CLICKED} eventHandler
     *
     * @param tile         the tile above which the highlight will be placed
     * @param color        the color of the highlight
     * @param eventHandler the eventHandler for a {@link MouseEvent#MOUSE_CLICKED}
     */
    public Rectangle addHighLight(Tile tile, Color color, EventHandler<MouseEvent> eventHandler)
    {
        Rectangle rectangle = SpriteHelper.createRectangle(color, tile, tileSize);
        rectangle.setOpacity(HIGHLIGHT_OPACITY);
        highLightGroup.getChildren().add(rectangle);
        highLightRectangles.put(tile, rectangle);
        
        
        if (eventHandler == null)
        {
            return rectangle;
        }
        
        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);//todo hand unhandled events to nodes beneath inccase of info???
        rectangle.cursorProperty().set(Cursor.HAND);
        return rectangle;
    }
    
    public Rectangle addHighLight(Tile tile, Color color)
    {
        return addHighLight(tile, color, null);
    }
    
    public Rectangle addHighLight(Tile tile)
    {
        return addHighLight(tile, Color.BLUE);
    }
    
    
    public void removeHighLight(Tile tile)
    {
        Rectangle rectangle = highLightRectangles.get(tile);
        highLightGroup.getChildren().remove(rectangle);
        highLightRectangles.remove(tile);
    }
    
    
    public void clearHighLights()
    {
        highLightGroup.getChildren().clear();
        highLightRectangles.clear();
    }
}
