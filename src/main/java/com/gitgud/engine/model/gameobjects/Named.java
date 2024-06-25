package com.gitgud.engine.model.gameobjects;

public interface Named
{
    
    
    String WHITESPACE = " ";
    
    
    String name();
    static String formatString(String string)
    {
        String[] array= string.split(WHITESPACE);

        for (int i = 0; i < array.length; i++)
        {
            array[i] = array[i].substring(0, 1).toUpperCase() + array[i].substring(1).toLowerCase();
        }
        return String.join(WHITESPACE, array);
    }
}
