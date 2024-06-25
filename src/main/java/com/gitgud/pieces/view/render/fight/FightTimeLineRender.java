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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.TreeSet;


public class FightTimeLineRender extends HBox implements UpdatableRender<FightTimeLine>
{
    private final FightTimeLine fightTimeLine;
    
    
    public FightTimeLineRender(FightTimeLine fightTimeLine)
    {
        this.fightTimeLine = fightTimeLine;
        
        render(fightTimeLine);
    }
    
    
    @Override
    public void render(FightTimeLine data)
    {
        TreeSet<FightAgent> current=data.current();
        TreeSet<FightAgent> next=data.next();
        int currentSize=current.size();
        
        ArrayList<FightAgent> fightAgents = new ArrayList<>();
        fightAgents.addAll(current);
        fightAgents.addAll(next);
        
        for(int i=0;i<fightAgents.size();i++)
        {
            FightAgent fightAgent = fightAgents.get(i);
            GridMappableRender<FightAgent> render= new GridMappableRender<>(fightAgent);
            if (i==currentSize)
            {
            
            }
            
            
            
            this.getChildren().add(render);
        }
    }
    
    private static Rectangle borderRectangle=new Rectangle();
    
    
    @Override
    public void updateRender()
    {
    
    }
    
    
    @Override
    public FightTimeLine getData()
    {
        return fightTimeLine;
    }
    
}
