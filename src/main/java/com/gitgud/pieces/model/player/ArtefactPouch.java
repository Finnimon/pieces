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
import java.util.List;


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
    
    
    private final Artefact[] equippedArtefacts;
    
    
    private final HashSet<Artefact> allOwnedArtefacts;
    
    
    public ArtefactPouch()
    {
        equippedArtefacts = new Artefact[MAX_ARTEFACTS];
        allOwnedArtefacts = new HashSet<>();
    }
    
    
    public int getMAX_ARTEFACTS()
    {
        return MAX_ARTEFACTS;
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
        
        getAffectableFightAgents().forEach(fA->artefact.getModifier().apply(fA));
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
        
        getAffectableFightAgents().forEach(fA->artefact.getModifier().disApply(fA));
    }
}
