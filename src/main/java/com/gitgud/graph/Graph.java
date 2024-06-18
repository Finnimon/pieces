package com.gitgud.graph;

import com.gitgud.engine.model.map.Tile;

import java.util.*;


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
    
    
    public Element place(Vertex vertex, Element element)
    {
        if (!containsVertex(vertex))
        {
            return null;
        }
        
        
        return vertices.put(vertex, element);
    }
    
    
    public Element get(Vertex vertex)
    {
        return vertices.get(vertex);
    }
    
    
    public Element get(int index)
    {
        return get(getVertex(index));
    }
    
    
    public Vertex getVertex(int index)
    {
        Object[] vertices = this.vertices.keySet().toArray();
        
        if (index >= vertices.length)
        {
            return null;
        }
        
        return (Vertex) vertices[index];
    }
    
    
    public Vertex getVertex(Element element)
    {
        return verticeSet().stream().filter(vertex -> get(vertex) == element).findFirst().orElse(null);
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
        HashSet<Edge> edgeSet;
        
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
    
    
    public Collection<Element> elements()
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
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex root, double range)
    {
        Graph<Vertex, Element, Edge> subGraph = new Graph<>();
        
        if (!this.containsVertex(root))
        {
            return subGraph;
        }
        
        subGraph.vertices.put(root, get(root));
        HashSet<Edge> rootEdges = new HashSet<>(
                getEdges(root).stream().filter(edge -> determineWeight(edge) <= range).toList());
        
        subGraph.edges.put(root, rootEdges);
        
        for (Edge edge : getEdges(root))
        {
            Vertex current = edge.getTo();
            
            subGraph.vertices.put(current, get(current));
            subGraph.addAll(subGraph(current, range - determineWeight(edge)));
        }
        
        
        return subGraph;
    }
    
    
    private float determineWeight(Edge edge)
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
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, float range)
    {
        return subGraph(start, (double) range);
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, int range)
    {
        return subGraph(start, (double) range);
    }
    
    
    public void addAll(Graph<Vertex, Element, Edge> addGraph)
    {
        for (Vertex vertex : addGraph.verticeSet())
        {
            vertices.putIfAbsent(vertex, addGraph.get(vertex));
            
            HashSet<Edge> currentEdges = getEdges(vertex);
            if (currentEdges == null)
            {
                currentEdges = new HashSet<>();
                edges.put(vertex, currentEdges);
            }
            
            currentEdges.addAll(addGraph.getEdges(vertex));
        }
    }
}
