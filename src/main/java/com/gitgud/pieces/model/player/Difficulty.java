package com.gitgud.pieces.model.player;

import com.gitgud.engine.model.Applicable;
import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.control.EnemyAlgorithm;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentAttackModifier;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentDefenceModifier;
import org.jetbrains.annotations.NotNull;


public enum Difficulty implements Applicable<Fight>
{
    EASY(new FightAgentAttackModifier(0, 0, 0)),
    
    MEDIUM(new FightAgentDefenceModifier(2, 0, 1.2f)),
    
    HARD(new FightAgentAttackModifier(2, 3, 2f));
    
    
    private final Modifier<FightAgent> modifier;
    
    
    Difficulty(Modifier<FightAgent> fightAgentAttackModifier)
    {
        this.modifier = fightAgentAttackModifier;
    }
    
    
    public static Difficulty fromString(String string)
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
    
    
    public Modifier<FightAgent> getFightAgentAttackModifier()
    {
        return modifier;
    }
    
    
    public String getAsString()
    {
        return this.toString();
    }
    
    
    @Override
    public Fight apply(@NotNull Fight fight)
    {
        fight.getGridMap()
             .nonNullElements()
             .stream()
             .filter(x -> x.getAllegiance() == EnemyAlgorithm.ENEMY_ALLEGIANCE)
             .forEach(modifier::modify);
        return fight;
    }
    
}
