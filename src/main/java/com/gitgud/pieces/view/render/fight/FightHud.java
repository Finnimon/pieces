package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.ActionContextHud;
import com.gitgud.pieces.model.fight.Fight;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;


/**
 * Hud for the fight scene.
 *
 * @author Julius Rohe, Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @since 30.06.2024
 */
public class FightHud extends ActionContextHud<Fight>
{
    
    private final FightTimeLineRender fightTimeLineRender;
    
    
    public FightHud(Fight data)
    {
        super(data);
        this.fightTimeLineRender = new FightTimeLineRender(data.getFightTimeLine());
        render(data);
    }
    
    
    @Override
    public void render(Fight model)
    {
        ObservableList<Node> children = getChildren();
        children.clear();
        children.add(fightTimeLineRender);
        setAlignment(fightTimeLineRender, Pos.TOP_CENTER);
    }
    
    
    protected FightTimeLineRender getFightTimeLineRender()
    {
        return fightTimeLineRender;
    }
    
    
    @Override
    public void updateRender()
    {
        super.updateRender();
        fightTimeLineRender.updateRender();
        
    }
}
