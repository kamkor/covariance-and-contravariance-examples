/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package kamkor.variance.solutions.ex1;

import java.util.ArrayList;
import java.util.List;

/**
 * Write code that will make array throw runtime exception ArrayStoreException when modifying array:
 * arr[0] = "iamstring";
 */
public class CovariantArray {
	public static void main(String[] args) {
		final Integer[] ints = { 0 };
		final Object[] objects = ints;
		try	{
			objects[0] = "iamstring";
		} catch (ArrayStoreException exception) {
			exception.printStackTrace();
		}

//		Arrays in Java are unfortunately covariant. Covariant type parameter can't be safely used as method argument type.
//		arr[0] can be seen as arr.set(0, "iamstring");
//
//	    ArrayStoreException is thrown at runtime, because arr is actually an instance of type Integer[], so you can't supply it
//		with object of type String.

// 		Take a look at similar example, but this time using java.util.List
		final List<Integer> intsList = new ArrayList<>();
		intsList.add(5);

//		Line below won't compile, because List is invariant in its type parameter. So we can't use covariant subtyping.
//		final List<Object> invariantList = intsList;

//		when we add variance using use-site annotations, then we can use covariant subtyping:
		final List<? extends Object> objectsList = intsList;

//		however, because it is not safe to use covariant type parameter as method argument type, Java compiler will
// 		prevent you from using setter/consumer methods of List.
//		objectsList.add("iamstring");
//		objectsList.add((Object)"iamstring");
//		only null is allowed by Java compiler
		objectsList.add(null);

//		you can still use producing methods from List though, as return type is a safe position of covariant type parameter
		System.out.println(objectsList.get(0));

//		in case of List<? extends Object>, get will return Object, but it can be anything
//		in case of List<? extends Number>, get will return type Number, but it can of course be Double or Integer, or any other
//		subtype of Number.

//		To summarize, Arrays in java are covariant which is bad, because it leads to runtime errors. But when you use covariance
//		in Java using use-site annotations for generics, Compiler will keep you safe. So now you have an idea, why compiler
//		adds all those restrictions for legal positions of covariant and contravariant type parameter in Scala.
	}
}
