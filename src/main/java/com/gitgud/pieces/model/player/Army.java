package com.gitgud.pieces.model.player;


import com.gitgud.pieces.model.gameObjects.FightAgentType;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;

import java.util.ArrayList;
import java.util.HashMap;


public record Army(HashMap<FightAgentType, ArrayList<FightAgent>> baseCampStash)
{
}
