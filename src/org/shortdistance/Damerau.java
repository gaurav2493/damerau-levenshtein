/**
 * Dameru is a class that allows an application to implement Levenshtein Distance algorithm to find the shortest distance between two words.
 * It can also help in comparing a word with a list of different words to find the nearest matching word.
 * 
 * A small example --
 * 
 * 		List list=new ArrayList();
 *		list.add("hello");
 *		list.add("bye");
 *		list.add("Good night");
 *		Dameru lev=new Dameru("helo",list);
 *		System.out.println(lev.computeNearestWord());
 *
 *In this example, the nearest word which matches `helo` in the list `hello` gets printed.
 * 
 * @author Gaurav Sharma
 * @version 1.0
 */

package org.shortdistance;

import java.util.Arrays;
import java.util.List;


public class Damerau implements DistanceCalculator {
	private String inputString;
	private List<String> compareStringList;
	/**
	 * 
	 * @param inputString Contains the String to be compared
	 * @param compareStringContains the list of Strings to be compared with inputString
	 */
	public Damerau(String inputString,String compareString)
	{
		/**
		 * This constructor can be used to create an object with only 1 String to compare with.
		 * Furthur, {@code addToCompareList} method can be used to add more Strings for comparison 
		 */
		this.inputString=inputString;
		this.compareStringList.add(compareString);
	}
	
	public Damerau(String inputString,String compareString[])
	{
		this.inputString=inputString;
		compareStringList=Arrays.asList(compareString);
	}
	
	public Damerau(String inputString,List<String> compareList)
	{
		this.inputString=inputString;
		this.compareStringList=compareList;
	}
	
	public String computeNearestWord() throws InvalidTypeException
	{
		if(compareStringList.size()<=1)
			throw new InsufficientParameterException();
		try{
			return compareStringList.get(computeNearestWordIndex(inputString,compareStringList));
		}catch(java.lang.ClassCastException e)
		{
			throw new InvalidTypeException();
		}
	}
	
	public static int computeNearestWordIndex(String inputString, String compareString[])
	{
		int temp,distance=99,index=-1;
		for(int i=0;i<compareString.length;i++)
		{
			temp=computeDistance(inputString,compareString[i]);
			if(temp<=distance)
			{
				distance=temp;
				index=i;
			}
		}
		return index;
	}
	
	public static int computeNearestWordIndex(String inputString, List<String> compareStringList)
	{
		int temp,distance=99,index=-1;
		for(int i=0;i<compareStringList.size();i++)
		{
			temp=computeDistance(inputString,compareStringList.get(i));
			if(temp<=distance)
			{
				distance=temp;
				index=i;
			}
		}
		return index;
	}
	
	public static int[] computeAllDistances(String inputString, List<String> compareStringList)
	{
		if(compareStringList.size()==0)
			throw new InsufficientParameterException();
		int distances[]=new int[compareStringList.size()];
		for(int i=0;i<compareStringList.size();i++)
		{
			distances[i]=computeDistance(inputString,compareStringList.get(i));
		}
		return distances;
	}
	
	public static int[] computeAllDistances(String inputString, String[] compareString)
	{
		int distances[]=new int[compareString.length];
		for(int i=0;i<compareString.length;i++)
		{
			distances[i]=computeDistance(inputString,compareString[i]);
		}
		return distances;
	}
	
	public static int computeDistance(String inputString,String compareString) {
	 try{
		 int d[][]=new int[inputString.length()+1][compareString.length()+1];
			int i,j,cost;
			for(i=0;i<=inputString.length();i++)
				d[i][0]=i;
			for(j=1;j<=compareString.length();j++)
				d[0][j]=j;
			
			for(i=1;i<=inputString.length();i++)
			{
				for(j=1;j<=compareString.length();j++)
				{
					if(inputString.charAt(i-1)==compareString.charAt(j-1))
						cost=0;
					else
						cost=1;
					
					d[i][j]=minimum(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1]+cost);
					if(i>1 && j>1 && inputString.charAt(i-1)==compareString.charAt(j-2) && inputString.charAt(i-2)==compareString.charAt(j-1))
						d[i][j]=minimum(d[i][j],d[i-2][j-2]+cost);
					
				}
			}			
			return d[inputString.length()][compareString.length()]; 
	    }catch(NullPointerException e)
	    {
	    	throw new NullStringObjectException();
	    }
	  
	  }

	@Override
	public void addToCompareList(String compareWord)
	{
		if(compareWord==null || compareWord.length()<=1)
			throw new TooShortStringException();
		compareStringList.add(compareWord);
	}

	@Override
	public void addToCompareList(List<String> compareList) 
	{		
		compareStringList.addAll(compareList);		
	}

	@Override
	public int computeShortestDistance() {
		int temp,distance=99;
		for(int i=0;i<compareStringList.size();i++)
		{
			temp=computeDistance(inputString,compareStringList.get(i));
			if(temp<=distance)
			{
				distance=temp;
			}
		}
		return distance;
	}
	
	public static int minimum(int a,int b,int c)
	{
		if(a<b)
		{
			if(a<c)
				return a;
			else
				return c;
		}
		else if(b<c)
			return b;
		else
			return c;
	}
	public static int minimum(int a,int b)
	{
		if(a<b)
			return a;
		else
			return b;
	}

}
