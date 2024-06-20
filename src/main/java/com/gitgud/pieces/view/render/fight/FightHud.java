package com.gitgud.pieces.view.render.fight;

import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.view.render.Hud;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public class FightHud extends Hud<Fight>
{
    private final AvailableActionsRender availableActionsRender;
    private final FightTimeLineRender fightTimeLineRender;
    public FightHud(Fight data)
    {
        super(data);
        this.availableActionsRender = new AvailableActionsRender(data);
        this.fightTimeLineRender = new FightTimeLineRender(data);
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
