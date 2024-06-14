package com.gitgud.engine.model.action;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;


public abstract class Reaction extends Event
{
    public Reaction(EventType<? extends Event> eventType)
    {
        super(eventType);
    }
    
    
    public Reaction(Object source, EventTarget target, EventType<? extends Event> eventType)
    {
        super(source, target, eventType);
    }
}
