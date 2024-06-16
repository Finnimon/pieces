package com.gitgud.engine.model.map;


import com.gitgud.engine.model.gameObject.GridMappable;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


/**
 * A GridMap as graph
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 15.05.2024
 * @Version: 3.0
 */
public class GridMap<T extends GridMappable> extends AbstractMap<Tile, T> implements NavigableMap<Tile, T>, Serializable
{
    private final TreeMap<Tile, T> graph;//todo render
    
    
    private final int width;
    
    
    private final int height;
    
    
    /**
     * The maps adjecancyMatrix or edges
     *
     * @Author: Finn L.
     * @Owner: Finn L.
     */
    private final float[][] adjecancyMatrix;
    
    
    public GridMap(TreeMap<Tile, T> graph, int width, int height)
    {
        this.graph = graph;
        this.width = width;
        this.height = height;
        
        
        this.adjecancyMatrix = null;
    }
    
    
    public GridMap(TreeMap<Tile, T> graph, int width, int height, float[][] adjecancyMatrix)
    {
        this.graph = graph;
        this.width = width;
        this.height = height;
        this.adjecancyMatrix = adjecancyMatrix;
    }
    
    
    public float[][] getAdjecancyMatrix()
    {
        return adjecancyMatrix;
    }
    
    
    public int getWidth()
    {
        return width;
    }
    
    
    public int getHeight()
    {
        return height;
    }
    
    
    public Tile getTile(int x, int y)
    {
        return getTileByIndex(calculateIndex(x, y));
    }
    
    
    public Tile getTile(T gridMappable)
    {
        return keySet().stream().filter(tile -> get(tile) == gridMappable).findFirst().orElse(null);
    }
    
    
    private boolean isConnected(Tile tile, Tile otherTile)
    {
        if (Math.abs(tile.xPosition() - otherTile.xPosition()) > 1)
        {
            return false;
        }
        if (Math.abs(tile.yPosition() - otherTile.yPosition()) > 1)
        {
            return false;
        }
        
        
        return otherTile.terrain().isTraversable() && tile.terrain().isTraversable();
    }
    
    
    /**
     * Returns all non-null T Values
     *
     * @return all non-null T Values in this map
     * @Author: Finn L.
     * @Owner: Finn L.
     * @Since: 15.05.2024
     * @Version: 1.0
     */
    public List<T> getAllGridMappables()
    {
        return this.graph.values().stream().filter(Objects::nonNull).toList();
    }
    
    
    public int getTileIndex(Tile tile)
    {
        return calculateIndex(tile.xPosition(), tile.yPosition());
    }
    
    
    private int calculateIndex(int x, int y)
    {
        return y * width + x;
    }
    
    
    public Tile getTileByIndex(int index)
    {
        return keySet().stream().filter(tile -> getTileIndex(tile) == index).findFirst().orElse(null);
    }
    
