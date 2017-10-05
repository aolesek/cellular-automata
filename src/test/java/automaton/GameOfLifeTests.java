package automaton;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import automaton.BinaryState;
import automaton.CellCoordinates;
import automaton.CellState;
import automaton.Coords2D;
import automaton.GameOfLife;
import automaton.GeneralStateFactory;
import automaton.MoorNeighbourhood;

public class GameOfLifeTests {
	
	
	@Test
	public void equalsTest() {
		MoorNeighbourhood moor = new MoorNeighbourhood(3, 3);
		
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		
		states.put(new Coords2D(0,0), BinaryState.DEAD);
		states.put(new Coords2D(0,1), BinaryState.ALIVE);
		states.put(new Coords2D(0,2), BinaryState.DEAD);
		
		states.put(new Coords2D(1,0), BinaryState.DEAD);
		states.put(new Coords2D(1,1), BinaryState.ALIVE);
		states.put(new Coords2D(1,2), BinaryState.DEAD);
		
		states.put(new Coords2D(2,0), BinaryState.DEAD);
		states.put(new Coords2D(2,1), BinaryState.ALIVE);
		states.put(new Coords2D(2,2), BinaryState.DEAD);
		
		GeneralStateFactory gsf = new GeneralStateFactory(states);
		
		GameOfLife gof = new GameOfLife(moor, 3, 3);
	
		gof.setMapFromCellFactory(gsf);
		
		MoorNeighbourhood moor2 = new MoorNeighbourhood(3, 3);
		
		Map<CellCoordinates, CellState> states2 = new HashMap<CellCoordinates, CellState>();
		
		states2.put(new Coords2D(0,0), BinaryState.DEAD);
		states2.put(new Coords2D(0,1), BinaryState.ALIVE);
		states2.put(new Coords2D(0,2), BinaryState.DEAD);
		
		states2.put(new Coords2D(1,0), BinaryState.DEAD);
		states2.put(new Coords2D(1,1), BinaryState.ALIVE);
		states2.put(new Coords2D(1,2), BinaryState.DEAD);
		
		states2.put(new Coords2D(2,0), BinaryState.DEAD);
		states2.put(new Coords2D(2,1), BinaryState.ALIVE);
		states2.put(new Coords2D(2,2), BinaryState.DEAD);
		
		GeneralStateFactory gsf2 = new GeneralStateFactory(states2);
		
		GameOfLife gof2 = new GameOfLife(moor2, 3, 3);
	
		gof2.setMapFromCellFactory(gsf2);
		
		Assert.assertTrue("Game of Life should be equal to the same Game of Life", gof.equals(gof2));
		

		
		
	}
	

	@Test
	public void blinkerTest() {
		MoorNeighbourhood moor = new MoorNeighbourhood(3, 3);
		
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		
		states.put(new Coords2D(0,0), BinaryState.DEAD);
		states.put(new Coords2D(0,1), BinaryState.ALIVE);
		states.put(new Coords2D(0,2), BinaryState.DEAD);
		
		states.put(new Coords2D(1,0), BinaryState.DEAD);
		states.put(new Coords2D(1,1), BinaryState.ALIVE);
		states.put(new Coords2D(1,2), BinaryState.DEAD);
		
		states.put(new Coords2D(2,0), BinaryState.DEAD);
		states.put(new Coords2D(2,1), BinaryState.ALIVE);
		states.put(new Coords2D(2,2), BinaryState.DEAD);
		
		GeneralStateFactory gsf = new GeneralStateFactory(states);
		
		GameOfLife gof = new GameOfLife(moor, 3, 3, false, "23/3");
	
		gof.setMapFromCellFactory(gsf);
		
		GameOfLife gofOutput = (GameOfLife) gof.nextState();	
		
		Map<CellCoordinates, CellState> states2 = new HashMap<CellCoordinates, CellState>();
		
		states2.put(new Coords2D(0,0), BinaryState.DEAD);
		states2.put(new Coords2D(0,1), BinaryState.DEAD);
		states2.put(new Coords2D(0,2), BinaryState.DEAD);
		
		states2.put(new Coords2D(1,0), BinaryState.ALIVE);
		states2.put(new Coords2D(1,1), BinaryState.ALIVE);
		states2.put(new Coords2D(1,2), BinaryState.ALIVE);
		
		states2.put(new Coords2D(2,0), BinaryState.DEAD);
		states2.put(new Coords2D(2,1), BinaryState.DEAD);
		states2.put(new Coords2D(2,2), BinaryState.DEAD);
		
		GeneralStateFactory gsf2 = new GeneralStateFactory(states2);
		
		GameOfLife gofCorrect = new GameOfLife(moor, 3, 3, false, "23/3");
	
		gofCorrect.setMapFromCellFactory(gsf2);
		
		/*System.out.println(gof.toString());
		gof.displayMapCells(3, 3);
		System.out.println();
		
		System.out.println(gofOutput.toString());
		gofOutput.displayMapCells(3, 3);
		System.out.println();
		System.out.println(gofCorrect.toString());
		gofCorrect.displayMapCells(3, 3);System.out.println();
		System.out.println("\n");*/
		
		Assert.assertTrue("Blinker test after one iteration", gofOutput.equals(gofCorrect));
		gofOutput = (GameOfLife) gofOutput.nextState();
		Assert.assertTrue("Blinker test after two iterations, should be same as initial", gofOutput.equals(gof));

	}
	
