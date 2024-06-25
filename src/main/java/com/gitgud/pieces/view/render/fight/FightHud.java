package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.ActionContextHud;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.engine.view.Hud;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public class FightHud extends ActionContextHud<Fight>
{
    
//    private final FightTimeLineRender fightTimeLineRender;
    
    
    public FightHud(Fight data)
    {
        super(data);
//        this.fightTimeLineRender = new FightTimeLineRender(data);
    }
    
    
    @Override
    public void render(Fight data)
    {
        ObservableList<Node> children = getChildren();
        children.clear();
        //todo
    }
    
    
    @Override
    public void updateRender()
    {
        super.updateRender();
        //todo
    }
}
