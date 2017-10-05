package automaton;

import java.util.Set;
/***
 * klasa bedaca automatem jednowymiarowym
 * @author arek_
 *
 */	
public class OneDimGame extends Automaton1Dim {
	
	public OneDimGame(CellNeighborhood neighborsStrategy, int size) {
		super(neighborsStrategy, size);

	}

	@Override
	protected Automaton newInstance(CellStateFactory stateFactory, CellNeighborhood cellNeighbourhood){
		Automaton OneDimGame = new OneDimGame(super.getCellNeighborhood(), super.getSize());
		return OneDimGame;
		}
	@Override
	protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates ){
		
		boolean rightNeighbor=false;
		int sumLife=0;
		/////////// RULE 30
		for(Cell c : neighborsStates){
			if(c.state == BinaryState.ALIVE)
				sumLife++;
		}
		
		// sprawdzamy czy prawa komórka jest żywa
			Coords1D current = (Coords1D) super.getCurrentCoords();
			for(Cell c : neighborsStates){
				Coords1D coords1D = (Coords1D) c.coords;
				
				if((coords1D.x == (current.x+1)) && c.state==BinaryState.ALIVE){
					rightNeighbor=true;
				}
			}
		
		
		if(sumLife == 1 && currentState==BinaryState.DEAD){
			currentState = BinaryState.ALIVE;
		}
		else if(sumLife == 0 && currentState == BinaryState.ALIVE){
			currentState = BinaryState.ALIVE;
		}
		else if(sumLife == 1 && currentState == BinaryState.ALIVE && rightNeighbor==true){
			currentState = BinaryState.ALIVE;
		}
		else{
			currentState = BinaryState.DEAD;
		}
		
		return currentState;
		}
}
