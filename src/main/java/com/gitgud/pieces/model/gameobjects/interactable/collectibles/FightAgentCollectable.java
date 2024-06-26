package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.Core;

import java.util.Arrays;
import java.util.Objects;


public class FightAgentCollectable extends GameObject implements Collectible
{
    private final FightAgent fightFigure;
    
    
    public FightAgentCollectable(FightAgent fightFigure)
    {
        super("Resting " + fightFigure.name(), fightFigure.description() + "\r\nThey would like to join your army",
              fightFigure.getSpriteFilePath());
        this.fightFigure = fightFigure;
    }
    
    
    @Override
    public void addToInventory()
    {
        FightAgent fightFigure = getFightFigure();
        Mission mission = ActiveGameController.getInstance().get().getMission();
        
        FightAgent[] activeFightAgents = mission.getActiveFightAgents();
        Core.insertAtFirstNullIndex(activeFightAgents, fightFigure);
        
        if (Arrays.asList(activeFightAgents).contains(fightFigure))
        {
            return;
        }
        
        FightAgent[] discardedFightAgents = mission.getDiscardedFightAgents();
        Core.insertAtFirstNullIndex(discardedFightAgents, fightFigure);
    }
    
    
    @Override
    public boolean isCollectionPossible()
    {
        Mission mission = ActiveGameController.getInstance().get().getMission();
        return Arrays.stream(mission.getActiveFightAgents()).anyMatch(Objects::isNull) || Arrays.stream(
                mission.getDiscardedFightAgents()).anyMatch(Objects::isNull);
    }
    
    
    public FightAgent getFightFigure()
    {
        return fightFigure;
    }
}
