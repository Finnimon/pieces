package com.gitgud.pieces.model.gameobjects;

public enum FightAgentType
{
    PAWN("pawn"), ROOK("rook"), BISHOP("bishop"), KNIGHT("knight"), QUEEN("queen");
    //    , KING("king");
    
    
    public static final String DOT_JSON = ".json";
    
    
    public static final String DIRECTORY_FILEPATH = "src\\main\\resources\\com\\gitgud\\gameObjectTypes\\fightAgentTypes\\";
    
    
    public static final int TYPE_MULTIPLIER = 100;
    
    
    private final String type;
    
    
    private final String assetUrl;
    
    
    FightAgentType(String type)
    {
        this.type = type;
        this.assetUrl = DIRECTORY_FILEPATH + type + DOT_JSON;
    }
    
    
    public static FightAgentType fromString(String type)
    {
        for (FightAgentType t : FightAgentType.values())
        {
            if (t.type.equalsIgnoreCase(type))
            {
                return t;
            }
        }
        return null;
    }
    
    
    public int typeToInt()
    {
        return this.ordinal() * TYPE_MULTIPLIER;
    }
    
    
    public String getAsString()
    {
        return this.type;
    }
    
    
    public String getAssetUrl()
    {
        return this.assetUrl;
    }
}
