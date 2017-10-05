package automaton;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import automaton.CellCoordinates;
import automaton.Coords2D;
import automaton.VonNeumanNeighbourhood;

public class VonNeumanNeighbourhoodTests {
	@Test
	public void simpleNeighborsTest() {
		VonNeumanNeighbourhood neighborhood = new VonNeumanNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(1,1);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(0,1));
		correctNeighbors.add(new Coords2D(2,1));
		correctNeighbors.add(new Coords2D(1,2));//TOD\":F assertj

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong VonNeuman Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 4, outputNeighbors.size());
	}
	
	@Test
	public void cornerNeighborsTest_noWrapping() {
		VonNeumanNeighbourhood neighborhood = new VonNeumanNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(0,1));


		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong VonNeuman Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 2, outputNeighbors.size());
	}
	
	@Test
	public void cornerNeighborsTest_wrapping() {
		VonNeumanNeighbourhood neighborhood = new VonNeumanNeighbourhood(3,3,true,1);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(0,1));
		//wrapped
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(0,2));


		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong VonNeuman Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 4, outputNeighbors.size());
	}
	
	@Test
	public void simpleNeighborsTestRad2() {
		VonNeumanNeighbourhood neighborhood = new VonNeumanNeighbourhood(5,5,false,2);
		
		Coords2D exampleCoords = new Coords2D(2,2);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(2,1));
		
		correctNeighbors.add(new Coords2D(0,2));
		correctNeighbors.add(new Coords2D(1,2));
		
		correctNeighbors.add(new Coords2D(3,2));
		correctNeighbors.add(new Coords2D(4,2));

		correctNeighbors.add(new Coords2D(2,3));
		correctNeighbors.add(new Coords2D(2,4));	

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong VonNeuman Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());
	}
	
	@Test
	public void cornerNeighborsTest_noWrappingRad2() {
		VonNeumanNeighbourhood neighborhood = new VonNeumanNeighbourhood(5,5,false,2);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(2,0));
		
		correctNeighbors.add(new Coords2D(0,1));
		correctNeighbors.add(new Coords2D(0,2));



		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong VonNeuman Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 4, outputNeighbors.size());
	}
	
	@Test
	public void cornerNeighborsTest_wrappingRad2() {
		VonNeumanNeighbourhood neighborhood = new VonNeumanNeighbourhood(5,5,true,2);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(0,1));
		//wrapped
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(0,2));
		
		correctNeighbors.add(new Coords2D(0,3));
		correctNeighbors.add(new Coords2D(0,4));
		
		correctNeighbors.add(new Coords2D(3,0));
		correctNeighbors.add(new Coords2D(4,0));


		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertEquals("Wrong VonNeuman Neighborhood neighbors", (correctNeighbors),outputNeighbors);
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());
	}
}
