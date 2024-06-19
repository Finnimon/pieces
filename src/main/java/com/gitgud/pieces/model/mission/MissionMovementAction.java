package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.action.TileMovementAction;
import com.gitgud.engine.model.map.Tile;


public class MissionMovementAction extends TileMovementAction<Mission>
{
    @Override
    public void enAct(Mission awaiter)
    {
        awaiter.setPlayerAgentPosition(getTo());
    }
    
    
    public MissionMovementAction(Tile from, Tile to)
    {
        super(from, to);
    }
}
