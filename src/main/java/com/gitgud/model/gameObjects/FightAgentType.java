package com.gitgud.model.gameObjects;

public enum FightAgentType
{
    PAWN ("pawn", "src/main/resources/com/gitgud/gameObjectTypes/fightAgentTypes/pawn.json"),
    ROOK ("rook", "src/main/resources/com/gitgud/gameObjectTypes/fightAgentTypes/rook.json"),
    BISHOP ("bishop", "src/main/resources/com/gitgud/gameObjectTypes/fightAgentTypes/bishop.json"),
    KNIGHT ("knight", "src/main/resources/com/gitgud/gameObjectTypes/fightAgentTypes/knight.json"),
    QUEEN ("queen", "src/main/resources/com/gitgud/gameObjectTypes/fightAgentTypes/queen.json"),
    KING ("king", "src/main/resources/com/gitgud/gameObjectTypes/fightAgentTypes/king.json");

    private final String type;

    private final String assetUrl;

    FightAgentType (String type, String assetUrl)
    {
        this.type = type;
        this.assetUrl = assetUrl;
    }

    public String getAsString ()
    {
        return this.type;
    }

    public String getAssetUrl ()
    {
        return this.assetUrl;
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
