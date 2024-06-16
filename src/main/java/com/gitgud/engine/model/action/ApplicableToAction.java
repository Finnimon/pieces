package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiter;
import com.gitgud.engine.model.action.types.Applicable;


public interface ApplicableToAction<Awaiter extends ActionAwaiter, ApplicableClass extends Applicable<Target>, Target> extends TargetedAction<Awaiter, Target>
{
    
    ApplicableClass getApplicable();
}
