//package com.gitgud.pieces.utility.services;
//
//import com.gitgud.pieces.model.gameobjects.AssetLocator;
//import com.gitgud.pieces.model.gameobjects.GameObjectAttribute;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.HashMap;
//
//
///**
// * Diese Klasse stellt alle Methoden bereit, um Informationen aus externen Spielressourcen an Spielobjekte zu
// verteilen.
// *
// * @author Finn Maximilian Kramer
// * @version 1.0
// */
//public class AssetParser
//{
//    // Die aktuell geladene Ressource
//    private static FileReader assetFile;
//
//
//    /**
//     * Lädt eine Ressource in die Klassenvariable.
//     *
//     * @param assetUrl Pfad zur Ressource
//     */
//    private static void loadAssetFile(String assetUrl)
//    {
//        try
//        {
//            AssetParser.assetFile = new FileReader(assetUrl);
//        }
//        catch (FileNotFoundException e)
//        {
//            throw new RuntimeException();
//        }
//    }
//
//
//    /**
//     * Liefert ein Array aus DOT_JSON-Objekten aus einer Ressource.
//     *
//     * @param assetUrl Pfad zur Ressource
//     * @return Gibt ein JsonArray-Objekt zurück.
//     */
//    public static JsonArray parseJsonArray(String assetUrl)
//    {
//        loadAssetFile(assetUrl);
//        JsonElement jsonTree = JsonParser.parseReader(AssetParser.assetFile);
//
//        if (jsonTree.isJsonArray())
//        {
//            return jsonTree.getAsJsonArray();
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//
//    /**
//     * Liefert eine Zuordnung von Attributwerten auf entsprechende DOT_JSON-Objekte.
//     *
//     * @param objectTypes   Ein Array aus DOT_JSON-Objekten
//     * @param attributeName Name des Attributes, dessen Wert als Schlüssel verwendet werden soll
//     * @return Gibt ein HashMap<String, JsonObject>-Objekt zurück.
//     */
//    public static HashMap<String, JsonObject> attributeKeyToJsonObject(JsonArray objectTypes, String attributeName)
//    {
//        HashMap<String, JsonObject> map = new HashMap<>();
//
//        if (objectTypes != null)
//        {
//            for (int i = 0; i < objectTypes.size(); i++)
//            {
//                JsonObject jsonObject = objectTypes.get(i).getAsJsonObject();
//                map.put(jsonObject.get(attributeName).getAsString(), jsonObject);
//            }
//
//            return map;
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//
//    /**
//     * Liefert ein DOT_JSON-Objekt zu dem Wert des Typ-Attributes für ein FightAgent-Objekt.
//     *
//     * @param types Ein Array aus DOT_JSON-Objekten mit allen FightAgent-Typen
//     * @param type  Bezeichnung des FightAgent-Typen
//     * @return Gibt ein JsonObject-Objekt zurück.
//     */
//    public static JsonObject getFightAgentByType(JsonArray types, String type)
//    {
//        loadAssetFile(AssetLocator.FIGHT_AGENT_TYPES);
//        HashMap<String, JsonObject> unitTypeMap = attributeKeyToJsonObject(types, GameObjectAttribute.TYPE);
//        return unitTypeMap.get(type);
//    }
//
//
//    /**
//     * Liefert ein DOT_JSON-OBjekt zu dem Wert des Typ-Attributes für ein Spell-Objekt
//     *
//     * @param types Ein Array aus DOT_JSON-Objekten mit allen Spell-Typen
//     * @param type  Bezeichnung des Spell-Typen
//     * @return Gibt ein JsonObject-Objekt zurück
//     */
//    public static JsonObject getSpellByType(JsonArray types, String type)
//    {
//        loadAssetFile(AssetLocator.SPELL_TYPES);
//        HashMap<String, JsonObject> unitTypeMap = attributeKeyToJsonObject(types, GameObjectAttribute.TYPE);
//        return unitTypeMap.get(type);
//    }
//
//
//    /**
//     * Liefert für ein DOT_JSON-Objekt für Attribute mit natürlichen Zahlenwerten eine Abbildung der
//     Attributbezeichnungen auf die Zahlenwerte.
//     *
//     * @param object Ein DOT_JSON-Objekt mit Attributen mit natürlichen Zahlenwerten
//     * @return Gibt ein HashMap<String, Integer>-Objekt zurück.
//     */
//    public static HashMap<String, Integer> mapAttributeKeysToIntegers(JsonObject object)
//    {
//        HashMap<String, Integer> map = new HashMap<>();
//
//        for (String key : object.keySet())
//        {
//            try
//            {
//                map.put(key, object.get(key).getAsInt());
//            }
//            catch (NumberFormatException e)
//            {
//            }
//        }
//
//        return map;
//    }
//}
