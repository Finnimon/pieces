package com.gitgud.engine.model.action;


public interface TargetedAction<Awaiter extends ActionAwaiter, Target> extends Action<Awaiter>
{
    Target getTarget();
}
