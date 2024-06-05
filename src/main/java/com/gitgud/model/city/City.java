package com.gitgud.model.city;

import com.gitgud.model.city.buildings.CityBuilding;

import java.util.Collection;


/**
 * The City. It's where all the {@link com.gitgud.model.city.buildings.CityBuilding}s are.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class City
{
    private final Collection<CityBuilding> cityBuildings;
    
    
    public City(Collection<CityBuilding> cityBuildings)
    {
        this.cityBuildings = cityBuildings;
    }
    
    
    public Collection<CityBuilding> getCityBuildings()
    {
        return cityBuildings;
    }
}
