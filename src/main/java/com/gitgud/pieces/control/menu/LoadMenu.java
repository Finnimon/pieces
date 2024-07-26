package com.gitgud.pieces.control.menu;

import com.gitgud.pieces.model.activeGame.GameState;
import javafx.event.Event;

import java.util.function.Consumer;
import java.util.function.Predicate;


public class LoadMenu extends BaseActionMenu
{
    public LoadMenu()
    {
        super("Load", x->true, x->showLoadScreen());
    }
    private static void showLoadScreen()
    {}
}
