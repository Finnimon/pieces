package com.gitgud.graph;

import java.util.*;
import java.util.function.Predicate;


/**
 * The Default graph implementation does not contain Methods for removing Nodes and is neither weighted, nor directed.
 *
 * @param <Vertex>
 * @param <Element>
 * @param <Edge>
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 */
public class Graph<Vertex extends com.gitgud.graph.Vertex, Element, Edge extends com.gitgud.graph.Edge<Vertex>>
{
    public static final float DEFAULT_WEIGHT = 0.9999f;
    
    
    private final TreeMap<Vertex, Element> vertices;
    
    
    private final TreeMap<Vertex, HashSet<Edge>> edges;
    
    
    public Graph(TreeMap<Vertex, Element> vertices, TreeMap<Vertex, HashSet<Edge>> edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }
    
    
    public Graph()
    {
        this.vertices = new TreeMap<>();
        this.edges = new TreeMap<>();
    }
    
    
    protected TreeMap<Vertex, Element> getVertices()
    {
        return vertices;
    }
    
    
    protected TreeMap<Vertex, HashSet<Edge>> getEdgeMap()
    {
        return edges;
    }
    
    
    /**
     * Adds a new {@param vertex} with Element and Edges to the Graph and assigns it an index.
     * If this already contains {@param vertex} it will return false and do nothing.
     *
     * @param vertex
     * @param element
     * @param edges
     * @return
     */
    public boolean add(Vertex vertex, Element element, HashSet<Edge> edges)
    {
        if (vertices.containsKey(vertex))
        {
            return false;
        }
        
        
        vertex.setIndex(vertices.size());
        
        
        vertices.put(vertex, element);
        addEdges(vertex, edges);
        
        
        return true;
    }
    
    
    public boolean add(Vertex vertex, Element element)
    {
        return add(vertex, element, new HashSet<>());
    }
    
    
    public boolean add(Vertex vertex)
    {
        return add(vertex, null);
    }
    
    
    /**
     * Places {@param element} at {@param vertex} if this contains {@param vertex}.
     *
     * @param vertex
     * @param element
     * @return the previous Element or null if there was none or if the vertex does not exist.
     */
    public Element place(Vertex vertex, Element element)
    {
        if (!containsVertex(vertex))
        {
            return null;
        }
        
        
        return vertices.put(vertex, element);
    }
    
    
    public Element place(int index, Element element)
    {
        Vertex vertex = getVertex(index);
        
        return place(vertex, element);
    }
    
    
    /**
     * Gets the {@link Element} on {@param vertex}
     *
     * @param vertex
     * @return {@link Element} mapped to {@param vertex}
     */
    public Element get(Vertex vertex)
    {
        return vertices.get(vertex);
    }
    
    
    /**
     * Gets the {@link Element} at {@param index}
     *
     * @param index The Index of the {@link Vertex} which has the {@link Element}
     * @return The {@link Element} or null if there is none
     */
    public Element get(int index)
    {
        return get(getVertex(index));
    }
    
    
    /**
     * Gets the {@link Vertex} at {@param index}
     *
     * @param index The Index of the {@link Vertex}
     * @return The {@link Vertex} or null if there is none
     */
    public Vertex getVertex(int index)
    {
        Object[] vertices = this.vertices.keySet().toArray();
        
        if (index >= vertices.length)
        {
            return null;
        }
        
        return (Vertex) vertices[index];
    }
    
    
    /**
     * Finds first {@link Vertex} with {@param element} as value
     *
     * @param element The value mapped to the {@link Vertex}.
     * @return The {@link Vertex} or null if there is none
     */
    public Vertex getVertex(Element element)
    {
        return getVertices(element).stream().findFirst().orElse(null);
    }
    
    
    /**
     * Gets all Vertices with {@param element} as value.
     *
     * @param element The value mapped to the {@link Vertex}es.
     * @return All {@link Vertex}es with {@param element} as value or an empty {@link Collection} if there are none
     */
    public Collection<Vertex> getVertices(Element element)
    {
        return verticeSet().stream().filter(vertex -> get(vertex) == element).toList();
    }
    
    
    public boolean addEdge(Vertex from, Edge edge)
    {
        Vertex to = edge.getTo();
        
        boolean operationPossible = !(!containsVertex(from) || !containsVertex(to));
        
        if (!operationPossible)
        {
            return false;
        }
        
        nullSafeEdgeAdd(from, edge);
        
        nullSafeEdgeAdd(to, (Edge) edge.reverse(from));
        
        
        return true;
    }
    
    
    private void nullSafeEdgeAdd(Vertex from, Edge edge)
    {
        HashSet<Edge> edgeSet = edges.computeIfAbsent(from, k -> new HashSet<>());
        edgeSet.add(edge);
    }
    
    
    public boolean addEdges(Vertex from, HashSet<Edge> edges)
    {
        boolean operationPossible = containsVertex(from);
        
        if (!operationPossible)
        {
            return false;
        }
        
        
        HashSet<Edge> edgeSet = getEdgeMap().computeIfAbsent(from, k -> new HashSet<>());
        
        
        edges.forEach(edge -> addEdge(from, edge));
        
        
        return true;
    }
    
    
    public boolean containsVertex(Vertex vertex)
    {
        return vertices.containsKey(vertex);
    }
    
    
    public TreeSet<Vertex> verticeSet()
    {
        return new TreeSet<>(vertices.keySet());
    }
    
    
    public Collection<Element> nonNullElements()
    {
        return vertices.values().stream().filter(Objects::nonNull).toList();
    }
    
    
    public int size()
    {
        return vertices.size();
    }
    
    
    public Collection<Vertex> getAdjacentVertices(Vertex root)
    {
        ArrayList<Vertex> adjacentVertices = new ArrayList<>();
        
        for (Edge edge : getEdges(root))
        {
            adjacentVertices.add(edge.getTo());
        }
        
        
        return adjacentVertices;
    }
    
    
    public HashSet<Edge> getEdges(Vertex root)
    {
        return getEdgeMap().get(root);
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex root, double range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter)
    {
        return subGraph(root, range, fromEdgeFilter, toEdgeFilter, new HashMap<>());
    }
    
    
    /**
     * Sub graphs indexing will be lost. This is to ensure that the original indexing is intact.
     * Use non Indexed methods on the returned SubGraph.
     *
     * @param root
     * @param range
     * @param fromEdgeFilter
     * @param toEdgeFilter
     * @param weights
     * @return
     */
    public Graph<Vertex, Element, Edge> subGraph(Vertex root, double range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter, HashMap<Vertex, Double> weights)
    {
        Graph<Vertex, Element, Edge> subGraph = new Graph<>();
        
        
        if (!this.containsVertex(root))
        {
            return subGraph;
        }
        
        subGraph.vertices.put(root, get(root)); //adds current root Element
        weights.put(root, range);
        subGraph.edges.put(root, new HashSet<>());
        
        for (Edge edge : getEdges(root))
        {
            Vertex current = edge.getTo();
            
            if (!toEdgeFilter.test(current))
            {
                continue;
            }
            
            double weight = determineWeight(edge);
            
            if (weight > range)
            {
                continue;
            }
            
            subGraph.vertices.put(current, get(current));
            subGraph.edges.put(current, new HashSet<>());
            subGraph.addEdge(root, edge);
            
            double remainingRange = range - weight;
            
            if (remainingRange <= 0 || !fromEdgeFilter.test(current))
            {
                continue;
            }
            
            if (weights.containsKey(current)&&weights.get(current)<remainingRange)
                continue;
            
            subGraph.addAll(subGraph(current, remainingRange, fromEdgeFilter, toEdgeFilter));
        }
        
        
        return subGraph;
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, float range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter)
    {
        return subGraph(start, (double) range, fromEdgeFilter, toEdgeFilter);
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, int range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter)
    {
        return subGraph(start, (double) range, fromEdgeFilter, toEdgeFilter);
    }
    
    
    private double determineWeight(Edge edge)
    {
        if (!(edge instanceof WeightedEdge<?>))
        {
            return DEFAULT_WEIGHT;
        }
        else
        {
            return ((WeightedEdge<?>) edge).getWeight();
        }
    }
    
    
    /**
     * This Method does not ensure that the indexing is intact afterwards. This means that if Vertices with are added, that have the same index as an existing vertex, the index will be overwritten.
     *
     * @param addGraph
     */
    public void addAll(Graph<Vertex, Element, Edge> addGraph)
    {
        for (Vertex vertex : addGraph.verticeSet())
        {
            vertices.putIfAbsent(vertex, addGraph.get(vertex));
            HashSet<Edge> currentEdges = addGraph.getEdges(vertex);
            
            if (currentEdges == null)
            {
                currentEdges = new HashSet<>();
            }
            
            for (Edge edge : currentEdges)
            {
                addEdge(vertex, edge);
            }
        }
    }
    
    
    public List<Element> elements()
    {
        return vertices.values().stream().toList();
    }
    
    
    public Element clearVertex(Vertex vertex)
    {
        return vertices.put(vertex, null);
    }
    
}
