package com.gitgud.pieces.testing;


import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;
import com.gitgud.pieces.utility.builder.fightAgent.KnightBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.gitgud.pieces.testing.Missions.getTestMap;


public interface Fights
{
    // FightAgent DEFAULT_FIGHT_AGENT=new FightAgent("Name", "Description")//todo
    
    
    public static Fight getTestFight()
    {
        GridMap<FightAgent> fightTestMap = getTestMap(12, 12);
        FightAgentDirector director = new FightAgentDirector(new KnightBuilder());
        HashSet<FightAgent> fightAgents = new HashSet<>();
        
        for (int i = 0; i < 6; i++)
        {
            int type = 1000*(i%2)+100*(i%5) + 10 * (i % 3) + 5;
            if(2==(i%5))
            {
                type+=100;
            }
            FightAgent fightAgent = director.make(type);
            fightAgents.add(fightAgent);
            Tile tile= fightTestMap.verticeSet().stream().filter(t -> t.getTerrain().isTraversable()&&fightTestMap.get(t)==null).findFirst().get();
            fightTestMap.place(tile, fightAgent);
        }
        
        return new Fight(fightTestMap);
    }
    
    
    static boolean[][] readMapFile(String path)
    {
        List<String> lines;
        try
        {
            lines=Files.readAllLines(Path.of(path));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        boolean[][] map=new boolean[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++)
        {
            String line=lines.get(y);
            for (int x = 0; x < line.length(); x++)
            {
                map[y][x]=line.charAt(x)=='1';
            }
        }
        return map;
    }
    
    
    static <GMType extends GridMappable> GridMap<GMType> mapFromBoolMap(String path)
    {
        boolean[][] map=readMapFile(path);
        
        return GridMap.create(map);
    }
}
