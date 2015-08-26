package com.ideas.fbl.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.ideas.fbl.model.Vertex;

/**
 * {@link RouteFinderDistanceHelper} unit test cases
 * @author sachin_nikam
 *
 */
public class RouteFinderDistanceHelperTest {

	/**
	 * {@link RouteFinderDistanceHelper} object
	 */
	private RouteFinderDistanceHelper distanceHelper = null;
	
	/**
     * {@link RouteFinderImplTestData} object
     */
    private RouteFinderImplTestData data = null;

	@Before
    public void setUp() {
		PropertyConfigurator.configure(RouteFinderImpl.LOG4J_PROPERTIES_PATH);
		data = new RouteFinderImplTestData();
		distanceHelper = new RouteFinderDistanceHelper();
        MockitoAnnotations.initMocks(this);
    }
	
	/**
	 * Test {@link RouteFinderDistanceHelper}.getShortestDistance method
	 */
	@Test
	public void testGetShortestDistance() {
		RouteFinderImpl.LOGGER.debug("inside getShortestDistance");
		final Vertex vertex = (Vertex)data.nodes.get(0);
		distanceHelper.setDistance(new HashMap<Vertex, Integer>());
		Assert.assertNotNull(distanceHelper.getShortestDistance(vertex));
		Assert.assertNotSame(distanceHelper.getShortestDistance(vertex), vertex.getMinDistance());
		RouteFinderImpl.LOGGER.debug("done with getShortestDistance");
	}
	
	/**
     * Test {@link RouteFinderImpl}.getMinimum method
     */
    @Test
    public void testGetMinimum() {
    	RouteFinderImpl.LOGGER.debug("inside testComputePaths");
    	final Vertex vertex = (Vertex)data.nodes.get(0);
    	final Set<Vertex> vertexSet = new HashSet<Vertex>();
    	vertexSet.add(vertex);
    	Assert.assertNotNull(distanceHelper.getMinimum(vertexSet));
    	Assert.assertEquals(vertex.getLabel(), distanceHelper.getMinimum(vertexSet).getLabel());
        RouteFinderImpl.LOGGER.debug("done with testComputePaths");
    }
}
