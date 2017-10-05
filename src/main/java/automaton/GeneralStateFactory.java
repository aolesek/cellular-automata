package automaton;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * klasa generuje stan pocz�tkowy automatu.
 * @author arek_
 *
 */
public class GeneralStateFactory implements CellStateFactory {
	
	public Map<CellCoordinates, CellState> states;

	public GeneralStateFactory(Map<CellCoordinates, CellState> initialStates) {
		this.states = initialStates;	
	}
	
	/**
	 * Metoda zwraca stan kom�rki z mapy states (pocz�tkowy stan kom�rki ustawiany na samym pocz�tku gry)
	 */
	@Override
	public CellState initialState(CellCoordinates coordinates) {
		return states.get(coordinates);
	}
	
	public Map<CellCoordinates, CellState> getStates(){
		return this.states;
	}
	
	public boolean clearStates(int i, int j, int width, int height, int mapwidth, int mapheight, CellState state) {
		for (int a = i; a <= i + width; a++)
			for (int b = j; b <= j + height; b++) {
				if (a < mapwidth && b < mapheight)
					states.put(new Coords2D(a,b), state);
				else return false;
			}
		return true;
	}
}
