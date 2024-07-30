package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


/**
 * Interactable that can be collected and will be removed from their containing {@link GridMap} upon interaction.
 * @param <AAType> The type of containing {@link ActionAwaitingController}
 */
public interface Collectible<AAType extends ActionAwaitingController<?, GameObject, ?>> extends Interactable<AAType>
{
    @Override
    default void interact(AAType missionController)
    {
        collect(missionController);
    }
    
    
    /**
     * If Collection is currently possible, will collect the {@link Collectible} and remove it from the {@link GridMap}.
     * @param awaiter the containing {@link ActionAwaitingController}
     */
    default void collect(AAType awaiter)
    {
        if (!isCollectionPossible())
        {
            return;
        }
        
        
        addToInventory();
        removeFromMap(awaiter);
    }
    
    
    /**
     * Determines if the {@link Collectible} can be collected.
     * @return If the {@link Collectible} can be collected.
     */
    default boolean isCollectionPossible()
    {
        return true;
    }
    
    
    /**
     * Adds this Collectible to the Players inventory or causes a resulting reaction.
     * @Precondition: The {@link #isCollectionPossible()} must return {@code true}.
     * @Postcondition: No undefined behaviour will occur.
     */
    void addToInventory();
    
    
    /**
     * Removes this Collectible from the {@link GridMap}.
     * @param awaiter the containing {@link ActionAwaitingController} to remove the {@link Collectible} from.
     */
    private void removeFromMap(AAType awaiter)
    {
        GridMap gridMap = awaiter.getModel().getGridMap();
        Tile vertex = (Tile) gridMap.getVertex(this);
        
        if (vertex == null)
        {
            return;
        }
        
        
        gridMap.clearVertex(vertex);
        
        awaiter.getRender().getGridMapRender().removeGridMappable((GameObject) this);
    }
    
    
}
