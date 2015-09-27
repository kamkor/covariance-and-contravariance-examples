package kamkor.covariance;

import org.junit.Test;

public class CovariantArrayExample {

	// Scala solves this by making its Arrays invariant (nonvariant)
	@Test(expected = ArrayStoreException.class)
	public void covariantArraysLeadToRuntimeExceptions() {
		String[] strings = { "abc" };
		Object[] objects = strings;
		// OOPS! Line below throws runtime exception: ArrayStoreException.
		// Reason is that objects is actually an instance of 
		// Array of Strings and we try to update it with an Integer.
		objects[0] = 1;
	}

}
