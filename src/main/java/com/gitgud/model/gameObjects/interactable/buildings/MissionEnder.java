package com.gitgud.model.gameObjects.interactable.buildings;

import com.gitgud.control.MissionController;


public class MissionEnder extends GridMappableBuilding
{
    private static final String NAME = "Portal Home";
    private static final String DESCRIPTION = "This portal leads the Black King home to his City";
    private static final String SPRITE_URL = "";//todo !!!!
    public MissionEnder()
    {
        super(NAME, DESCRIPTION, SPRITE_URL);
    }
    
    
    /**
     * Sets the mission as finished
     * @param missionController the mission controller in whose data this interactable was triggered
     */
    @Override
    public void interact(MissionController missionController)
    {
        missionController.getMission().setFinished(true);
    }
}
