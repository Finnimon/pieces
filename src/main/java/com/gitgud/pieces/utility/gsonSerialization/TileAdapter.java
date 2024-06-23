package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.engine.model.map.Terrain;
import com.gitgud.engine.model.map.Tile;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.geometry.Point2D;

import java.io.IOException;


public class TileAdapter extends TypeAdapter<Tile>
{
    
    @Override
    public void write(JsonWriter out, Tile value) throws IOException
    {
        out.beginObject();
        out.name("x").value(value.getX());
        out.name("y").value(value.getY());
        out.name("Terrain");
        new Gson().toJson(value.getTerrain(), Terrain.class, out);
        out.name("index").value(value.getIndex());
        out.endObject();
    }
    
    
    @Override
    public Tile read(JsonReader in) throws IOException
    {
        double x = in.nextDouble();
        double y = in.nextDouble();
        Terrain terrain = new Gson().fromJson(in.nextString(), Terrain.class);
        int index = in.nextInt();
        return new Tile(x, y, terrain, index);
    }
}
