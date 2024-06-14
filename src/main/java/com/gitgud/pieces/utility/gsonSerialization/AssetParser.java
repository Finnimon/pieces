package com.gitgud.pieces.utility.gsonSerialization;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class AssetParser
{
    // Die aktuell geladene Ressource
    private static FileReader assetFile;

    /**
     * Lädt eine Ressource in die Klassenvariable.
     * @param assetUrl Pfad zur Ressource
     */
    private static void loadAssetFile (String assetUrl)
    {
        try
        {
            AssetParser.assetFile = new FileReader(assetUrl);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Liefert ein Array aus JSON-Objekten aus einer Ressource.
     * @param assetUrl Pfad zur Ressource
     * @return Gibt ein JsonArray-Objekt zurück.
     */
    public static JsonArray parseJsonArray (String assetUrl)
    {
        loadAssetFile(assetUrl);
        JsonElement jsonTree = JsonParser.parseReader(AssetParser.assetFile);

        if (jsonTree.isJsonArray())
        {
            return jsonTree.getAsJsonArray();
        }
        else
        {
            return null;
        }
    }
}
