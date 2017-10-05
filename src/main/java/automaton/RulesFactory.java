package automaton;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * klasa generuje sety s¹siadów ¿ywych i martwych na podstawie dostarczonego ci¹gu znaków oznaczaj¹cego zasady dla GameOfLife np. 23/3
 * @author arek_
 *
 */
public class RulesFactory {
	private String rulesString;
	private Set<Integer> survivors;
	private Set<Integer> dead;
	
	public RulesFactory() {
		this.rulesString = new String("23/3");
		this.survivors = new HashSet<Integer>();
		this.dead = new HashSet<Integer>();		
		parseRulesString();
	}
	
	public RulesFactory(String newRules) {
		this.rulesString = newRules;
		this.survivors = new HashSet<Integer>();
		this.dead = new HashSet<Integer>();
		parseRulesString();
	}
	
	/**
	 * 
	 * @return zwraca zbiór ¿ywych s¹siadów
	 */
	public Set<Integer> getSurvivorsSet() {
		return this.survivors;
	}
	
	/**
	 * 
	 * @return zwraca zbiór martwych s¹siadów
	 */
	public Set<Integer> getDeadSet() {
		return this.dead;
	}
	
	private void parseRulesString() {
		Pattern pattern = Pattern.compile("([0-9]*)/([0-9]*)");
		Matcher matcher = pattern.matcher(this.rulesString);
		
		String survivorsString = null, deadString = null;
		
		if (matcher.find( )) {
	         survivorsString = new String(matcher.group(1));
	         deadString = new String(matcher.group(2));
	    }
		
		for (int i = 0; i < survivorsString.length(); i++) {
			int survivor = Character.getNumericValue(survivorsString.charAt(i));
			this.survivors.add(survivor);
		}
		
		for (int i = 0; i < deadString.length(); i++) {
			int dead = Character.getNumericValue(deadString.charAt(i));
			this.dead.add(dead);
		}
	}
} 
