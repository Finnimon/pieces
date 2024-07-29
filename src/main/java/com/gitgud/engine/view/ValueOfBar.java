package com.gitgud.engine.view;

import com.gitgud.engine.model.attackDefenseLogic.Health;
import javafx.beans.binding.NumberExpressionBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;

import java.util.List;


public class ValueOfBar extends ProgressBar
{
    private final SimpleDoubleProperty value;
    
    
    private final SimpleDoubleProperty max;
    
    
    public ValueOfBar(NumberExpressionBase value, NumberExpressionBase max)
    {
        this();
        this.value.bind(value);
        this.max.bind(max);
    }
    
    
    public ValueOfBar()
    {
        value = new SimpleDoubleProperty();
        max = new SimpleDoubleProperty();
        init();
    }
    
    
    public static ValueOfBar healthBar(Health health)
    {
        ValueOfBar valueOfBar = new ValueOfBar(health.healthProperty(), health.maxHealthProperty());
        valueOfBar.setStyle("-fx-accent: red");
        
        return valueOfBar;
    }
    
    
    private void init()
    {
        progressProperty().bind(value.divide(max));
        addNumberLabels();
    }
    
    
    private void addNumberLabels()
    {
        ObservableList<Node> children = getChildren();
        List<Label> labels = List.of(numberBoundLabel(value), new Label("/"), numberBoundLabel(max));
        children.addAll(labels);
    }
    
    
    private Label numberBoundLabel(NumberExpressionBase numberExpressionBase)
    {
        Label label = new Label();
        label.textProperty().bind(numberExpressionBase.asString());
        label.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return label;
    }
}
