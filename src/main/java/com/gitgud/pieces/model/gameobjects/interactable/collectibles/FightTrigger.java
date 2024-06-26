package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public class FightTrigger extends GameObject implements Collectible<MissionController>
{
    
    
    public static final String DESCRIPTION = "An Enemy Encampment is in Sight." + Strings.LINE_BREAK + "Do you dare attack?";
    
    
    public static final String NAME = "Enemy";
    
    
    public static final String SPRITE_FILE_PATH = "spriteFilePath";
    
    
    private final Fight fight;
    
    private final Tile[] startingPositions;
    
    
    public FightTrigger(Fight fight, Tile[] startingPositions)
    {
        super(NAME, DESCRIPTION, SPRITE_FILE_PATH);
        this.fight = fight;
        this.startingPositions = startingPositions;
    }
    
    
    @Override
    public void addToInventory()
    {
        FightController fightController= new FightController  (fight);
         fightController.start();
        StageController.getInstance().getStage();
    }
    
    private Fight prepareFight(MissionController missionController)
    {
        FightAgent[] activeFightAgents= missionController.getModel().getActiveFightAgents();
        GridMap<FightAgent> gridMap= fight.getGridMap();
        
        for (int i = 0; i < activeFightAgents.length; i++)
        {
            FightAgent fightAgent = activeFightAgents[i];
            Tile position = startingPositions[i];
            gridMap.place( position,fightAgent);
        }
        
        return fight;
    }
}
