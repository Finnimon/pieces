package com.gitgud.pieces.control;

import com.gitgud.engine.control.Controller;
import com.gitgud.engine.control.Startable;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.view.render.city.CityRender;


/**
 * Controller for the {@link City}s scene.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class CityController extends Controller<City, CityRender> implements Startable
{
    
    public CityController(City model)
    {
        super(model, new CityRender(model));
    }
    
    
    public void start()
    {
        throw new RuntimeException("Unimplemented");
    }
    
}
