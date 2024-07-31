package com.gitgud.pieces.model.city.buildings.headQuarters;

import com.gitgud.pieces.model.city.buildings.CityBuilding;
import com.gitgud.pieces.model.mission.Mission;

import java.util.List;


/**
 * CityBuilding for selecting missions.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 10.06.2024
 * @Version: 2.0
 */
public class HeadQuarters extends CityBuilding
{
    public HeadQuarters(String name, String description, int level)
    {
        super(name, description, level);
    }
    
    
    public HeadQuarters()
    {
        super("HeadQuarters", "Select missions here", 1);
    }
    
    
    /**
     * Gets all currently available {@link MissionSelection}s, determined by the player's progress.
     * @return All available {@link MissionSelection}s
     */
    public List<MissionSelection> getAvailableMissionSelections()
    {
        return List.of(MissionSelection.values()).subList(0, getLevel());
    }
}
