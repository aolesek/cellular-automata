package automaton;

import java.util.HashSet;
import java.util.Set;
/**
 * klasa realizuj¹ca funkcje ustalenia s¹siedztwa Von Neumanna dla komórki
 * @author arek_
 *
 */
public class VonNeumanNeighbourhood implements CellNeighborhood {
	
	int startIndexX, endIndexX, startIndexY, endIndexY;
	int minX = 0, minY = 0;
	int maxX, maxY;
	int r;
	boolean boardWrapping;
	boolean left, right, up, down;
	
	Coords2D coords2D;
	
	
	public VonNeumanNeighbourhood(int width, int height){ //prwdp bedzie wywo³ywany np. w Automaton2Dim
		maxX = width - 1;
		maxY = height - 1;
		this.r = 1;
		this.boardWrapping = false;
	}
	
	public VonNeumanNeighbourhood(int width, int height, boolean boardWrapping){ //prwdp bedzie wywo³ywany np. w Automaton2Dim
		this(width, height);
		this.boardWrapping = boardWrapping;
	}
	
	public VonNeumanNeighbourhood(int width, int height, boolean boardWrapping, int r){ //prwdp bedzie wywo³ywany np. w Automaton2Dim
		this(width, height, boardWrapping);
		this.r = r;
	}
	
	@Override
	public void setBoardWrapping(Boolean wrapping) {
		this.boardWrapping = wrapping;
	}
	
	@Override
	public void setRadius(int radius) {
		this.r = radius;
	}
	
	@Override
	public Set<CellCoordinates> cellNeighbours(CellCoordinates cellCoordinates) {

		Set<CellCoordinates> coordsSet = new HashSet<CellCoordinates>();
		
		coords2D = (Coords2D) cellCoordinates;
		
		startIndexX = coords2D.x - r;
		endIndexX = coords2D.x + r;
		startIndexY = coords2D.y - r;
		endIndexY = coords2D.y + r;
		
		if (boardWrapping) {
			for (int i = startIndexX; i <= endIndexX; i++) {
				if (i != coords2D.x)
					coordsSet.add(new Coords2D((i + maxX + 1)%(maxX + 1), coords2D.y));
			}
			
			for (int j = startIndexY; j <= endIndexY; j++) {
				if (j != coords2D.y)
				coordsSet.add(new Coords2D(coords2D.x,(j + maxY + 1)%(maxY + 1)));
			}
			
		} else {
			if (startIndexX < 0)
				startIndexX = 0;
			if (endIndexX > maxX)
				endIndexX = maxX;
			if (startIndexY < 0)
				startIndexY = 0;
			if (endIndexY > maxY)
				endIndexY = maxY;
			
			for (int i = startIndexX; i <= endIndexX; i++) {
				if (i != coords2D.x)
					coordsSet.add(new Coords2D(i, coords2D.y));
			}
			
			for (int j = startIndexY; j <= endIndexY; j++) {
				if (j != coords2D.y)
				coordsSet.add(new Coords2D(coords2D.x,j));
			}
		}
		
		return coordsSet;
	}
}