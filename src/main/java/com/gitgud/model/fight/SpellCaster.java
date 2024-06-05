package com.gitgud.model.fight;


import com.gitgud.control.ActiveGameController;
import com.gitgud.control.PlayerController;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;
import com.gitgud.model.player.Player;

import java.util.HashMap;
import java.util.HashSet;


public interface SpellCaster
{
    public SpellBook getSpellbook();
    
    
    public default HashMap<Spell, HashSet<Tile>> getSpellTargets(Fight fight)
    {
        HashMap<Spell, HashSet<Tile>> targets = new HashMap<>();
        
        
        for (Spell spell : getSpellbook().spells())
        {
          targets.put(spell,  extracted(spell, fight));
          
          
        }
        return null;
    }
    
    
    private HashSet<Tile> extracted(Spell spell, Fight fight)
    {
        GridMap<FightAgent> gridMap = fight.getGridMap();
        
        HashSet<Tile> targets = new HashSet<>();
        boolean friendlyTargeting = spell.getSpellType().getIsFriendlyTargeting();
        
        Player thisPlayer= ActiveGameController.getInstance().get().getPlayer();
        
        HashMap<Player, HashSet<FightAgent>> ownershipMap= fight.getOwnershipMap();
        for (Player player: fight.getOwnershipMap().keySet()
             )
        {
         throw new RuntimeException("Not Implemented");
        }
        
        return targets;
    }
    
    
    
}
