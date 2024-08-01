package com.gitgud.pieces.utility.artefacts;

import com.gitgud.engine.model.DisApplicable;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentAttackModifier;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentDefenceModifier;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;

import java.util.List;


/**
 * <p>This Enum is used to Instantiate {@link Artefact}s. This should only be used during development or testing to
 * create Mission Maps etc.
 * <p> Please remove from the final production code.
 *
 * @author Finn L.
 * @version 1.2
 * @Owner: Finn L.
 * @since 28.07.2024
 */
public enum Artefacts
{
    BLOODY_SWORD_1("Bloody Sword",
                   "An ancient blade. See if it makes your soldiers fight more furious.",
                   "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact" +
                   "/BloodyBlade.png",
                   new FightAgentModifier(List.of(new FightAgentAttackModifier(7, -1, 0.99f),
                                                  new FightAgentDefenceModifier(-2, -2, 1.001f))),
                   1),
    TURTLE_RING_1("Turtle Ring",
                  "A ring made of turtle. It will surely make your soldiers fight more carefully.",
                  "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact" +
                  "/TurtelShellRing.png",
                  new FightAgentModifier(List.of(new FightAgentAttackModifier(-1, -1, 1.002f),
                                                 new FightAgentDefenceModifier(3, 3, 1.002f))),
                  1),
    BARBARIC_HELMET_1("Barbaric Helmet",
                      "A brutal looking helmet. It holds a lot of power.",
                      "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact" +
                      "/BarbariansHelmet.png",
                      new FightAgentModifier(List.of(new FightAgentAttackModifier(7, -3, 0.95f),
                                                     new FightAgentDefenceModifier(3, 3, 1.002f))),
                      2),
    ANGEL_FEATHER_1("An Angels Feather",
                    "A feather fallen from skies. It brings with it good luck",
                    "src/main/resources/com/gitgud/pieces/model/gameobjects/interactable/collectibles/artefact" +
                    "/AngelFeather.png",
                    new FightAgentModifier(List.of(new FightAgentAttackModifier(0, 0, 1.02f),
                                                   new FightAgentDefenceModifier(0, 5, 1.04f))),
                    1);
    
    
    /**
     * The name of a created {@link Artefact}.
     */
    private final String name;
    
    
    /**
     * The description of a created {@link Artefact}.
     */
    private final String description;
    
    
    /**
     * The sprite filepath of a created {@link Artefact}.
     */
    private final String spriteFilePath;
    
    
    /**
     * The dis-applicable of a created {@link Artefact}.
     */
    private final DisApplicable<FightAgent> disApplicable;
    
    
    /**
     * The level of a created {@link Artefact}.
     */
    private final int level;
    
    
    /**
     * Private Enum Constructor.
     *
     * @param name           The name of a created {@link Artefact}.
     * @param description    The description of a created {@link Artefact}.
     * @param spriteFilePath The sprite filepath of a created {@link Artefact}.
     * @param disApplicable  The dis-applicable of a created {@link Artefact}.
     * @param level          The level of a created {@link Artefact}.
     */
    Artefacts(String name, String description, String spriteFilePath, DisApplicable<FightAgent> disApplicable,
              int level)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
        this.disApplicable = disApplicable;
        this.level = level;
    }
    
    
    /**
     * Creates an {@link Artefact} with the attributes provided by the Enum.
     *
     * @return The created Artefact.
     */
    public Artefact createArtefact()
    {
        return new Artefact(name, description, spriteFilePath, disApplicable, level);
    }
}
