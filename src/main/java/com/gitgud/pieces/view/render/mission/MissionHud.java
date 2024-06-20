package com.gitgud.pieces.view.render.mission;

import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.Hud;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;


public class MissionHud extends Hud<Mission>
{
    //add all relevant attributes here
    public MissionHud(Mission data)
    {
        super(data);
        
        render(getData());
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
        //you can update the render here
        //see Hud.java for available super methods. You can actually get the data from the super class with getData()
        //for example:
        //render(getData());
        //dont call render in your updateRender() method
        // as render is meant to only be called in the constructor
    }
}
