package com.gitgud.model.graph;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public interface Graph<Node extends Comparable<Node>, Element>
{
    
    TreeMap<Node, Element> getMap();
    
    
    public boolean[][] getAdjecancyMatrix();
    
    
    public boolean[][] calculateAdjecancyMatrix();
    
    
    public default Node getNode(int x, int y)
    {
        return getNodeByIndex(calculateIndex(x, y));
    }
    
    
    public default Node getNode(Element element)
    {
        return nodeSet().stream().filter(Node -> getElement(Node) == element).findFirst().orElse(null);
    }
    
    
    public int calculateIndex(Node node);
    
    public default Node getNode(int index)
    {
        return getMap().keySet().stream().filter(node -> calculateIndex(node)==index).findFirst().orElse(null);
    }
    
    /**
     * Returns all non-null Elements
     *
     * @return all non-null Elements in this graph
     * @Author: Finn L.
     * @Owner: Finn L.
     * @Since: 15.05.2024
     * @Version: 1.0
     */
    public default List<Element> getAllNonNullElements()
    {
        return getMap().values().stream().filter(Objects::nonNull).toList();
    }
    
    
    public int getNodeIndex(Node Node);
    
    
    public int calculateIndex(int x, int y);
    
    
    public default Node getNodeByIndex(int index)
    {
        return nodeSet().stream().filter(Node -> getNodeIndex(Node) == index).findFirst().orElse(null);
    }
    
    //region Overrides
    
    
    public default int size()
    {
        return getMap().size();
    }
    
    
    public default boolean isEmpty()
    {
        return getMap().isEmpty();
    }
    
    
    public default boolean containsNode(Node node)
    {
        return getMap().containsKey(node);
    }
    
    
    public default boolean containsElement(Element element)
    {
        return getMap().containsValue(element);
    }
    
    
    public default Element getElement(Node key)
    {
        return getMap().get(key);
    }
    
    
    public default Element place(Node node, Element element)
    {
        if (!getMap().containsKey(node))
        {
            throw new IllegalArgumentException("Graph does not contain " + node);
        }
        return getMap().put(node, element);
    }
    
    
    public default void placeAll(Map<? extends Node, ? extends Element> m)
    {
        for (Node node : m.keySet())
        {
            place(node, m.get(node));
        }
    }
    
    
    public default void clear()
    {
        nodeSet().forEach(this::clearNode);
    }
    
    
    public default Map.Entry<Node, Element> lowerEntry(Node node)
    {
        return getMap().lowerEntry(node);
    }
    
    
    public default Node lowerNode(Node node)
    {
        return getMap().lowerKey(node);
    }
    
    
    public default Map.Entry<Node, Element> floorEntry(Node node)
    {
        return getMap().floorEntry(node);
    }
    
    
    public default Node floorNode(Node node)
    {
        return getMap().floorKey(node);
    }
    
    
    public default Map.Entry<Node, Element> ceilingEntry(Node node)
    {
        return getMap().ceilingEntry(node);
    }
    
    
    public default Node ceilingNode(Node node)
    {
        return getMap().ceilingKey(node);
    }
    
    
    public default Map.Entry<Node, Element> higherEntry(Node node)
    {
        return getMap().higherEntry(node);
    }
    
    
    public default Node higherNode(Node node)
    {
        return getMap().higherKey(node);
    }
    
    
    public default Map.Entry<Node, Element> firstEntry()
    {
        return getMap().firstEntry();
    }
    
    
    public default Map.Entry<Node, Element> lastEntry()
    {
        return getMap().lastEntry();
    }
    
    
    public default Map.Entry<Node, Element> pollFirstEntry()
    {
        return getMap().pollFirstEntry();
    }
    
    
    public default NavigableSet<Node> navigableNodeSet()
    {
        return getMap().navigableKeySet();
    }
    
    
    public default NavigableSet<Node> descendingNodeSet()
    {
        return getMap().descendingKeySet();
    }
    
    
    public default Node firstNode()
    {
        return getMap().firstKey();
    }
    
    
    public default Node lastNode()
    {
        return getMap().lastKey();
    }
    
    
    public default Set<Node> nodeSet()
    {
        return getMap().keySet();
    }
    
    
    public default Collection<Element> elements()
    {
        return getMap().values();
    }
    
    
    public default Set<Map.Entry<Node, Element>> entrySet()
    {
        return getMap().entrySet();
    }
    
    
    public default void forEach(BiConsumer<? super Node, ? super Element> action)
    {
        getMap().forEach(action);
    }
    
    
    public default void replaceAll(BiFunction<? super Node, ? super Element, ? extends Element> function)
    {
        getMap().replaceAll(function);
    }
    
    
    public default Element placeIfAbsent(Node node, Element element)
    {
        if (!containsNode(node))
        {
            return null;
        }
        return getMap().putIfAbsent(node, element);
    }
    
    
    public default Collection<Node> removeElementOccurences(Element element)
    {
        Collection<Node> removalNodes = nodeSet().stream().filter(node -> getElement(node) == element).toList();
        removalNodes.forEach(this::clearNode);
        return removalNodes;
    }
    
    
    public default Element clearNode(Node node)
    {
        try
        {
            return place(node, null);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }
    
    
    public default boolean replaceElement(Node key, Element oldValue, Element newValue)
    {
        return getMap().replace(key, oldValue, newValue);
    }
    
}
