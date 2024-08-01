package com.gitgud.engine.model;

import com.gitgud.engine.control.Ending;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Objects that have a Lifetime of Turns must implement this interface. Can be used for temporary status-effects or
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Durable
{
    /**
     * Binds this durable to a Turning and undoes it, upon a certain number of turns elapsing.
     *
     * @param turning The Turning to be bound to.
     */
    default void bind(Turning turning)
    {
        SimpleIntegerProperty turnProperty = turning.turnProperty();
        SimpleIntegerProperty durationProperty = new SimpleIntegerProperty(getDuration());
        int startingTurn = turning.getTurn();
        NumberBinding countUp = turnProperty.subtract(startingTurn);
        NumberBinding countDown = durationProperty.subtract(countUp);
        BooleanBinding isDone = countDown.isEqualTo(0);
        
        if (turning instanceof Ending ending)
        {
            isDone = isDone.or(ending.isFinishedProperty());
        }
        
        isDone.addListener((observable, oldValue, newValue) ->
                           {
                               if (!newValue)
                               {
                                   return;
                               }
                               undo();
                           });
    }
    
    
    int getDuration();
    
    
    void undo();
}
