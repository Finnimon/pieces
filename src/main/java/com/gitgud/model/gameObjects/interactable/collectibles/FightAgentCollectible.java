package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;
import com.gitgud.utility.Core;

import java.util.Arrays;
import java.util.Objects;


public class FightAgentCollectible extends GameObject implements Collectible
{
    private final FightAgentFL fightFigure;
    
    
    public FightAgentCollectible(FightAgentFL fightFigure)
    {
        super("Resting " + fightFigure.getName(), fightFigure.getDescription() + "\r\nThey would like to join your army",
              fightFigure.getSpriteUrl());
        this.fightFigure = fightFigure;
    }
    
    
    @Override
    public void addToInventory(MissionController missionController)
    {
        FightAgentFL fightFigure = getFightFigure();
        
        FightAgentFL[] activeFightFigures = missionController.getActiveFightFigures();
        Core.insertAtFirstNullIndex(activeFightFigures, fightFigure);
        
        if (Arrays.asList(activeFightFigures).contains(fightFigure))
        {
            return;
        }
        
        FightAgentFL[] discardedFightFigures = missionController.getDiscardedFightFigures();
        Core.insertAtFirstNullIndex(discardedFightFigures, fightFigure);
    }
    
    
    @Override
    public boolean isCollectionPossible(MissionController missionController)
    {
        return Arrays.stream(missionController.getActiveFightFigures()).anyMatch(Objects::isNull) || Arrays.stream(
                missionController.getDiscardedFightFigures()).anyMatch(Objects::isNull);
    }
    
    
    public FightAgentFL getFightFigure()
    {
        return fightFigure;
    }
}
