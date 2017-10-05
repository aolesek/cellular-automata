package automaton;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * klasa Automaton, dziedzicz¹ po niej wszystkie automaty we frameworku
 *
 * @author arek_
 *
 */
public abstract class Automaton {
	
	private Map<CellCoordinates, CellState> cells;
	private CellNeighborhood neighborsStrategy;
	private CellStateFactory stateFactory;

	CellIterator cellIterator = new CellIterator();

	
	public Automaton(CellNeighborhood neighborsStrategy) {
		this.neighborsStrategy = neighborsStrategy;
	}
	
	/**
	 * 
	 * @param height wysokoœc planszy
	 * @param width szerokoœæ planszy
	 * 
	 */
	public void displayMapCells(int height, int width,CellState state){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				
				if (state instanceof BinaryState) {
					if(cells.get(coords) == BinaryState.ALIVE)
						System.out.print("o");
					else if(cells.get(coords) == BinaryState.DEAD)
						System.out.print("x");
					else if(cells.get(coords) == null)
						System.out.print("n");

				} else if (state instanceof QuadState) {
					if(cells.get(coords) == QuadState.RED)
						System.out.print("R");
					else if(cells.get(coords) == QuadState.GREEN)
						System.out.print("G");
					else if(cells.get(coords) == QuadState.BLUE)
						System.out.print("B");
					else if(cells.get(coords) == QuadState.YELLOW)
						System.out.print("Y");
					else if(cells.get(coords) == QuadState.DEAD || cells.get(coords) == null)
						System.out.print("X");
				}

			}
			System.out.println("");
		}
	}
	
	public void displayMapCellsWIRE(int width, int height){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++) {
				Coords2D coords = new Coords2D();
				coords.x = j;
				coords.y = i;
				if(cells.get(coords) == WireElectronState.ELECTRON_HEAD)
					System.out.print("n");
				else if(cells.get(coords) == WireElectronState.VOID)
					System.out.print(" ");
				else if(cells.get(coords) == WireElectronState.WIRE)
					System.out.print("x");
				else if(cells.get(coords) == WireElectronState.ELECTRON_TAIL)
					System.out.print("r");
				else if(cells.get(coords) == null)
					System.out.print("a");
			}
			System.out.println("");
		}
	}
	
	public void displayMapCellsOneDim(int size){
		for (int i = 0; i < size; i++){
				Coords1D coords = new Coords1D();
				coords.x = i;
				
 				if(cells.get(coords) == BinaryState.ALIVE)
					System.out.print("o");
				else if(cells.get(coords) == BinaryState.DEAD)
					System.out.print(" ");
				else if(cells.get(coords) == null)
					System.out.print("n");
				else
					System.out.print("z");
			}
			System.out.println("");
		
	}
	
	public CellNeighborhood getCellNeighborhood(){
		return this.neighborsStrategy;
	}
	
	public void setCellNeighborhood(CellNeighborhood neighborsStrategy){
		this.neighborsStrategy = neighborsStrategy;
	}
	
	public void setMap(Map<CellCoordinates, CellState> newCells){
		this.cells=newCells;
	}
	
	public void putMap(CellCoordinates c, CellState s){
		this.cells.put(c, s);
	}
	
	public void setCellStateFactory(CellStateFactory newCellStateFactory){
		this.stateFactory=newCellStateFactory;
	}
	
	public void setMapFromCellFactory(GeneralStateFactory gsf){
		//gsf= (GeneralStateFactory) stateFactory;
		this.cells=gsf.getStates();
	}
	
	public CellCoordinates getCurrentCoords(){
		return this.cellIterator.currentCoordinates;
	}
	
	public Map<CellCoordinates, CellState> getMap(){
		return this.cells;
	}
	
	/**
	 * Metoda nextState() tworzy nowy obiekt Automaton, który jest nastêpnym stanem danej gry. Nowy obiekt
	 * tworzy sie na podstawie "starego" obiektu Automaton.
	 * Metoda nextState() opiera sie na klasie CellIterator, dzieki której iterujemy po wszystkich komórkach "starej"
	 * planszy, ustawiaj¹c dla ka¿ej nowy stan, w nowym Automaton
	 * @return zwraca nowy obiekt automaton, metoda newInstance jest przes³aniana w klasach podrzêdnych,
	 * dlatego zwracamy odpowiednie implementacja klasy Automaton np. GameOfLife
	 * 
	 */
	public Automaton nextState() {
		
		Automaton newGame = newInstance(stateFactory, neighborsStrategy); 

		while(cellIterator.hasNext()){
			Cell cell = cellIterator.next();
			newGame.cellIterator.next();
			
			if(neighborsStrategy != null){ // tylko do Langtona
				Set<CellCoordinates> setNeighbors = neighborsStrategy.cellNeighbours(cell.coords);
				Set<Cell> setCells = mapCoordinates(setNeighbors);
				newGame.cellIterator.setState(nextCellState(cell.state, setCells));
			}
			else
				newGame.cellIterator.setState(nextCellState(cell.state, null));
		
		}
	
		
		newGame.cellIterator.resetCoords();
		
		return newGame;
	}

	/**
	 * 
	 * @param stateFactory plansza danej gry
	 * @param neighborhood s¹siedztwo wykorzystywane w danej grze
	 * @return zwraca obiekt Automaton, dok³adnie odpowiednie implementacje klasy Automaton np. GameOfLife
	 */
	protected Automaton newInstance(CellStateFactory stateFactory, CellNeighborhood neighborhood){
		return null;
	}
	
	/**
	 * 
	 * @param coordinates wspó³rzêdne obecnie analizowanej komórki
	 * @return zwraca true jesli istnieje nastêpna komórka na planszy false jeœli nie
	 */
	protected boolean hasNextCoordinates(CellCoordinates coordinates){
		return false; 
	}
	
	/**
	 * 
	 * @return zwraca pocz¹tkowe wspó³rzêdne planszy
	 */
	protected CellCoordinates initialCoordinates(){
		return null;
	}
	
	/**
	 * na podstawie obecnych wspó³rzêdnych zwracamy nastepne wspó³rzêdne komórki planszy
	 * metoda przes³aniana w Automaton2Dim i Automaton1Dim 
	 * @param coordinates wspó³rzêdne obecnie analizowanej komórki
	 * @return zwracamy wspó³rzêdne  nastepnej komórki planszy
	 */
	protected CellCoordinates nextCoordinates(CellCoordinates coordinates){
		return null;
	}
	
	/**
	 * ustawiamy nastepny stan danej komórki na podstawie starego stanu i kolekcji s¹siadów danej komórki
	 * metoda przes³aniana w implementacjach odpowiednich gier np. GameOfLife
	 * @param currentstate obecny stan
	 * @param neighboursStates kolekcja komórek s¹siadów danej komórki
	 * @return
	 */
	protected CellState nextCellState (CellState currentstate, Set<Cell> neighboursStates){
		
		// na podstawie sasiadow ustalamy nowy stan komórki
		// musimy mieæ info o sasiedztwie (MoorNei lub VonNeuman)
		// bierzemy to z neighborsStrategy
		// na tej podstawie, wiemy któr¹ metoda obliczamy nastepny stan komórki

		// Zaimplementowane juz w GameOfLife
		return null;
	}
	
	/**
	 * metoda zwraca kolekcje komórek na podstawie kolekcji wpsó³rzêdnych komórek 
	 * @param cellCoordinates kolekcja wpsó³rzêdnych komórek 
	 * @return metoda zwraca kolekcje komórek
	 */
	private Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoordinates){
		
		Set<Cell> cellsTable = new HashSet<Cell>();
		
		for(CellCoordinates coordinates : cellCoordinates){
			Cell cell = new Cell();
			cell.state = cells.get(coordinates);
			cell.coords = coordinates;
			cellsTable.add(cell);
		}
		
		return cellsTable;
	}
	
	public void changeStateLive(CellCoordinates coords) {
		if (coords instanceof Coords2D) {
			if (getMap().get(coords) instanceof BinaryState) {
				if (getMap().get(coords) == BinaryState.ALIVE)
					getMap().put(coords, BinaryState.DEAD);
				else
					getMap().put(coords, BinaryState.ALIVE);
			} else if (getMap().get(coords) instanceof QuadState) {
				if (getMap().get(coords) == QuadState.DEAD)
					getMap().put(coords, QuadState.RED);
				else if (getMap().get(coords) == QuadState.RED)
					getMap().put(coords, QuadState.GREEN);
				else if (getMap().get(coords) == QuadState.GREEN)
					getMap().put(coords, QuadState.BLUE);
				else if (getMap().get(coords) == QuadState.BLUE)
					getMap().put(coords, QuadState.YELLOW);
				else if (getMap().get(coords) == QuadState.YELLOW)
					getMap().put(coords, QuadState.DEAD);
			
			} else if (getMap().get(coords) instanceof AntState) {
				if (getMap().get(coords) == AntState.NONE)
					getMap().put(coords, AntState.NORTH);
				else if (getMap().get(coords) == AntState.NORTH)
					getMap().put(coords, AntState.SOUTH);
				else if (getMap().get(coords) == AntState.SOUTH)
					getMap().put(coords, AntState.EAST);
				else if (getMap().get(coords) == AntState.EAST)
					getMap().put(coords, AntState.WEST);
				else if (getMap().get(coords) == AntState.WEST)
					getMap().put(coords, AntState.NONE);
			
			} else if (getMap().get(coords) instanceof WireElectronState) {
				if (getMap().get(coords) == WireElectronState.VOID)
					getMap().put(coords, WireElectronState.WIRE);
				else if (getMap().get(coords) == WireElectronState.WIRE)
					getMap().put(coords, WireElectronState.ELECTRON_HEAD);
				else if (getMap().get(coords) == WireElectronState.ELECTRON_HEAD)
					getMap().put(coords, WireElectronState.ELECTRON_TAIL);
				else if (getMap().get(coords) == WireElectronState.ELECTRON_TAIL)
					getMap().put(coords, WireElectronState.VOID);
			}
		} else {
			//Coords 1D VOID, WIRE, ELECTRON_HEAD, ELECTRON_TAIL
		}

	}
	
	/**
	 * Klasa odpowiadaj¹ca za iterowanie po komórkach.
	 * @author  arek_
	 *
	 */
	private class CellIterator{
		
		private CellCoordinates currentCoordinates;
		
		CellIterator(){
			currentCoordinates = initialCoordinates();
		}
		
		/**
		 * 
		 * @return Zwraca true je¿eli iterator nie osi¹gn¹³ koñca planszy.
		 */
		public boolean hasNext(){
			return hasNextCoordinates(currentCoordinates);
			}
		
		/**
		 * 
		 * @return Zwraca nastêpn¹ komórkê z planszy
		 */
		public Cell next(){
				currentCoordinates = nextCoordinates(currentCoordinates);
				Cell cell = new Cell();
				 cell.state = cells.get(currentCoordinates);
				 cell.coords = currentCoordinates;
				return cell;			
		}
		
		/**
		 * Zmienia stan komórki na któr¹ wskazuje iterator
		 * @param c wejœciowy stan
		 */
		public void setState(CellState c){	
			cells.put(currentCoordinates, c);
		}
		/**
		 * Resetuje aktualne wspó³rzêdne.
		 */
		public void resetCoords(){
			currentCoordinates = initialCoordinates();
		}
	}
}
