package com.gitgud.utility;

/**
 * Stores the progress of the active Game
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public class MissionProgress
{
    private boolean completedFirstMission;
    
    
    public MissionProgress(boolean completedFirstMission)
    {
        this.completedFirstMission = completedFirstMission;
    }
    
    
    public boolean isCompletedFirstMission()
    {
        return completedFirstMission;
    }
    
    
    public void setCompletedFirstMission(boolean completedFirstMission)
    {
        this.completedFirstMission = completedFirstMission;
    }
}
