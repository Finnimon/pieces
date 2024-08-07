package com.gitgud.pieces.view.render.mission;

import com.gitgud.engine.view.ActionContextHud;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.player.WalletRender;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;


public class MissionHud extends ActionContextHud<Mission>
{
    public MissionHud(Mission data)
    {
        super(data);
        render(data);
    }
    
    
    @Override
    public void render(Mission model)
    {
        setPickOnBounds(false);
        ObservableList<Node> children = getChildren();
        WalletRender walletRender = new WalletRender();
        setAlignment(walletRender, Pos.TOP_RIGHT);
        children.add(walletRender);
    }
    
    
    @Override
    public void updateRender()
    {
        super.updateRender();
    }
}
