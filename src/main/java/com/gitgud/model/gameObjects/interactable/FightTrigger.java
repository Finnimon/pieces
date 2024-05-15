package com.gitgud.model.gameObjects.interactable;

import com.gitgud.control.FightController;
import com.gitgud.control.MissionController;
import com.gitgud.model.fight.Fight;
import com.gitgud.model.gameObjects.GameObject;


public class FightTrigger extends GameObject implements Interactable
{
    private final Fight fight;
    
    
    public FightTrigger(String name, String description, String spriteUrl, Fight fight)
    {
        super(name, description, spriteUrl);
        this.fight = fight;
    }
    
    
    public Fight getFight()
    {
        return fight;
    }
    
    
    @Override
    public void interact(MissionController missionController)
    {
        new Thread(new FightController(missionController, this)).start();
    }
}
