package com.gitgud.model.gameObjects;


import com.gitgud.model.gameObjects.gridMovable.FightAgent;

public class Main
{
    public static void main(String[] args)
    {
        FightAgent agent = new FightAgent(GameObjectType.KNIGHT, "faction1", 4, "white");
        System.out.println(agent.getSpriteUrl());
    }
}
