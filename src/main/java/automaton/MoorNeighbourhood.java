package automaton;

import java.util.HashSet;
import java.util.Set;
/**
 * klasa realizuje funkcjê ustalania s¹siedztwa Moore'a dla jednej komórki
 * @author arek_
 *
 */
public class MoorNeighbourhood implements CellNeighborhood {
	int minX, minY = 0;
	int r;
	int maxX, maxY; 
	Coords2D coords2D;
	boolean boardWrapping;
	
	MoorNeighbourhood(int width, int height){ 
		this.maxX = width - 1;
		this.maxY = height - 1;
		this.r = 1;
		this.boardWrapping = false;
	}
	
	MoorNeighbourhood(int width, int height, boolean boardWrapping){ 
		this(width, height);
		this.boardWrapping = boardWrapping;
	}
	
	MoorNeighbourhood(int width, int height, boolean boardWrapping, int r){ 
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
	
	/**
	 * ustalenie s¹siadów komórki
	 * @param cellCoordinates wspó³rzêdne komórki
	 * @return Set zawieraj¹cy s¹siadów
	 */
	@Override
	public Set<CellCoordinates> cellNeighbours(CellCoordinates cellCoordinates) {
		
		int startIndexX, startIndexY, endIndexX, endIndexY;
		Set<CellCoordinates> coordsSet = new HashSet<CellCoordinates>();
		
		coords2D = (Coords2D) cellCoordinates;
	
		
		startIndexX=coords2D.x-r;
		endIndexX=coords2D.x+r;
		startIndexY=coords2D.y-r;
		endIndexY=coords2D.y+r;
		
		if(boardWrapping) {
			
			for (int i = startIndexX; i <= endIndexX; i++) {
				for (int j = startIndexY; j <= endIndexY; j++) {
					if ( !((i == coords2D.x) && (j == coords2D.y)) ) {
						Coords2D coords = new Coords2D();

						coords.x = (i + maxX + 1)%(maxX + 1);

						coords.y = (j + maxY + 1)%(maxY + 1);
						
						coordsSet.add(coords);	
					}
				}
			}	
		} else {
			if(coords2D.x == 0)
				startIndexX=0;
			if(coords2D.x == maxX)
				endIndexX=maxX;
			if(coords2D.y == 0)
				startIndexY=0;
			if(coords2D.y == maxY)
				endIndexY=maxY;
			
			for(int i= startIndexX;i<=endIndexX;i++){
				for(int j=startIndexY;j<=endIndexY;j++){
					if ( !((i == coords2D.x) && (j == coords2D.y)) ) {
						coordsSet.add(new Coords2D(i,j));			
					}
				}
			}			
		}
		return coordsSet;
	}

}
