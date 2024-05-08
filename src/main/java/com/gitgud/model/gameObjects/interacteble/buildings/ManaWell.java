package com.gitgud.model.gameObjects.interacteble.buildings;


import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.FightAgent;

import java.util.List;


public class ManaWell extends GridMappableBuilding
{
    private final static String NAME = "Mana Well";
    
    
    private final static String DESCRIPTION = "Come to this building to replenish all your troops' mana.";
    
    
    private static final String SPRITE_URL = "src/resources/sprites/buildings/manawell.png";
    
    
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
        missionController.getActiveFightFigures();
        replenishFightFiguresMana(missionController.getActiveFightFigures());
        replenishFightFiguresMana(missionController.getDiscardedFightFigures());
    }
}






























