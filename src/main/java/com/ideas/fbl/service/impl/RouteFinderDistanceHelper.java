package com.ideas.fbl.service.impl;

import java.util.Map;
import java.util.Set;
import com.ideas.fbl.model.Vertex;

/**
 * Class to set the shortest distance for the provided vertex
 * @author sachin_nikam
 *
 */
public class RouteFinderDistanceHelper {
	/**
	 * The {@link Map} of vertexes and respective distances 
	 */
	private Map<Vertex, Integer> distance;

	/**
	 * Gets the {@link Map} containing vertex distance information
	 * @return
	 */
	public Map<Vertex, Integer> getDistance() {
		return distance;
	}

	/**
	 * Sets the {@link Map} of distances.
	 * @param distance
	 */
	public void setDistance(Map<Vertex, Integer> distance) {
		this.distance = distance;
	}

	/**
	 * Gets the shortest distance of the provided destination vertex.
	 * @param destination  The destination  {@link Vertex}
	 * @return  The distance
	 */
	public int getShortestDistance(Vertex destination) {
		RouteFinderImpl.LOGGER.debug("getShortestDistance start");
		Integer d = distance.get(destination);
		if (d == null) {
			d = Integer.MAX_VALUE;
		}
		RouteFinderImpl.LOGGER.debug("getShortestDistance end");
		return d;
	}

	/**
	 * Gets the  {@link Vertex}  object whose distance is minimum from the set of vertexes.
	 * @param vertexes  The  {@link Set}  of vertexes
	 * @return  The  {@link Vertex}  object
	 */
	public Vertex getMinimum(Set<Vertex> vertexes) {
		RouteFinderImpl.LOGGER.debug("getMinimum start");
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		RouteFinderImpl.LOGGER.debug("getMinimum end");
		return minimum;
	}
}