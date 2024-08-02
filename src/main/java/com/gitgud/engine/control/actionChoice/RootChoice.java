package com.gitgud.engine.control.actionChoice;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * Base Interface of all {@link ActionChoice}s that are not Leafs in the Choice Tree
 *
 * @param <ChoiceType>
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @see ActionChoice
 * @since 28.06.2024
 */
public interface RootChoice<ChoiceType>
{
    /**
     * Determines and returns if this Choice without Leaf functionality has no child choices and can therefore not be
     * selected
     *
     * @return true if this choice has no child choices
     */
    default boolean isEmpty()
    {
        if (getChildren().isEmpty())
        {
            return true;
        }
        
        for (var child : getChildren())
        {
            if (!(child instanceof RootChoice root))
            {
                return false;
            }
            
            if (!root.isEmpty())
            {
                return false;
            }
            
        }
        
        return true;
    }
    
    
    /**
     * Gets all child {@link ActionChoice}s of this RootChoice
     *
     * @return the list of child {@link ActionChoice}s
     */
    @NotNull List<ChoiceType> getChildren();
}
