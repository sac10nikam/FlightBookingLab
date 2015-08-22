package com.ideas.fbl.model;

import java.util.List;

/**
 * <p>Model to represent Destination/node/vertex details. It represents the flight city details.</p>
 * @author sachin_nikam
 *
 */
public class Vertex implements Comparable<Vertex> {

	/**
	 * Unique ID for every node.
	 */
	private int id;
	/**
	 * Label to be displayed on the vertex (Optional).
	 */
	private final String label;
	/**
	 * To hold a previous node in the shortest path. (This would hold a single
	 * node only in one of the possible shortest paths.)
	 */
	private Vertex previous;
	/**
	 * A distance measure for this vertex from source vertex.
	 */
	public double sourceDistance = Double.POSITIVE_INFINITY;
	/**
	 * A List of all previous nodes in all the possible shortest paths.
	 */
	private List<Vertex> previousNodes;
	
	/**
	 * A edge array containing adjacent nodes.
	 */
	public Edge[] adjacencies;
	
	/**
	 * Vertex Constructor with single parameter
	 * @param id
	 */
	public Vertex(final int id) {
		this(id, id + "");
	}
	
	/**
	 * Constructor with two parameters used to set Id and label/city name of the 
	 * vertex/city/node
	 * @param id
	 * @param label
	 */
	public Vertex(final int id, final String label) {
		this.id = id;
		this.label = label;
	}
	
	/**
	 * Gets the vertex Id.
	 * @return the vertex id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the previous vertex/cities list
	 * @return the previous vertex list
	 */
	public List<Vertex> getPreviousNodes() {
		return previousNodes;
	}
	
	/**
	 * Sets the previous vertex/city
	 * @param previousNodes the previous cities list to set
	 */
	public void setPreviousNodes(final List<Vertex> previousNodes) {
		this.previousNodes = previousNodes;
	}
	
	/**
	 * Sets the city id/vertex id
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}
	
	/**
	 * Gets the city name/label
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Gets the previous vertex/city
	 * @return the previous
	 */
	public Vertex getPrevious() {
		return previous;
	}
	
	/**
	 * Sets the previous city
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(final Vertex previous) {
		this.previous = previous;
	}
	
	/**
	 * Gets the source city distance from this city
	 * @return the minDistance
	 */
	public double getMinDistance() {
		return sourceDistance;
	}
	
	/**
	 * Sets the source city distance from this city
	 * @param minDistance
	 *            the minDistance to set
	 */
	public void setMinDistance(final double minDistance) {
		this.sourceDistance = minDistance;
	}
	
	/**
	 * Compares Vertex.sourceDistance object with the specified Vertex.sourceDistance object for order. 
	 * Returns a negative integer,
	 * zero, or a positive integer as this object is less than, equal to, or greater than the
	 * specified object. 
	 * 
	 */
	@Override
	public int compareTo(Vertex other) {
		return Double.compare(sourceDistance, other.sourceDistance);
	}
	
	/**
	 * public int hashCode()
	 * Returns a hash code value for the Vertex object.   
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode() {
		final int prime = 61;
		int result = 1;
		result = prime * result + ((id == 0) ? 0 : new Integer(id).hashCode());
		return result;
	}
	
	/**
	 * public boolean equals(Object object)
	 * Indicates whether some Vertex object is "equal to" this one based on Vertex object id. 
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Vertex vertex = (Vertex) object;
		if (id == 0) {
			if (vertex.id != 0)
				return false;
		} else if (id != vertex.id)
			return false;
		return true;
	}
	
	/**
	 * public String toString()
	 * Returns a Label to be displayed on the vertex.
	 */
	@Override
	public String toString() {
		return label;
	}
}