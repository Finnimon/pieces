package com.gitgud.pieces.control;


import com.gitgud.engine.control.ActionAwaiterController;
import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;
import javafx.concurrent.Task;


//todo render as scene
public class FightController extends ActionAwaiterController<Fight, FightAgent>
{
    
    
    public FightController(Fight fight)
    {
        super(fight, new FightRender(fight));
    }
    
    
    
    
    
    @Override
    public Tile getActivePosition()
    {
        Fight fight = this.getModel();
        return fight.getGridMap().getVertex(fight.getFightTimeLine().getActiveFightAgent());
    }
    
    
    @Override
    public Task<ActionChoice<ActionAwaiterController<Fight, FightAgent>, Fight, FightAgent>> getActionChoiceTask()
    {
        return null;//todo
    }
    
    
    @Override
    public void end()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        
        getModel().end();//todo
        
        activeGame.setFight(null);
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
}