	@Test
	public void rules33oscillatorTest() {
		MoorNeighbourhood moor = new MoorNeighbourhood(3, 3);
		
		Map<CellCoordinates, CellState> states = new HashMap<CellCoordinates, CellState>();
		
		states.put(new Coords2D(0,0), BinaryState.ALIVE);
		states.put(new Coords2D(0,1), BinaryState.DEAD);
		states.put(new Coords2D(0,2), BinaryState.DEAD);
		
		states.put(new Coords2D(1,0), BinaryState.ALIVE);
		states.put(new Coords2D(1,1), BinaryState.ALIVE);
		states.put(new Coords2D(1,2), BinaryState.DEAD);
		
		states.put(new Coords2D(2,0), BinaryState.DEAD);
		states.put(new Coords2D(2,1), BinaryState.ALIVE);
		states.put(new Coords2D(2,2), BinaryState.DEAD);
		
		GeneralStateFactory gsf = new GeneralStateFactory(states);
		
		GameOfLife gof = new GameOfLife(moor, 3, 3, false, "3/3");
	
		gof.setMapFromCellFactory(gsf);
		
		GameOfLife gofOutput = (GameOfLife) gof.nextState();	
		
		Map<CellCoordinates, CellState> states2 = new HashMap<CellCoordinates, CellState>();
		
		states2.put(new Coords2D(0,0), BinaryState.DEAD);
		states2.put(new Coords2D(0,1), BinaryState.ALIVE);
		states2.put(new Coords2D(0,2), BinaryState.DEAD);
		
		states2.put(new Coords2D(1,0), BinaryState.ALIVE);
		states2.put(new Coords2D(1,1), BinaryState.ALIVE);
		states2.put(new Coords2D(1,2), BinaryState.DEAD);
		
		states2.put(new Coords2D(2,0), BinaryState.ALIVE);
		states2.put(new Coords2D(2,1), BinaryState.DEAD);
		states2.put(new Coords2D(2,2), BinaryState.DEAD);
		
		GeneralStateFactory gsf2 = new GeneralStateFactory(states2);
		
		GameOfLife gofCorrect = new GameOfLife(moor, 3, 3, false, "3/3");
	
		gofCorrect.setMapFromCellFactory(gsf2);
		
		/*gof.displayMapCells(3, 3);System.out.println();
		gofOutput.displayMapCells(3, 3);System.out.println();
		gofCorrect.displayMapCells(3, 3);
		System.out.println("\n");*/
		
		Assert.assertTrue("Blinker test after one iteration", gofOutput.equals(gofCorrect));
		gofOutput = (GameOfLife) gofOutput.nextState();
		//gofOutput.displayMapCells(3, 3);System.out.println();
		Assert.assertTrue("Blinker test after two iterations, should be same as initial", gofOutput.equals(gof));

	}
	
