package com.gitgud.model.gameObjects.interactable;

import com.gitgud.control.ActiveGameController;
import com.gitgud.control.MissionController;
import com.gitgud.model.fight.Fight;
import com.gitgud.model.gameObjects.GameObject;


public class FightTrigger extends GameObject implements Interactable
{
    private final String fightFilePath;
    
    
    public FightTrigger(String name, String description, String spriteUrl, Fight fight, String fightFilePath)
    {
        super(name, description, spriteUrl);
        this.fightFilePath = fightFilePath;
    }
    
    
    
    @Override
    public void interact(MissionController missionController)
    {
        ActiveGameController.getInstance().get().setFight(null);//todo
    }
}
