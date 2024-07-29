package com.gitgud.pieces.model.city.buildings.headQuarter;

import com.gitgud.pieces.model.city.buildings.CityBuilding;
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
        for (MissionSelection mission : MissionSelection.values())
        {
            missions.add(mission.ordinal(), mission.getMission());
        }
        
        
        return missions;
    }
    
    
    @Override
    public int levelUp()
    {
        incrementLevel();
        return getLevel();
    }
}
