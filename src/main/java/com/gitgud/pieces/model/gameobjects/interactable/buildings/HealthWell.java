package com.gitgud.pieces.model.gameobjects.interactable.buildings;

import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;

import java.util.List;


public class HealthWell extends GridMappableBuilding<MissionController>
{
    private final static String NAME = "Health Well";
    
    
    private final static String DESCRIPTION = "Come to this building to replenish all your troops' health.";
    
    
    //todo
    private static final String SPRITE_URL = "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/buildings/HealthWell.png";
    
    
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
        Mission mission = missionController.getModel();
        healFightFigures(mission.getActiveFightAgents());
        healFightFigures(mission.getDiscardedFightAgents());
    }
    
}
