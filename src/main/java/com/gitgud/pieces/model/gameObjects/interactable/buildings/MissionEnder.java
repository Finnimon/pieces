package com.gitgud.pieces.model.gameObjects.interactable.buildings;

import com.gitgud.engine.model.gameObject.interactable.GridMappableBuilding;

import com.gitgud.engine.model.gameObject.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;


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
     *
     * @param gridMap the mission controller in whose data this interactable was triggered
     * @Owner: Finn L.
     * @Author. Finn L
     * @Since: 12.06.2024
     * @Version: 1.1
     */
    @Override
    public void interact(GridMap gridMap)
    {
        ActiveGameController.getInstance().get().getMission().setFinished(true);
    }
}
