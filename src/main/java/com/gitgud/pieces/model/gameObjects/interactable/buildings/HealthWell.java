package com.gitgud.pieces.model.gameObjects.interactable.buildings;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.engine.model.gameObject.interactable.GridMappableBuilding;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;

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
    public void interact(GridMap gridMap)
    {
        Mission mission = ActiveGameController.getInstance().get().getMission();
        healFightFigures(mission.getActiveFightAgents());
        healFightFigures(mission.getDiscardedFightAgents());
    }
    
}
