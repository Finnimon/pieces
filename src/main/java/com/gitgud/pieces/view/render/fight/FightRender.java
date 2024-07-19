package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.BaseActionContextRender;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public class FightRender extends BaseActionContextRender<Fight, FightAgent, FightHud>
{
    public FightRender(Fight data)
    {
        super(data, new FightHud(data));
    }
    
    
}
