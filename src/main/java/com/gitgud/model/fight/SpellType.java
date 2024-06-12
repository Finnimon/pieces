package com.gitgud.model.fight;


import com.gitgud.model.gameObjects.FightAgentType;
public enum SpellType
{
    HEAL("heal"),
    DAMAGE("damage"),
    BUFF("buff"),
    DEBUFF("debuff");

    private final String type;

    SpellType (String type)
    {
        this.type = type;
    }

    public String getAsString ()
    {
        return this.type;
    }

    public static SpellType fromString (String string)
    {
        for (SpellType t : SpellType.values()) {
            if (t.type.equalsIgnoreCase(string)) {
                return t;
            }
        }
        return null;
    }

    public boolean getIsFriendlyTargeting ()
    {
        return this == BUFF || this == HEAL;
    }
}
