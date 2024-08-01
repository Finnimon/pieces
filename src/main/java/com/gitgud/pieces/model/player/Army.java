package com.gitgud.pieces.model.player;


import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

import java.util.HashMap;
import java.util.HashSet;


@Deprecated
public record Army(HashMap<FightAgentType, HashSet<FightAgent>> baseCampStash)
{
}
