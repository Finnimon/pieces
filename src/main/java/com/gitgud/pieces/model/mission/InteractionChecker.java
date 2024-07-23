package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.MissionController;


public class InteractionChecker
{
    private static boolean isInterActionPossible(Mission mission, Tile tile)
    {
        return mission.getGridMap().getNeighbors(tile).contains(mission.getPlayerAgentPosition());
    }
    
    
    public static void interactIfPossible(MissionController missionController, Tile tile)
    {
        if (!(missionController.getModel().getGridMap().get(tile) instanceof Interactable interactable)) return;
        
        if (isInterActionPossible(missionController.getModel(), tile))
        {
            interactable.interact(missionController);
            missionController.advance();
        }
    }
}
