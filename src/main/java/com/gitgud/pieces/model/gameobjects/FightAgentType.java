package com.gitgud.pieces.model.gameobjects;

public enum FightAgentType
{
    PAWN(),
    KNIGHT(),
    ROOK(),
    BISHOP(),
    QUEEN();
    
    
    
    public static final int TYPE_MULTIPLIER = 100;
    
    
    public int typeToInt()
    {
        return this.ordinal() * TYPE_MULTIPLIER;
    }
}
