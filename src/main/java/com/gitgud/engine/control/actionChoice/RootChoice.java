package com.gitgud.engine.control.actionChoice;

import java.util.List;


public interface RootChoice<ChoiceType>
{
    default boolean isEmpty()
    {
        return getChoices().isEmpty();
    }
    
    
    List<ChoiceType> getChoices();
}
