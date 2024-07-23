package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.view.ActionContextHud;
import com.gitgud.pieces.model.fight.Fight;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;


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
    public void render(Fight data)
    {
        ObservableList<Node> children = getChildren();
        children.clear();
        children.add(fightTimeLineRender);
        ReadOnlyDoubleProperty stageWidth = StageController.getInstance().getStage().getScene().widthProperty();
        ReadOnlyDoubleProperty fightTimeLineWidth = fightTimeLineRender.widthProperty();
        fightTimeLineRender.translateXProperty().bind(stageWidth.subtract(fightTimeLineWidth).divide(2));
        //todo
    }
    
    
    @Override
    public void updateRender()
    {
        super.updateRender();
        fightTimeLineRender.updateRender();
        
    }
}
