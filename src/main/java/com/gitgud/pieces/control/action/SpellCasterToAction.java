package com.gitgud.pieces.control.action;

import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.fight.SpellCaster;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public final class SpellCasterToAction implements ToAction<FightController, Tile>
{
    private final Tile tile;
    
    
    private final FightController fightController;
    
    
    private final Spell spell;
    
    
    private final SpellCaster spellCaster;
    
    
    public SpellCasterToAction(Tile tile, FightController fightController, Spell spell, SpellCaster spellCaster)
    {
        this.spellCaster = spellCaster;
        this.tile = tile;
        this.fightController = fightController;
        this.spell = spell;
    }
    
    
    @Override
    public Tile getTo()
    {
        return tile;
    }
    
    
    @Override
    public void enAct(FightController actionAwaitingController)
    {
        FightAgent fightAgent = fightController.getModel().getGridMap().get(tile);
        spellCaster.cast(spell, fightAgent);
        if (fightAgent.isDead())
        {
            fightController.getRender().getGridMapRender().removeGridMappable(fightAgent);
        }
        
    }
}
