package com.gitgud.engine.control;

import com.gitgud.engine.model.gameobjects.interactable.Interactable;


public class InterActionController
{
    private static InterActionController instance;
    private Interactable interactable;
    
    private InterActionController()
    {
    
    }
    
    private static InterActionController getInstance()
    {
        if (instance == null)
        {
            instance = new InterActionController();
        }
        
        return instance;
    }
    
    public static Interactable setFlag(Interactable interactable)
    {
        Interactable oldInteractable=getInstance().interactable;
        getInstance().interactable=interactable;
        return oldInteractable;
    }
    
    public static boolean hasFlag()
    {
        return getInstance().interactable!=null;
    }
    
    public static Interactable clearFlag()
    {
        Interactable oldInteractable=getInstance().interactable;
        getInstance().interactable=null;
        return oldInteractable;
    }
}
