package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.engine.view.Hud;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;


public class FightRender extends Group implements ActionContextRender<Fight,FightAgent>
{
    private final static int TILE_SIZE = 200;
    
    
    private final Fight fight;
    
    
    private final GridMapRender<FightAgent> gridMapRender;
    
    
    private final FightHud fightHud;
    
    
    public FightRender(Fight fight)
    {
        this.fight = fight;
        this.gridMapRender = new GridMapRender<>(fight.getGridMap(), TILE_SIZE);
        this.fightHud = new FightHud(fight);
        
        render(fight);
    }
    
    
    @Override
    public void updateRender()
    {
        fightHud.updateRender();
    }
    
    
    @Override
    public void render(Fight data)
    {
        ObservableList<Node> children = getChildren();
        
        children.add(gridMapRender);
        children.add(fightHud);
    }
    
    
    @Override
    public Fight getData()
    {
        return fight;
    }
    
    
    @Override
    public Hud<Fight> getHud()
    {
        return this.fightHud;
    }
    
    
    @Override
    public GridMapRender<FightAgent> getGridMapRender()
    {
        return gridMapRender;
    }
    
    
}
