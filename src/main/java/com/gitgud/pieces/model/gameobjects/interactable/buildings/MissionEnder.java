package com.gitgud.pieces.model.gameobjects.interactable.buildings;

import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;


public class MissionEnder extends GridMappableBuilding<MissionController>
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
