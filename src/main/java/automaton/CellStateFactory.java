package automaton;

/**
 * klasy implementuj�ce ten interefjs inicjalizuj� plansz�
 * @author arek_
 *
 */
public interface CellStateFactory {
	public CellState initialState(CellCoordinates coordinates);

}
