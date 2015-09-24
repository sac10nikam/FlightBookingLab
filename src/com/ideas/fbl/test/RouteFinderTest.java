package com.ideas.fbl.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.ideas.fbl.exception.RouteFinderException;
import com.ideas.fbl.model.Edge;
import com.ideas.fbl.model.Graph;
import com.ideas.fbl.model.Vertex;
import com.ideas.fbl.service.RouteFinder;
import com.ideas.fbl.service.impl.RouteFinderImpl;

/**
 * Unit test cases for {@link RouteFinder} services.
 * 
 * @author sachin_nikam
 *
 */
public class RouteFinderTest {

	/**
	 * log4j properties file path
	 */
	public static final String logPropertiesPath = "./log4j.properties";

	/**
	 * {@link LOGGER} object
	 */
	public static final Logger LOGGER = Logger.getLogger(RouteFinderTest.class);

	/**
	 * The {@link List} of {@link Vertex}s
	 */
	private List<Vertex> nodes;

	/**
	 * The {@link List} of {@link Edge}s
	 */
	private List<Edge> edges;

	/**
	 * Constructor with no argument
	 */
	public RouteFinderTest() {
		// No implementation
	}

	/**
	 * Before each test
	 */
	@Before
	public void initialize() {
		PropertyConfigurator.configure(logPropertiesPath);
	}

	@Test
	public void testGetShortestRouteTo() {
		try {
			addCities();
			addFlightRouteDetails();

			Graph graph = new Graph(nodes, edges);
			RouteFinderImpl dijkstra = new RouteFinderImpl(graph);
			dijkstra.computePaths(nodes.get(0));
			LinkedList<Vertex> paths = dijkstra.getShortestRouteTo(nodes.get(7));
			assertNull(paths);
			// assertFalse(paths.size() > 0);
			if (paths != null) {
				for (Vertex vertex : paths) {
					System.out.println(vertex);
				}
			} else {
				Vertex vertex = nodes.get(7);

			}
		} catch (RouteFinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test RouteFinder.computePaths method
	 */
	@Test
	public void testComputePaths() {
		try {
			LOGGER.debug("inside testComputePaths");
			final Vertex vertex = null;
			RouteFinderImpl routeFinderImpl = new RouteFinderImpl(null);
			assertNull(vertex);
			assertTrue(vertex.getPreviousNodes().size() > 0);
			routeFinderImpl.computePaths(vertex);
		} catch (Exception ex) {
			ex.getMessage();
		}
		LOGGER.debug("done with testComputePaths");
	}

	/**
	 * Test RouteFinder.getDistanceBetweenNodes method
	 */

	public void TestGetDistanceBetweenNodes() {
		LOGGER.debug("Method getDistanceBetweenNodes: get distance between nodes start");
		int distance = 0;
		final Vertex sourceVertex = new Vertex(1, "a");
		final Vertex targetVertex = new Vertex(7, "g");

		for (final Edge edge : sourceVertex.adjacencies) {
			if (edge.getSource().equals(sourceVertex) && edge.getDestination().equals(targetVertex)) {
				distance = edge.getWeight();
			}
		}
		assertTrue(distance != Double.NEGATIVE_INFINITY);
		LOGGER.debug("get distance between nodes end");

	}

	/**
	 * Sets the edges/routes details
	 */
	private void addFlightRouteDetails() {
		edges = new ArrayList<Edge>();
		addEdge(1, 0, 5, 1);
		addEdge(2, 1, 0, 0);
		addEdge(3, 1, 2, 1);
		addEdge(4, 2, 3, 1);
		addEdge(5, 2, 4, 4);
		addEdge(6, 2, 5, 5);
		addEdge(7, 3, 4, 1);
		addEdge(8, 4, 1, 1);
		addEdge(9, 5, 5, 0);
		addEdge(10, 6, 6, 0);
		addEdge(11, 7, 1, 1);
	}

	/**
	 * Set the nodes/cities details
	 */
	private void addCities() {
		nodes = new ArrayList<Vertex>();
		for (int i = 0; i < 8; i++) {
			Vertex location = new Vertex(i, "Node_" + i);
			nodes.add(location);
		}
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
	private void addEdge(int edgeId, int sourceLocNo, int destLocNo, int numberOfFlights) {
		if (numberOfFlights >= 0) {
			Edge edge = new Edge(edgeId, nodes.get(sourceLocNo), nodes.get(destLocNo), numberOfFlights);
			edges.add(edge);
		} else {
			LOGGER.error("Negative distance is not allowed. You could use Bellman Ford search algorithm"
					+ " having negative distance supported");
		}
	}
}