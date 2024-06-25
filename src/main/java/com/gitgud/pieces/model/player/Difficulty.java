package com.gitgud.pieces.model.player;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentAttackModifier;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentDefenceModifier;


public enum Difficulty
{
    EASY(new FightAgentAttackModifier(0, 0, 0)),

    MEDIUM(new FightAgentDefenceModifier(2, 0, 1.2f)),

    HARD(new FightAgentAttackModifier(2, 3, 2f));


    private final Modifier<FightAgent> modifier;
    
    
    Difficulty(Modifier<FightAgent> fightAgentAttackModifier)
    {
        this.modifier = fightAgentAttackModifier;
    }
    
    
    public Modifier<FightAgent> getFightAgentAttackModifier ()
    {
        return modifier;
    }


    public String getAsString ()
    {
        return this.toString();
    }


    public static Difficulty fromString (String string)
    {
        for (Difficulty difficulty : Difficulty.values())
        {
            if (string.equalsIgnoreCase(difficulty.toString()))
            {
                return difficulty;
            }
        }
        return null;
    }
}
