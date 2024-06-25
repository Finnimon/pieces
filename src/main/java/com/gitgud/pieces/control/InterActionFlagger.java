package com.gitgud.pieces.control;

import com.gitgud.engine.control.InterActionController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.pieces.model.mission.Mission;


public class InterActionFlagger implements Runnable
{
    
    private final Mission mission;
    //    private final ActiveGame activeGame;
    
    
    private Interactable lastInteractable = null;
    
    
    private boolean newCheckNeeded = false;
    
    
    public InterActionFlagger(Mission mission)
    //    public InterActionFlagger()
    {
        //        activeGame = ActiveGameController.getInstance().get();
        this.mission = mission;
    }
    
    
    @Override
    public void run()
    {
        boolean inMissionFight = false;
        
        while (mission != null && !mission.isFinished())
        //        while (activeGame.getGameState() == GameState.MISSION || (inMissionFight = activeGame.getGameState() == GameState.MISSION_FIGHT))
        {
            synchronized (this){
                if (!newCheckNeeded)
                {
                    continue;
                }
                System.out.println("InterActionFlagger.run()");
                
                newCheckNeeded = false;
                setFlagIfNeeded(mission);
            }
        }
        
        InterActionController.clearFlag();
    }
    
    
    private void setFlagIfNeeded(Mission mission)
    {
        GameObject gameObject = mission.getGridMap().get(mission.getPlayerAgentPosition());
        
        if (gameObject == null)
        {
            return;
        }
        
        if (!(gameObject instanceof Interactable))
        {
            return;
        }
        
        if (lastInteractable == gameObject)
        {
            return;
        }
        
        lastInteractable = (Interactable) gameObject;
        
        InterActionController.setFlag((Interactable) gameObject);
    }
    
    public void newCheck()
    {
        newCheckNeeded = true;
        setFlagIfNeeded(mission);
    }
}
