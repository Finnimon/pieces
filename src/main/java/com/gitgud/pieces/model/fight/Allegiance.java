package com.gitgud.pieces.model.fight;

public enum Allegiance
{
    BLACK,//friendly
    WHITE;//enemy
    
    
    public static final int TYPE_MULTIPLIER = 1000;
    
    
    private final int type;
    
    
    Allegiance()
    {
        this.type = ordinal() * TYPE_MULTIPLIER;
    }
    
    
    public int typeToInt()
    {
        return type;
    }
    
}
