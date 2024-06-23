package com.gitgud.engine.view;

import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.view.events.AppendRemoveNodeOnMouseEvent;
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
    public static final int WIDTH = 90;
    
    
    public static final double OPACITY = 0.6;
    
    
    public ActionChoiceRender(ActionChoice<?, ?, ?,?> actionChoice)
    {
        super();
        
        render(actionChoice);
    }
    
    
    @Override
    public void render(ActionChoice<?, ?, ?,?> data)
    {
        Label description = new Label(data.description());
        description.setFont(Font.font( 20));
        description.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        StackPane stackPane = new StackPane();
        Label label = new Label(data.name());
        label.setFont(Font.font( 20));
        Circle circle = new Circle();
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.radiusProperty().bind(label.widthProperty());
        
        stackPane.getChildren().addAll(circle, label);
        
        label.setAlignment(javafx.geometry.Pos.CENTER);
        circle.setOpacity(OPACITY);
        
        getChildren().add(stackPane);
        this.addEventHandler(MouseEvent.ANY, new AppendRemoveNodeOnMouseEvent(this, description));
    }
    
    
}
