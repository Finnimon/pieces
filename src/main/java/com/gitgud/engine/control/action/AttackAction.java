package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.engine.view.infopane.InfoPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.util.Duration;


public interface AttackAction<AwaiterType extends ActionAwaitingController<ModelType, FighterType, RenderType>, ModelType extends ActionAwaiterModel<FighterType>, FighterType extends Fighter, RenderType extends ActionContextRender<ModelType, FighterType>> extends
                                                                                                                                                                                                                                                                FromToAction<AwaiterType, Tile>
{
    @Override
    default void enAct(AwaiterType awaiter)
    {
        synchronized (this)
        {
            GridMap<FighterType> gridMap = awaiter.getModel().getGridMap();
            Tile from = getFrom();
            Tile to = getTo();
            float distance = (float) from.distance(to);
            FighterType attacked = gridMap.get(to);
            
            int takenDamage = attacked.getHealth();
            gridMap.get(from).attack(attacked, distance);
            takenDamage -= attacked.getHealth();
            
            
            GridMapRender<FighterType> gridMapRender = awaiter.getRender().getGridMapRender();
            
            renderTakenDamage(takenDamage, awaiter);
            
            if (!attacked.isDead())
            {
                return;
            }
            
            
            gridMap.clearVertex(to);
            gridMapRender.removeGridMappable(attacked);
        }
    }
    
    
    private void renderTakenDamage(int takenDamage, AwaiterType awaiter)
    {
        Label damage = new Label(takenDamage + " DAMAGE");
        damage.setFont(new Font(40));
        damage.setVisible(false);
        damage.setBackground(InfoPane.DEFAULT_BACKGROUND);
        ObservableList<Node> awaiterChildren = awaiter.getRender().getChildren();
        awaiterChildren.add(damage);
        damage.setAlignment(Pos.CENTER);
        
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), e -> awaiterChildren.remove(damage));
        
        damage.setVisible(true);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }
    
}
