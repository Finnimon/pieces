package com.gitgud.pieces.model.city.buildings;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.gameObjects.Faction;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A {@link CityBuilding} with the option to train {@link FightAgent}s of a specified {@link Faction}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class FactionCamp extends CityBuilding
{
    private final Faction faction;
    
    
    private final List<Modifier<FightAgent>> modifiers;
    
    
    public FactionCamp(String name, String description, String spriteFilePath, int level, Faction faction,
                       Modifier<FightAgent> modifier)
    {
        super(name, description, spriteFilePath, level);
        this.faction = faction;
        modifiers = new ArrayList<>();
        getModifiers().add(modifier);
    }
    
    
    public Faction getFaction()
    {
        return this.faction;
    }
    
    
    public List<Modifier<FightAgent>> getModifiers()
    {
        return this.modifiers;
    }
    
    
    @Override
    public int levelUp()
    {
        super.levelUp();
        modifiers.addAll(modifiers);
        return getLevel();
    }
    
    
    private Collection<FightAgent> getTrainableFightAgents()
    {
        return ActiveGameController.getInstance().get().getPlayer().army().baseCampStash().values().stream().flatMap(
                Collection::stream).collect(Collectors.toCollection(ArrayList::new)).stream().filter(
                x -> x.getFaction() == this.faction).toList();
    }
    
    
    private void trainFightAgent(FightAgent fightAgent)
    {
        FightAgentModifier.applyModifiers(fightAgent, getModifiers());
    }
}
