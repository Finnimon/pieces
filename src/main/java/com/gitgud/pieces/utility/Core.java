package com.gitgud.pieces.utility;


import java.util.stream.IntStream;


/**
 * Various utility functions
 *
 * @author Finn Lindig
 * @Owner: Finn Lindig
 * @Since: 16.04.2020
 */
public record Core()
{
    /**
     * This function simulates rolling a chance based on the given probability.
     *
     * @param chance The probability of the event happening.
     * @return true if the event happens based on the probability, false otherwise.
     */
    public static boolean roll(float chance)
    {
        return Math.random() < chance;
    }
    
    
    public static <T> void setMatrixValuesSymmetrically(T[][] matric, int yIndex, int xIndex, T value)
    {
        matric[yIndex][xIndex] = value;
        matric[xIndex][yIndex] = value;
    }
    
    
    public static void insertAtFirstNullIndex(Object[] objects, Object object)
    {
        replaceFirstOccurrence(objects, null, object);
    }
    
    
    public static void replaceFirstOccurrence(Object[] objects, Object object, Object replacement)
    {
        IntStream.range(0, objects.length).filter(i -> objects[i] == object).findFirst().ifPresent(
                i -> objects[i] = replacement);
    }
    
    
}
