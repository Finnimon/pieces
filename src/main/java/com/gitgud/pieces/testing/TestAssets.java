package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.*;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;

import java.util.HashMap;
import java.util.HashSet;


public interface TestAssets
{
    GridMap<GridMappable> testMap = TestStuff.getTestMap(12, 12);
    
//     static ActiveGame getTestActiveGame()
//     {
//
//         new ActiveGame(getTestPlayer())
//     }
     
     static Player getTestPlayer()
     {
         return new Player("TestPlayer", Difficulty.EASY, testArmy(),testWallet(),testArtefactPouch(),testSpellBook());
     }
     
     static Army testArmy()
     {
         HashMap<FightAgentType, HashSet<FightAgent>> baseCampStash = new HashMap<>();
         for (FightAgentType type: FightAgentType.values())
         {
             baseCampStash.put(type, new HashSet<>());
         }
         FightAgentDirector director = new FightAgentDirector();
         HashSet<FightAgent> fightAgents = new HashSet<>();
         
         baseCampStash.put(FightAgentType.KNIGHT, fightAgents);
         
         for (int i = 0; i < 3; i++)
         {
             int type = 300 + 10 * (i % 3) + 1;
             
             FightAgent fightAgent = director.make(type);
             fightAgents.add(fightAgent);
         }
         
         return new Army(baseCampStash);
     }
     
     static Wallet testWallet()
     {
         HashMap<ResourceType, Long> resourceMap = new HashMap<>();
         
         for (ResourceType type: ResourceType.values())
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
