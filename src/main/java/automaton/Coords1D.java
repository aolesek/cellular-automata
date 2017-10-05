package automaton;

/**
 * klasa bedaca wspolrzednymi komorki dla automatow jednowymiarowych
 * @author arek_
 *
 */
public final class Coords1D implements CellCoordinates {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		return result;
	}
	
	public Coords1D () {
		
	}
	
	public Coords1D (int x) {
		this.x = x;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coords1D other = (Coords1D) obj;
		if (x != other.x)
			return false;
		return true;
	}

	public int x;

}
