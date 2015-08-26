package com.ideas.fbl.model;

/**
 * <p>Model to represent path/route between flight cities. 
 * 
 * A (directed) edge contains four values: a unique identifier, a "from" vertex, 
 * a "to" vertex, and an integer weight.  Subclass Edge if you want to store more 
 * complicated info.</p>
 * @author sachin_nikam
 *
 */
public class Edge {
	/**
	 * Unique ID for every flight edge/path/route.
	 */
	private int id;
	
	/**
	 * Flight from Vertex/node/city.
	 */
	private final Vertex source;
	
	/**
	 * Flight to Vertex/node/city.
	 */
	private Vertex destination;
	
	/**
	 * Number of flights between cities/nodes.
	 */
	private final int weight;

	/**
	 * Constructor with parameters. Used to set path/route, number of flights details. 
	 * @param source The from city object
	 * @param weight The number of flights
	 */
	public Edge(final Vertex source, final int weight) {
		this.source = source;
		this.weight = weight;
	}
	
	/**
	 * Constructor with parameters. Used to set path/route, number of flights details.
	 * @param id The edge id
	 * @param source The from city vertex
	 * @param destination The to city vertex
	 * @param weight The number of flights
	 */
	public Edge(final int id, final Vertex source, final Vertex destination, final int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	/**
	 * Gets the edge/path/route Id
	 * @return the path/edge id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the source city/vertex/node
	 * @return The source {@link Vertex}
	 */
	public Vertex getSource() {
		return source;
	}
	
	/**
	 * Gets the source city/vertex/node
	 * @return The source {@link Vertex}
	 */
	public Vertex getDestination() {
		return destination;
	}

	/**
	 * Gets the number of flights between cities/nodes
	 * @return The weight/number of flights
	 */
	public int getWeight() {
		return weight;
	}
}
