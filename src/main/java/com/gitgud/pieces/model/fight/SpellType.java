package com.gitgud.pieces.model.fight;

public enum SpellType
{
    HEAL,
    DAMAGE,
    BUFF,
    DEBUFF;
    
    public boolean getIsFriendlyTargeting()
    {
        return this == BUFF || this == HEAL;
    }
}
