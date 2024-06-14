package com.gitgud.pieces.control;

import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.gameObjects.agents.SpellCasterFightAgent;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


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
