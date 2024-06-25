package com.gitgud.pieces.control;

import com.gitgud.engine.control.Controller;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.view.render.city.CityRender;


public class CityController extends Controller<City, CityRender>
{
    
    public CityController(City model)
    {
        super(model, new CityRender(model));
    }
    
    
}
