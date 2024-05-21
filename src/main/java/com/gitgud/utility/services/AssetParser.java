package com.gitgud.utility.services;

import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.GameObjectAttribute;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 * Diese Klasse stellt alle Methoden bereit, um die Informationen aus den externen Spielressourcen an die Spielobjekte zu verteilen.
 * @author Finn Maximilian Kramer
 * @version 1.0
 */
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

    /**
     * Liefert eine Abbildung von spezifischen Attributwerten auf JSON-Objekte.
     * @param objectTypes Ein Array aus JSON-Objekten
     * @param attributeName Name des Attributes, dessen Werte als Schlüssel verwendet werden sollen
     * @return Gibt ein HashMap<String, JsonObject>-Objekt zurück.
     */
    public static HashMap<String, JsonObject> attributeKeyToJsonObject (JsonArray objectTypes, String attributeName)
    {
        HashMap<String, JsonObject> map = new HashMap<>();

        if (objectTypes != null)
        {
            for (int i = 0; i < objectTypes.size(); i++)
            {
                JsonObject jsonObject = objectTypes.get(i).getAsJsonObject();
                map.put(jsonObject.get(attributeName).getAsString(), jsonObject);
            }

            return map;
        }
        else
        {
            return null;
        }
    }

    /**
     * Liefert ein JSON-Objekt zu dem Wert des Typ-Attributes für ein FightAgent-Objekt.
     * @param types Ein Array aus JSON-Objekten mit allen FightAgent-Typen
     * @param type Bezeichnung des FightAgent-Typen
     * @return Gibt ein JsonObject-Objekt zurück.
     */
    public static JsonObject getFightAgentByType (JsonArray types, String type)
    {
        loadAssetFile(AssetLocator.FIGHT_AGENT_TYPES);
        HashMap<String, JsonObject> unitTypeMap = attributeKeyToJsonObject(types, GameObjectAttribute.TYPE);
        return unitTypeMap.get(type);
    }


    /**
     * Liefert ein JSON-OBjekt zu dem Wert des Typ-Attributes für ein Spell-Objekt
     * @param types Ein Array aus JSON-Objekten mit allen Spell-Typen
     * @param type Bezeichnung des Spell-Typen
     * @return Gibt ein JsonObject-Objekt zurück
     */
    public static JsonObject getSpellByType (JsonArray types, String type)
    {
        loadAssetFile(AssetLocator.SPELL_TYPES);
        HashMap<String, JsonObject> unitTypeMap = attributeKeyToJsonObject(types, GameObjectAttribute.TYPE);
        return unitTypeMap.get(type);
    }


    /**
     * Liefert für ein JSON-Objekt für Attribute mit natürlichen Zahlenwerten eine Abbildung der Attributbezeichnungen auf die Zahlenwerte.
     * @param object Ein JSON-Objekt mit Attributen mit natürlichen Zahlenwerten
     * @return Gibt ein HashMap<String, Integer>-Objekt zurück.
     */
    public static HashMap<String, Integer> mapAttributeKeysToIntegers (JsonObject object)
    {
        HashMap<String, Integer> map = new HashMap<>();

        for (String key : object.keySet())
        {
            try
            {
                map.put(key, object.get(key).getAsInt());
            }
            catch (NumberFormatException e) {}
        }

        return map;
    }

    /**
     * Liefert ein JsonElement-Objekt als Attributwert eines JsonObject-Objektes aus einem JsonArray-Objekt aus einer Ressource.
     * @param assetUrl Pfad zur Ressource
     * @param type Wert des Typ-Attributes des JsonObject-Objektes aus einem JsonArray-Objekt
     * @param attributeName Bezeichnung des Attributes, dessen Wert zurückgegeben werden soll.
     * @return Der Wert des Attributes wird als JsonElement-Objekt zurückgegeben.
     */
    public static JsonElement getAttributeValueByTypeFromArray (String assetUrl, String type, String attributeName)
    {
        JsonArray types = AssetParser.parseJsonArray(assetUrl);
        HashMap<String, JsonObject> typeToJsonObject = AssetParser.attributeKeyToJsonObject(types, attributeName);

        if (typeToJsonObject != null)
        {
            return typeToJsonObject.get(type).getAsJsonObject().get(attributeName);
        }

        return null;
    }
}
