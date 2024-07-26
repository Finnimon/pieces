package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.mission.Mission;

import java.util.ArrayList;
import java.util.List;


public class HeadQuarter extends CityBuilding
{
    public HeadQuarter(String name, String description, int level)
    {
        super(name, description, level);
    }
    
    
    public HeadQuarter()
    {
        super("HeadQuarter", "Select missions here", 1);
    }
    
    
    public List<Mission> getAvailableMissions()
    {
        return getAllMissions().subList(0, getLevel());
    }
    
    
    public List<Mission> getAllMissions()
    {
        ArrayList<Mission> missions = new ArrayList<>();
        for (Missions mission : Missions.values())
        {
            missions.add(mission.ordinal(), mission.getMission());
        }
        
        
        return missions;
    }
}
