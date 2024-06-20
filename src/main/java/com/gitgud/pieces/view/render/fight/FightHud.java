package com.gitgud.pieces.view.render.fight;

import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.view.render.Hud;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.Collection;


public class FightHud extends Hud<Fight>
{
    public FightHud(Fight data)
    {
        super(data);
    }
    
    
    @Override
    public void render(Fight data)
    {
        ObservableList<Node> children = getChildren();
        children.clear();
        //tdo
    }
    
    
    @Override
    public void updateRender()
    {
        //todo
    }
}
