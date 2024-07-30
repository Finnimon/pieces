package com.gitgud.engine.model;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Objects that have a Lifetime of Turns must implement this interface
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 * @implNote Not finished
 */
public interface Durable
{
    int getDuration();
    public void undo();
    public default void bind(Turning turning)
    {
        SimpleIntegerProperty turnProperty = turning.turnProperty();
        SimpleIntegerProperty durationProperty = new SimpleIntegerProperty(getDuration());
        int startingTurn=turning.getTurn();
        NumberBinding countUp= turnProperty.subtract(startingTurn);
        NumberBinding countDown=durationProperty.subtract(countUp);
        BooleanBinding isDone = countDown.isEqualTo(0);
        isDone.addListener((observable, oldValue, newValue) ->
                                           {
                                               if (!newValue)
                                               {
                                                   return;
                                               }
                                               undo();
                                           });
    }
}
