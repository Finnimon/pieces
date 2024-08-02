package com.gitgud.pieces.view.render.city;

import com.gitgud.engine.view.Render;
import com.gitgud.pieces.model.city.City;
import javafx.scene.layout.AnchorPane;


/**
 * Render for {@link City}.
 *
 * @author Finn L.
 * @version 0
 * @Owner: Finn L.
 */
public class CityRender extends AnchorPane implements Render<City>
{
    public CityRender(City city)
    {
        render(city);
    }
    
    
    @Override
    public void render(City model)
    {
    
    }
}
