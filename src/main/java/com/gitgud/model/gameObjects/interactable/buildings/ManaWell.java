package com.gitgud.model.gameObjects.interactable.buildings;


import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.mission.Mission;

import java.util.List;


public class ManaWell extends GridMappableBuilding
{
    private final static String NAME = "Mana Well";
    
    
    private final static String DESCRIPTION = "Come to this building to replenish all your troops' mana.";
    
    //todo
    private static final String SPRITE_URL = "src/main/resources/com/gitgud/sprites/interactables/manawell.png";
    
    
    public ManaWell()
    {
        super(NAME, DESCRIPTION, SPRITE_URL);
    }
    
    
    private void replenishFightFiguresMana(FightAgent[] fightFigures)
    {
        List.of(fightFigures).forEach(fightFigure -> fightFigure.setMana(fightFigure.getMaxMana()));
    }
    
    
    @Override
    public void interact(MissionController missionController)
    {
        Mission mission = missionController.getMission();
        
        replenishFightFiguresMana(mission.getActiveFightAgents());
        replenishFightFiguresMana(mission.getDiscardedFightAgents());
    }
}






























