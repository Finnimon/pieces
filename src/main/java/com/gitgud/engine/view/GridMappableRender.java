package com.gitgud.engine.view;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.events.AppendRemoveNodeOnMouseEvent;
import com.gitgud.engine.view.infopane.GridMappableInfoPane;
import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class GridMappableRender<GridMappableType extends GridMappable> extends Group implements Render<GridMappableType>
{
    private final Rectangle rectangle;
    
    
    public GridMappableRender(GridMappableType gridMappable, double x, double y, double width, double height)
    {
        rectangle = new Rectangle();
        
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        render(gridMappable);
    }
    
    public GridMappableRender(GridMappableType gridMappable, double x, double y)
    {
        this(gridMappable, x, y, gridMappable.getSprite().getWidth(), gridMappable.getSprite().getHeight());
    }
    
    
    public GridMappableRender(GridMappableType gridMappable)
    {
        this(gridMappable, 0, 0);
    }
    
    public GridMappableRender(GridMappableType gridMappable,double x, double y ,double size)
    {
        this(gridMappable, x, y, size, size);
    }
    public GridMappableRender(GridMappableType gridMappable,double size)
    {
        this(gridMappable, 0, 0, size);
    }
    
    
    @Override
    public void render(GridMappableType data)
    {
        rectangle.setFill(new ImagePattern(data.getSprite()));
        
        AppendRemoveNodeOnMouseEvent.add(this, new GridMappableInfoPane<>(data));
        getChildren().add(rectangle);
    }
    
    public final void setX(double x)
    {
        rectangle.setX(x);
    }
    public final void setY(double y)
    {
        rectangle.setY(y);
    }
}
