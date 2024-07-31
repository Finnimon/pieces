package com.gitgud.engine.view;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


/**
 * This class can Render a GridMap into a {@link javafx.scene.Group}. It also offers relocation, removal and adding
 * methods, as well as highlighting with event-handling.
 *
 * @param <Type> The type of the {@link GridMappable} on the {@link GridMapRender}.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.1
 */
public class GridMapRender<Type extends GridMappable> extends Group implements Render<GridMap<Type>>
{
    /**
     * The opacity of highlight.
     */
    public static final double HIGHLIGHT_OPACITY = 0.17;
    
    
    /**
     * <p>The width and height of the rectangles representing tiles.
     */
    private final int tileSize;
    
    
    /**
     * The Group for the tiles.
     */
    private final Group tileGroup;
    
    
    /**
     * The Group for the {@link GridMappableRender}s.
     */
    private final Group gridMappableGroup;
    
    
    /**
     * A mapping of {@link GridMappable}s and their {@link GridMappableRender}s to allow for removal and relocation
     * operations.
     */
    private final HashMap<Type, GridMappableRender<Type>> gridMappableRenders = new HashMap<>();
    
    
    /**
     * Group for the applied highlights.
     */
    private final Group highLightGroup;
    
    
    /**
     * Association between {@link Tile}s and their {@link Rectangle}s for highlighting to allow for removal and
     * relocation operations.
     */
    private final HashMap<Tile, Rectangle> highLightRectangles = new HashMap<>();
    
    
    /**
     * Creates a new {@link GridMapRender} from the provided {@link GridMap}.
     *
     * @param gridMap  GridMap to be rendered.
     * @param tileSize The height and Width of the tiles.
     */
    public GridMapRender(@NotNull GridMap<Type> gridMap, int tileSize)
    {
        super();
        this.tileSize = tileSize;
        
        tileGroup = new Group();
        gridMappableGroup = new Group();
        highLightGroup = new Group();
        
        addChildrenInCorrectOrder();
        
        render(gridMap);
    }
    
    
    /**
     * <p>Asserts that the {@link Render}'s children are in the correct order.
     * <p>If they are added in the wrong order, the {@link Render} will not render properly and the
     * {@link #gridMappableRenders} and or {@link #highLightRectangles} will be visible.
     */
    private void addChildrenInCorrectOrder()
    {
        ObservableList<Node> children = getChildren();
        children.add(tileGroup);
        children.add(gridMappableGroup);
        children.add(highLightGroup);
    }
    
    
    @Override
    public void render(@NotNull GridMap<Type> model)
    {
        renderTiles(model);
        renderGridMappables(model);
    }
    
    
    /**
     * Renders the Tiles of the {@code gridMap}.
     *
     * @param gridMap The gridMap whose tiles are to be rendered.
     */
    private void renderTiles(@NotNull GridMap<Type> gridMap)
    {
        ObservableList<Node> children = this.tileGroup.getChildren();
        HashMap<Tile, ImagePattern> gridMapTiles = SpriteHelper.loadImagePatterns(gridMap.verticeSet());
        
        for (Tile tile : gridMap.verticeSet())
        {
            ImagePattern currentPattern = gridMapTiles.get(tile);
            
            Rectangle rectangle = SpriteHelper.createRectangle(currentPattern, tile, tileSize);
            rectangle.setSmooth(false);
            children.add(rectangle);
        }
        
    }
    
    
    /**
     * Renders all non-null {@link GridMappable} on the {@code gridMap}.
     *
     * @param gridMap The gridMap whose {@link GridMappable}s are to be rendered.
     */
    private void renderGridMappables(@NotNull GridMap<Type> gridMap)
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
    
    
    /**
     * Adds a new {@link GridMappableRender} to the {@link #gridMappableGroup}.
     *
     * @param gridMappable The {@link GridMappable} to be rendered.
     * @param tile         The {@link Tile} that the {@link GridMappable} is on.
     */
    public void addGridMappable(@NotNull Type gridMappable, @NotNull Tile tile)
    {
        double x = tile.getX() * tileSize;
        double y = tile.getY() * tileSize;
        GridMappableRender<Type> render = new GridMappableRender<>(gridMappable, x, y, tileSize);
        
        gridMappableGroup.getChildren().add(render);
        
        gridMappableRenders.put(gridMappable, render);
    }
    
    
    /**
     * Gets the {@link GridMappableRender} of the provided {@link GridMappable}.
     *
     * @param gridMappable The {@link GridMappable} to get the {@link GridMappableRender} of.
     * @return The {@link GridMappableRender} associated with the provided {@link GridMappable} or null if not found.
     */
    public GridMappableRender<Type> getGridMappableRender(Type gridMappable)
    {
        return gridMappableRenders.get(gridMappable);
    }
    
    
    /**
     * Relocates a GridMappable on the {@link GridMapRender}.
     *
     * @param gridMappable The GridMappable to be relocated.
     * @param next         The new position for the GridMappable.
     */
    public void relocateGridMappable(@NotNull Type gridMappable, @NotNull Tile next)
    {
        GridMappableRender<?> gridMappableRender = gridMappableRenders.get(gridMappable);
        gridMappableRender.setTranslateX(next.getX() * tileSize);
        gridMappableRender.setTranslateY(next.getY() * tileSize);
    }
    
    
    /**
     * Removes a {@link GridMappable}'s {@link GridMappableRender} from the {@link GridMapRender}.
     *
     * @param gridMappable The GridMappable whose associated {@link GridMappableRender} is to be removed.
     */
    public void removeGridMappable(Type gridMappable)
    {
        GridMappableRender<?> gridMappableRender = gridMappableRenders.get(gridMappable);
        gridMappableGroup.getChildren().remove(gridMappableRender);
        gridMappableRenders.remove(gridMappable);
    }
    
    
    /**
     * <p>Places a Highlight Rectangle at the provided Tile.
     * <p>Defaults {@link #addHighLight(Tile, Color)} to {@link Color#BLUE}
     *
     * @param tile The tile above which the highlight will be placed.
     * @return The Highlight Rectangle in case it needs to be given additional eventHandlers.
     * @see #addHighLight(Tile, Color)
     */
    public Rectangle addHighLight(@NotNull Tile tile)
    {
        return addHighLight(tile, Color.BLUE);
    }
    
    
    /**
     * <p>Places a Highlight Rectangle at the provided Tile in the provided Color.
     * <p>Defaults {@link #addHighLight(Tile, Color, EventHandler)} to a null eventHandler and sets it to be Mouse
     * Transparent.
     *
     * @param tile  The tile above which the highlight will be placed.
     * @param color The color of the highlight.
     * @return The Highlight Rectangle in case it needs to be given additional eventHandlers.
     * @see #addHighLight(Tile, Color, EventHandler)
     */
    public Rectangle addHighLight(@NotNull Tile tile, @NotNull Color color)
    {
        Rectangle hightLight = addHighLight(tile, color, null);
        //if no eventHandler is provided, the highlight is set to be Mouse Transparent
        hightLight.setMouseTransparent(true);
        return hightLight;
    }
    
    
    /**
     * Highlights are transparent rectangles and recieve a {@link MouseEvent#MOUSE_CLICKED} eventHandler
     *
     * @param tile         the tile above which the highlight will be placed
     * @param color        the color of the highlight
     * @param eventHandler the eventHandler for a {@link MouseEvent#MOUSE_CLICKED}
     */
    public Rectangle addHighLight(@NotNull Tile tile, @NotNull Color color, EventHandler<MouseEvent> eventHandler)
    {
        Rectangle rectangle = SpriteHelper.createRectangle(color, tile, tileSize);
        rectangle.setOpacity(HIGHLIGHT_OPACITY);
        highLightGroup.getChildren().add(rectangle);
        highLightRectangles.put(tile, rectangle);
        
        
        if (eventHandler == null)
        {
            return rectangle;
        }
        
        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED,
                                  eventHandler);//todo hand unhandled events to nodes beneath inccase of info???
        rectangle.cursorProperty().set(Cursor.HAND);
        return rectangle;
    }
    
    
    /**
     * Removes The Highlight from {@code tile}.
     *
     * @param tile The tile to remove the highlight from.
     */
    public void removeHighLight(@NotNull Tile tile)
    {
        Rectangle rectangle = highLightRectangles.get(tile);
        highLightGroup.getChildren().remove(rectangle);
        highLightRectangles.remove(tile);
    }
    
    
    /**
     * Removes All Highlights.
     */
    public void clearHighLights()
    {
        highLightGroup.getChildren().clear();
        highLightRectangles.clear();
    }
}
