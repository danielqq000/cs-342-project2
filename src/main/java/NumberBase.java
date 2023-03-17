// NumberBase.java
// author: GuanYu Huang, UID: 677460448
// This file belongs to Project2 for CS342 Spring 2023
//
// Description:
// Provide a list of number, where user's pick will store here.
// Provide functions to get list, size of list, add number to list, and generate random list.
//


import java.util.*;
import java.util.Random;


public class NumberBase {


	private List<Integer> list = new ArrayList<>();

	public List<Integer> getList() {

		return list;
	}

	public int getSize() {

		return list.size();
	}

	// if number is in the list, return false
	// add number into the list, then sort it
	public boolean addNumber(int n) {

		if (list.contains(n))
			return false;

		list.add(n);
		Collections.sort(list);
	
		return true;
	}

	public List<Integer> randomList (int n) {

		int num = 0;
		Random random = new Random();

		for(int i = 0; i < n;){

			num = random.nextInt(80) + 1;
			if (addNumber(num))
				i++;
		}

		return list;
	}

}
