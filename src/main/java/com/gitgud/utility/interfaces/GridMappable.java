package com.gitgud.utility.interfaces;

import com.gitgud.model.mission.map.GridMapping;
import com.gitgud.model.mission.map.Tile;


public interface GridMappable extends Sprite, Describable, Named
{
    public GridMapping getMappingTo(Tile tile);
}
