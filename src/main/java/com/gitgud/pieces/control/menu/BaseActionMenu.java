package com.gitgud.pieces.control.menu;

import com.gitgud.pieces.model.game.GameState;
import javafx.event.Event;

import java.util.function.Consumer;
import java.util.function.Predicate;


@Deprecated
public class BaseActionMenu implements ActionMenu
{
    private final String name;
    
    
    private final Predicate<GameState> condition;
    
    
    private final Consumer<Event> action;
    
    
    public BaseActionMenu(String name, Predicate<GameState> condition, Consumer<Event> action)
    {
        this.name = name;
        this.condition = condition;
        this.action = action;
    }
    
    
    @Override
    public String name()
    {
        return name;
    }
    
    
    @Override
    public Predicate<GameState> condition()
    {
        return condition;
    }
    
    
    @Override
    public Consumer<Event> action()
    {
        return action;
    }
}
