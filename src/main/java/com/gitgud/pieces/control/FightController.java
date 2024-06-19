package com.gitgud.pieces.control;


import com.gitgud.pieces.model.fight.Fight;


//todo render as scene
public class FightController
{
    //todo render
    private final Fight fight;
    
    
    public FightController(Fight fight)
    {
        this.fight = fight;
    }
    
    
    public Fight getFight()
    {
        return fight;
    }
    
    
}
