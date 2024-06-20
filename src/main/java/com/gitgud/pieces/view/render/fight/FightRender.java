package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.GridMapRender;
import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;


public class FightRender extends Scene implements UpdatableRender<Fight>
{
    private final static int TILE_SIZE = 200;
    
    
    private final Group root;
    
    
    private final Fight fight;
    
    
    private final GridMapRender<FightAgent> gridMapRender;
    
    
    private final FightHud fightHud;
    
    
    private FightRender(Group root, Fight fight)
    {
        super(root);
        this.root = root;
        this.fight = fight;
        this.gridMapRender = new GridMapRender<>(fight.getGridMap(), TILE_SIZE);
        this.fightHud = new FightHud(fight);
        
        render(fight);
    }
    
    
    public static FightRender create(Fight fight)
    {
        return new FightRender(new Group(), fight);
    }
    
    
    @Override
    public void updateRender()
    {
    
    }
    
    
    @Override
    public void render(Fight data)
    {
        ObservableList<Node> childen = root.getChildren();
        
        childen.add(gridMapRender);
        childen.add(fightHud);
    }
    
    
    @Override
    public Fight getData()
    {
        return null;
    }
}
