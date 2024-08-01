package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.MissionController;


/**
 * Helperclass for Interactions in {@link com.gitgud.pieces.view.render.mission.MissionRender.MissionRender}.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see com.gitgud.pieces.view.render.mission.MissionRender.MissionRender#addInteractionHandlers(MissionController)
 * @since 30.06.2022
 */
public class InteractionChecker
{
    /**
     * Private Constructor to prevent Instantiation.
     */
    private InteractionChecker()
    {
    }
    
    
    public static void interactIfPossible(MissionController missionController, Tile tile)
    {
        if (!(missionController.getModel().getGridMap().get(tile) instanceof Interactable interactable))
        {
            return;
        }
        
        if (isInterActionPossible(missionController.getModel(), tile))
        {
            interactable.interact(missionController);
            missionController.advance();
        }
    }
    
    
    private static boolean isInterActionPossible(Mission mission, Tile tile)
    {
        return mission.getPlayerAgentPosition().distance(tile) <= Math.sqrt(2);
    }
}
