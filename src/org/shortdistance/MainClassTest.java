
package org.shortdistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClassTest {
	
	public static void main(String[] args) {
	    
		List<String> list=new ArrayList<String>();
		list.add("hello");
		list.add("bye");
		list.add("good day");
		list.add("good night");
		list.add("Thank you");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a String :");
		String s1=sc.next();		
		Damerau damerau=new Damerau(s1, list);
		String s2=damerau.computeNearestWord();
		if(s1.equals(s2))
		{
			System.out.print("You Entered a correct word : "+s2);
		}
		else
		{
			System.out.println("Did you mean : "+s2);
		}
		sc.close();
		
		//System.out.println(Dameru.computeDistance("good night", "good day"));
	  }
}
