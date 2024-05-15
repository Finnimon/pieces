package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.control.PlayerController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.player.ArtefactPouch;
import com.gitgud.utility.Core;
import com.gitgud.utility.modification.Modifier;


/**
 * Artifacts that can be placed in {@link com.gitgud.model.map.GridMap} and collected by the {@link com.gitgud.model.player.Player}.
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
    public void addToInventory(MissionController missionController)
    {
        ArtefactPouch artefactPouch = PlayerController.getInstance().getPlayer().artefactPouch();
        Artefact[] equippedArtifacts = artefactPouch.getEquippedArtifacts();
        
        Core.insertAtFirstNullIndex(equippedArtifacts, this);
        
        artefactPouch.getAllOwnedArtefacts().add(this);
    }
}


