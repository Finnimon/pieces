package com.gitgud.pieces.model.player;

import com.gitgud.pieces.model.gameObjects.interactable.collectibles.Artefact;

import java.util.ArrayList;


/**
 * Stores the {@link Artefact}s in the {@link Player#artefactPouch()}.
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public class ArtefactPouch
{
    private final int MAX_ARTIFACTS = 3;
    
    
    private final Artefact[] equippedArtefacts = new Artefact[MAX_ARTIFACTS];
    
    
    private final ArrayList<Artefact> allOwnedArtefacts = new ArrayList<>();
    
    
    public ArtefactPouch()
    {
    }
    
    
    public int getMAX_ARTIFACTS()
    {
        return MAX_ARTIFACTS;
    }
    
    
    public Artefact[] getEquippedArtifacts()
    {
        return equippedArtefacts;
    }
    
    
    public ArrayList<Artefact> getAllOwnedArtefacts()
    {
        return allOwnedArtefacts;
    }
}
