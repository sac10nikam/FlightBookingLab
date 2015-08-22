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
import com.ideas.fbl.service.impl.RouteFinderImpl;

public class RouteFinderTest {

	public static final String logPropertiesPath = "./log4j.properties";
	public final static Logger logger = Logger.getLogger(RouteFinderTest.class);
	private List<Vertex> nodes;
	private List<Edge> edges;

	/**
	 * Before each test
	 */
	@Before
	public void initialize() {
		PropertyConfigurator.configure(logPropertiesPath);
	}
	
	@Test
	public void testExcute() {

		try {
			addCities();
			addFlightRouteDetails();

			Graph graph = new Graph(nodes, edges);
			RouteFinderImpl dijkstra = new RouteFinderImpl(graph);
			dijkstra.computePaths(nodes.get(0));
			LinkedList<Vertex> paths;

			paths = dijkstra.getShortestRouteTo(nodes.get(7));
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
			logger.debug("inside testComputePaths");
			final Vertex vertex = null;
	        RouteFinderImpl routeFinderImpl = new RouteFinderImpl(null);
	        assertNull(vertex);
	        assertTrue(vertex.getPreviousNodes().size() > 0);
	        routeFinderImpl.computePaths(vertex);
    	} catch (Exception ex) {
    		ex.getMessage();
    	}
		logger.debug("done with testComputePaths");
	}
	
	/**
	 * Test RouteFinder.getDistanceBetweenNodes method
	 */
	
	public void TestGetDistanceBetweenNodes() {
		logger.debug("Method getDistanceBetweenNodes: get distance between nodes start");
    	int distance = 0; 
    	final Vertex sourceVertex = new Vertex(1, "a");
    	final Vertex targetVertex = new Vertex(7, "g");
    	
    	for (final Edge edge : sourceVertex.adjacencies) {
          if (edge.getSource().equals(sourceVertex)
              && edge.getDestination().equals(targetVertex)) {
            distance = edge.getWeight();
          }
        }
        assertTrue(distance != Double.NEGATIVE_INFINITY);
        logger.debug("get distance between nodes end");
        
	}
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

	private void addCities() {
		nodes = new ArrayList<Vertex>();
		for (int i = 0; i < 8; i++) {
			Vertex location = new Vertex(i, "Node_" + i);
			nodes.add(location);
		}
	}

	private void addEdge(int edgeId, int sourceLocNo, int destLocNo, int numberOfFlights) {
		Edge edge = new Edge(edgeId, nodes.get(sourceLocNo), nodes.get(destLocNo), numberOfFlights);
		edges.add(edge);
	}
}