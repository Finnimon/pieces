package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.*;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;

import java.util.HashMap;
import java.util.HashSet;


public interface TestAssets
{
    GridMap<GridMappable> testMap = TestStuff.getTestMap(12, 12);
    
    
    static ActiveGame getTestActiveGame()
    {
        return new ActiveGame(getTestPlayer(), new City(), Missions.MISSION0, null);
    }
    
    
    static Player getTestPlayer()
    {
        return new Player("TestPlayer", Difficulty.EASY, testArmy(), testWallet(), testArtefactPouch(),
                          testSpellBook());
    }
    
    
    static Army testArmy()
    {
        HashMap<FightAgentType, HashSet<FightAgent>> baseCampStash = new HashMap<>();
        for (FightAgentType type : FightAgentType.values())
        {
            baseCampStash.put(type, new HashSet<>());
        }
        
        HashSet<FightAgent> fightAgents = new HashSet<>();
        
        createFightAgents(fightAgents, Allegiance.BLACK);
        
        
        return new Army(baseCampStash);
    }
    
    
    private static void createFightAgents(HashSet<FightAgent> fightAgents, Allegiance allegiance)
    {
        FightAgentDirector director = new FightAgentDirector();
        
        fightAgents.add(director.make(
                FightAgentDirector.calculateType(allegiance, FightAgentType.KNIGHT, Faction.MONOCHROME, 1)));
        
        fightAgents.add(director.make(
                FightAgentDirector.calculateType(allegiance, FightAgentType.PAWN, Faction.MONOCHROME, 1)));
        fightAgents.add(director.make(
                FightAgentDirector.calculateType(allegiance, FightAgentType.QUEEN, Faction.MONOCHROME, 1)));
        fightAgents.add(director.make(
                FightAgentDirector.calculateType(allegiance, FightAgentType.ROOK, Faction.MONOCHROME, 1)));
    }
    
    
    static Wallet testWallet()
    {
        HashMap<ResourceType, Long> resourceMap = new HashMap<>();
        
        for (ResourceType type : ResourceType.values())
        {
            resourceMap.put(type, 10L);
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
    
}
