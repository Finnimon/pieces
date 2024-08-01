package com.gitgud.pieces.model.gameobjects.agents;


import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.agent.Agent;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.fight.SpellCaster;
import com.gitgud.pieces.model.gameobjects.Faction;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Agent for the player in the {@link com.gitgud.pieces.model.mission.Mission} and their {@link SpellCaster} stand in
 * for {@link com.gitgud.pieces.model.fight.Fight}.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see com.gitgud.pieces.model.player.Player
 * @since 25.06.2024
 */
public class PlayerAgent extends Agent implements SpellCaster
{
    private static final boolean IS_FLYING = false;
    
    
    private static final int MOVEMENT_RANGE = 7;
    
    
    private static final String NAME_SUFFIX = " King";
    
    
    private static final String DESCRIPTION = "Once a just king. Now broken by many defensive battles he marches " +
                                              "forth to retake all his lands.";
    
    
    private static final String SPRITE_PATH_PREFIX = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects" +
                                                     "\\agents\\";
    
    
    private static final String SPRITE_PATH_SUFFIX = "\\black_king.png";
    
    
    private static final Faction DEFAULT_FACTION = Faction.MONOCHROME;
    
    
    private final SimpleIntegerProperty mana = new SimpleIntegerProperty(200);
    
    
    public PlayerAgent()
    {
        this(ActiveGameController.getInstance().get().getCity().getPlayerReborn().getFaction());
    }
    
    
    public PlayerAgent(Faction faction)
    {
        super(determineName(faction), DESCRIPTION, determinSpriteFilePath(faction), IS_FLYING, MOVEMENT_RANGE);
    }
    
    
    private static String determineName(Faction faction)
    {
        return Named.formatString(faction.name()) + NAME_SUFFIX;
    }
    
    
    /**
     * Determines the spritefilepath for the Black king of the given {@link Faction}.
     *
     * @param faction The faction used for this {@link PlayerAgent}.
     * @return The spritefilepath for this {@link PlayerAgent}.
     */
    private static String determinSpriteFilePath(Faction faction)
    {
        return SPRITE_PATH_PREFIX + faction.name() + SPRITE_PATH_SUFFIX;
    }
    
    
    @Override
    public SpellBook getSpellbook()
    {
        return ActiveGameController.getInstance().get().getPlayer().spellbook();
    }
    
    
    @Override
    public Allegiance getAllegiance()
    {
        return Allegiance.BLACK;
    }
    
    
    @Override
    public SimpleIntegerProperty manaProperty()
    {
        return mana;
    }
}
