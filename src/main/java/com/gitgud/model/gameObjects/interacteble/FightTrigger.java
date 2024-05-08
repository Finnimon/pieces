package com.gitgud.model.gameObjects.interacteble;

import com.gitgud.control.FightController;
import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.mission.fight.Fight;
import com.gitgud.utility.interfaces.Interacteble;


public class FightTrigger extends GameObject implements Interacteble
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
