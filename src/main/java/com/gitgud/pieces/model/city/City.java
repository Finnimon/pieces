package com.gitgud.pieces.model.city;

import com.gitgud.pieces.model.city.buildings.*;
import com.gitgud.pieces.model.city.buildings.headQuarter.HeadQuarter;
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
        monoChromeFactionCamp = new FactionCamp(Faction.MONOCHROME);
        pinkFactionCamp = new FactionCamp(Faction.PINK);
        greenFactionCamp = new FactionCamp(Faction.GREEN);
    }
    
    
    public BlackSmith getBlackSmith()
    {
        return blackSmith;
    }
    
    
    public FactionCamp getMonoChromeFactionCamp()
    {
        return monoChromeFactionCamp;
    }
    
    
    public FactionCamp getPinkFactionCamp()
    {
        return pinkFactionCamp;
    }
    
    
    public FactionCamp getGreenFactionCamp()
    {
        return greenFactionCamp;
    }
    
    
    public HeadQuarter getHeadQuarter()
    {
        return headQuarter;
    }
    
    
    public MagicEnhancer getMagicEnhancer()
    {
        return magicEnhancer;
    }
    
    
    public Market getMarket()
    {
        return market;
    }
    
    
    public TrainingGrounds getTrainingGrounds()
    {
        return trainingGrounds;
    }
}
