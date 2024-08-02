package com.gitgud.pieces.control.action;

import com.gitgud.engine.control.action.TileMovementAction;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender;


/**
 * Override for special case of player agent in Mission, because he is not on the actual GridMap.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 * @see Mission
 */
public class MissionMovementAction extends TileMovementAction<MissionController, Mission, GameObject, MissionRender>
{
    /**
     * Defaults to {@link TileMovementAction#TileMovementAction(Tile, Tile)}
     *
     * @author Finn L.
     * @Owner: Finn L.
     * @Since: 05.06.2024
     * @Version: 1.0
     * @see TileMovementAction
     */
    public MissionMovementAction(Tile from, Tile to)
    {
        super(from, to);
    }
    
    
    @Override
    public void enAct(MissionController awaiter)
    {
        Mission mission = awaiter.getModel();
        mission.setPlayerAgentPosition(getTo());
        awaiter.getRender().getGridMapRender().relocateGridMappable(mission.getPlayerAgent(), getTo());
    }
}
