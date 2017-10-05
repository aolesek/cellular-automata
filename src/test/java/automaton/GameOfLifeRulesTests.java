package automaton;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import automaton.RulesFactory;

public class GameOfLifeRulesTests {

	@Test
	public void simpleTest() {

		RulesFactory rules = new RulesFactory("128/345");
		
		Set<Integer> outputSurvivors = rules.getSurvivorsSet();
		Set<Integer> outputDead = rules.getDeadSet();
		
		Set<Integer> correctSurvivors = new HashSet<Integer>();
		Set<Integer> correctDead = new HashSet<Integer>();
		
		correctSurvivors.add(1);
		correctSurvivors.add(2);
		correctSurvivors.add(8);
		
		correctDead.add(3);
		correctDead.add(4);
		correctDead.add(5);
		
		Assert.assertTrue("Parsed survivors should be same as in the rulesString", outputSurvivors.equals(correctSurvivors));
		Assert.assertTrue("Parsed dead should be same as in the rulesString", outputDead.equals(correctDead));

		
	}
	
	@Test
	public void emptyRuleTest() {
		RulesFactory rules = new RulesFactory("/345");
		
		Set<Integer> outputSurvivors = rules.getSurvivorsSet();
		Set<Integer> outputDead = rules.getDeadSet();
		
		Set<Integer> correctSurvivors = new HashSet<Integer>();
		Set<Integer> correctDead = new HashSet<Integer>();
		
		correctDead.add(3);
		correctDead.add(4);
		correctDead.add(5);
		
		Assert.assertTrue("Parsed survivors should be same as in the rulesString", outputSurvivors.equals(correctSurvivors));
		Assert.assertTrue("Parsed dead should be same as in the rulesString", outputDead.equals(correctDead));

		
	}
	
}
