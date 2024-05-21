package com.gitgud.model.gameObjects;


import com.gitgud.model.gameObjects.gridMovable.FightAgent;
public class Main
{
    public static void main(String[] args)
    {
        FightAgent pawn = new FightAgent("pawn", "faction1", 4, "white");
        System.out.print(pawn.getSpriteUrl());
    }
}
