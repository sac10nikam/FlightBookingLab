package com.ideas.fbl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ideas.fbl.model.Edge;
import com.ideas.fbl.model.Vertex;

public class RouteFinderImplTestData {
	/**
	 * {@link RouteFinderImpl} object
	 */
	@InjectMocks
	public RouteFinderImpl routeFinderImpl;
	/**
	 * {@link RouteFinderDistanceHelper} object
	 */
	@Mock
	public RouteFinderDistanceHelper routeFinderDistanceHelper;
	/**
	 * {@link List} of {@link Vertex}es
	 */
	@Mock
	public List<Vertex> nodes;
	/**
	 * {@link List} of {@link Edge}s
	 */
	@Mock
	public List<Edge> edges;

	/**
	 * {@link RouteFinderImplTestData} constructor
	 */
	public RouteFinderImplTestData() {
		this.nodes = getNodes();
		this.edges = getEdges(this.nodes);
	}
	
	/**
     * Gets the list of edges/routes details
     * @param List of {@link Vertex}
     */
    private List<Edge> getEdges(final List<Vertex> nodes) {
    	RouteFinderImplTest.LOGGER.debug("inside getEdges");
        final List<Edge> edges = new ArrayList<Edge>();
        addEdge(1, 0, 5, 1, edges, nodes);
        addEdge(2, 1, 0, 1, edges, nodes);
        addEdge(3, 1, 2, 0, edges, nodes);
        addEdge(4, 2, 3, 1, edges, nodes);
        addEdge(5, 2, 4, 4, edges, nodes);
        addEdge(6, 2, 5, 5 , edges, nodes);
        addEdge(7, 3, 4, 1, edges, nodes);
        addEdge(8, 4, 1, 1, edges, nodes);
        addEdge(9, 5, 5, 0, edges, nodes);
        addEdge(10, 6, 6, 0, edges, nodes);
        addEdge(11, 7, 1, 1, edges, nodes);
        RouteFinderImplTest.LOGGER.debug("done with getEdges");
        return edges;
    }

    /**
     * Set the nodes/cities details
     */
    private List<Vertex> getNodes() {
    	RouteFinderImplTest.LOGGER.debug("inside getNodes");
        final List<Vertex> nodes = new ArrayList<Vertex>();
        final String[] nodeStr = {"a","b","c","d","e","f","g","h"};
        for (int i = 0; i < nodeStr.length; i++) {
            Vertex location = new Vertex(i, nodeStr[i]);
            nodes.add(location);
        }
        RouteFinderImplTest.LOGGER.debug("done with getNodes");
        return nodes;
    }
    
    /**
     * Add the edge to the {@link List} of edges based on source and destination
     * vertex Id and the distance/number of flights between source and
     * destination vertexes
     * 
     * @param edgeId
     *            The edge Id
     * @param sourceLocNo
     *            The source vertex Id
     * @param destLocNo
     *            The destination vertex Id
     * @param numberOfFlights
     *            The distance between source and destination vertexes
     */
    private void addEdge(final int edgeId, final int sourceLocNo, final int destLocNo, 
    		final int numberOfFlights, final List<Edge> edges, final List<Vertex> nodes) {
    	RouteFinderImplTest.LOGGER.debug("inside addEdge");
        if (numberOfFlights >= 0) {
            final Edge edge = new Edge(edgeId, nodes.get(sourceLocNo), nodes.get(destLocNo), numberOfFlights);
            edges.add(edge);
        } else {
        	RouteFinderImplTest.LOGGER.error("Negative distance is not allowed. You could use Borman Ford search algorithm"
                    + " having negative distance supported");
        }
        RouteFinderImplTest.LOGGER.debug("done with addEdge");
    }
}