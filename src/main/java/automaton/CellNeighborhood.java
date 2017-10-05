package automaton;

import java.util.Set;

/**
 * tworzy zbiór s¹siadów
 * @author arek_
 *
 */
public interface CellNeighborhood {
	
	/**
	 * metoda zwracaj¹ca zbiór wspó³rzêdnych s¹siadów komórki 
	 * @param cell wspó³rzêdne komórki, dla której wyszukujemy s¹siadów
	 * @return metoda zwraca zbiór wspó³rzêdnych s¹siadów danej komórki
	 */
	public Set<CellCoordinates> cellNeighbours(CellCoordinates cell);
	
	/**
	 * metoda odpowiadaj¹ca za w³¹czenie zawijania planszy
	 * @param wrapping jesli true zawijamy plansze, jesli false nie
	 */
	public void setBoardWrapping(Boolean wrapping);
	
	/**
	 * ustawiamy promieñ obszaru, w którym wyszukujemy s¹siadów
	 * @param radius promieñ 
	 */
	public void setRadius(int radius);
}
