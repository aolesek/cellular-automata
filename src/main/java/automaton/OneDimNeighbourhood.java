package automaton;

import java.util.HashSet;
import java.util.Set;

/**
 * klasa wyszukuje s�siad�w dla automat�w jednowymiarowych
 * @author arek_
 *
 */
public class OneDimNeighbourhood implements CellNeighborhood {
	
	int size;
	OneDimNeighbourhood(int size){
		this.size=size;
	}
	
	@Override
	public void setBoardWrapping(Boolean wrapping) {
		
	}
	
	@Override
	public void setRadius(int radius) {
		
	}

	/**
	 * metoda zwraca set s�siad�w kom�rki o wsp�rzednych cell
	 * @param cell kom�rka kt�rej s�siad�w liczymy
	 * @return coordsSet zbi�r s�siad�w
	 */
	@Override
	public Set<CellCoordinates> cellNeighbours(CellCoordinates cell) {
		
		Set<CellCoordinates> coordsSet = new HashSet<CellCoordinates>();
		
		Coords1D coords1D = (Coords1D) cell;
		int i=1;
			if((coords1D.x-i)>=0){
				Coords1D coords = new Coords1D();
				coords.x = coords1D.x-i;
				coordsSet.add(coords);
			}
			if((coords1D.x+i)<=size){
				Coords1D coords = new Coords1D();
				coords.x = coords1D.x+i;
				coordsSet.add(coords);
			}
				
		
		return coordsSet;
	}

}
