package com.gitgud.pieces.control;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.StageController;
import com.gitgud.engine.control.actionChoice.*;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;

import java.util.ArrayList;
import java.util.List;


//todo render as scene
public class FightController extends ActionAwaitingController<Fight, FightAgent, FightRender>
{
    private final EnemyAlgorithm enemyAlgorithm;
    
    
    public FightController(Fight fight)
    {
        super(fight, new FightRender(fight));
        enemyAlgorithm = new EnemyAlgorithm(this);
    }
    
    
    @Override
    public void advance()
    {
        getModel().getFightTimeLine().advance();
        
        getRender().getGridMapRender().clearHighLights();
        
        if (tryEnd())
        {
            return;
        }
        
        if (getActiveFightAgent().getAllegiance() == enemyAlgorithm.getEnemyAllegiance())
        {
            enemyAlgorithm.act();
            return;
        }
        
        getActionChoice().show(this);
        hightlightActivePosition();
    }
    
    
    @Override
    public Tile getActivePosition()
    {
        Fight fight = this.getModel();
        FightAgent activeFightAgent = getActiveFightAgent();
        return fight.getGridMap().getVertex(activeFightAgent);
    }
    
    
    @Override
    public ActionChoice<FightController, Fight, FightAgent, FightRender> getActionChoice()
    {
        List<ActionChoice<FightController, Fight, FightAgent, FightRender>> choices = new ArrayList<>();
        
        choices.add(getMovementChoiceRoot());
        choices.add(getAttackRootChoice());
        choices.add(getSpellRootChoice());
        choices.add(getSkipTurnChoice());
        
        return new RootActionChoice<>("root", "root", choices, this);//todo
    }
    
    
    public RootToActionChoice<FightController, Fight, FightAgent, FightRender> getMovementChoiceRoot()
    {
        Tile position = getActivePosition();
        
        FightAgent activeFightAgent = getActiveFightAgent();
        
        
        return new MovementRootChoice<>(this, activeFightAgent, position);
    }
    
    
    public AttackRootChoice<FightController, Fight, FightAgent, FightRender> getAttackRootChoice()
    {
        Tile position = getActivePosition();
        
        FightAgent activeFightAgent = getActiveFightAgent();
        
        
        return new AttackRootChoice<>(this, activeFightAgent);//todo
    }
    
    
    public RootToActionChoice<FightController, Fight, FightAgent, FightRender> getSpellRootChoice()
    {
        FightAgent agent = getActiveFightAgent();
        if (!(agent instanceof SpellCasterFightAgent spellCasterFightAgent))
        {
            return new RootToActionChoice<>("Magic", "Cast Spells, available to you", this, new ArrayList<>());
        }
        
        throw new RuntimeException("not implemented");
    }
    
    
    private FightAgent getActiveFightAgent()
    {
        return getModel().getFightTimeLine().getActiveFightAgent();
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
    
    
    @Override
    public void start()
    {
        StageController.getInstance().getStage().show();
        if (getActiveFightAgent().getAllegiance() == enemyAlgorithm.getEnemyAllegiance())
        {
            enemyAlgorithm.act();
        }
            super.start();
    }
}
