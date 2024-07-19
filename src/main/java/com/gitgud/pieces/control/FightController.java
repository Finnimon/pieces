package com.gitgud.pieces.control;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.actionChoice.*;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class FightController extends ActionAwaitingController<Fight, FightAgent, FightRender>
{
    
    
    public static final int MOVEMENT_CHOICE_INDEX = 0;
    
    
    public static final int ATTACK_CHOICE_INDEX = 1;
    
    
    public static final int SPELL_CHOICE_INDEX = 2;
    
    
    public static final int SKIP_TURN_CHOICE_INDEX = 3;
    
    
    private final EnemyAlgorithm enemyAlgorithm;
    
    
    public FightController(Fight fight)
    {
        super(fight, new FightRender(fight));
        enemyAlgorithm = new EnemyAlgorithm(this);
    }
    
    
    @Override
    public void advance()
    {
        Fight fight = this.getModel();
        fight.incrementTurn();
        if (tryEnd())
        {
            return;
        }
        FightRender render = getRender();
        render.updateRender();
        render.getGridMapRender().clearHighLights();
        hightlightActivePosition();
        executeActionChoiceTask(onSucceeded());
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
        
        choices.add(MOVEMENT_CHOICE_INDEX, getMovementChoiceRoot());
        choices.add(ATTACK_CHOICE_INDEX, getAttackRootChoice());
        choices.add(SPELL_CHOICE_INDEX, getSpellRootChoice());
        choices.add(SKIP_TURN_CHOICE_INDEX, getSkipTurnChoice());
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
        
        System.out.println(activeGame.getGameState());
        getModel().end();//todo
        
        activeGame.setFight(null);
        
        GameFlow.startNextSceneController();
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
    
    
    @Override
    public void start()
    {
        getRender().show();
        executeActionChoiceTask(onSucceeded());
    }
    
    
    private Consumer<ActionChoice> onSucceeded()
    {
        return actionChoice ->
        {
            if (getActiveFightAgent().getAllegiance() == enemyAlgorithm.getEnemyAllegiance())
            {
                System.out.println("enemyAlgorithm.act((RootChoice) actionChoice)");
                enemyAlgorithm.act((RootChoice) actionChoice);
                return;
            }
            hightlightActivePosition();
            actionChoice.show(this);
        };
    }
}
