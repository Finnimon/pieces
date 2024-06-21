package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.action.TileMovementAction;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.MissionController;


public class MissionMovementAction extends TileMovementAction<MissionController>
{
    
    public void enAct(Mission awaiter)
    {
        awaiter.setPlayerAgentPosition(getTo());
    }
    
    
    public MissionMovementAction(Tile from, Tile to)
    {
        super(from, to);
    }
}
