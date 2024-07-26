package com.gitgud.engine.view;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootChoice;
import com.gitgud.engine.view.events.AppendRemoveNodeOnMouseEvent;
import com.gitgud.engine.view.infopane.InfoPane;
import com.gitgud.engine.view.infopane.NameDescribableInfoPane;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class ActionChoiceRender extends Group implements Render<ActionChoice<?, ?, ?, ?>>
{
    public static final int SIZE = 50;
    
    
    private static final double OPACITY = 0.6;
    
    
    private static final int FONT_SIZE = 15;
    
    
    public ActionChoiceRender(ActionChoice<?, ?, ?, ?> actionChoice)
    {
        render(actionChoice);
    }
    
    
    @Override
    public void render(ActionChoice<?, ?, ?, ?> data)
    {
        StackPane stackPane = new StackPane();
        Label label = new Label(data.name());
        label.setFont(Font.font(FONT_SIZE));
        Circle circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.radiusProperty().bind(label.widthProperty());
        
        stackPane.getChildren().addAll(circle, label);
        label.setPrefWidth(SIZE);
        label.setMinWidth(SIZE);
        label.setMaxWidth(SIZE);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        circle.setOpacity(OPACITY);
        
        getChildren().add(stackPane);
        
        
        InfoPane<?> infoPane = new NameDescribableInfoPane<>(data);
        
        AppendRemoveNodeOnMouseEvent.add(this, infoPane);
        
        
        if (!(data instanceof RootChoice<?> rootChoice))
        {
            addSelectionEventHandling(data);
            return;
        }
        
        if (!rootChoice.isEmpty())
        {
            addSelectionEventHandling(data);
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
