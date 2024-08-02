package com.gitgud.pieces.control.action;

import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.fight.SpellCaster;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public final class SpellCasterToAction implements ToAction<FightController, Tile>
{
    private final Tile to;
    
    
    private final FightController fightController;
    
    
    private final Spell spell;
    
    
    private final SpellCaster spellCaster;
    
    
    public SpellCasterToAction(Tile to, FightController fightController, Spell spell, SpellCaster spellCaster)
    {
        this.spellCaster = spellCaster;
        this.to = to;
        this.fightController = fightController;
        this.spell = spell;
    }
    
    
    @Override
    public void enAct(FightController actionAwaitingController)
    {
        FightAgent fightAgent = fightController.getModel().getGridMap().get(to);
        spellCaster.cast(spell, fightAgent);
        if (fightAgent.isDead())
        {
            fightController.getModel().getGridMap().clearVertex(getTo());
            fightController.getRender().getGridMapRender().removeGridMappable(fightAgent);
        }
        
    }
    
    
    @Override
    public Tile getTo()
    {
        return to;
    }
}
