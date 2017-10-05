package automaton;

/**
 * klasy implementuj¹ce ten interefjs inicjalizuj¹ planszê
 * @author arek_
 *
 */
public interface CellStateFactory {
	public CellState initialState(CellCoordinates coordinates);

}
