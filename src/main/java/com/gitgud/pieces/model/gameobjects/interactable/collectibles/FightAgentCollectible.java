package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.Core;

import java.util.Arrays;
import java.util.Objects;


public class FightAgentCollectible extends GameObject implements Collectible<MissionController>
{
    private final FightAgent fightAgent;
    
    
    public FightAgentCollectible(FightAgent fightAgent)
    {
        super("Resting " + fightAgent.name(),
              fightAgent.description() + "\r\nThey would like to join your army",
              fightAgent.getSpriteFilePath());
        this.fightAgent = fightAgent;
    }
    
    
    @Override
    public void addToInventory()
    {
        FightAgent fightAgent = getFightAgent();
        Mission mission = ActiveGameController.getInstance().get().getMission();
        
        FightAgent[] activeFightAgents = mission.getActiveFightAgents();
        Core.insertAtFirstNullIndex(activeFightAgents, fightAgent);
        
        if (Arrays.asList(activeFightAgents).contains(fightAgent))
        {
            return;
        }
        
        FightAgent[] discardedFightAgents = mission.getDiscardedFightAgents();
        Core.insertAtFirstNullIndex(discardedFightAgents, fightAgent);
    }
    
    
    @Override
    public boolean isCollectionPossible()
    {
        Mission mission = ActiveGameController.getInstance().get().getMission();
        return Arrays.stream(mission.getActiveFightAgents()).anyMatch(Objects::isNull) ||
               Arrays.stream(mission.getDiscardedFightAgents()).anyMatch(Objects::isNull);
    }
    
    
    public FightAgent getFightAgent()
    {
        return fightAgent;
    }
}
