package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.view.GridMappableRender;
import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.fight.FightTimeLine;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.TreeSet;


public class FightTimeLineRender extends HBox implements UpdatableRender<FightTimeLine>
{
    
    
    public static final int HEIGHT = 50;
    
    
    private final FightTimeLine fightTimeLine;
    
    
    public FightTimeLineRender(FightTimeLine fightTimeLine)
    {
        super(HEIGHT);
        this.fightTimeLine = fightTimeLine;
        
        render(fightTimeLine);
        this.visibleProperty().setValue(true);
    }
    
    
    @Override
    public void render(FightTimeLine data)
    {
        System.out.println("FightTimeLineRender.render()");
        TreeSet<FightAgent> current=data.current();
        TreeSet<FightAgent> next=data.next();
        int currentSize=current.size();
        
        ArrayList<FightAgent> fightAgents = new ArrayList<>();
        fightAgents.addAll(current);
        fightAgents.addAll(next);
        System.out.print(fightAgents.size());
        for(int i=0;i<fightAgents.size();i++)
        {
            FightAgent fightAgent = fightAgents.get(i);
            GridMappableRender<FightAgent> fightAgentRender= new GridMappableRender<>(fightAgent, HEIGHT);
            
            if (i==currentSize)
            {
            this.getChildren().add(BORDER_RECTANGLE);
            }
            
            fightAgentRender.setVisible(true);
            
            this.getChildren().add(fightAgentRender);
        }
    }
    
    private static Rectangle BORDER_RECTANGLE=new Rectangle(5, HEIGHT, Color.BLACK);
    
    
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
