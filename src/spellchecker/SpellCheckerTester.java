package spellchecker;

import junit.framework.TestCase;


/*
 * File created on: Mar 12, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellCheckerTester extends TestCase {
	
	public SpellCheckerTester(String name) {
		super(name);
	}
	


	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	
	/*
	 * Ideas for tomorrow:
	 * - Weight edit operations to preference some over others (which?)
	 * - Add bigrams to model.
	 * - Develop distributed design.
	 * - Combine buildKnownWords and buildBigramsSet to make one pass.
	 * - Make editDist2 into generic editDistNPlusOne
	 * 
	 * 
	 * Bored?
	 * - Mine Guava's collections for useful things.
	 *
	 */
	
	
	public void evaluateSpellChecker1() {
		
	}
	

}
