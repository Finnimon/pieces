package com.gitgud.model.gameObjects;

import com.gitgud.model.map.GridMapping;
import com.gitgud.model.map.Tile;


public interface GridMappable extends Sprite, Describable, Named
{
    public GridMapping getMappingTo(Tile tile);
}
