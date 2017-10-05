package automaton;

import java.util.Set;

/**
 * tworzy zbi�r s�siad�w
 * @author arek_
 *
 */
public interface CellNeighborhood {
	
	/**
	 * metoda zwracaj�ca zbi�r wsp�rz�dnych s�siad�w kom�rki 
	 * @param cell wsp�rz�dne kom�rki, dla kt�rej wyszukujemy s�siad�w
	 * @return metoda zwraca zbi�r wsp�rz�dnych s�siad�w danej kom�rki
	 */
	public Set<CellCoordinates> cellNeighbours(CellCoordinates cell);
	
	/**
	 * metoda odpowiadaj�ca za w��czenie zawijania planszy
	 * @param wrapping jesli true zawijamy plansze, jesli false nie
	 */
	public void setBoardWrapping(Boolean wrapping);
	
	/**
	 * ustawiamy promie� obszaru, w kt�rym wyszukujemy s�siad�w
	 * @param radius promie� 
	 */
	public void setRadius(int radius);
}
