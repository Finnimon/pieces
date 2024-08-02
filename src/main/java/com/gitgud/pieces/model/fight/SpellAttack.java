package com.gitgud.pieces.model.fight;

import com.gitgud.engine.model.Applicable;
import com.gitgud.engine.model.attackDefenseLogic.Attack;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import org.jetbrains.annotations.NotNull;


/**
 * Adapter for {@link Attack} to {@link Applicable} of FightAgent.
 *
 * @param attack
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @since 28.07.2024
 */
public record SpellAttack(Attack attack) implements Applicable<FightAgent>
{
    @Override
    public FightAgent apply(@NotNull FightAgent fightAgent)
    {
        attack.apply(fightAgent);
        return fightAgent;
    }
}
