package com.gitgud.pieces.view.render.fight;

import com.gitgud.engine.view.BaseActionContextRender;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.MainMenu;
import javafx.scene.input.KeyEvent;


/**
 * Render for the {@link Fight} Scene.
 *
 * @author Julius Rohe, Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @since 30.06.2024
 */
public class FightRender extends BaseActionContextRender<Fight, FightAgent, FightHud>
{
    public FightRender(Fight data)
    {
        super(data, new FightHud(data));
        this.addEventHandler(KeyEvent.KEY_PRESSED, MainMenu.OPEN_MAIN_MENU_ON_ESCAPE);
    }
    
    
    @Override
    public void show()
    {
        StageController.getInstance().setRoot(this);
    }
}
