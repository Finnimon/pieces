package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMapContext;
import com.gitgud.model.map.GridMapping;


public class FightAgentController extends GridMovableController implements Comparable<FightAgentController>
{
    
    public FightAgentController(GridMapContext<FightAgent> gridMapContext, GridMapping<FightAgent> gridMapping)
    {
        super(gridMapContext, gridMapping);
    }
    
    
    public FightAgentController(GridMapContext<FightAgent> gridMapContext, FightAgent FightFigure)
    {
        super(gridMapContext, FightFigure);
    }
    
    public FightAgent getFightFigure()
    {
        return (FightAgent) getGridMapping().getKey();
    }
    
    
    @Override
    public int compareTo(FightAgentController other)
    {
        return getFightFigure().getInitiative() - other.getFightFigure().getInitiative();
    }
}
