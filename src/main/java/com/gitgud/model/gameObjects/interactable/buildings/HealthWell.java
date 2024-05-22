package com.gitgud.model.gameObjects.interactable.buildings;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;

import java.util.List;


public class HealthWell extends GridMappableBuilding
{
    private final static String NAME = "Health Well";
    
    
    private final static String DESCRIPTION = "Come to this building to replenish all your troops' health.";
    
    //todo
    private static final String SPRITE_URL = "src/main/resources/com/gitgud/sprites/interactables/healthwell.png";
    
    
    public HealthWell()
    {
        super(NAME, DESCRIPTION, SPRITE_URL);
    }
    
    
    private void healFightFigures(FightAgent[] fightFigures)
    {
        List.of(fightFigures).forEach(fightFigure -> fightFigure.setHealth(fightFigure.getMaxHealth()));
    }
    
    
    @Override
    public void interact(MissionController missionController)
    {
        missionController.getActiveFightFigures();
        healFightFigures(missionController.getActiveFightFigures());
        healFightFigures(missionController.getDiscardedFightFigures());
    }
    
}