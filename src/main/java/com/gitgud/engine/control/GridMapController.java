package com.gitgud.engine.control;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.view.GridMapRender;


public class GridMapController extends Controller<GridMap>
{

    public GridMapController(GridMap gridMap, GridMapRender render)
    {
        super(gridMap, render);
    }
}
