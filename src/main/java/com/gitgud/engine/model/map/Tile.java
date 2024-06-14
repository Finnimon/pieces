package com.gitgud.engine.model.map;


/**
 * @param xPosition todo as javafx grouptodo remove Combat unit
 */
public record Tile(int xPosition, int yPosition, Terrain terrain)
{
    public void validatePosition(int xActual, int yActual)
    {
        if (xPosition() != xActual || yPosition() != yActual)
        {
            throw new MatchException("Tile at (" + xActual + "," + yActual + ") has wrong position", null);
        }
    }
}
