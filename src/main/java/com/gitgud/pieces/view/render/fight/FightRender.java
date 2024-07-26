package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.BaseActionContextRender;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.geometry.Insets;
import javafx.scene.Node;


public class FightRender extends BaseActionContextRender<Fight, FightAgent, FightHud>
{
    public FightRender(Fight data)
    {
        super(data, new FightHud(data));
    }
    
    
    @Override
    public void show()
    {
        StageController.getInstance().setRoot(this);
    }
}
