package com.gitgud.pieces.model.city;

import com.gitgud.pieces.model.city.buildings.*;
import com.gitgud.pieces.model.city.buildings.headQuarters.HeadQuarters;
import com.gitgud.pieces.model.gameobjects.Faction;
import org.jetbrains.annotations.NotNull;


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
    /**
     * The model for the playerReborn.
     */
    private final PlayerReborn playerReborn;
    
    
    /**
     * The model for the blacksmith.
     */
    private final BlackSmith blackSmith;
    
    
    /**
     * The model for the monoChromeFactionCamp.
     */
    private final FactionCamp monoChromeFactionCamp;
    
    
    /**
     * The model for the pinkFactionCamp.
     */
    private final FactionCamp pinkFactionCamp;
    
    
    /**
     * The model for the greenFactionCamp.
     */
    private final FactionCamp greenFactionCamp;
    
    
    /**
     * The model for the headQuarters.
     */
    private final HeadQuarters headQuarters;
    
    
    /**
     * The model for the magicEnhancer.
     */
    private final MagicEnhancer magicEnhancer;
    
    
    /**
     * The model for the market.
     */
    private final Market market;
    
    
    /**
     * The model for the trainingGrounds.
     */
    private final TrainingGrounds trainingGrounds;
    
    
    /**
     * Creates a new {@link City} and assigns the passed in
     * {@link com.gitgud.pieces.model.city.buildings.CityBuilding}s.
     *
     * @param blackSmith            The model for the blacksmith.
     * @param monoChromeFactionCamp The model for the monoChromeFactionCamp.
     * @param pinkFactionCamp       The model for the pinkFactionCamp.
     * @param greenFactionCamp      The model for the greenFactionCamp.
     * @param headQuarters          The model for the headQuarters.
     * @param magicEnhancer         The model for the magicEnhancer.
     * @param market                The model for the market.
     * @param trainingGrounds       The model for the trainingGrounds.
     * @throws IllegalArgumentException If any passed FactionCamps factions do not match their name.
     * @Precondition: The {@link FactionCamp}s all have the proper Faction.
     * @Postcondition: No undefined behaviour will occur. No Exceptions will be thrown.
     */
    public City(@NotNull PlayerReborn playerReborn, @NotNull BlackSmith blackSmith,
                @NotNull FactionCamp monoChromeFactionCamp, @NotNull FactionCamp pinkFactionCamp,
                @NotNull FactionCamp greenFactionCamp, @NotNull HeadQuarters headQuarters,
                @NotNull MagicEnhancer magicEnhancer, @NotNull Market market, @NotNull TrainingGrounds trainingGrounds)
    {
        this.playerReborn = playerReborn;
        this.blackSmith = blackSmith;
        this.monoChromeFactionCamp = monoChromeFactionCamp;
        this.pinkFactionCamp = pinkFactionCamp;
        this.greenFactionCamp = greenFactionCamp;
        this.headQuarters = headQuarters;
        this.magicEnhancer = magicEnhancer;
        this.market = market;
        this.trainingGrounds = trainingGrounds;
        assertFactionCampNamesMatch();
    }
    
    
    /**
     * Asserts that the {@link FactionCamp}s all have the proper Faction.
     *
     * @throws IllegalArgumentException If any FactionCamps factions do not match their name.
     */
    private void assertFactionCampNamesMatch()
    {
        if (!monoChromeFactionCamp.getFaction().equals(Faction.MONOCHROME))
        {
            throw new IllegalArgumentException("The monoChromeFactionCamp does not have the proper faction.");
        }
        
        if (!pinkFactionCamp.getFaction().equals(Faction.PINK))
        {
            throw new IllegalArgumentException("The pinkFactionCamp does not have the proper faction.");
        }
        
        if (!greenFactionCamp.getFaction().equals(Faction.GREEN))
        {
            throw new IllegalArgumentException("The greenFactionCamp does not have the proper faction.");
        }
    }
    
    
    /**
     * Creates a new {@link City} and assigns starting {@link com.gitgud.pieces.model.city.buildings.CityBuilding}s
     * to it.
     */
    public City()
    {
        headQuarters = new HeadQuarters();
        blackSmith = new BlackSmith();
        market = new Market();
        magicEnhancer = new MagicEnhancer();
        trainingGrounds = new TrainingGrounds();
        monoChromeFactionCamp = new FactionCamp(Faction.MONOCHROME);
        pinkFactionCamp = new FactionCamp(Faction.PINK);
        greenFactionCamp = new FactionCamp(Faction.GREEN);
        playerReborn = new PlayerReborn();
    }
    
    
    /**
     * Getter for the blackSmith.
     *
     * @return The blackSmith.
     */
    public BlackSmith getBlackSmith()
    {
        return blackSmith;
    }
    
    
    /**
     * Getter for the monoChromeFactionCamp.
     *
     * @return The monoChromeFactionCamp.
     */
    public FactionCamp getMonoChromeFactionCamp()
    {
        return monoChromeFactionCamp;
    }
    
    
    /**
     * Getter for the pinkFactionCamp.
     *
     * @return The pinkFactionCamp.
     */
    public FactionCamp getPinkFactionCamp()
    {
        return pinkFactionCamp;
    }
    
    
    /**
     * Getter for the greenFactionCamp.
     *
     * @return The greenFactionCamp.
     */
    public FactionCamp getGreenFactionCamp()
    {
        return greenFactionCamp;
    }
    
    
    /**
     * Getter for the headQuarters.
     *
     * @return The headQuarters.
     */
    public HeadQuarters getHeadQuarters()
    {
        return headQuarters;
    }
    
    
    /**
     * Getter for the magicEnhancer.
     *
     * @return The magicEnhancer.
     */
    public MagicEnhancer getMagicEnhancer()
    {
        return magicEnhancer;
    }
    
    
    /**
     * Getter for the market.
     *
     * @return The market.
     */
    public Market getMarket()
    {
        return market;
    }
    
    
    /**
     * Getter for the trainingGrounds.
     *
     * @return The trainingGrounds.
     */
    public TrainingGrounds getTrainingGrounds()
    {
        return trainingGrounds;
    }
    
    
    /**
     * Getter for the playerReborn.
     *
     * @return The playerReborn.
     */
    public PlayerReborn getPlayerReborn()
    {
        return playerReborn;
    }
}
