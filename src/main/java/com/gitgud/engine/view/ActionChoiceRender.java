package com.gitgud.engine.view;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootChoice;
import com.gitgud.engine.view.infopane.InfoPane;
import com.gitgud.engine.view.infopane.NameDescribableInfoPane;
import com.gitgud.engine.view.utility.AppendRemoveNodeOnMouseEventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


/**
 * Render for the {@link ActionChoice}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class ActionChoiceRender extends StackPane implements Render<ActionChoice<?, ?, ?, ?>>
{
    public static final int SIZE = 50;
    
    
    private static final double OPACITY = 0.6;
    
    
    private static final int FONT_SIZE = 15;
    
    
    public ActionChoiceRender(ActionChoice<?, ?, ?, ?> actionChoice)
    {
        render(actionChoice);
    }
    
    
    @Override
    public void render(ActionChoice<?, ?, ?, ?> model)
    {
        Label label = new Label(model.name());
        label.setFont(Font.font(FONT_SIZE));
        Circle circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.radiusProperty().bind(label.widthProperty());
        
        getChildren().addAll(circle, label);
        label.setPrefWidth(SIZE);
        label.setMinWidth(SIZE);
        label.setMaxWidth(SIZE);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        circle.setOpacity(OPACITY);
        
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        
        
        InfoPane<?> infoPane = new NameDescribableInfoPane<>(model);
        
        AppendRemoveNodeOnMouseEventHandler.add(this, infoPane);
        
        
        if (!(model instanceof RootChoice<?> rootChoice))
        {
            addSelectionEventHandling(model);
            return;
        }
        
        if (!rootChoice.isEmpty())
        {
            addSelectionEventHandling(model);
            return;
        }
        
        circle.setFill(Color.GRAY);
    }
    
    
    private void addSelectionEventHandling(ActionChoice<?, ?, ?, ?> data)
    {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, data.getMouseEventHandler());
        cursorProperty().set(Cursor.HAND);
    }
    
    
}
