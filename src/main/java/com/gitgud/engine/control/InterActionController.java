package com.gitgud.engine.control;

import com.gitgud.engine.model.gameobjects.interactable.Interactable;

import java.util.Observable;


public class InterActionController
{
    private static InterActionController instance;
    private Interactable interactable;
    
    private InterActionController()
    {
    
    }
    
    public static InterActionController getInstance()
    {
        if (instance == null)
        {
            instance = new InterActionController();
        }
        
        return instance;
    }
    
    public Interactable addFlag(Interactable interactable)
    {
        Interactable oldInteractable=this.interactable;
        this.interactable=interactable;
        return oldInteractable;
    }
    
}
