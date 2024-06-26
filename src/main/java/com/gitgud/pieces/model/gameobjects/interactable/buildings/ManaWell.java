package com.gitgud.pieces.model.gameobjects.interactable.buildings;


import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;


public class ManaWell extends GridMappableBuilding<MissionController>
{
    private final static String NAME = "Mana Well";
    
    
    private final static String DESCRIPTION = "Come to this building to replenish all your troops' mana.";
    
    
    //todo
    private static final String SPRITE_URL = "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/buildings/ManaWell.png";
    
    
    public ManaWell()
    {
        super(NAME, DESCRIPTION, SPRITE_URL);
    }
    
    
    private void replenishFightFiguresMana(FightAgent[] fightAgents)
    {
        for (FightAgent agent : fightAgents)
        {
            if (agent == null)
            {
                continue;
            }
            agent.setMana(agent.getMaxMana());
        }
    }
    
    
    @Override
    public void interact(MissionController missionController)
    {
        Mission mission = missionController.getModel();
        replenishFightFiguresMana(mission.getActiveFightAgents());
        replenishFightFiguresMana(mission.getDiscardedFightAgents());
    }
}






























