package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.*;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


public class FightRender extends BaseActionContextRender<Fight, FightAgent,FightHud>
{
    public FightRender(Fight data)
    {
        super(data, new FightHud(data));
    }
}
