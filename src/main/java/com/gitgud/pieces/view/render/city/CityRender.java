package com.gitgud.pieces.view.render.city;

import com.gitgud.engine.view.Render;
import com.gitgud.pieces.model.city.City;
import javafx.scene.layout.AnchorPane;


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
