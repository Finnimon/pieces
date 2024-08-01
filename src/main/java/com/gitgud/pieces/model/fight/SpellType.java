package com.gitgud.pieces.model.fight;

/**
 * Indicates the type of effect a {@link Spell} has.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.07.2024
 * @Version: 1.0
 * @see Spell
 * @see SpellCaster
 */
public enum SpellType
{
    /**
     * Permanently Heals a friendly {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent}
     *
     * @see com.gitgud.pieces.utility.modification.fightAgent.FightAgentHealthModifier
     */
    HEAL(),
    /**
     * Causes damage to a non-friendly {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent}
     *
     * @see com.gitgud.engine.model.attackDefenseLogic.Attack
     */
    DAMAGE(),
    /**
     * Applies a {@link com.gitgud.engine.model.Durable} buff to a friendly
     * {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent},
     *
     * @see com.gitgud.engine.model.Durable
     */
    BUFF(),
    /**
     * Applies a {@link com.gitgud.engine.model.Durable} debuff to a non-friendly
     * {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent},
     *
     * @see com.gitgud.engine.model.Durable
     */
    DEBUFF();
    
    
    public boolean getIsFriendlyTargeting()
    {
        return this == BUFF || this == HEAL;
    }
}
