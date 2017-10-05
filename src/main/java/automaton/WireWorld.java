package automaton;

import java.util.Set;

/**
 * klasa realizuj¹ca automat WireWorld
 * @author arek_
 *
 */
public class WireWorld extends Automaton2Dim {
	public WireWorld(CellNeighborhood neighborsStrategy, int width, int height) {
		super(neighborsStrategy, width, height);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighbourhood){
		WireWorld wireWorld = new WireWorld(super.getCellNeighborhood(), super.getWidth(), super.getHeight());
		return wireWorld;
		}
	@Override
	protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates ){
		
		if(currentState == WireElectronState.VOID){
			return WireElectronState.VOID;
		}
		else if(currentState == WireElectronState.ELECTRON_HEAD){
			return WireElectronState.ELECTRON_TAIL;
		}
		else if(currentState == WireElectronState.ELECTRON_TAIL){
			return WireElectronState.WIRE;
		}
		else if(currentState == WireElectronState.WIRE){
			
			int sumHeads=0;
			
			for(Cell c : neighborsStates){
				if(c.state == WireElectronState.ELECTRON_HEAD)
					sumHeads++;
			}
			
			if(sumHeads == 1 || sumHeads == 2)
				return WireElectronState.ELECTRON_HEAD;
			else
				return WireElectronState.WIRE;
		}
		else{
			//System.out.println("Cos jest nie tak WIREWORLD");
			return null;
			
		}
		
		}
}
