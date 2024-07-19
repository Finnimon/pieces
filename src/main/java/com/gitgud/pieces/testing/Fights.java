package com.gitgud.pieces.testing;


import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightTrigger;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public interface Fights
{
    // FightAgent DEFAULT_FIGHT_AGENT=new FightAgent("Name", "Description")//todo
    
    
    Fight FIGHT1 = getFight1();
    
    
    Fight FIGHT2 = getFight2();
    
    
    static Fight getFight1()
    {
        GridMap<FightAgent> gridMap = mapFromBoolMap("src/main/resources/com/gitgud/maps/FightMap1");
        FightAgentDirector director = new FightAgentDirector();
        ArrayList<FightAgent> agents = new ArrayList<>();
        //Allegiance allegiance, FightAgentType fightAgentType, Faction faction, int level
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.PAWN, Faction.MONOCHROME, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.ROOK, Faction.PINK, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.ROOK, Faction.GREEN, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.KNIGHT, Faction.MONOCHROME, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.PAWN, Faction.PINK, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.BLACK, FightAgentType.PAWN, Faction.PINK, 1)));
        
        
        gridMap.place(3, 1, agents.get(0));
        gridMap.place(0, 0, agents.get(1));
        gridMap.place(11, 0, agents.get(2));
        gridMap.place(8, 1, agents.get(3));
        gridMap.place(6, 3, agents.get(4));
        gridMap.place(9, 3, agents.get(5));
        
        return new Fight(gridMap);
    }
    
    
    static Fight getFight2()
    {
        GridMap<FightAgent> gridMap = mapFromBoolMap("src/main/resources/com/gitgud/maps/FightMap1");
        FightAgentDirector director = new FightAgentDirector();
        ArrayList<FightAgent> agents = new ArrayList<>();
        //Allegiance allegiance, FightAgentType fightAgentType, Faction faction, int level
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.QUEEN, Faction.MONOCHROME, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.ROOK, Faction.MONOCHROME, 2)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.ROOK, Faction.GREEN, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.KNIGHT, Faction.MONOCHROME, 1)));
        agents.add(director.make(
                FightAgentDirector.calculateType(Allegiance.WHITE, FightAgentType.KNIGHT, Faction.PINK, 1)));
        
        gridMap.place(3, 1, agents.get(0));
        gridMap.place(0, 0, agents.get(1));
        gridMap.place(11, 0, agents.get(2));
        gridMap.place(8, 1, agents.get(3));
        gridMap.place(6, 3, agents.get(4));
        
        
        return new Fight(gridMap);
    }
    
    
    FightTrigger FIGHT_TRIGGER1 = new FightTrigger(FIGHT1);
    
    
    static boolean[][] readMapFile(String path)
    {
        List<String> lines;
        try
        {
            lines = Files.readAllLines(Path.of(path));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        boolean[][] map = new boolean[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++)
        {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
            {
                map[y][x] = line.charAt(x) == '1';
            }
        }
        return map;
    }
    
    
    static <GMType extends GridMappable> GridMap<GMType> mapFromBoolMap(String path)
    {
        boolean[][] map = readMapFile(path);
        
        return GridMap.create(map);
    }
}
