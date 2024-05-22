package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;


public class SpellCasterFightAgentController extends FightAgentController
{
    public SpellCasterFightAgentController(GridMap<FightAgentFL> gridMap, FightAgentFL fightAgent)
    {
        super(gridMap, fightAgent);
    }
    
    
    public SpellCasterFightAgentController(GridMap<FightAgentFL> gridMap, FightAgentFL fightAgent, Tile position)
    {
        super(gridMap, fightAgent, position);
    }
    
}
