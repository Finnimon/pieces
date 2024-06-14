package com.gitgud.pieces.model.gameObjects;

public enum FightAgentType
{
    PAWN ("pawn"),
    ROOK ("rook"),
    BISHOP ("bishop"),
    KNIGHT ("knight"),
    QUEEN ("queen"),
    KING ("king");

    private final String type;

    FightAgentType (String type)
    {
        this.type = type;
    }

    public String getAsString ()
    {
        return this.type;
    }

    public static FightAgentType fromString (String type)
    {
        for (FightAgentType t : FightAgentType.values()) {
            if (t.type.equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }
}
