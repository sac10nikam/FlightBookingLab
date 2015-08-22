package com.ideas.fbl.model;

import java.util.List;

/**
 * <p>A flight graph contains a collection of Vertex objects, each of which
 * maintains its own adjacency list of edges (see the Vertex and Edge 
 * classes).</p>
 * @author sachin_nikam
 *
 */
public class Graph {
	/**
	 * List of Vertexes/nodes/cities.
	 */
	private final List<Vertex> vertexes;
	/**
	 * List of edges/routes/paths.
	 */
	private final List<Edge> edges;

	/**
	 * Constructor with parameters. Used to set list of vertexes/nodes/flight cities
	 * @param vertexes The {@link List} object
	 * @param edges The {@link List} object
	 */
	public Graph(final List<Vertex> vertexes, final List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	/**
	 * Gets the list of vertexes/cities/nodes
	 * @return The {@link List} of cities
	 */
	public List<Vertex> getVertexes() {
		return vertexes;
	}

	/**
	 * Gets the list of paths/routes/edges between cities
	 * @return The {@link List} of edges between cities
	 */
	public List<Edge> getEdges() {
		return edges;
	}
}
