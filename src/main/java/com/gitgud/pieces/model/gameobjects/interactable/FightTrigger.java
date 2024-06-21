package com.gitgud.pieces.model.gameobjects.interactable;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.fight.Fight;


public class FightTrigger extends GameObject implements Interactable
{
    private final String fightFilePath;
    
    
    public FightTrigger(String name, String description, String spriteUrl, Fight fight, String fightFilePath)
    {
        super(name, description, spriteUrl);
        this.fightFilePath = fightFilePath;
    }
    
    
    @Override
    public void interact(GridMap gridMap)
    {
        ActiveGameController.getInstance().get().setFight(null);//todo
    }
}
