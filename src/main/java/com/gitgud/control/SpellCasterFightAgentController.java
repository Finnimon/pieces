package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.gameObjects.gridMovable.SpellCasterFightAgent;
import com.gitgud.model.map.GridMapContext;
import com.gitgud.model.map.GridMapping;


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
