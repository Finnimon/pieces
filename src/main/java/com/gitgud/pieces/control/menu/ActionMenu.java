package com.gitgud.pieces.control.menu;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.game.GameState;
import javafx.event.Event;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Deprecated
public interface ActionMenu extends Menu, Selectable
{
    @Override
    default void select(Event event)
    {
        if (!condition().test(ActiveGameController.getGameState()))
        {
            return;
        }
        event.consume();
        action().accept(event);
    }
    
    
    Predicate<GameState> condition();
    
    
    Consumer<Event> action();
}
