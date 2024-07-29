package com.gitgud.pieces.model.game;

public enum GameState
{
    NOT_LOADED,
    CITY,
    MISSION,
    MISSION_FIGHT,
    ARENA_FIGHT
    //
    //
    //    public static GameState getGameState(boolean isMission, boolean isFight)
    //    {
    //        if (isMission && isFight)
    //        {
    //            return MISSION_FIGHT;
    //        }
    //        else if (isMission)
    //        {
    //            return MISSION;
    //        }
    //        else if (isFight)
    //        {
    //            return ARENA_FIGHT;
    //        }
    //        else
    //        {
    //            return CITY;
    //        }
    //    }
}
