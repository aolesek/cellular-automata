package automaton;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * implementacja automatu komórkowego GameOfLife
 * @author arek_
 *
 */
public class GameOfLife extends Automaton2Dim {
	
	boolean quadLife;
	RulesFactory  rules;
	
	public GameOfLife(CellNeighborhood neighborsStrategy, int width, int height) {
		super(neighborsStrategy, width, height);
		this.quadLife = false;
		this.rules = new RulesFactory("23/3");

	}
	
	public GameOfLife(CellNeighborhood neighborsStrategy, int width, int height, boolean quadLife) {
		super(neighborsStrategy, width, height);
		this.quadLife = quadLife;

	}
	
	public GameOfLife(CellNeighborhood neighborsStrategy, int width, int height, boolean quadLife, String rulesString) {
		super(neighborsStrategy, width, height);
		this.quadLife = quadLife;
		this.rules = new RulesFactory(rulesString);
	}
	
	public GameOfLife(CellNeighborhood neighborsStrategy, int width, int height, boolean quadLife, RulesFactory rules) {
		super(neighborsStrategy, width, height);
		this.quadLife = quadLife;
		this.rules = rules;
	}
	
	public void setRules(RulesFactory rules) {
		this.rules = rules;
	}
	
	public void setQuadLife(Boolean quadLife) {
		this.quadLife=quadLife;
	}
	
	/**
	 * metoda tworzy now¹ mape na podstawie starej, z wybran¹ przez u¿ytkownika struktur¹
	 * @param coords wspó³rzêdne komórki na której ma powstaæ dana struktura
	 * @param name nazwa wybranej przez u¿ytkownika struktury
	 */
	
	public CellState randomState(Coords2D coords) {
		if(quadLife) {
			Random generator = new Random();
			int a = Math.abs(generator.nextInt() % 3);
			
			switch(a) {
			case 0:
					return QuadState.RED;
			case 1:
					return QuadState.GREEN;
					
			case 2:
					return QuadState.BLUE;
					
			case 3:
					return QuadState.YELLOW;
			}
		} 
		
		return BinaryState.ALIVE;
	}
	
