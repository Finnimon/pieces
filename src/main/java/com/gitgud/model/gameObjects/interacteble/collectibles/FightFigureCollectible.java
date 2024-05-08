package com.gitgud.model.gameObjects.interacteble.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.FightAgent;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.utility.Core;
import com.gitgud.utility.interfaces.Collectible;

import java.util.Arrays;
import java.util.Objects;


public class FightFigureCollectible extends GameObject implements Collectible
{
    private final FightAgent fightFigure;
    
    
    public FightFigureCollectible(FightAgent fightFigure)
    {
        super("Resting " + fightFigure.getName(), fightFigure.getDescription() + "\nThey would like to join your army",
              fightFigure.getSpriteUrl());
        this.fightFigure = fightFigure;
    }
    
    
    @Override
    public void addToInventory(MissionController missionController)
    {
        FightAgent fightFigure = getFightFigure();
        
        FightAgent[] activeFightFigures = missionController.getActiveFightFigures();
        Core.insertAtFirstNullIndex(activeFightFigures, fightFigure);
        
        if (Arrays.asList(activeFightFigures).contains(fightFigure))
        {
            return;
        }
        
        FightAgent[] discardedFightFigures = missionController.getDiscardedFightFigures();
        Core.insertAtFirstNullIndex(discardedFightFigures, fightFigure);
    }
    
    
    @Override
    public boolean isCollectionPossible(MissionController missionController)
    {
        return Arrays.stream(missionController.getActiveFightFigures()).anyMatch(Objects::isNull) || Arrays.stream(
                missionController.getDiscardedFightFigures()).anyMatch(Objects::isNull);
    }
    
    
    public FightAgent getFightFigure()
    {
        return fightFigure;
    }
}
