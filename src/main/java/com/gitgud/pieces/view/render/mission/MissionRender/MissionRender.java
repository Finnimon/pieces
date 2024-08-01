package com.gitgud.pieces.view.render.mission.MissionRender;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.BaseActionContextRender;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.engine.view.GridMappableRender;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.InteractionChecker;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.MainMenu;
import com.gitgud.pieces.view.render.mission.MissionHud;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class MissionRender extends BaseActionContextRender<Mission, GameObject, MissionHud>
{
    
    public MissionRender(Mission mission)
    {
        super(mission, new MissionHud(mission));
        this.addEventHandler(KeyEvent.KEY_PRESSED, MainMenu.OPEN_MAIN_MENU_ON_ESCAPE);
    }
    
    
    public void addInteractionHandlers(MissionController missionController)
    {
        GridMapRender<GameObject> gridMapRender = getGridMapRender();
        GridMap<GameObject> gridMap = getData().getGridMap();
        for (GameObject gameObject : getData().getGridMap().nonNullElements())
        {
            if (!(gameObject instanceof Interactable<?> interactable))
            {
                continue;
            }
            
            GridMappableRender<GameObject> interactableRender = gridMapRender.getGridMappableRender(gameObject);
            
            interactableRender.addEventHandler(MouseEvent.MOUSE_CLICKED,
                                               interactableEventHandler(interactable,
                                                                        missionController,
                                                                        gridMap.getVertex(gameObject)));
        }
    }
    
    
    public static EventHandler<MouseEvent> interactableEventHandler(Interactable<?> interactable,
                                                                    MissionController missionController, Tile tile)
    {
        return mouseEvent ->
        {
            if (!mouseEvent.getButton().equals(MouseButton.PRIMARY))
            {
                return;
            }
            
            mouseEvent.consume();
            InteractionChecker.interactIfPossible(missionController, tile);
        };
    }
    
    
    @Override
    public void render(Mission model)
    {
        renderPlayerAgent();
        super.render(model);
    }
    
    
    @Override
    public void updateRender()
    {
        updatePlayerAgentRender();
        getHud().updateRender();
    }
    
    
    private void updatePlayerAgentRender()
    {
        PlayerAgent playerAgent = getData().getPlayerAgent();
        Tile playerAgentPosition = getData().getPlayerAgentPosition();
        getGridMapRender().relocateGridMappable(playerAgent, playerAgentPosition);
    }
    
    
    private void renderPlayerAgent()
    {
        PlayerAgent playerAgent = getData().getPlayerAgent();
        Tile playerAgentPosition = getData().getPlayerAgentPosition();
        getGridMapRender().addGridMappable(playerAgent, playerAgentPosition);
    }
    
    
    @Override
    public void show()
    {
        StageController.getInstance().setRoot(this);
    }
}
