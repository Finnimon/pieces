package com.gitgud.pieces.utility;

import com.gitgud.engine.model.Applicable;
import com.gitgud.engine.model.DisApplicable;
import com.gitgud.engine.model.attackDefenseLogic.Attack;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.utility.modification.DurableModifier;
import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.HealthWell;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.ManaWell;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.MissionEnder;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.Portal;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightAgentCollectible;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightTrigger;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.ResourceCollectible;
import com.gitgud.pieces.utility.modification.fightAgent.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.jetbrains.annotations.NotNull;


/**
 * Provides polymorphic adapters for the {@link com.google.gson.Gson} instance.
 *
 * @author Finn L.
 */
public class PolyMorphAdapter
{
    @NotNull
    protected static RuntimeTypeAdapterFactory<GridMappable> getGameObjectPolyMorphAdapter()
    {
        RuntimeTypeAdapterFactory<GridMappable> gameObjectAdapterFactory =
                RuntimeTypeAdapterFactory.of(GridMappable.class,
                                                                                                        "@type");
        gameObjectAdapterFactory.recognizeSubtypes()
                                .registerSubtype(GameObject.class)
                                .registerSubtype(HealthWell.class)
                                .registerSubtype(ManaWell.class)
                                .registerSubtype(PlayerAgent.class)
                                .registerSubtype(Artefact.class)
                                .registerSubtype(FightTrigger.class)
                                .registerSubtype(FightAgentCollectible.class)
                                .registerSubtype(MissionEnder.class)
                                .registerSubtype(Portal.class)
                                .registerSubtype(ResourceCollectible.class)
                                .registerSubtype(FightAgent.class)
                                .registerSubtype(SpellCasterFightAgent.class);
        return gameObjectAdapterFactory;
    }
    
    
    protected static RuntimeTypeAdapterFactory<Applicable> getApplicablePolyMorphAdapter()
    {
        RuntimeTypeAdapterFactory<Applicable> applicableAdapterFactory;
        applicableAdapterFactory = RuntimeTypeAdapterFactory.of(Applicable.class, "@type");
        applicableAdapterFactory.recognizeSubtypes()
                                .registerSubtype(DisApplicable.class)
                                .registerSubtype(Modifier.class)
                                .registerSubtype(DurableModifier.class)
                                .registerSubtype(FightAgentModifier.class)
                                .registerSubtype(FightAgentAttackModifier.class)
                                .registerSubtype(FightAgentDefenceModifier.class)
                                .registerSubtype(FightAgentMovementModifier.class)
                                .registerSubtype(FightAgentManaModifier.class)
                                .registerSubtype(FightAgentHealthModifier.class)
                                .registerSubtype(Attack.class);
        return applicableAdapterFactory;
    }
    
    
}
