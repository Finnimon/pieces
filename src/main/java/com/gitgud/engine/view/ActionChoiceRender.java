package com.gitgud.engine.view;

import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.utility.Strings;
import com.gitgud.engine.view.events.AppendRemoveNodeOnMouseEvent;
import com.gitgud.engine.view.infopane.InfoPane;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class ActionChoiceRender extends Group implements Render<ActionChoice<?, ?, ?,?>>
{
    public static final int SIZE = 40;
    
    
    private static final double OPACITY = 0.6;
    
    
    private static final int FONT_SIZE = 12;
    
    
    public ActionChoiceRender(ActionChoice<?, ?, ?,?> actionChoice)
    {
        render(actionChoice);
    }
    
    
    @Override
    public void render(ActionChoice<?, ?, ?,?> data)
    {   StackPane stackPane = new StackPane();
        Label label = new Label(data.name());
        label.setFont(Font.font( FONT_SIZE));
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
        
        String infoPaneString = data.name() + Strings.LINE_BREAK + data.description();
        InfoPane infoPane=new InfoPane<>(infoPaneString);
        
        AppendRemoveNodeOnMouseEvent.add(this,infoPane);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, data.getMouseEventHandler());
    }
    
    
}
