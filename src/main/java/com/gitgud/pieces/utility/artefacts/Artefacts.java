package com.gitgud.pieces.utility.artefacts;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentAttackModifier;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentDefenceModifier;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;

import java.util.List;


/**
 * <p>This Enum is used to Instantiate {@link Artefact}s. This should only be used during development or testing to create Mission Maps etc.
 * <p> Please remove from production code.
 *
 * @author Finn L.
 * @version 1.0
 * @since 28.07.2024
 * @Owner: Finn L.
 */
public enum Artefacts
{
    BLOODY_SWORD("Bloody Sword",
                 "An ancient blade. See if it makes your soldiers fight more furious.",
                 "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact/BloodyBlade.png",
                 new FightAgentModifier(List.of(new FightAgentAttackModifier(7, -1, 0.99f),
                                                new FightAgentDefenceModifier(-2, -2, 1.001f))),
                 1),
    TURTLE_RING("Turtle Ring",
                "A ring made of turtle. It will surely make your soldiers fight more carefully.",
                "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact/TurtelShellRing.png",
                new FightAgentModifier(List.of(new FightAgentAttackModifier(-1, -1, 1.002f),
                                               new FightAgentDefenceModifier(3, 3, 1.002f))),
                1),
    BARBARIC_HELMET("Barbaric Helmet",
                    "A brutal looking helmet. It holds a lot of power.",
                    "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact/BarbariansHelmet.png",
                    new FightAgentModifier(List.of(new FightAgentAttackModifier(7, -3, 0.95f),
                                                   new FightAgentDefenceModifier(3, 3, 1.002f))),
                    1),
    ANGEL_FEATHER("An Angels Feather",
                  "A feather fallen from skies. It brings with it good luck",
                  "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact/AngelFeather.png",
                  new FightAgentModifier(List.of(new FightAgentAttackModifier(0, 0, 1.02f),
                                                 new FightAgentDefenceModifier(0, 5, 1.04f))),
                  1);
    
    
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    private final Modifier<FightAgent> modifier;
    
    
    private final int level;
    
    
    Artefacts(String name, String description, String spriteFilePath, Modifier<FightAgent> modifier, int level)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
        this.modifier = modifier;
        this.level = level;
    }
    
    
    public Artefact createArtefact()
    {
        return new Artefact(name, description, spriteFilePath, modifier, level);
    }
}
