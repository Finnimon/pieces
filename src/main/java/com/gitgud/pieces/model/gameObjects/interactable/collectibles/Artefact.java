package com.gitgud.pieces.model.gameObjects.interactable.collectibles;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.engine.model.gameObject.interactable.Collectible;
import com.gitgud.engine.model.gameObject.GameObject;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.player.ArtefactPouch;
import com.gitgud.pieces.utility.Core;
import com.gitgud.engine.utility.modification.Modifier;


/**
 * Artifacts that can be placed in {@link com.gitgud.engine.model.map.GridMap} and collected by the {@link com.gitgud.pieces.model.player.Player}.
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public class Artefact extends GameObject implements Collectible
{
    private final ArtefactType artefactType;
    
    
    private final Modifier<FightAgent> modifier;
    
    
    public Artefact(ArtefactType artefactType, Modifier<FightAgent> modifier)
    {
        super(artefactType.name(), artefactType.getDescription(), artefactType.getSpriteUrl());
        this.artefactType = artefactType;
        this.modifier = modifier;
    }
    
    
    public ArtefactType getArtifactType()
    {
        return artefactType;
    }
    
    
    public Modifier<FightAgent> getModifier()
    {
        return modifier;
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return null;
    }
    
    
    @Override
    public void addToInventory()
    {
        ArtefactPouch artefactPouch = ActiveGameController.getInstance().get().getPlayer().artefactPouch();
        Artefact[] equippedArtifacts = artefactPouch.getEquippedArtifacts();
        
        Core.insertAtFirstNullIndex(equippedArtifacts, this);
        
        artefactPouch.getAllOwnedArtefacts().add(this);
    }
}


