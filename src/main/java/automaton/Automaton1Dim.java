package automaton;

import java.util.HashMap;
import java.util.Map;

/**
 * klasa dziedzicz¹ca po Automaton dla automatów jednowymiarowych
 * @author arek_
 *
 */
public abstract class Automaton1Dim extends Automaton {
	private int size;
	
	
	/**
	 *
	 * @return rozmiar planszy
	 */
	public int getSize(){
		return this.size;
	}

	/**
	 * inicjalizuje mapê pustymi komórkami
	 * @return Mapa indeksowana CellCoordinates zawieraj¹ca CellState
	 */
	private Map<CellCoordinates, CellState> initializeMap() {
		Map<CellCoordinates, CellState> newCells = new HashMap<CellCoordinates, CellState>();
		for (int i = 0; i < size; i++){
				Coords1D coords = new Coords1D();
				coords.x = i;
				newCells.put(coords, BinaryState.DEAD); 
			}
		return newCells;
	}
	
	/**
	 * 
	 * @param neighborsStrategy s¹siedztwo
	 * @param size rozmiar
	 */
	public Automaton1Dim(CellNeighborhood neighborsStrategy, int size) {
		super(neighborsStrategy);
		this.size=size;
		super.setMap(initializeMap());
	}
	
	@Override
	protected boolean hasNextCoordinates(CellCoordinates c){
		Coords1D coords1D = (Coords1D) c;
		if(coords1D.x == size)
			return false;
		else
			return true;
	}
	

	@Override
	protected CellCoordinates initialCoordinates(){
		Coords1D coords1D = new Coords1D();
		coords1D.x=-1;
		
		return coords1D;
	}
	

	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates c){
		Coords1D coords1D = (Coords1D) c;
		coords1D.x++;
		return coords1D;
	}
}
