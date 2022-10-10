package com.highpeak.task;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("input.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner sc=new Scanner(fis);
	    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
	    sc.nextLine(); sc.nextLine(); sc.nextLine();

	    ArrayList<Item> goodies_items = new ArrayList<Item>();
	    
	    while(sc.hasNextLine())  
	    {
	      String current[] = sc.nextLine().split(": ");
	      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
	    }
	    sc.close();

	    Collections.sort(goodies_items, new Comparator<Item>(){
	      public int compare(Item a, Item b) { 
	        return a.price - b.price; 
	      } 
	    });

	    int min_diff = goodies_items.get(goodies_items.size()-1).price;
	    int min_index = 0;
	    for(int i=0;i<goodies_items.size()-number_of_employees+1;i++) {
	      int diff = goodies_items.get(number_of_employees+i-1).price-goodies_items.get(i).price;
             
	      if(diff<=min_diff) {
	          min_diff = diff;
	          min_index = i;
	        }
	      }
	    FileWriter fw = null;
		try {
			fw = new FileWriter("output.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			fw.write("The goodies selected for distribution are:\n\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    for(int i=min_index;i<min_index + number_of_employees; i++) {
	      try {
			fw.write(goodies_items.get(i).toString() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    try {
			fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	}

