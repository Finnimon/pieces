package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.engine.model.map.Tile;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.geometry.Point2D;

import java.io.IOException;


public class TileTypeAdapter extends TypeAdapter<Point2D>
{
    
    @Override
    public void write(JsonWriter out, Point2D value) throws IOException
    {
        //todo
    }
    
    
    @Override
    public Point2D read(JsonReader in) throws IOException
    {
        return null;
    }
}
