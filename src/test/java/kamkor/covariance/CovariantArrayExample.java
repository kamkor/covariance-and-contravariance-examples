package kamkor.covariance;

import org.junit.Test;

public class CovariantArrayExample {

	// Scala solves this by making its Arrays invariant (nonvariant)
	@Test(expected = ArrayStoreException.class)
	public void covariantArraysLeadToRuntimeExceptions() {
		String[] c1 = { "abc" };
		Object[] c2 = c1;
		// OOPS! Below lone throws a runtime exception. ArrayStoreException
		c2[0] = 1;
	}

}
