package com.gitgud.control;

import com.gitgud.model.gameObjects.FightAgent;
import com.gitgud.model.gameObjects.SpellCasterFightAgent;
import com.gitgud.model.mission.GridMapContext;
import com.gitgud.model.mission.map.GridMapping;


public class SpellCasterFightAgentController extends FightAgentController
{
    public SpellCasterFightAgentController(GridMapContext<FightAgent> gridMapContext, GridMapping gridMapping)
    {
        super(gridMapContext, gridMapping);
    }
    
    
    public SpellCasterFightAgentController(GridMapContext<FightAgent> gridMapContext, FightAgent FightFigure)
    {
        super(gridMapContext, FightFigure);
    }
    
    
    @Override
    public SpellCasterFightAgent getFightFigure()
    {
        return (SpellCasterFightAgent) super.getFightFigure();
    }
}
