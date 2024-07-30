package com.gitgud.pieces.model.fight;


import com.gitgud.engine.model.map.Tile;

import java.util.HashMap;
import java.util.HashSet;


public interface SpellCaster
{
    default HashMap<Spell, HashSet<Tile>> getSpellTargets(Fight fight)
    {
        HashMap<Spell, HashSet<Tile>> targets = new HashMap<>();
        
        
        for (Spell spell : getSpellbook().spells())
        {
            //            targets.put(spell, extracted(spell, fight));
            
            
        }
        return null;
    }
    
    
    SpellBook getSpellbook();
    
    
    //    private HashSet<Tile> extracted(Spell spell, Fight fight)
    //    {
    //        GridMap<FightAgent> gridMap = fight.getGridMap();
    //
    //        HashSet<Tile> targets = new HashSet<>();
    //        boolean friendlyTargeting = spell.getType().getIsFriendlyTargeting();
    //
    //        Player thisPlayer = ActiveGameController.getInstance().get().getPlayer();
    //
    //        HashMap<Player, HashSet<FightAgent>> ownershipMap = fight.getOwnershipMap();
    //        for (Player player : fight.getOwnershipMap().keySet())
    //        {
    //            throw new RuntimeException("Not Implemented");
    //        }
    //
    //        return targets;
    //    }
    
    
}