	@Test
	public void gliderTest() {
		MoorNeighbourhood moor = new MoorNeighbourhood(4, 4);
		
		Map<CellCoordinates, CellState> states1 = new HashMap<CellCoordinates, CellState>();
		
		states1.put(new Coords2D(0,0), BinaryState.DEAD);
		states1.put(new Coords2D(0,1), BinaryState.DEAD);
		states1.put(new Coords2D(0,2), BinaryState.ALIVE);
		states1.put(new Coords2D(0,3), BinaryState.DEAD);
		
		states1.put(new Coords2D(1,0), BinaryState.ALIVE);
		states1.put(new Coords2D(1,1), BinaryState.DEAD);
		states1.put(new Coords2D(1,2), BinaryState.ALIVE);
		states1.put(new Coords2D(1,3), BinaryState.DEAD);
		
		states1.put(new Coords2D(2,0), BinaryState.DEAD);
		states1.put(new Coords2D(2,1), BinaryState.ALIVE);
		states1.put(new Coords2D(2,2), BinaryState.ALIVE);
		states1.put(new Coords2D(2,3), BinaryState.DEAD);
		
		states1.put(new Coords2D(3,0), BinaryState.DEAD);
		states1.put(new Coords2D(3,1), BinaryState.DEAD);
		states1.put(new Coords2D(3,2), BinaryState.DEAD);
		states1.put(new Coords2D(3,3), BinaryState.DEAD);
		
		GeneralStateFactory gsf1 = new GeneralStateFactory(states1);
		
		GameOfLife gol1 = new GameOfLife(moor, 4, 4);
	
		gol1.setMapFromCellFactory(gsf1);
		
		Map<CellCoordinates, CellState> states2 = new HashMap<CellCoordinates, CellState>();
		
		states2.put(new Coords2D(0,0), BinaryState.DEAD);
		states2.put(new Coords2D(0,1), BinaryState.ALIVE);
		states2.put(new Coords2D(0,2), BinaryState.DEAD);
		states2.put(new Coords2D(0,3), BinaryState.DEAD);
		
		states2.put(new Coords2D(1,0), BinaryState.DEAD);
		states2.put(new Coords2D(1,1), BinaryState.DEAD);
		states2.put(new Coords2D(1,2), BinaryState.ALIVE);
		states2.put(new Coords2D(1,3), BinaryState.ALIVE);
		
		states2.put(new Coords2D(2,0), BinaryState.DEAD);
		states2.put(new Coords2D(2,1), BinaryState.ALIVE);
		states2.put(new Coords2D(2,2), BinaryState.ALIVE);
		states2.put(new Coords2D(2,3), BinaryState.DEAD);
		
		states2.put(new Coords2D(3,0), BinaryState.DEAD);
		states2.put(new Coords2D(3,1), BinaryState.DEAD);
		states2.put(new Coords2D(3,2), BinaryState.DEAD);
		states2.put(new Coords2D(3,3), BinaryState.DEAD);
		
		GeneralStateFactory gsf2 = new GeneralStateFactory(states2);
		
		GameOfLife gol2 = new GameOfLife(moor, 4, 4);
	
		gol2.setMapFromCellFactory(gsf2);
		
		Map<CellCoordinates, CellState> states3 = new HashMap<CellCoordinates, CellState>();
		
		states3.put(new Coords2D(0,0), BinaryState.DEAD);
		states3.put(new Coords2D(0,1), BinaryState.DEAD);
		states3.put(new Coords2D(0,2), BinaryState.ALIVE);
		states3.put(new Coords2D(0,3), BinaryState.DEAD);
		
		states3.put(new Coords2D(1,0), BinaryState.DEAD);
		states3.put(new Coords2D(1,1), BinaryState.DEAD);
		states3.put(new Coords2D(1,2), BinaryState.DEAD);
		states3.put(new Coords2D(1,3), BinaryState.ALIVE);
		
		states3.put(new Coords2D(2,0), BinaryState.DEAD);
		states3.put(new Coords2D(2,1), BinaryState.ALIVE);
		states3.put(new Coords2D(2,2), BinaryState.ALIVE);
		states3.put(new Coords2D(2,3), BinaryState.ALIVE);
		
		states3.put(new Coords2D(3,0), BinaryState.DEAD);
		states3.put(new Coords2D(3,1), BinaryState.DEAD);
		states3.put(new Coords2D(3,2), BinaryState.DEAD);
		states3.put(new Coords2D(3,3), BinaryState.DEAD);
		
		GeneralStateFactory gsf3 = new GeneralStateFactory(states3);
		
		GameOfLife gol3 = new GameOfLife(moor, 4, 4);
	
		gol3.setMapFromCellFactory(gsf3);
		
		GameOfLife golOutput1 = (GameOfLife) gol1.nextState();
		
		GameOfLife golOutput2 = (GameOfLife) golOutput1.nextState();
		
		//Assert.assertEquals("Glider test in first iteration", golOutput1,);
		Assert.assertTrue("Glider test in the second iteration", golOutput2.equals(gol3));
	}
}
