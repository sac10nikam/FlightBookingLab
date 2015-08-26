package com.ideas.fbl.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.ideas.fbl.exception.RouteFinderException;
import com.ideas.fbl.model.Edge;
import com.ideas.fbl.model.Graph;
import com.ideas.fbl.model.Vertex;

/**
 * {@link RouteFinderImpl} unit test cases
 * @author sachin_nikam
 *
 */
public class RouteFinderImplTest {

    /**
     * Log4j Logger object.
     */
    public static final Logger LOGGER = LogManager.getLogger(RouteFinderImpl.class);

    /**
     * {@link RouteFinderImplTestData} object
     */
    private RouteFinderImplTestData data = null;
  
	@Before
    public void setUp() {
		PropertyConfigurator.configure(RouteFinderImpl.LOG4J_PROPERTIES_PATH);
		data = new RouteFinderImplTestData();
        Graph graph = new Graph( data.nodes, data.edges );
        data.routeFinderImpl = new RouteFinderImpl(graph);
        MockitoAnnotations.initMocks(this);
    }
    
    
    /**
     * Test {@link RouteFinderImpl}.getShortestRouteTo method
     */
    @Test
	public void testGetShortestRouteTo() {
		try {
			LOGGER.debug("inside testGetShortestRouteTo");
			// From node a to node f
			Vertex fromNode = data.nodes.get(0);
			Vertex toNode = data.nodes.get(5);
			data.routeFinderImpl.computePaths(fromNode);
			outputShortestRoute(data.nodes.get(0), data.nodes.get(5));
			
			// From node a to node c
			fromNode = data.nodes.get(0);
			toNode = data.nodes.get(2);
			data.routeFinderImpl.computePaths(fromNode);
			outputShortestRoute(fromNode, toNode);

			// From node c to node d
			fromNode = data.nodes.get(2);
			toNode = data.nodes.get(3);
			data.routeFinderImpl.computePaths(fromNode);
			outputShortestRoute(fromNode, toNode);
			
			// From node c to node e
			fromNode = data.nodes.get(2);
			toNode = data.nodes.get(4);
			data.routeFinderImpl.computePaths(fromNode);
			outputShortestRoute(fromNode, toNode);
			
			// From node b to node c
			fromNode = data.nodes.get(1);
			toNode = data.nodes.get(2);
			data.routeFinderImpl.computePaths(fromNode);
			outputShortestRoute(fromNode, toNode);
		} catch (RouteFinderException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.debug("done with testGetShortestRouteTo");
	}
    
	/**
	 * Output the shortest route
	 * @param fromNode The from {@link Vertex}
	 * @param toNode The to {@link Vertex}
	 */
    private void outputShortestRoute(final Vertex fromNode, final Vertex toNode) {
		final LinkedList<Vertex> paths = data.routeFinderImpl.getShortestRouteTo(toNode);
		LOGGER.info("===========================Test Output start ==================================");
		LOGGER.info("Shortest Route from city "+ fromNode + " to city " 
		+ toNode + " \n=> " + (paths == null ? "No route available" : paths));
		LOGGER.info("===========================Test Output end ====================================");
	}
    
    /**
     * Test the route with no flights
     */
    @Test
    public void testRouteWithNoFlights() {
    	LOGGER.debug("inside testRoutewithNoFlights");
    	final Vertex sourceVertex = (Vertex)data.nodes.get(1);
    	final Vertex destinationVertex = (Vertex)data.nodes.get(2);
    	for (Edge edge : data.edges) {
			if (edge.getSource().equals(sourceVertex) && edge.getDestination().equals(destinationVertex)) {
				org.junit.Assert.assertNotNull(edge.getWeight());
				// shortest route from node b to node c
				data.routeFinderImpl.computePaths(sourceVertex);
				outputShortestRoute(sourceVertex, destinationVertex);
				
				// Number of flights between city b and city c  
				LOGGER.info("===========================Test Output start ==================================");
				LOGGER.info("Number of flights between city "+ sourceVertex +
						" and destination vertex " + destinationVertex + " = "+ edge.getWeight());
				LOGGER.info("===========================Test Output end ====================================");
			}
		}
    	LOGGER.debug("done with testRoutewithNoFlights");
    }
    
    /**
     * Test {@link RouteFinderImpl}.getDistanceBetweenNodes method
     */
    @Test
    public void testGetDistanceBetweenNodes() {
    	LOGGER.debug("inside getDistanceBetweenNodes");
    	final Vertex sourceVertex = (Vertex)data.nodes.get(0);
    	final Vertex destinationVertex = (Vertex)data.nodes.get(5);
    	for (Edge edge : data.edges) {
			if (edge.getSource().equals(sourceVertex) && edge.getDestination().equals(destinationVertex)) {
				org.junit.Assert.assertNotNull(edge.getWeight());
				LOGGER.info("===========================Test Output start ==================================");
				LOGGER.info("Distance between vertex "+ sourceVertex +
						" and destination vertex " + destinationVertex + " = "+ edge.getWeight());
				LOGGER.info("===========================Test Output end ====================================");
			}
		}
    	LOGGER.debug("done with getDistanceBetweenNodes");
    }
    
    /**
     * Test {@link RouteFinderImpl}.computePaths method
     */
    @Test
    public void testComputePaths() {
        LOGGER.debug("inside testComputePaths");
        final Vertex vertex = (Vertex)data.nodes.get(0);
        Set<Vertex> vertexSet = new HashSet<Vertex>();
    	vertexSet.add(vertex);
        data.routeFinderImpl.computePaths(vertex);
        LOGGER.debug("done with testComputePaths");
    }
    
}
