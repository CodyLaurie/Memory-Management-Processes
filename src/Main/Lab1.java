package Main;

import java.util.*;

public class Lab1{
	static Scanner scan = new Scanner(System.in);
	static float max;
	public static void main(String[] args) {
		System.out.println("Welcome Cody Laurie's Dynamic Memory Allocator");
		System.out.println("This program is used to find the largest number by Dynamically Allocating Memory");
		System.out.println("--------------------------------------------------------------------------------");
		max();
	}
	
	public static void max() {
		System.out.print("Please enter a total number of items: ");
		int size = 0;
		try {
			size = scan.nextInt();
		}catch(Exception e) {
			scan = new Scanner(System.in);
			max();
		}
		System.out.println();
		for (int i = 1; i <= size ; i++) {
			nxtnum(i);
		}
		System.out.print("The Largest Item is :  " +max);
		System.exit(0);
	}
	
	public static void nxtnum(int index) {
		System.out.print("Number "+ index +": ");	
		try {
			float test = scan.nextFloat();
			if (test>max) {
				max = test;
			}
		}catch(Exception e) {
			scan = new Scanner(System.in);
			nxtnum(index);
		}
	}
}