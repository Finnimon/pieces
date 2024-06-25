package com.gitgud.pieces.model.player;

import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;

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
    private final int MAX_ARTEFACTS = 3;
    
    
    private final Artefact[] equippedArtefacts = new Artefact[MAX_ARTEFACTS];
    
    
    private final ArrayList<Artefact> allOwnedArtefacts = new ArrayList<>();
    
    
    public ArtefactPouch()
    {
    }
    
    
    public int getMAX_ARTEFACTS()
    {
        return MAX_ARTEFACTS;
    }
    
    
    public Artefact[] getEquippedArtefacts ()
    {
        return equippedArtefacts;
    }
    
    
    public ArrayList<Artefact> getAllOwnedArtefacts ()
    {
        return allOwnedArtefacts;
    }
}
