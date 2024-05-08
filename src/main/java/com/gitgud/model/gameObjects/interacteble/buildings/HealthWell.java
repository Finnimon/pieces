package com.gitgud.model.gameObjects.interacteble.buildings;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;

import java.util.List;


public class HealthWell extends GridMappableBuilding
{
    private final static String NAME = "Health Well";
    
    
    private final static String DESCRIPTION = "Come to this building to replenish all your troops' health.";
    
    
    private static final String SPRITE_URL = "src/resources/sprites/buildings/healthwell.png";
    
    
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
