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

	// Default Constructor
	public NumberBase() {
	
		this.list = new ArrayList<>();
	}
	// Constructor for exist list
	public NumberBase (List<Integer> list) {
		
		this.list = new ArrayList<>();
		this.list = list;
	}

	public List<Integer> getList() {

		return list;
	}

	public int getSize() {

		return list.size();
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		list.forEach(i -> sb.append(i).append(" "));
		
		String str = sb.toString();
		return str;
	}

	// if number is in the list, return false
	// add number into the list, then sort it
	public void addNumber(int n) {

		if (list.contains(n))
			return;

		list.add(n);
		Collections.sort(list);
	}

	public void removeNumber(int n) {

		if(!list.contains(n))
			return;

		list.remove(Integer.valueOf(n));
		Collections.sort(list);
	}
	

	public void randomList (int n) {

		int num = 0;
		Random random = new Random();

		while(list.size() < n){
			num = random.nextInt(80) + 1;
			addNumber(num);
		}
	}

	public void cleanList() {

		while(list.size() > 0)
			list.remove(0);
	}
}
