package com.gitgud.engine.view;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;


public class GridMapRender<Type extends Sprite> extends Group implements Render<GridMap<Type>>
{
    public static final double HIGHLIGHT_OPACITY = 0.3;
    
    
    private final int tileSize;
    
    
    private final Group tileGroup;
    
    
    private final Group gridMappableGroup;
    
    
    private final HashMap<Type, Rectangle> gridMappableRectangles = new HashMap<>();
    
    
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
        HashMap<Type, ImagePattern> gridMappablePatterns = SpriteHelper.loadImagePatterns(gridMap.nonNullElements());
        
        children.clear();
        
        for (Tile tile : gridMap.verticeSet())
        {
            Type gridMappable = gridMap.get(tile);
            
            if (gridMappable == null)
            {
                continue;
            }
            
            addGridMappable(gridMappable, tile, gridMappablePatterns.get(gridMappable));
        }
        
    }
    
    
    @Override
    public void render(GridMap<Type> gridMap)
    {
        renderTiles(gridMap);
        renderGridMappables(gridMap);
    }
    
    
    public void relocateGridMappable(Type gridMappable, Tile next)
    {
        Rectangle rectangle = gridMappableRectangles.get(gridMappable);
        rectangle.setX(next.getX() * tileSize);
        rectangle.setY(next.getY() * tileSize);
    }
    
    
    public void removeGridMappable(Type gridMappable)
    {
        Rectangle rectangle = gridMappableRectangles.get(gridMappable);
        gridMappableGroup.getChildren().remove(rectangle);
        gridMappableRectangles.remove(gridMappable);
    }
    
    
    public void addGridMappable(Type gridMappable, Tile tile)
    {
        ImagePattern pattern = new ImagePattern(new Image(gridMappable.getSpriteUrl()));
        
        addGridMappable(gridMappable, tile, pattern);
    }
    
    
    public void addGridMappable(Type gridMappable, Tile tile, ImagePattern pattern)
    {
        Rectangle rectangle = SpriteHelper.createRectangle(pattern, tile, tileSize);
        
        gridMappableGroup.getChildren().add(rectangle);
        
        gridMappableRectangles.put(gridMappable, rectangle);
    }
    
    
    private void addInfoShowingEventHandlerToGridMappable(Rectangle rectangle, Type gridMappable)
    {
        //todo
    }
    
    
    /**
     * Highlights are transparent rectangles and recieve a {@link MouseEvent#MOUSE_CLICKED} eventHandler
     *
     * @param tile         the tile above which the highlight will be placed
     * @param color        the color of the highlight
     * @param eventHandler the eventHandler for a {@link MouseEvent#MOUSE_CLICKED}
     */
    public void addHighLight(Tile tile, Color color, EventHandler<MouseEvent> eventHandler)
    {
        Rectangle rectangle = SpriteHelper.createRectangle(color, tile, tileSize);
        rectangle.setOpacity(HIGHLIGHT_OPACITY);
        EventType<MouseEvent> eventType = new EventType<>(MouseEvent.MOUSE_CLICKED);
        rectangle.addEventHandler(eventType, eventHandler);
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
