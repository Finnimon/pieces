package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.fight.Fight;
import javafx.scene.layout.BorderPane;


public class FightTimeLineRender extends BorderPane implements UpdatableRender<Fight>
{
    private final Fight fight;
    
    
    public FightTimeLineRender(Fight fight)
    {
        this.fight = fight;
        
        render(fight);
    }
    
    
    @Override
    public void render(Fight data)
    {
    
    }
    
    
    @Override
    public void updateRender()
    {
    
    }
    
    
    @Override
    public Fight getData()
    {
        return null;
    }
    
}
