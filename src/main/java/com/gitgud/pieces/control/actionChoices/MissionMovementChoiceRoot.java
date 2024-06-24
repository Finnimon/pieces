package com.gitgud.pieces.control.actionChoices;

import com.gitgud.engine.control.RootToActionChoice;
import com.gitgud.engine.control.ToActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;

import java.util.List;


public class MissionMovementChoiceRoot extends RootToActionChoice<MissionController, Mission, GameObject, MissionRender>
{
    
    public MissionMovementChoiceRoot(String name, String description, MissionController awaiter,
                                     List<ToActionChoice<MissionController, Mission, GameObject, MissionRender>> toActionChoices)
    {
        super(name, description, awaiter, toActionChoices);
    }
}
