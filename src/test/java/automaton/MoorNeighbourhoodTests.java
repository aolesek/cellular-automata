package automaton;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import automaton.CellCoordinates;
import automaton.Coords2D;
import automaton.MoorNeighbourhood;

import org.junit.Assert;

public class MoorNeighbourhoodTests {
	@Test
	public void simpleNeighborsTest() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(1,1);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if ( ! ((i == 1) && (j == 1)) )
				{
					correctNeighbors.add(new Coords2D(i, j));
				}
			}
		}
		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());
	}
	
	@Test
	public void lcornerCellTest_noWrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(0,1));
		correctNeighbors.add(new Coords2D(1,1));
		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 3, outputNeighbors.size());
	}
	
	@Test
	public void rcornerCellTest_noWrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(2,2);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		
		correctNeighbors.add(new Coords2D(1,1));
		correctNeighbors.add(new Coords2D(2,1));
		correctNeighbors.add(new Coords2D(1,2));
		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 3, outputNeighbors.size());
	}
	
	@Test
	public void lborderCellTest_noWrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(0,1);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		correctNeighbors.add(new Coords2D(1,1));
		correctNeighbors.add(new Coords2D(0,2));
		correctNeighbors.add(new Coords2D(1,2));		
		correctNeighbors.add(new Coords2D(0,0));
		correctNeighbors.add(new Coords2D(1,0));

		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 5, outputNeighbors.size());
	}
	
	@Test
	public void rborderCellTest_noWrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3,false,1);
		
		Coords2D exampleCoords = new Coords2D(2,1);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(1,1));		
		correctNeighbors.add(new Coords2D(1,2));
		correctNeighbors.add(new Coords2D(2,2));

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 5, outputNeighbors.size());
	}
	
	@Test
	public void lborderCellTest_wrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3, true,1);
		
		Coords2D exampleCoords = new Coords2D(0,1);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		correctNeighbors.add(new Coords2D(1,1));
		correctNeighbors.add(new Coords2D(0,2));
		correctNeighbors.add(new Coords2D(1,2));		
		correctNeighbors.add(new Coords2D(0,0));
		correctNeighbors.add(new Coords2D(1,0));
		//wrapped
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(2,1));
		correctNeighbors.add(new Coords2D(2,2));

		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());
	}
	
	@Test
	public void rborderCellTest_wrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3, true,1);
		
		Coords2D exampleCoords = new Coords2D(2,1);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		correctNeighbors.add(new Coords2D(1,0));
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(1,1));		
		correctNeighbors.add(new Coords2D(1,2));
		correctNeighbors.add(new Coords2D(2,2));
		//wrapped
		correctNeighbors.add(new Coords2D(0,0));
		correctNeighbors.add(new Coords2D(0,1));
		correctNeighbors.add(new Coords2D(0,2));

		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());
	}
	
	@Test
	public void uborderCellTest_wrapping() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(3,3, true,1);
		
		Coords2D exampleCoords = new Coords2D(1,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		correctNeighbors.add(new Coords2D(0,0));
		correctNeighbors.add(new Coords2D(0,1));
		correctNeighbors.add(new Coords2D(1,1));		
		correctNeighbors.add(new Coords2D(2,0));
		correctNeighbors.add(new Coords2D(2,1));
		//wrapped
		correctNeighbors.add(new Coords2D(0,2));
		correctNeighbors.add(new Coords2D(1,2));
		correctNeighbors.add(new Coords2D(2,2));

		

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());
	}
	
	@Test
	public void simpleCellTest_noWrappingRad2() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(5,5, false, 2);
		
		Coords2D exampleCoords = new Coords2D(2,2);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4 ; j++) {
				if ( !((i == 2) && (j == 2)) ) {
					correctNeighbors.add(new Coords2D(i,j));
				}
			}
		}

		
		Assert.assertEquals("Wrong number of neighbors", 24, outputNeighbors.size());

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
	}
	
	@Test
	public void cornerCellTest_noWrappingRad2() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(5,5, false, 2);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2 ; j++) {
				if ( !((i == 0) && (j == 0)) ) {
					correctNeighbors.add(new Coords2D(i,j));
				}
			}
		}

		
		Assert.assertEquals("Wrong number of neighbors", 8, outputNeighbors.size());

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
	}
	
	@Test
	public void cornerCellTest_wrappingRad2() {
		MoorNeighbourhood neighborhood = new MoorNeighbourhood(5,5, true, 2);
		
		Coords2D exampleCoords = new Coords2D(0,0);
		
		Set<CellCoordinates> correctNeighbors = new HashSet<CellCoordinates>();
		Set<CellCoordinates> outputNeighbors = new HashSet<CellCoordinates>();
		outputNeighbors = neighborhood.cellNeighbours(exampleCoords);
		
		
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2 ; j++) {
				if ( !((i == 0) && (j == 0)) ) {
					correctNeighbors.add(new Coords2D(i,j));
				}
			}
		}
		
		correctNeighbors.add(new Coords2D(3,0));
		correctNeighbors.add(new Coords2D(3,1));
		correctNeighbors.add(new Coords2D(3,2));
		correctNeighbors.add(new Coords2D(4,0));
		correctNeighbors.add(new Coords2D(4,1));
		correctNeighbors.add(new Coords2D(4,2));

		
		correctNeighbors.add(new Coords2D(0,3));
		correctNeighbors.add(new Coords2D(1,3));
		correctNeighbors.add(new Coords2D(2,3));
		correctNeighbors.add(new Coords2D(0,4));
		correctNeighbors.add(new Coords2D(1,4));
		correctNeighbors.add(new Coords2D(2,4));

		correctNeighbors.add(new Coords2D(4,4));
		correctNeighbors.add(new Coords2D(4,3));
		correctNeighbors.add(new Coords2D(3,4));
		correctNeighbors.add(new Coords2D(3,3));

		
		Assert.assertEquals("Wrong number of neighbors", 24, outputNeighbors.size());

		Assert.assertTrue("Cell shouldn't be one of it's own neighboors", !outputNeighbors.contains(exampleCoords));
		Assert.assertTrue("Wrong Moore Neighborhood neighbors", outputNeighbors.equals(correctNeighbors));
	}

}
