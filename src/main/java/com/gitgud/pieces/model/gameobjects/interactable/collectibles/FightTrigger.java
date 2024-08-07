package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.control.game.Game;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;

import java.util.*;


public class FightTrigger extends GameObject implements Collectible<MissionController>
{
    
    
    public static final String DESCRIPTION = "An Enemy Encampment is in Sight." +
                                             Strings.LINE_BREAK +
                                             "Do you dare attack?";
    
    
    public static final String NAME = "Enemy";
    
    
    public static final String DEFAULT_SPRITE_FILE_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\model" +
                                                          "\\gameobjects\\agents\\monochrome\\white_pawn.png";
    
    
    public static final int MINIMUM_STARTING_POSITION_COUNT = 5;
    
    
    private final Fight fight;
    
    
    private final Tile[] startingPositions;
    
    
    public FightTrigger(Fight fight, Tile[] startingPositions)
    {
        super(NAME, DESCRIPTION, DEFAULT_SPRITE_FILE_PATH);
        this.fight = fight;
        this.startingPositions = startingPositions;
    }
    
    
    public FightTrigger(Fight fight)
    {
        super(NAME, DESCRIPTION, determineSpriteFilePath(fight));
        this.fight = fight;
        this.startingPositions = determinStartingPositions(fight);
    }
    
    
    private static String determineSpriteFilePath(Fight fight)
    {
        FightAgent fightAgent = fight.getGridMap().nonNullElements().stream().findFirst().orElse(null);
        
        return fightAgent == null ? DEFAULT_SPRITE_FILE_PATH : fightAgent.getSpriteFilePath();
    }
    
    
    private static Tile[] determinStartingPositions(Fight fight)
    {
        GridMap<FightAgent> gridMap = fight.getGridMap();
        int width = gridMap.getWidth();
        width--;
        int height = gridMap.getHeight();
        height--;
        Collection<FightAgent> enemyFightAgents = gridMap.nonNullElements();
        ArrayList<Tile> enemyPositions = new ArrayList<>();
        
        enemyFightAgents.stream().forEach(enemy -> enemyPositions.add(gridMap.getVertex(enemy)));
        
        ArrayList<Tile> startingPositions = new ArrayList<>();
        for (Tile tile : enemyPositions)
        {
            Tile startingPosition = gridMap.getVertex(width - tile.getX(), height - tile.getY());
            startingPositions.add(startingPosition);
        }
        
        if (startingPositions.size() >= MINIMUM_STARTING_POSITION_COUNT)
        {
            return startingPositions.toArray(new Tile[0]);
        }
        
        while (startingPositions.size() < MINIMUM_STARTING_POSITION_COUNT)
        {
            Tile startingPosition = gridMap.verticeSet()
                                           .stream()
                                           .filter(t -> t.getTerrain().isTraversable() &&
                                                        gridMap.get(t) == null &&
                                                        !startingPositions.contains(t))
                                           .findFirst()
                                           .orElse(null);
            
            
            startingPositions.add(startingPosition);
        }
        
        
        return startingPositions.toArray(new Tile[0]);
    }
    
    
    @Override
    public boolean isCollectionPossible()
    {
        Mission mission = ActiveGameController.getInstance().get().getMission();
        return Arrays.stream(mission.getActiveFightAgents()).anyMatch(Objects::nonNull);
    }
    
    
    @Override
    public void addToInventory()
    {
        prepareFight();
        ActiveGameController.getInstance().get().setFight(fight);
        Game.Flow.showNextScene();
    }
    
    
    private void prepareFight()
    {
        FightAgent[] activeFightAgents = ActiveGameController.getInstance().get().getMission().getActiveFightAgents();
        GridMap<FightAgent> gridMap = fight.getGridMap();
        TreeSet<FightAgent> current = fight.getFightTimeLine().current();
        
        for (int i = 0; i < activeFightAgents.length; i++)
        {
            FightAgent fightAgent = activeFightAgents[i];
            activeFightAgents[i] = null;
            if (fightAgent == null)
            {
                continue;
            }
            Tile position = startingPositions[i];
            gridMap.place(position, fightAgent);
            current.add(fightAgent);
        }
    }
}
