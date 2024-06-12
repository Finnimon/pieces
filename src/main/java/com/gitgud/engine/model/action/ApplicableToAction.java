package com.gitgud.engine.model.action;

import com.gitgud.engine.model.action.types.Applicable;


public interface ApplicableToAction<A extends Applicable<Target>, Target> extends TargetedAction<Target>
{
    A getApplicable();
}
