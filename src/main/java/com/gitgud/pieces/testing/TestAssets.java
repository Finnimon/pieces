package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface TestAssets
{
    GridMap<GridMappable> testMap = TestStuff.getTestMap(12, 12);
}
