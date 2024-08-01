package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.player.ResourceType;


/**
 * Constants for the {@link CityBuilding}s.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public interface CityConstants
{
    //region FactionCamp
    ResourceCost LEVEL_UP_RESOURCE_COST = new ResourceCost(ResourceType.PLATINUM, 100);
    
    
    ResourceCost PAWN_COST = new ResourceCost(ResourceType.GOLD, 100);
    
    
    ResourceCost KNIGHT_COST = new ResourceCost(ResourceType.GOLD, 350);
    
    
    ResourceCost BISHOP_COST = new ResourceCost(ResourceType.GOLD, 400);
    
    
    ResourceCost ROOK_COST = new ResourceCost(ResourceType.GOLD, 400);
    
    
    ResourceCost QUEEN_COST = new ResourceCost(ResourceType.GOLD, 1000);
    //endregion
}
