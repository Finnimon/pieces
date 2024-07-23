package com.gitgud.pieces.model.city.buildings;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * A {@link CityBuilding} with the option to train {@link FightAgent}s of a specified {@link Faction}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class TrainingGrounds extends CityBuilding
{
    private static final String NAME = "TrainingGrounds";
    
    
    private static final String description = "Your Pieces can be trained here.";
    
    
    private final List<Modifier<FightAgent>> modifiers;
    
    
    public TrainingGrounds(int level, Modifier<FightAgent> modifier)
    {
        super(NAME, description, level);
        modifiers = new ArrayList<>();
        getModifiers().add(modifier);
    }
    
    
    public Collection<Modifier<FightAgent>> getModifiers()
    {
        return this.modifiers;
    }
    
    
    @Override
    public int levelUp()
    {
        super.levelUp();
        modifiers.add(modifiers.stream().toList().getLast());
        return getLevel();
    }
    
    
    private Collection<FightAgent> getTrainableFightAgents()
    {
        return ActiveGameController.getInstance().get().getPlayer().army();
    }
    
    
    private void trainFightAgent(FightAgent fightAgent)
    {
        FightAgentModifier.applyModifiers(fightAgent, getModifiers());
    }
}