	public void addStructure(Coords2D coords, String name) {
		CellStateFactory gsf = new GeneralStateFactory(getMap());
		switch(name) {
			case "Block":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 4, 4, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+1),randomState(coords));
				}
				break;
			case "Beehive":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 6, 5, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+2),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+3),randomState(coords));
				}
				break;
			case "Loaf":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 6, 6, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y+2),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+4), randomState(coords));
				}
				break;
			case "Boat":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 5, 5, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+1), randomState(coords));
				}
				
				break;
			case "Blinker":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 5,5, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+1), randomState(coords));
				}
				
				break;
			case "Toad":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 6,6, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+2), randomState(coords));
				}
				
				break;
			case "Beacon":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 6,6, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+1), randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+3),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+2),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+3),randomState(coords));
				}
				break;
			case "Pulsar":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 17, 17,  getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+3),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+5), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+9),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+10), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+11), randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+5), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+9), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+10), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+11), randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+5), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+9), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+10),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+11),randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+13, coords.y+3),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+13, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+13, coords.y+5),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+13, coords.y+9),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+13, coords.y+10),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+13, coords.y+11), randomState(coords));
					
					for (int i = 0; i < 3; i++) {
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3+i, coords.y+1), randomState(coords));
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3+i, coords.y+6), randomState(coords));
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3+i, coords.y+8), randomState(coords));
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3+i, coords.y+13), randomState(coords));
					}
					
					for (int i = 0; i < 3; i++) {
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+9+i, coords.y+1), randomState(coords));
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+9+i, coords.y+6),randomState(coords));
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+9+i, coords.y+8), randomState(coords));
						((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+9+i, coords.y+13), randomState(coords));
					}
					
				}
				break;
			case "Pentadecathlon":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 16,9, getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+4),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+5, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+5, coords.y+5), randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+6, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+7, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+8, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+9, coords.y+4), randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+10, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+10, coords.y+5), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+11, coords.y+4), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+12, coords.y+4), randomState(coords));
				}
				break;
			case "Glider":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 5,5 , getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+1), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+2), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+2), randomState(coords));
				}
				break;
			case "LWSS":
				if (((GeneralStateFactory) gsf).clearStates(coords.x - 1, coords.y - 1, 5,5 , getWidth(), getHeight(), BinaryState.DEAD)) {
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+1),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+2),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x, coords.y+3),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+1, coords.y+3),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+2, coords.y+3),randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+3, coords.y+3), randomState(coords));
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y+2), randomState(coords));
					
					((GeneralStateFactory) gsf).getStates().put(new Coords2D(coords.x+4, coords.y), randomState(coords));
				}
				break;
			
		}
		
		this.setMapFromCellFactory((GeneralStateFactory) gsf);
		
	}
	
	@Override
	protected Automaton newInstance(CellStateFactory stateFactory, CellNeighborhood cellNeighbourhood){
		Automaton gameOfLife = new GameOfLife(super.getCellNeighborhood(), super.getWidth(), super.getHeight(), quadLife, rules);
		return gameOfLife;
		}
	
	@Override
	protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates ){
		int sumLife ;
		sumLife = 0;
		int sumRed = 0, sumGreen = 0, sumBlue = 0, sumYellow = 0;
		
		for(Cell c : neighborsStates){
			if (c.state instanceof BinaryState) {
				if(c.state != BinaryState.DEAD && c.state != null) {
					sumLife++;
				}
			} else {
				if(c.state == QuadState.RED)
					sumRed++;
				if(c.state == QuadState.GREEN)
					sumGreen++;
				if(c.state == QuadState.BLUE)
					sumBlue++;
				if(c.state == QuadState.YELLOW)
					sumYellow++;
			}
		}
		if (!quadLife) {
			Set<Integer> survivors = rules.getSurvivorsSet();
			Set<Integer> dead = rules.getDeadSet();
		
	
			//System.out.println(survivors.toString());
			//System.out.println(dead.toString());
				
			if (currentState != BinaryState.DEAD && currentState != null) {
				currentState = BinaryState.DEAD;
				Iterator<Integer> siterator = survivors.iterator();
				while(siterator.hasNext()) {
					Integer survivor = siterator.next();
		
					if(sumLife == survivor) {
						currentState = BinaryState.ALIVE;
					}
				}
			} else 	{
				currentState = BinaryState.DEAD;
				Iterator<Integer> diterator = dead.iterator();
				while(diterator.hasNext()) {
					Integer dea = diterator.next();
					if(sumLife == dea) {
						currentState = BinaryState.ALIVE;
					}
				}
			}
		} else {
			//System.out.println("quadLife");
			Set<Integer> survivors = rules.getSurvivorsSet();
			Set<Integer> dead = rules.getDeadSet();
		
	
			//System.out.println(survivors.toString());
			//System.out.println(dead.toString());
				
			if (currentState != QuadState.DEAD && currentState != null) {
				CellState oldState = currentState;
				currentState = QuadState.DEAD;
				Iterator<Integer> siterator = survivors.iterator();
				while(siterator.hasNext()) {
					Integer survivor = siterator.next();
					System.out.println(sumRed+sumGreen+sumBlue+sumYellow);
					if((sumRed+sumGreen+sumBlue+sumYellow) == survivor) {
						currentState = oldState;
					}
				}
			} else 	{
				currentState = QuadState.DEAD;
				Iterator<Integer> diterator = dead.iterator();
				while(diterator.hasNext()) {
					Integer dea = diterator.next();
					if((sumRed+sumGreen+sumBlue+sumYellow) == dea) {
						currentState = getColor(sumRed, sumGreen, sumBlue, sumYellow);
					}
				}
			}
		}
		
		return currentState;
		
	}
	
	private QuadState getColor(int sumRed, int sumGreen, int sumBlue, int sumYellow) {
		Map<QuadState,Integer> sums;
		
		if (sumRed > sumBlue && sumRed > sumGreen && sumRed > sumYellow)
			return QuadState.RED;
		
		if (sumGreen > sumRed && sumGreen > sumBlue && sumGreen > sumYellow)
			return QuadState.GREEN;
		
		if (sumBlue > sumRed && sumBlue > sumGreen && sumBlue > sumYellow)
			return QuadState.BLUE;
		
		if (sumYellow > sumRed && sumYellow > sumGreen && sumYellow > sumYellow)
			return QuadState.YELLOW;
		
		
		if (sumRed == sumGreen && sumGreen == sumBlue && sumYellow < sumRed)
			return QuadState.YELLOW;
		
		if (sumRed == sumGreen && sumGreen == sumYellow && sumBlue < sumRed)
			return QuadState.BLUE;
		
		if (sumRed == sumYellow && sumYellow == sumBlue && sumGreen < sumRed)
			return QuadState.GREEN;
		
		if (sumYellow == sumGreen && sumGreen == sumBlue && sumRed < sumYellow)
			return QuadState.RED;
		
		Random generator = new Random();
		int a = Math.abs(generator.nextInt() % 3);
		
		switch(a) {
		case 0:
				return QuadState.RED;
		case 1:
				return QuadState.GREEN;
				
		case 2:
				return QuadState.BLUE;
				
		case 3:
				return QuadState.YELLOW;
				
		}
		
		return QuadState.RED;
	}
	
	public boolean equals(GameOfLife gol2) {
		if (gol2 == null) return false;
		if (gol2.getMap().equals(this.getMap())) {
			return true;
		} else
		{
			return false;
		}

	}
	
	public String toString() {
		String output = new String();
		
		output = super.getMap().toString();
		
		return output;
	}
}
