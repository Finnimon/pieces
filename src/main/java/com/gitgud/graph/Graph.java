package com.gitgud.graph;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;


/**
 * <p>The Default base graph implementation .
 * <p>Is neither weighted, nor directed.
 * <p>Adjecancies are noted using adjecency Collections
 * <p>Allows for null Elements but not null vertices
 *
 * @param <Vertex>
 * @param <Element>
 * @param <Edge>
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 * @see com.gitgud.graph.Vertex
 * @see com.gitgud.graph.Edge
 */
public class Graph<Vertex extends com.gitgud.graph.Vertex, Element, Edge extends com.gitgud.graph.Edge<Vertex>>
{
    public static final float DEFAULT_WEIGHT = 0.9999f;
    
    
    /**
     * Vertices with Elements mapped to them
     */
    private final TreeMap<Vertex, Element> vertices;
    
    
    /**
     * The Edges mapped to their from Vertex
     */
    private final TreeMap<Vertex, HashSet<Edge>> edges;
    
    
    /**
     * Default Constructor that creates an empty Graph
     */
    public Graph()
    {
        this(new TreeMap<>(), new TreeMap<>());
    }
    
    
    /**
     * Default Constructor that initializes a non-empty Graph
     *
     * @param vertices The Vertex Element mappings for this Graph
     * @param edges    The Edges for this Graph
     * @Precondition: All Vertices in {@code edges} are in {@code vertices} also and the other way around and their
     * indexing is intact.
     * @Postcondition: The Graph will function properly
     */
    public Graph(@NotNull TreeMap<Vertex, Element> vertices, @NotNull TreeMap<Vertex, HashSet<Edge>> edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }
    
    
    /**
     * <p>Allows direct access to {@link #vertices}.
     * <p>Careless accessing and changing may create a faulty and non-functional Graph
     *
     * @return The graphs {@link Vertex} {@link Element} mappings
     * @Precondition: The data should never be directly accessed or be made public.
     * @Postcondition: The Graph will remain intact and functional
     */
    protected final TreeMap<Vertex, Element> getVertices()
    {
        return vertices;
    }
    
    
    /**
     * Gets an unmodifiable mapping of {@link Vertex}es to {@link Edge}s
     *
     * @return An unmodifiable mapping of {@link #edges}
     */
    public Map<Vertex, Set<Edge>> unmodifiableEdgeMap()
    {
        HashMap<Vertex, Set<Edge>> map = new HashMap<>();
        for (Vertex vertex : verticeSet())
        {
            map.put(vertex, (Collections.unmodifiableSet(getEdges(vertex))));
        }
        
        return Collections.unmodifiableMap(map);
    }
    
    
    /**
     * Returns all vertices
     *
     * @return All {@link Vertex}es
     */
    public TreeSet<Vertex> verticeSet()
    {
        return new TreeSet<>(vertices.keySet());
    }
    
    
    /**
     * Gets all edges from {@code root}
     *
     * @param root The root {@link Vertex} for the search
     * @return All {@link Edge}s from {@code root}
     */
    public HashSet<Edge> getEdges(Vertex root)
    {
        return getEdgeMap().get(root);
    }
    
    
    /**
     * <p>Allows direct access to {@link #edges}.
     * <p>Careless accessing and changing may create a faulty and non-functional Graph
     *
     * @return The graphs {@link Edge}s mapped to their from {@link Vertex}.
     * @Precondition: This data should never be directly accessed or be made public.
     * @Postcondition: The Graph will remain intact and functional
     */
    protected final TreeMap<Vertex, HashSet<Edge>> getEdgeMap()
    {
        return edges;
    }
    
    
    /**
     * Override for {@link #add(com.gitgud.graph.Vertex, Object)} that maps null to {@code vertex}.
     *
     * @param vertex The new {@link Vertex}.
     * @return If the {@code vertex} was successfully added true or if the Graph already contained it false.
     * @see #add(com.gitgud.graph.Vertex, Object)
     */
    public boolean add(@NotNull Vertex vertex)
    {
        return add(vertex, null);
    }
    
    
    /**
     * Override for {@link #add(com.gitgud.graph.Vertex, Object, HashSet)} that draws no Edges
     *
     * @param vertex  The new {@link Vertex}.
     * @param element The element to be mapped to {@code vertex}.
     * @return If the {@code vertex} was successfully added true or if the Graph already contained it false.
     * @see #add(com.gitgud.graph.Vertex, Object, HashSet)
     */
    public boolean add(@NotNull Vertex vertex, Element element)
    {
        return add(vertex, element, new HashSet<>());
    }
    
    
    /**
     * Adds a new {@code vertex} with Element and Edges to the Graph and assigns it the next available index.
     * If this already contains {@code vertex} it will return false and do nothing.
     *
     * @param vertex  The new {@link Vertex}.
     * @param element The element to be mapped to {@param vertex}.
     * @param edges   The edges to be drawn from {@code vertex}.
     * @return If the {@code vertex} was successfully added true or if the Graph already contained it false.
     */
    public boolean add(@NotNull Vertex vertex, Element element, @NotNull HashSet<Edge> edges)
    {
        if (vertices.containsKey(vertex))
        {
            return false;
        }
        //updates the vertex index
        vertex.setIndex(size());
        
        vertices.put(vertex, element);
        addEdges(vertex, edges);
        
        return true;
    }
    
    
    /**
     * Determines the number of {@link Vertex}es in this Graph
     *
     * @return The number of {@link Vertex}es in this Graph
     */
    public int size()
    {
        return vertices.size();
    }
    
    
    /**
     * Adds a set of edges from {@code from}
     *
     * @param from  The from {@link Vertex}
     * @param edges The {@link Edge}s to be drawn from {@code from}
     * @return Whether any of the {@code edges} were added
     */
    public boolean addEdges(Vertex from, Set<Edge> edges)
    {
        if (!containsVertex(from))
        {
            return false;
        }
        
        boolean anyAdded = false;
        
        for (Edge edge : edges)
        {
            boolean edgeAdded = addEdge(from, edge);
            
            if (anyAdded || !edgeAdded)
            {
                continue;
            }
            anyAdded = true;
        }
        return anyAdded;
    }
    
    
    /**
     * Checks if the Graph contains {@code vertex}
     *
     * @param vertex The {@link Vertex} to be checked
     * @return if the Graph contains {@code vertex}
     */
    public boolean containsVertex(Vertex vertex)
    {
        return vertices.containsKey(vertex);
    }
    
    
    /**
     * Adds an {@link Edge} between {@code from} and {@code edge}.getTo(), bidirectional
     *
     * @param from The from {@link Vertex}
     * @param edge The {@link Edge} from {@code from}
     * @return Whether operation was possible ie the Graph contains both {@code from} and {@code edge}.getTo()
     */
    public boolean addEdge(@NotNull Vertex from, @NotNull Edge edge)
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
    
    
    /**
     * Ensures, that {@code edge} is added to {@code from} even if {@code from} has not yet occurred in {@code edges}.
     *
     * @param from the from {@link Vertex}
     * @param edge the edge to be drawn from {@code from}
     */
    private void nullSafeEdgeAdd(Vertex from, Edge edge)
    {
        HashSet<Edge> edgeSet = edges.computeIfAbsent(from, k -> new HashSet<>());
        edgeSet.add(edge);
    }
    
    
    /**
     * Places {@code element} at {@code index} if it is not out of bounds. and returns the previously associated
     * element.
     *
     * @param index   The {@link Vertex} index for {@code element} to be placed or
     * @param element the {@link Element} to place on {@code index}
     * @return The {@link Element} previously associated with {@link Vertex} at {@code index} or null if it is null
     * or no association is in this graph.
     */
    public Element place(int index, Element element)
    {
        Vertex vertex = getVertex(index);
        
        return place(vertex, element);
    }
    
    
    /**
     * Gets the {@link Vertex} at {@code index}
     *
     * @param index The Index of the {@link Vertex}
     * @return The {@link Vertex} or null if {@code index} is out of bounds
     */
    public Vertex getVertex(int index)
    {
        if (index >= size())
        {
            return null;
        }
        return new ArrayList<>(this.verticeSet()).get(index);
    }
    
    
    /**
     * Places {@code element} at {@code vertex} if this contains {@code vertex}.
     *
     * @param vertex  The vertex for {@code element} to be placed or
     * @param element the {@link Element} to place on {@code vertex}
     * @return The {@link Element} previously associated with {@code vertex} or null if it is null or no association
     * is in this graph.
     */
    public Element place(@NotNull Vertex vertex, Element element)
    {
        if (!containsVertex(vertex))
        {
            return null;
        }
        
        return vertices.put(vertex, element);
    }
    
    
    /**
     * Gets the {@link Element} at {@code index}
     *
     * @param index The Index of the {@link Vertex} which has the {@link Element}
     * @return The {@link Element} or null if there is none
     */
    public Element get(int index)
    {
        return get(getVertex(index));
    }
    
    
    /**
     * Gets the {@link Element} on {@code vertex}
     *
     * @param vertex The {@link Vertex} whose mapping should be queried.
     * @return {@link Element} mapped to {@code vertex}
     */
    public Element get(Vertex vertex)
    {
        return vertices.get(vertex);
    }
    
    
    /**
     * Finds first the {@link Vertex} with {@code element} mapped to it and returns it
     *
     * @param element The value mapped to the {@link Vertex}.
     * @return The first {@link Vertex} with {@code element} mapped to it or null if there is none
     */
    public Vertex getVertex(Element element)
    {
        return getVertices(element).stream().findFirst().orElse(null);
    }
    
    
    /**
     * Gets all Vertices with {@code element} as value.
     *
     * @param element The value mapped to the {@link Vertex}es.
     * @return All {@link Vertex}es with {@code element} mapped to it.
     */
    public Collection<Vertex> getVertices(Element element)
    {
        return verticeSet().stream().filter(vertex -> get(vertex) == element).toList();
    }
    
    
    /**
     * defaults to {@link #place(com.gitgud.graph.Vertex, Element)} with {@code null} as {@code element}
     *
     * @param vertex the vertex to be cleared
     * @see #place(com.gitgud.graph.Vertex, Element)
     */
    public Element clearVertex(Vertex vertex)
    {
        return place(vertex, null);
    }
    
    
    /**
     * Queries the Graph for all non-null {@link Element}s
     *
     * @return all non-null {@link Element}s in this Graph
     */
    public Collection<Element> nonNullElements()
    {
        return elements().stream().filter(Objects::nonNull).toList();
    }
    
    
    /**
     * Gets all Elements in this Graph
     *
     * @return All {@link Element}es in this Graph
     */
    public Collection<Element> elements()
    {
        return vertices.values().stream().toList();
    }
    
    
    /**
     * Gets an unmodifiable mapping of {@link Vertex}es to {@link Element}s
     *
     * @return An unmodifiable mapping of {@link Vertex}es to {@link Element}s
     */
    public Map<Vertex, Element> unmodifiableMapping()
    {
        return Collections.unmodifiableMap(vertices);
    }
    
    
    /**
     * Finds and returns all directly adjacent vertices
     *
     * @param root The root {@link Vertex} for the search
     * @return All directly adjacent {@link Vertex}es to {@code root}
     */
    public Collection<Vertex> getAdjacentVertices(Vertex root)
    {
        ArrayList<Vertex> adjacentVertices = new ArrayList<>();
        
        for (Edge edge : getEdges(root))
        {
            adjacentVertices.add(edge.getTo());
        }
        
        
        return adjacentVertices;
    }
    
    
    /**
     * Defaults to {@link #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate, HashMap)} with a new
     * HashMap for weights
     *
     * @see #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate, HashMap)
     */
    public Graph<Vertex, Element, Edge> subGraph(Vertex root, double range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter)
    {
        return subGraph(root, range, fromEdgeFilter, toEdgeFilter, new HashMap<>());
    }
    
    
    /**
     * <p>Sub graphs indexing will be lost. This is to ensure that the original indexing is intact.
     * <p>Use only non-index-based methods on the returned SubGraph.
     *
     * @param root           The root {@link Vertex} for the SubGraph
     * @param range          The maximum allowed distance from {@code root} going by adjacency
     * @param fromEdgeFilter a predicate to filter the {@link Vertex}s whose from edges should not be traversed. This
     *                      excludes {@code root}
     * @param toEdgeFilter   a predicate to filter the {@link Vertex}s whose edges should not be traversed
     * @param weights        The so far lowest weights from {@code root} of all visited vertices used for recursion
     *                       only. Should be called with {@code new HashMap()}
     * @return The SubGraph where all {@link Vertex}es are within {@code range} from {@code root} or an Empty Graph
     * if {@code root} is not in this Graph
     */
    private Graph<Vertex, Element, Edge> subGraph(Vertex root, double range, Predicate<Vertex> fromEdgeFilter,
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
            
            if (weights.containsKey(current) && weights.get(current) < remainingRange)
            {
                continue;
            }
            
            subGraph.addAll(subGraph(current, remainingRange, fromEdgeFilter, toEdgeFilter));
        }
        
        
        return subGraph;
    }
    
    
    /**
     * defaults to {@link #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate)} casting {@code range} to
     * {@code double}
     *
     * @see #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate)
     */
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, float range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter)
    {
        return subGraph(start, (double) range, fromEdgeFilter, toEdgeFilter);
    }
    
    
    /**
     * defaults to {@link #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate)} casting {@code range} to
     * {@code double}
     *
     * @see #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate)
     */
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, int range, Predicate<Vertex> fromEdgeFilter,
                                                 Predicate<Vertex> toEdgeFilter)
    {
        return subGraph(start, (double) range, fromEdgeFilter, toEdgeFilter);
    }
    
    
    /**
     * <p>Determines the Weight of an {@link Edge} for use in
     * {@link #subGraph(com.gitgud.graph.Vertex, double, Predicate, Predicate, HashMap)}
     * <p>Defaults to {@link #DEFAULT_WEIGHT} if the {@link Edge} does not implement {@link WeightedEdge}
     *
     * @param edge the {@link Edge} whose weight should be determined
     * @return the weight of the {@link Edge} {@code edge}
     */
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
     * This method does not provide robust indexing. It is meant to be used with caution.
     * Resulting Graphs are not unlikely to be entirely valid and should not use any index-based methods.
     *
     * @param addGraph the {@link Graph} to be added
     */
    protected void addAll(Graph<Vertex, Element, Edge> addGraph)
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
}
