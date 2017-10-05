package automaton;
/**
 * klasa bedaca opisem wspolrzednych dla automatow dwuwymiarowych
 * @author arek_
 *
 */
public class Coords2D implements CellCoordinates {
	public int x, y;
	
	public Coords2D() {
		
	}
	
	public String toString() {
		return new String("("+x+":"+y+")");
		
	}
	
	public Coords2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Coords2D c = (Coords2D) obj;
		if ((this.x == c.x) && (this.y == c.y)) 
			return true;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coords2D other = (Coords2D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
}
