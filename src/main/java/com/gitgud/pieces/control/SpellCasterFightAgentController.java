package com.gitgud.pieces.control;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;


public class SpellCasterFightAgentController extends FightAgentController
{
    public SpellCasterFightAgentController(GridMap<FightAgent> gridMap, FightAgent fightAgent)
    {
        super(fightAgent);
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
