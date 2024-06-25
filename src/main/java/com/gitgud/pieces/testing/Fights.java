package com.gitgud.pieces.testing;


import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;
import com.gitgud.pieces.utility.builder.fightAgent.KnightBuilder;

import java.util.HashMap;
import java.util.HashSet;

import static com.gitgud.pieces.testing.Missions.getTestMap;


public interface Fights
{
    // FightAgent DEFAULT_FIGHT_AGENT=new FightAgent("Name", "Description")//todo
    
    
    public static Fight getTestFight()
    {
        GridMap<FightAgent> fightTestMap = getTestMap(12, 12);
        FightAgentDirector director = new FightAgentDirector(new KnightBuilder());
        HashSet<FightAgent> fightAgents = new HashSet<>();
        HashMap<Player, HashSet<FightAgent>> ownershipMap = new HashMap<>();
        for (int i = 0; i < 5; i++)
        {
            int type = 1000*(i%2)+300 + 10 * (i % 3) + 5;
            
            FightAgent fightAgent = director.make(type);
            fightAgents.add(fightAgent);
            Tile tile= fightTestMap.verticeSet().stream().filter(t -> t.getTerrain().isTraversable()&&fightTestMap.get(t)==null).findFirst().get();
            fightTestMap.place(tile, fightAgent);
        }
        
        return new Fight(fightTestMap, ownershipMap);
    }
}
