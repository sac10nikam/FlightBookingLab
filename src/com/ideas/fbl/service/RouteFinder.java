package com.ideas.fbl.service;

import java.util.LinkedList;
import java.util.List;

import com.ideas.fbl.exception.RouteFinderException;
import com.ideas.fbl.model.Vertex;

/**
 * Route/Path finder service.
 * @author sachin_nikam
 *
 */
public interface RouteFinder {
	/**
	 * Computes the paths for the source node/vertex
	 * @param source The {@link Vertex} object
	 */
	void computePaths(final Vertex source) throws RouteFinderException;
	
	/**
	 * Gets the list of shortest path's to given vertex/node/city
	 * @param target The target city
	 * @return The path {@link LinkedList}
	 */
	LinkedList<Vertex> getShortestRouteTo(final Vertex target) throws RouteFinderException;
	
	/**
     * Gets the distance/number of flights between the provided nodes/cities.
     * @param sourceVertex The source {@link Vertex} object/city
     * @param targetVertex The destination {@link Vertex} object/city
     * @return The distance between nodes/number of flights
     */
	int getDistanceBetweenNodes(final Vertex sourceVertex, final Vertex targetVertex) 
			                                             throws RouteFinderException;
	
	/**
	 * Gets the list of neighbor cities/nodes/vertexes
	 * @param node The {@link Vertex} object
	 * @return The neighbors list
	 */
	List<Vertex> getNeighbors(Vertex node);
	
	/**
	 * Gets the flag whether the given node is settled node or unsettled.
	 * @param vertex The {@link Vertex} object
	 * @return The boolean flag for settled/unsettled node
	 */
	boolean isSettledNode(Vertex vertex);
}