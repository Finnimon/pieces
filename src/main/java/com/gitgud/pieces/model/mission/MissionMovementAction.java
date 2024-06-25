package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.action.TileMovementAction;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;


public class MissionMovementAction extends TileMovementAction<MissionController,Mission, GameObject, MissionRender>
{
    
    public void enAct(MissionController awaiter)
    {
        Mission mission=awaiter.getModel();
        mission.setPlayerAgentPosition(getTo());
        awaiter.getRender().getGridMapRender().relocateGridMappable(mission.getPlayerAgent(), getTo());
    }
    
    
    public MissionMovementAction(Tile from, Tile to)
    {
        super(from, to);
    }
}
