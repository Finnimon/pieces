package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.action.MovementAction;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.TileFromToAction;


public class MissionMovementAction extends TileFromToAction<Mission>
{
    @Override
    public void enAct(Mission awaiter)
    {
        awaiter.setPlayerAgentPosition(getTo());
        //todo cause rerender
    }
    
    
    public MissionMovementAction(Tile from, Tile to)
    {
        super(from, to);
    }
}
