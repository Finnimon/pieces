package com.gitgud.pieces.model.fight;

/**
 * The allegiance of a {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent}.
 */
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
    
    
    /**
     * Helper method for {@link com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector}.
     * @return the type of the {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent} as int.
     */
    public int typeToInt()
    {
        return type;
    }
    
}