    //region Overrides
    
    
    @Override
    public int size()
    {
        return graph.size();
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return graph.isEmpty();
    }
    
    
    @Override
    public boolean containsKey(Object key)
    {
        return graph.containsKey(key);
    }
    
    
    @Override
    public boolean containsValue(Object value)
    {
        return graph.containsValue(value);
    }
    
    
    @Override
    public T get(Object key)
    {
        return graph.get(key);
    }
    
    
    @Override
    public T put(Tile key, T value)
    {
        return graph.put(key, value);
    }
    
    
    @Override
    public T remove(Object key)
    {
        return graph.remove(key);
    }
    
    
    @Override
    public void putAll(Map<? extends Tile, ? extends T> m)
    {
        graph.putAll(m);
    }
    
    
    @Override
    public void clear()
    {
        graph.clear();
    }
    
    
    @Override
    public Comparator<? super Tile> comparator()
    {
        return graph.comparator();
    }
    
    
    @Override
    public Entry<Tile, T> lowerEntry(Tile key)
    {
        return graph.lowerEntry(key);
    }
    
    
    @Override
    public Tile lowerKey(Tile key)
    {
        return graph.lowerKey(key);
    }
    
    
    @Override
    public Entry<Tile, T> floorEntry(Tile key)
    {
        return graph.floorEntry(key);
    }
    
    
    @Override
    public Tile floorKey(Tile key)
    {
        return graph.floorKey(key);
    }
    
    
    @Override
    public Entry<Tile, T> ceilingEntry(Tile key)
    {
        return graph.ceilingEntry(key);
    }
    
    
    @Override
    public Tile ceilingKey(Tile key)
    {
        return graph.ceilingKey(key);
    }
    
    
    @Override
    public Entry<Tile, T> higherEntry(Tile key)
    {
        return graph.higherEntry(key);
    }
    
    
    @Override
    public Tile higherKey(Tile key)
    {
        return graph.higherKey(key);
    }
    
    
    @Override
    public Entry<Tile, T> firstEntry()
    {
        return graph.firstEntry();
    }
    
    
    @Override
    public Entry<Tile, T> lastEntry()
    {
        return graph.lastEntry();
    }
    
    
    @Override
    public Entry<Tile, T> pollFirstEntry()
    {
        return graph.pollFirstEntry();
    }
    
    
    @Override
    public Entry<Tile, T> pollLastEntry()
    {
        return graph.pollFirstEntry();
    }
    
    
    @Override
    public NavigableMap<Tile, T> descendingMap()
    {
        return graph.descendingMap();
    }
    
    
    @Override
    public NavigableSet<Tile> navigableKeySet()
    {
        return graph.navigableKeySet();
    }
    
    
    @Override
    public NavigableSet<Tile> descendingKeySet()
    {
        return graph.descendingKeySet();
    }
    
    
    @Override
    public NavigableMap<Tile, T> subMap(Tile fromKey, boolean fromInclusive, Tile toKey, boolean toInclusive)
    {
        return graph.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }
    
    
    @Override
    public NavigableMap<Tile, T> headMap(Tile toKey, boolean inclusive)
    {
        return graph.headMap(toKey, inclusive);
    }
    
    
    @Override
    public NavigableMap<Tile, T> tailMap(Tile fromKey, boolean inclusive)
    {
        return graph.tailMap(fromKey, inclusive);
    }
    
    
    @Override
    public SortedMap<Tile, T> subMap(Tile fromKey, Tile toKey)
    {
        return graph.subMap(fromKey, toKey);
    }
    
    
    @Override
    public SortedMap<Tile, T> headMap(Tile toKey)
    {
        return graph.headMap(toKey);
    }
    
    
    @Override
    public SortedMap<Tile, T> tailMap(Tile fromKey)
    {
        return graph.tailMap(fromKey);
    }
    
    
    @Override
    public Tile firstKey()
    {
        return graph.firstKey();
    }
    
    
    @Override
    public Tile lastKey()
    {
        return graph.lastKey();
    }
    
    
    @Override
    public Set<Tile> keySet()
    {
        return graph.keySet();
    }
    
    
    @Override
    public Collection<T> values()
    {
        return graph.values();
    }
    
    
    @Override
    public Set<Entry<Tile, T>> entrySet()
    {
        return graph.entrySet();
    }
    
    
    @Override
    public T getOrDefault(Object key, T defaultValue)
    {
        return graph.getOrDefault(key, defaultValue);
    }
    
    
    @Override
    public void forEach(BiConsumer<? super Tile, ? super T> action)
    {
        graph.forEach(action);
    }
    
    
    @Override
    public void replaceAll(BiFunction<? super Tile, ? super T, ? extends T> function)
    {
        graph.replaceAll(function);
    }
    
    
    @Override
    public T putIfAbsent(Tile key, T value)
    {
        return graph.putIfAbsent(key, value);
    }
    
    
    @Override
    public boolean remove(Object key, Object value)
    {
        return graph.remove(key, value);
    }
    
    
    @Override
    public boolean replace(Tile key, T oldValue, T newValue)
    {
        return graph.replace(key, oldValue, newValue);
    }
    
    
    @Override
    public T replace(Tile key, T value)
    {
        return graph.replace(key, value);
    }
    
    
    @Override
    public T computeIfAbsent(Tile key, Function<? super Tile, ? extends T> mappingFunction)
    {
        return graph.computeIfAbsent(key, mappingFunction);
    }
    
    
    @Override
    public T computeIfPresent(Tile key, BiFunction<? super Tile, ? super T, ? extends T> remappingFunction)
    {
        return graph.computeIfPresent(key, remappingFunction);
    }
    
    
    @Override
    public T compute(Tile key, BiFunction<? super Tile, ? super T, ? extends T> remappingFunction)
    {
        return graph.compute(key, remappingFunction);
    }
    
    
    @Override
    public T merge(Tile key, T value, BiFunction<? super T, ? super T, ? extends T> remappingFunction)
    {
        return graph.merge(key, value, remappingFunction);
    }
    
    //endregion
    
    
}
