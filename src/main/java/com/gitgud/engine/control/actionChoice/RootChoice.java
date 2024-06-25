package com.gitgud.engine.control.actionChoice;

import java.util.List;


public interface RootChoice<ChoiceType>
{
    public boolean isEmpty();
    
    public List<ChoiceType> getChoices();
}
