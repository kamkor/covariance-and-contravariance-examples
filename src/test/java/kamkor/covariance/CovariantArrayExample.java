package kamkor.covariance;

import org.junit.Test;

public class CovariantArrayExample {

	// Scala solves this by making its Arrays invariant (nonvariant)
	@Test(expected = ArrayStoreException.class)
	public void covariantArraysLeadToRuntimeExceptions() {
		String[] c1 = { "abc" };
		Object[] c2 = c1;
		// OOPS! Line below throws a runtime exception: ArrayStoreException
		// Reason is, that c2 is actually an instance of String array, and
		// we try to update it with an Integer.
		c2[0] = 1;
	}

}
