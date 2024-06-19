package com.gitgud.engine.model.action;

import com.gitgud.engine.model.action.types.Applicable;
import com.gitgud.engine.model.map.Tile;
import javafx.geometry.Point2D;


public interface ApplicableToAction<Awaiter extends ActionAwaiter, ApplicableClass extends Applicable<Target>,Point extends Point2D, Target> extends ToAction<Awaiter, Point>
{
    
    ApplicableClass getApplicable();
}
