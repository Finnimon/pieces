package com.gitgud.engine.model.action;

public interface TargetedAction<Target> extends Action
{
    Target getTarget();
}
