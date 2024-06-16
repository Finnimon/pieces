package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiter;


public interface TargetedAction<Awaiter extends ActionAwaiter, Target> extends Action<Awaiter>
{
    Target getTarget();
}
