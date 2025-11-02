package it.unibo.generics.graph;

import it.unibo.generics.graph.api.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
public class GraphImp<N> implements Graph<N>{
    private final Collection<N> nodes;
    private final Collection<List<N>> edges;
    public GraphImp(){
        this.nodes= new HashSet<>();
        this.edges = new HashSet<>();
    }
    @Override
    public void addNode(final N node) {
        if(node != null){
            this.nodes.add(node);
        }
        
    }
    @Override
    public void addEdge(final N source, final N target) {
        if(source != null && target != null){
            final List<N> edge = new ArrayList<N>(List.of(source, target));
            this.edges.add(edge);
        }
    }
    @Override
    public Set<N> nodeSet() {
        return (Set<N>) this.nodes;
    }
    
    @Override
    public Set<N> linkedNodes(final N node) {
        final Set<N> linkedNodes = new HashSet<>();
        for (final List<N> edge : this.edges) {
            if (edge.get(0).equals(node)){
                linkedNodes.add(edge.get(1));
            }
        }
        return linkedNodes;
    }

    private boolean dfs(final List<N> path, final N current, final N target, final Set<N> visited) {
    visited.add(current);
    if (current.equals(target)) {
        path.add(current);
        return true;
    }
    for (final N n : this.linkedNodes(current)) {
        if (!visited.contains(n) && dfs(path, n, target, visited)) {
            path.add(current);
            return true;
        }
    }
    return false;
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        final List<N> path = new ArrayList<>();
        final Set<N> visited = new HashSet<>();
        dfs(path, source, target, visited);
        Collections.reverse(path);
        return path;
    }

    
    
}