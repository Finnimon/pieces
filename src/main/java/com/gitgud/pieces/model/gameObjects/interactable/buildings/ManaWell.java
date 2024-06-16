package com.gitgud.pieces.model.gameObjects.interactable.buildings;


import com.gitgud.engine.model.gameObject.interactable.GridMappableBuilding;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;

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
    public void interact(GridMap gridMap)
    {
        Mission mission = ActiveGameController.getInstance().get().getMission();
        
        replenishFightFiguresMana(mission.getActiveFightAgents());
        replenishFightFiguresMana(mission.getDiscardedFightAgents());
    }
}






























