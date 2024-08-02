package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.fight.FightTimeLine;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.Constants;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.ArrayList;


/**
 * Render of {@link FightTimeLine}
 *
 * @author Julius Rohe, Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @since 30.06.2024
 */
public class FightTimeLineRender extends HBox implements UpdatableRender<FightTimeLine>
{
    
    
    private static final int HEIGHT = 50;
    
    
    private static final int SPACING = 5;
    
    
    private final FightTimeLine fightTimeLine;
    
    
    public FightTimeLineRender(FightTimeLine fightTimeLine)
    {
        super(SPACING);
        this.fightTimeLine = fightTimeLine;
        setMouseTransparent(true);
        setBackground(Constants.SEMI_TRANSPARENT_BACKGROUND);
        setMaxHeight(HEIGHT);
        setMaxWidth(Region.USE_PREF_SIZE);
        
        render(fightTimeLine);
    }
    
    
    @Override
    public void render(FightTimeLine model)
    {
        ArrayList<FightAgent> fightAgents = new ArrayList<>();
        fightAgents.addAll(model.current());
        fightAgents.addAll(model.next());
        
        fightAgents.forEach(this::addFightAgent);
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
