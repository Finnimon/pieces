package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.ActionContextHud;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.engine.view.Hud;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class FightHud extends ActionContextHud<Fight>
{
    
    private final FightTimeLineRender fightTimeLineRender;
    
    
    public FightHud(Fight data)
    {
        super(data);
        this.fightTimeLineRender = new FightTimeLineRender(data.getFightTimeLine());
    }
    
    
    @Override
    public void render(Fight data)
    {
        ObservableList<Node> children = getChildren();
        children.clear();
        children.add(fightTimeLineRender);
        BorderPane.setAlignment(fightTimeLineRender, Pos.CENTER);
        //todo
    }
    
    
    @Override
    public void updateRender()
    {
        super.updateRender();
        fightTimeLineRender.updateRender();
        //todo
    }
}
