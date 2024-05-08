package com.gitgud.utility.interfaces;

import com.gitgud.control.GridMovableController;
import com.gitgud.model.mission.GridMapContext;


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
    
    
    public GridMovableController getMovableController(GridMapContext gridMapContext);
}
