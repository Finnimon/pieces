package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.testing.Missions;


public class HeadQuarter extends CityBuilding
{
    private static final String FIRST_MISSION_JSON_FILE_PATH = "first_mission.json";
    
    
    private static final String SECOND_MISSION_JSON_FILE_PATH = "first_mission.json";
    
    
    private static final String THIRD_MISSION_JSON_FILE_PATH = "first_mission.json";
    
    
    private static final String FOURTH_MISSION_JSON_FILE_PATH = "first_mission.json";
    
    
    private static final String SIXTH_MISSION_JSON_FILE_PATH = "first_mission.json";
    
    
    private static final Mission mission = Missions.FIRST;
    
    
    public HeadQuarter(String name, String description, String spriteFilePath, int level)
    {
        super(name, description, level);
    }
    
    
    public HeadQuarter()
    {
        super("HeadQuarter", "Select missions here", 1);
    }
}
