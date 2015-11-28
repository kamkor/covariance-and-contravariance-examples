package kamkor.covariance;

public class CovariantArrayExample {

	public static void main(String[] args) {
		String[] strings = { "abc" };
		Object[] objects = strings;
		objects[0] = 1;
	}

}
