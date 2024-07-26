package com.gitgud.pieces.control.menu;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.activeGame.GameState;
import javafx.event.Event;

import java.util.function.Consumer;
import java.util.function.Predicate;


public interface ActionMenu extends Menu, Selectable
{
    Predicate<GameState> condition();
    
    
    Consumer<Event> action();
    
    
    @Override
    default void select(Event event)
    {
        if (!condition().test(ActiveGameController.getGameState())) return;
        event.consume();
        action().accept(event);
    }
}
