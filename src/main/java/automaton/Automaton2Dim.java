package automaton;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa dziedzicz¹ca po automaton dla automatów dwuwymiarowych takich jak Game of Life albo WireWorld.
 * @author arek_
 */
public abstract class Automaton2Dim extends Automaton {
	
	private int width, height;
	
	public Map<CellCoordinates, CellState> getMap(){
		return super.getMap();
	}
	/**
	 * 
	 * @return zwraca now¹ pust¹ plansze 
	 */
	private Map<CellCoordinates, CellState> initializeMap() {
		Map<CellCoordinates, CellState> newCells = new HashMap<CellCoordinates, CellState>();
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				
				newCells.put(coords, BinaryState.DEAD); // istance of 1111111111111111111111111111111111111111111111111
			}
		}
		return newCells;
	}
	
	/**
	 * 
	 * @param neighborsStrategy odpowiednie s¹siedztwo jakie wykorzystujemy w grze
	 * @param width d³ugoœæ planszy
	 * @param height wysokoœæ planszy
	 */
	public Automaton2Dim(CellNeighborhood neighborsStrategy, int width, int height) {
		super(neighborsStrategy);
		this.width=width;
		this.height=height;
		super.setMap(initializeMap());
	}
	

	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	@Override
	protected boolean hasNextCoordinates(CellCoordinates c){
		Coords2D coords2D;
		//spr czy to nie koniec planszy
		coords2D = (Coords2D) c;
		
		if(coords2D.x == (width - 1) && coords2D.y == (height - 1) )
			return false;
		else
			return true;
	}
	@Override
	protected CellCoordinates initialCoordinates(){
		Coords2D coords2D = new Coords2D();
		coords2D.x=-1;
		coords2D.y=0;
		return coords2D;
	}
	@Override
	protected CellCoordinates nextCoordinates(CellCoordinates c){
		Coords2D coords2D = (Coords2D) c;
		if(coords2D.x == width-1){
			coords2D.x=0;
			coords2D.y=coords2D.y+1;
		}
		else{
			coords2D.x++;
		}
			
		return coords2D;
	}

}
