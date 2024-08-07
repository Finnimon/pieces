package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.ArtefactPouch;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Artifacts that can be placed in {@link com.gitgud.engine.model.map.GridMap} and collected by the
 * {@link com.gitgud.pieces.model.player.Player}.
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public class Artefact extends GameObject implements Collectible<MissionController>, Leveler
{
    private final SimpleIntegerProperty level;
    
    
    private final Modifier<FightAgent> disApplicable;
    
    
    public Artefact(String name, String description, String spriteFilePath, Modifier<FightAgent> disApplicable,
                    int level)
    {
        super(name, description, spriteFilePath);
        this.disApplicable = disApplicable;
        this.level = new SimpleIntegerProperty(level);
    }
    
    
    @Override
    public void addToInventory()
    {
        ArtefactPouch artefactPouch = ActiveGameController.getInstance().get().getPlayer().artefactPouch();
        
        artefactPouch.addArtefact(this);
    }
    
    
    @Override
    public int levelUp()
    {
        FightAgentModifier fightAgentModifier = (FightAgentModifier) getModifier();
        fightAgentModifier.getModifiers().addAll(fightAgentModifier.getModifiers());
        return Leveler.super.levelUp();
    }
    
    
    public Modifier<FightAgent> getModifier()
    {
        return disApplicable;
    }
    
    
    @Override
    public SimpleIntegerProperty levelProperty()
    {
        return level;
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Artefact other))
        {
            return false;
        }
        return other.name().equals(name()) && other.getLevel() == getLevel();
    }
}