package com.gitgud.pieces.view.render;

import com.gitgud.engine.model.gameObject.GameObject;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.engine.view.Render;
import com.gitgud.pieces.model.mission.Mission;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MissionRender extends Scene implements Render <Mission>
{
    GridMapRender<GameObject> gridMapRender;
    private MissionRender(Parent root)
    {
        super(root);
    }
    
    public static MissionRender create(Mission mission)
    {
        return new MissionRender(new Group());
    }
    
    
    @Override
    public void render(Mission data)
    {
    
    }
}
