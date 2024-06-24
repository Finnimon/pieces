package com.gitgud.pieces.view.render.mission;

import com.gitgud.engine.view.ActionContextHud;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.engine.view.Hud;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public class MissionHud extends ActionContextHud<Mission>
{
    //add all relevant attributes here
    public MissionHud(Mission data)
    {
        super(data);
        render(data);
    }
    
    
    @Override
    public void render(Mission data)
    {
        //todo add all relevant data to getChilden();
        ObservableList<Node> children = getChildren();
        children.clear();//ensures that nothing, but this class, is left in the group
    }
    
    
    @Override
    public void updateRender()
    {
        super.updateRender();
    }
}
