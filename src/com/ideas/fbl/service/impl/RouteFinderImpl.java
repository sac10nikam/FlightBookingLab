package com.ideas.fbl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ideas.fbl.exception.RouteFinderException;
import com.ideas.fbl.model.Edge;
import com.ideas.fbl.model.Graph;
import com.ideas.fbl.model.Vertex;
import com.ideas.fbl.service.RouteFinder;

/**
 * <p>
 * Compute shortest paths in a graph i.e. shortest paths between cities based on 
 * number of flights/distance. Implementation of {@link RouteFinder} service.</p>
 * </p>
 * 
 * @author sachin_nikam
 *
 */
public class RouteFinderImpl implements RouteFinder {

	private RouteFinderDistanceUtil finderDistanceUtil = new RouteFinderDistanceUtil();

	/**
	 * Log4j Logger object.
	 */
	public static final Logger LOGGER = Logger.getLogger(RouteFinderImpl.class);

	/**
	 * List of nodes/vertexes/cities.
	 */
	private final List<Vertex> nodes;

	/**
	 * List of edges.
	 */
	private final List<Edge> edges;

	/**
	 * List of settled nodes
	 */
	private Set<Vertex> settledNodes;

	/**
	 * List of unsettled nodes
	 */
	private Set<Vertex> unSettledNodes;

	/**
	 * Map of adjacent nodes
	 */
	private Map<Vertex, Vertex> predecessors;
	/**
	 * Constructor with parameter - {@link Graph}
	 * 
	 * @param graph
	 *            The {@link Graph} object
	 */
	public RouteFinderImpl(final Graph graph) {
		LOGGER.debug("Constructor start");
		// create a copy of the array so that we can operate on this array
		this.nodes = new ArrayList<Vertex>(graph.getVertexes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
		LOGGER.debug("Constructor end");
	}

	/**
	 * Computes the paths for the source node/vertex
	 * 
	 * @param source
	 *            The {@link Vertex} object
	 */
	public void computePaths(final Vertex source) throws RouteFinderException {
		LOGGER.debug("computePaths start");
		settledNodes = new HashSet<Vertex>();
		unSettledNodes = new HashSet<Vertex>();
		finderDistanceUtil.setDistance(new HashMap<Vertex, Integer>());
		predecessors = new HashMap<Vertex, Vertex>();
		finderDistanceUtil.getDistance().put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			final Vertex node = finderDistanceUtil.getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
		LOGGER.debug("computePaths end");
	}

	/**
     * Gets the distance/number of flights between the provided nodes/cities.
     * @param sourceVertex The source {@link Vertex} object/city
     * @param targetVertex The destination {@link Vertex} object/city
     * @return The distance between nodes/number of flights
     */
	public int getDistanceBetweenNodes(final Vertex node, final Vertex target) 
												 throws RouteFinderException {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		final String errorMessage = "This case should not happen. There should some egdes between nodes";
		LOGGER.info(errorMessage);
		throw new RouteFinderException("No edges :" + errorMessage);
	}

	/**
	 * Gets the list of neighbor cities/nodes/vertexes
	 * 
	 * @param node
	 *            The {@link Vertex} object
	 * @return The neighbors list
	 */
	public List<Vertex> getNeighbors(final Vertex node) {
		final List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && !isSettledNode(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	/**
	 * This method returns the shortest path/route from the source to the
	 * selected target and NULL if no path exists
	 * 
	 * @param target
	 *            The target city/node
	 * @return The path {@link LinkedList}
	 */
	public LinkedList<Vertex> getShortestRouteTo(final Vertex target) throws RouteFinderException {
		final LinkedList<Vertex> paths = new LinkedList<Vertex>();
		Vertex step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		paths.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			paths.add(step);
		}
		// Put it into the correct order
		Collections.reverse(paths);
		return paths;
	}

	/**
	 * Gets the flag whether the given node is settled node or unsettled.
	 * @param vertex The {@link Vertex} object
	 * @return The boolean flag for settled/unsettled node
	 */
	public boolean isSettledNode(Vertex vertex) {
		boolean isSettledNode = false;
		if (settledNodes.contains(vertex)) {
			isSettledNode = true;
			return isSettledNode;
		} else {
			return isSettledNode;
		}
	}
	
	private void findMinimalDistances(final Vertex node) {
		LOGGER.debug("findMinimalDistances start");
		try {
			final List<Vertex> adjacentNodes = getNeighbors(node);
			for (Vertex target : adjacentNodes) {
				if (finderDistanceUtil.getShortestDistance(target) > finderDistanceUtil.getShortestDistance(node) + getDistanceBetweenNodes(node, target)) {
					finderDistanceUtil.getDistance().put(target, finderDistanceUtil.getShortestDistance(node) + getDistanceBetweenNodes(node, target));
					predecessors.put(target, node);
					unSettledNodes.add(target);
				}
			}
		} catch (RouteFinderException ex) {
			LOGGER.error("Error in finding minimal distances: " + ex.getMessage());
		}
		LOGGER.debug("findMinimalDistances end");
	}
}
