package com.gitgud.engine.view;

import com.gitgud.engine.model.attackDefenseLogic.Health;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;


/**
 * This class can be used to create a progress bar with a label that shows the current value and the max value.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class ValueOfBar extends StackPane
{
    /**
     * Current value.
     */
    private final NumberExpression value;
    
    
    /**
     * The max value.
     */
    private final NumberExpression max;
    
    
    /**
     * The ProgressBar that shows {@link #value}/{@link #max}.
     */
    private final ProgressBar progressBar;
    
    
    /**
     * The Label that shows a concat of {@link #value}+"/"+{@link #max}.
     */
    private final Label label;
    
    
    public ValueOfBar(NumberExpression value, NumberExpression max)
    {
        this.value = value;
        this.max = max;
        progressBar = new ProgressBar();
        label = new Label();
        init();
    }
    
    
    private void init()
    {
        initProgressbar();
        initLabel();
    }
    
    
    /**
     * <p>Binds {@link #progressBar} progressProperty to {@link #value}/{@link #value}.
     * <p>Adds {@link #progressBar} to {@link #getChildren()}.
     */
    private void initProgressbar()
    {
        SimpleDoubleProperty doubleValue = new SimpleDoubleProperty();
        doubleValue.bind(value);
        //this indirect binding is necessary if value is an IntegerProperty because 1/2=0. But we want to show 1/2=0.5
        progressBar.progressProperty().bind(doubleValue.divide(max));
        getChildren().add(progressBar);
        setAlignment(progressBar, Pos.CENTER);
    }
    
    
    /**
     * <p>Concat {@link #value}+/+{@link #max} to {@link #label}.
     * <p>Adds {@link #label} to {@link #getChildren()}.
     */
    private Label initLabel()
    {
        StringExpression stringExpression = Bindings.concat(value.asString(), "/", max.asString());
        label.textProperty().bind(stringExpression);
        getChildren().add(label);
        setAlignment(label, Pos.CENTER);
        return label;
    }
    
    
    /**
     * Factory method for creating a red ValueOfBar from a {@link Health}.
     *
     * @param health The {@link Health} whose health/maxHealth will be used as value/max.
     * @return The created and styled {@link ValueOfBar}.
     */
    public static ValueOfBar healthBar(Health health)
    {
        ValueOfBar valueOfBar = new ValueOfBar(health.healthProperty(), health.maxHealthProperty());
        valueOfBar.setProgressBarStyle("-fx-accent: #ff0000");
        valueOfBar.setLabelStyle("-fx-font-weight: bold");
        return valueOfBar;
    }
    
    
    public void setProgressBarStyle(String style)
    {
        progressBar.setStyle(style);
    }
    
    
    public void setLabelStyle(String style)
    {
        label.setStyle(style);
    }
}
