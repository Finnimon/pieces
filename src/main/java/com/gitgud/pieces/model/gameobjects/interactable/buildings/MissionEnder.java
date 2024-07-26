package com.gitgud.pieces.model.gameobjects.interactable.buildings;

import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.MissionController;


public class MissionEnder extends GridMappableBuilding<MissionController>
{
    private static final String NAME = "Portal Home";
    
    
    private static final String DESCRIPTION = "This portal leads you back home," +
                                              Strings.LINE_BREAK +
                                              " to your Kingdom of Ruins.";
    
    
    private static final String SPRITE_FILEPATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\interactable\\buildings\\MissionEnder.png";
    
    
    public MissionEnder()
    {
        super(NAME, DESCRIPTION, SPRITE_FILEPATH);
    }
    
    
    /**
     * Sets the mission as finished
     *
     * @param missionController the mission controller in whose data this interactable was triggered
     * @Owner: Finn L.
     * @Author. Finn L
     * @Since: 12.06.2024
     * @Version: 1.1
     */
    @Override
    public void interact(MissionController missionController)
    {
        missionController.getModel().setFinished(true);
    }
}
