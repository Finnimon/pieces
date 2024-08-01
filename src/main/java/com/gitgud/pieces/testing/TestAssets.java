package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.game.ActiveGame;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.*;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;
import javafx.beans.property.SimpleLongProperty;

import java.util.HashMap;
import java.util.HashSet;


public interface TestAssets
{
    
    static ActiveGame getTestActiveGame()
    {
        return new ActiveGame(getTestPlayer(), new City(), Missions.MISSION0, null);
    }
    
    static ActiveGame getNewGame()
    {
        return new ActiveGame(new Player("NEW_GAME",
                                         Difficulty.EASY,
                                         testArmy(),
                                         testWallet(),
                                         testArtefactPouch(),
                                         testSpellBook()), new City(), null, null);
    }
    
    static Player getTestPlayer()
    {
        return new Player("TestPlayer",
                          Difficulty.EASY,
                          testArmy(),
                          testWallet(),
                          testArtefactPouch(),
                          testSpellBook());
    }
    
    
    static HashSet<FightAgent> testArmy()
    {
        
        HashSet<FightAgent> fightAgents = new HashSet<>();
        
        createFightAgents(fightAgents, Allegiance.BLACK);
        
        
        return fightAgents;
    }
    
    
    static Wallet testWallet()
    {
        HashMap<ResourceType, SimpleLongProperty> resourceMap = new HashMap<>();
        
        for (ResourceType type : ResourceType.values())
        {
            resourceMap.put(type, new SimpleLongProperty(0));
        }
        
        return new Wallet(resourceMap);
    }
    
    
    static ArtefactPouch testArtefactPouch()
    {
        return new ArtefactPouch();
    }
    
    
    static SpellBook testSpellBook()
    {
        return new SpellBook(new HashSet<>());
    }
    
    
    private static void createFightAgents(HashSet<FightAgent> fightAgents, Allegiance allegiance)
    {
        FightAgentDirector director = new FightAgentDirector();
        
        fightAgents.add(director.make(FightAgentDirector.calculateType(allegiance,
                                                                       FightAgentType.KNIGHT,
                                                                       Faction.MONOCHROME,
                                                                       1)));
        
        fightAgents.add(director.make(FightAgentDirector.calculateType(allegiance,
                                                                       FightAgentType.PAWN,
                                                                       Faction.MONOCHROME,
                                                                       1)));
        fightAgents.add(director.make(FightAgentDirector.calculateType(allegiance,
                                                                       FightAgentType.ROOK,
                                                                       Faction.MONOCHROME,
                                                                       1)));
    }
    
}
