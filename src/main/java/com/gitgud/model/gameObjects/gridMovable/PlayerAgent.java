package com.gitgud.model.gameObjects.gridMovable;


public class PlayerAgent extends Agent
{
    private static final boolean IS_FLYING = false;
    
    
    private static final int MOVEMENT_RANGE = 8;
    
    
    private static final String NAME = "Black King";
    
    
    private static final String DESCRIPTION = "Once a just king. Now broken by many defensive battles he marches forth to retake all his lands.";
    
    
    private static final String SPRITE_PATH = "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_king.png";
    
    
    public PlayerAgent()
    {
        super(NAME, DESCRIPTION, SPRITE_PATH, IS_FLYING, MOVEMENT_RANGE);
    }
}
