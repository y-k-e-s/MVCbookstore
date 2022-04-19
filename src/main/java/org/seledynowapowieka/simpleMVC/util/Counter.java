package org.seledynowapowieka.simpleMVC.util;

public class Counter {
	private int counterValue;
	
	public Counter(){
		counterValue = 0;
	}
	
	public int incrementAndGet() {
		return ++counterValue;
	}
	
	public int get() {
		return counterValue;
	}
	
	public void reset() {
		counterValue = 0;
	}
	
	 public String toString()
	    {
	        return ""+counterValue;
	    }
	
}
