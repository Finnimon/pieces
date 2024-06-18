package com.gitgud.pieces.model.city;

import com.gitgud.pieces.model.city.buildings.CityBuilding;

import java.util.Collection;
import java.util.HashSet;


/**
 * The City. It's where all the {@link com.gitgud.pieces.model.city.buildings.CityBuilding}s are.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class City
{
    private final HashSet<CityBuilding> cityBuildings;
    
    
    public City(HashSet<CityBuilding> cityBuildings)
    {
        this.cityBuildings = cityBuildings;
    }
    
    
    public Collection<CityBuilding> getCityBuildings()
    {
        return cityBuildings;
    }
}
