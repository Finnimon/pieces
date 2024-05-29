package com.gitgud.control;

import com.gitgud.model.fight.Spell;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.gameObjects.gridMovable.SpellCasterFightAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;

import java.util.Collection;
import java.util.HashMap;


public class SpellCasterFightAgentController extends FightAgentController
{
    public SpellCasterFightAgentController(GridMap<FightAgent> gridMap, FightAgent fightAgent)
    {
        super(gridMap, fightAgent);
    }
    
    
    public SpellCasterFightAgentController(GridMap<FightAgent> gridMap, FightAgent fightAgent, Tile position)
    {
        super(gridMap, fightAgent, position);
    }
    
    
    @Override
    public SpellCasterFightAgent getFightAgent()
    {
        return (SpellCasterFightAgent) super.getFightAgent();
    }
}
