package com.gitgud.pieces.model.fight;


import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;


/**
 * The FightTimeLine for an ongoing fight.
 *
 * @param current The current fightAgents.
 * @param next    The next fightAgents.
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @since 16.05.2024
 */
public record FightTimeLine(@NotNull TreeSet<FightAgent> current, @NotNull TreeSet<FightAgent> next)
{
    
    /**
     * Factory Method for {@link FightTimeLine}. Adds all {@link FightAgent} to current and next becomes an empty
     * TreeSet.
     *
     * @param fightAgents The fightAgents.
     * @return The {@link FightTimeLine}.
     */
    public static FightTimeLine create(@NotNull Collection<FightAgent> fightAgents)
    {
        TreeSet<FightAgent> current = new TreeSet<>(fightAgents.stream().filter(Objects::nonNull).toList());
        TreeSet<FightAgent> next = new TreeSet<>();
        
        return new FightTimeLine(current, next);
    }
    
    
    /**
     * Advances the timeline by one step.
     */
    public synchronized void advance()
    {
        TreeSet<FightAgent> fightTimeLine = current();
        TreeSet<FightAgent> nextTimeLine = next();
        removeDeadFightAgents();
        
        nextTimeLine.add(fightTimeLine.removeFirst());
        
        if (!fightTimeLine.isEmpty())
        {
            return;
        }
        
        fightTimeLine.addAll(nextTimeLine);
        nextTimeLine.clear();
    }
    
    
    /**
     * Removes all dead {@link FightAgent} from {@link #current()} and {@link #next()}.
     */
    public void removeDeadFightAgents()
    {
        current.removeIf(Defender::isDead);
        next.removeIf(Defender::isDead);
    }
    
    
    /**
     * Gets the currently active {@link FightAgent}, that is the head element of {@link #current()}.
     *
     * @return The currently active {@link FightAgent}.
     */
    public FightAgent getActiveFightAgent()
    {
        return current.first();
    }
    
    
    /**
     * Gets all agents in {@link #current()} and {@link #next()}.
     *
     * @return All agents in {@link #current()} and {@link #next()}.
     */
    public ArrayList<FightAgent> getAllAgents()
    {
        ArrayList<FightAgent> fightAgents = new ArrayList<>(current);
        fightAgents.addAll(next);
        return fightAgents;
    }
    
}
