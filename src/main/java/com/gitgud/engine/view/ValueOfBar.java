package com.gitgud.engine.view;

import com.gitgud.engine.model.attackDefenseLogic.Health;
import javafx.beans.binding.NumberExpressionBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;


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
        valueOfBar.setStyle("-fx-accent: #ff0000");
        
        return valueOfBar;
    }
    
    
    private void init()
    {
        progressProperty().bind(value.divide(max));
    }
}
