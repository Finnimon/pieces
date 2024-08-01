package com.gitgud.pieces.model.player;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.game.ActiveGame;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.Core;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;


/**
 * Stores the {@link Artefact}s in the {@link Player#artefactPouch()}.
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2024
 */
public class ArtefactPouch
{
    /**
     * The max allowed number of equipped artefacts.
     */
    private static final int MAX_EQUIPPED_ARTEFACTS = 3;
    
    
    /**
     * Artefacts that are equipped ie in effect.
     */
    private final Artefact[] equippedArtefacts;
    
    
    /**
     * All Artefacts owned by the player include equipped artefact
     */
    private final HashSet<Artefact> allOwnedArtefacts;
    
    
    /**
     * Creates an empty Artefact pouch.
     */
    public ArtefactPouch()
    {
        equippedArtefacts = new Artefact[MAX_EQUIPPED_ARTEFACTS];
        allOwnedArtefacts = new HashSet<>();
    }
    
    
    public int getMAX_ARTEFACTS()
    {
        return MAX_EQUIPPED_ARTEFACTS;
    }
    
    
    public Artefact[] getEquippedArtefacts()
    {
        return equippedArtefacts;
    }
    
    
    public HashSet<Artefact> getAllOwnedArtefacts()
    {
        return allOwnedArtefacts;
    }
    
    
    public void addArtefact(Artefact artefact)
    {
        allOwnedArtefacts.add(artefact);
        equip(artefact);
    }
    
    
    public void equip(Artefact artefact)
    {
        Core.insertAtFirstNullIndex(equippedArtefacts, artefact);
        
        if (!Arrays.asList(equippedArtefacts).contains(artefact))
        {
            return;
        }
        
        getAffectableFightAgents().forEach(fA -> artefact.getApplicable().apply(fA));
    }
    
    
    @NotNull
    private HashSet<FightAgent> getAffectableFightAgents()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        HashSet<FightAgent> fightAgents = new HashSet<>(activeGame.getPlayer().army());
        
        Mission mission = activeGame.getMission();
        Fight fight = activeGame.getFight();
        
        
        if (mission == null && fight == null)
        {
            return fightAgents;
        }
        
        if (mission != null)
        {
            fightAgents.addAll(mission.getFightAgents());
        }
        if (fight != null)
        {
            fightAgents.addAll(fight.getFightTimeLine().getAllAgents());
            fightAgents.removeIf(fa -> fa.getAllegiance() != Allegiance.BLACK);
        }
        
        return fightAgents;
    }
    
    
    private void unEquip(Artefact artefact)
    {
        Core.replaceFirstOccurrence(equippedArtefacts, artefact, null);
        
        getAffectableFightAgents().forEach(fA -> artefact.getApplicable().disApply(fA));
    }
}
