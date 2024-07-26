package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.HealthWell;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.ManaWell;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.MissionEnder;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.Portal;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightAgentCollectible;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightTrigger;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.ResourceCollectible;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.model.player.ResourceType;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;


public interface Missions
{
    Mission FIRST = create(Fights.mapFromBoolMap("src/main/resources/com/gitgud/maps/MissionMap1"));
    
    
    static <GOType extends GameObject> GridMap<GOType> getTestMap(int width, int height)
    {
        return GridMap.create(TestStuff.booleanArray(width, height));
    }
    
    
    private static Mission create(GridMap<GameObject> gridMap)
    {
        Tile startingPosition = gridMap.verticeSet().stream().filter(
                tile -> tile.getTerrain().isTraversable()).findFirst().get();
        return new Mission(gridMap, startingPosition, new FightAgent[0]);
    }
    
    
    Mission MISSION0 = Missions.MISSION1(
            new FightAgent[]{ new FightAgentDirector().make(412), null, null, null, null });
    
    
    static Mission MISSION1(FightAgent[] fightAgents)
    {
        GridMap<GameObject> gridMap = Fights.mapFromBoolMap("src/main/resources/com/gitgud/maps/MissionMap1");
        Tile startingPosition = gridMap.getVertex(996);
        
        int index = 202;
        GameObject gameObject = new ResourceCollectible(200, ResourceType.IRON);
        gridMap.place(index, gameObject);

        index = 427;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 278;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 381;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 512;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 533;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 897;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 878;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 997;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 770;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);
        index = 816;
        gameObject = getRandomResourceCollectible();
        gridMap.place(index, gameObject);


        Tile portalTile = gridMap.verticeSet().stream().filter(
                t -> t.getTerrain().isTraversable() && gridMap.get(t) == null).findFirst().orElse(null);
        gameObject = new MissionEnder();
        gridMap.place(portalTile, gameObject);
        portalTile = gridMap.verticeSet().stream().filter(
                t -> t.getTerrain().isTraversable() && gridMap.get(t) == null).findFirst().orElse(null);
        index = 728;
        gameObject = new Portal(portalTile);
        gridMap.place(index, gameObject);
        index = 554;
        gameObject = new ManaWell();
        gridMap.place(index, gameObject);
        index = 601;
        gameObject = new HealthWell();
        gridMap.place(index, gameObject);

        index = 365;
        gameObject = getRandomFightAgentCollectable();
        gridMap.place(index, gameObject);
        index = 740;
        gameObject = getRandomFightAgentCollectable();
        gridMap.place(index, gameObject);

        gridMap.place(1, 31, new FightTrigger(Fights.FIGHT1));
//        gridMap.place(8, 7, new FightTrigger(Fights.FIGHT2));

        
        return new Mission(gridMap, startingPosition, fightAgents);
    }
    
    
    private static ResourceCollectible getRandomResourceCollectible()
    {
        int value = Math.round((float) Math.random() * 1000);
        ResourceType resourceType = ResourceType.values()[Math.round(
                (float) Math.random() * (ResourceType.values().length - 1))];
        return new ResourceCollectible(value, resourceType);
    }
    
    
    private static FightAgentCollectible getRandomFightAgentCollectable()
    {
        FightAgentDirector director = new FightAgentDirector();
        
        FightAgent fightAgent = director.make(FightAgentDirector.calculateType(Allegiance.BLACK, FightAgentType.QUEEN,
                                                                               Faction.values()[Math.round(
                                                                                       (float) Math.random() * (Faction.values().length - 1))],
                                                                               1));
        
        return new FightAgentCollectible(fightAgent);
    }
    
    
}
