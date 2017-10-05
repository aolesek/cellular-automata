package automaton;

/**
 * klasy implementujące ten interefjs inicjalizują planszę
 * @author arek_
 *
 */
public interface CellStateFactory {
	public CellState initialState(CellCoordinates coordinates);

}
