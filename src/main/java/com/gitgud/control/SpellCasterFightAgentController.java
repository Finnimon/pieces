package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.gameObjects.gridMovable.SpellCasterFightAgent;


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
