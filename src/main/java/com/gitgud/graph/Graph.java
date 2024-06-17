package com.gitgud.graph;

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
        Vertex vertex = (Vertex) vertices.keySet().toArray()[index];
        if (vertex.getIndex() != index)
        {
            throw new IllegalStateException("Vertex" + vertex + " has wrong index");
        }
        
        return vertex;
    }
    public Vertex getVertex(Element element)
    {
        return verticeSet().stream().filter(vertex -> get(vertex)==element).findFirst().orElse(null);
    }
    
    
    public HashSet<Edge> getEdgeMap(Vertex vertex)
    {
        return edges.get(vertex);
    }
    
    
    public boolean addEdge(Vertex from, Edge edge)
    {
        Vertex to = edge.getTo();
        
        boolean operationPossible = !(!containsVertex(from) || !containsVertex(to));
        
        if (operationPossible)
        {
            edges.get(from).add(edge);
            edges.get(to).add((Edge) edge.reverse(from));
        }
        
        
        return operationPossible;
    }
    
    
    public boolean addEdges(Vertex from, HashSet<Edge> edges)
    {
        boolean operationPossible = containsVertex(from);
        
        if (operationPossible)
        {
            edges.forEach(edge -> addEdge(from, edge));
        }
        
        
        return operationPossible;
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

        for (Edge edge:getEdges(root))
        {
            adjacentVertices.add(edge.getTo());
        }
        
        
        return adjacentVertices;
    }
    
    public Collection<Edge> getEdges(Vertex root)
    {
        return getEdgeMap().get(root);
    }
    
}
