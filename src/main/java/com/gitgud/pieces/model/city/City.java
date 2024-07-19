package com.gitgud.pieces.model.city;

import com.gitgud.pieces.model.city.buildings.*;
import com.gitgud.pieces.model.gameobjects.Faction;


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
    private final BlackSmith blackSmith;
    
    
    private final FactionCamp monoChromeFactionCamp;
    
    
    private final FactionCamp pinkFactionCamp;
    
    
    private final FactionCamp greenFactionCamp;
    
    
    private final HeadQuarter headQuarter;
    
    
    private final MagicEnhancer magicEnhancer;
    
    
    private final Market market;
    
    
    private final TrainingGrounds trainingGrounds;
    
    
    public City(BlackSmith blackSmith, FactionCamp monoChromeFactionCamp, FactionCamp pinkFactionCamp,
                FactionCamp greenFactionCamp, HeadQuarter headQuarter, MagicEnhancer magicEnhancer, Market market,
                TrainingGrounds trainingGrounds)
    {
        this.blackSmith = blackSmith;
        this.monoChromeFactionCamp = monoChromeFactionCamp;
        this.pinkFactionCamp = pinkFactionCamp;
        this.greenFactionCamp = greenFactionCamp;
        this.headQuarter = headQuarter;
        this.magicEnhancer = magicEnhancer;
        this.market = market;
        this.trainingGrounds = trainingGrounds;
    }
    
    
    public City()
    {
        headQuarter = new HeadQuarter();
        blackSmith = null;
        market = null;
        magicEnhancer = null;
        trainingGrounds = null;
        monoChromeFactionCamp = null;
        pinkFactionCamp = null;
        greenFactionCamp = null;
    }
    
    
    private void validateFactionCamps()
    {
    }
    
    
    private boolean validateFactionCamp(FactionCamp factionCamp, Faction faction)
    {
        return factionCamp.getFaction().equals(faction);
    }
}
