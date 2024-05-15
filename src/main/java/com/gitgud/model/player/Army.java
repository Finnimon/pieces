package com.gitgud.model.player;


import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.gameObjects.FightAgentType;

import java.util.ArrayList;
import java.util.HashMap;


public record Army(HashMap<FightAgentType, ArrayList<FightAgent>> baseCampStash)
{
}
