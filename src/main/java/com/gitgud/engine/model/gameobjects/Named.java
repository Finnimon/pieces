package com.gitgud.engine.model.gameobjects;

/**
 * An object with a name
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @since 22.04.2024
 */
public interface Named
{
    String WHITESPACE = " ";
    
    
    /**
     * Formats a String to be usable as a FileName.
      * @param string The String to format.
     * @return the formatted String.
     */
    static String formatString(String string)
    {
        String[] array = string.split(WHITESPACE);
        
        for (int i = 0; i < array.length; i++)
        {
            array[i] = array[i].substring(0, 1).toUpperCase() + array[i].substring(1).toLowerCase();
        }
        return String.join(WHITESPACE, array);
    }
    
    
    /**
     * Getter for the displayable name of an Object.
     * @return The name of the Object.
     */
    String name();
}
