package com.gitgud.pieces.control;

import com.gitgud.engine.control.Controller;
import com.gitgud.engine.control.Startable;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.view.render.city.CityRender;


public class CityController extends Controller<City, CityRender> implements Startable
{
    
    public CityController(City model)
    {
        super(model, new CityRender(model));
    }
    
    
    public void start()
    {
    }
    
    
    //todo
}
