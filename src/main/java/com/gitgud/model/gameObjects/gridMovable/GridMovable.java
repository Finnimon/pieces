package com.gitgud.model.gameObjects.gridMovable;

import com.gitgud.control.GridMovableController;
import com.gitgud.model.gameObjects.GridMappable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.GridMapContext;


/**
 * Objects with a range of movement and a type of movement
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface GridMovable extends GridMappable
{
    public int getMovementRange();
    
    
    public boolean isFlying();
    
    
    GridMovableController<Agent> getMovableController(GridMap<Agent> gridMap);
}
