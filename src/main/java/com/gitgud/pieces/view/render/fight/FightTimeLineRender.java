package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.fight.FightTimeLine;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.TreeSet;


public class FightTimeLineRender extends HBox implements UpdatableRender<FightTimeLine>
{
    
    
    public static final int HEIGHT = 50;
    
    
    private static final Rectangle SEP_RECTANGLE = new Rectangle(5, HEIGHT, Color.BLACK);
    
    
    private final FightTimeLine fightTimeLine;
    
    
    public FightTimeLineRender(FightTimeLine fightTimeLine)
    {
        super(HEIGHT);
        //        setBackground(InfoPane.DEFAULT_BACKGROUND);
        setPickOnBounds(false);
        this.fightTimeLine = fightTimeLine;
        
        render(fightTimeLine);
    }
    
    
    @Override
    public void render(FightTimeLine data)
    {
        TreeSet<FightAgent> current = data.current();
        int currentSize = current.size();
        
        ArrayList<FightAgent> fightAgents = new ArrayList<>();
        fightAgents.addAll(current);
        fightAgents.addAll(data.next());
        ObservableList<Node> children = getChildren();
        
        for (FightAgent fightAgent : fightAgents)
        {
            if (children.size() == currentSize) this.getChildren().add(SEP_RECTANGLE);
            addFightAgent(fightAgent);
        }
    }
    
    
    private void addFightAgent(FightAgent fightAgent)
    {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(HEIGHT);
        imageView.setPreserveRatio(true);
        imageView.setImage(fightAgent.getSprite());
        getChildren().add(imageView);
    }
    
    
    @Override
    public void updateRender()
    {
        this.getChildren().clear();
        render(fightTimeLine);
    }
    
    
    @Override
    public FightTimeLine getData()
    {
        return fightTimeLine;
    }
    
}
