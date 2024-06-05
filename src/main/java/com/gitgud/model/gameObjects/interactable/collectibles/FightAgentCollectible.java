package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.Core;

import java.util.Arrays;
import java.util.Objects;


public class FightAgentCollectible extends GameObject implements Collectible
{
    private final FightAgent fightFigure;
    
    
    public FightAgentCollectible(FightAgent fightFigure)
    {
        super("Resting " + fightFigure.getName(), fightFigure.getDescription() + "\r\nThey would like to join your army",
              fightFigure.getSpriteFilePath());
        this.fightFigure = fightFigure;
    }
    
    
    @Override
    public void addToInventory(MissionController missionController)
    {
        FightAgent fightFigure = getFightFigure();
        
        FightAgent[] activeFightAgents = missionController.getMission().getActiveFightAgents();
        Core.insertAtFirstNullIndex(activeFightAgents, fightFigure);
        
        if (Arrays.asList(activeFightAgents).contains(fightFigure))
        {
            return;
        }
        
        FightAgent[] discardedFightAgents = missionController.getMission().getDiscardedFightAgents();
        Core.insertAtFirstNullIndex(discardedFightAgents, fightFigure);
    }
    
    
    @Override
    public boolean isCollectionPossible(MissionController missionController)
    {
        return Arrays.stream(missionController.getMission().getActiveFightAgents()).anyMatch(Objects::isNull) || Arrays.stream(
                missionController.getMission().getDiscardedFightAgents()).anyMatch(Objects::isNull);
    }
    
    
    public FightAgent getFightFigure()
    {
        return fightFigure;
    }
}
