/*============================================
  class OrderedArrayList
  Wrapper class for ArrayList.
  Imposes the restriction that stored items 
  must remain sorted in ascending order
  ============================================*/

//ArrayList's implementation is in the java.util package
import java.util.ArrayList;


public class OrderedArrayList {

    // instance of class ArrayList, holding objects of type Comparable 
    // (ie, instances of a class that implements interface Comparable)
    private ArrayList<Comparable> _data;


    // default constructor initializes instance variable _data
    public OrderedArrayList() {
	_data = new ArrayList<Comparable>();
    }


    public String toString() { 
	return _data.toString(); 
    }


    public Comparable remove( int index ) { 
	return _data.remove(index); 
    }


    public int size() { 
	return _data.size();
    }

    
    public Comparable get( int index ) { 
	return _data.get(index); 
    }


    // addLinear takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a linear search to find appropriate index
    public void addLinear( Comparable newVal ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {
	    if ( newVal.compareTo( _data.get(p) ) < 0 ) { //newVal < oal[p]
		_data.add( p, newVal );
		return; //Q:why not break?
	    }
	}
	_data.add( newVal ); //newVal > every item in oal, so add to end
    }


    // addBinary takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a binary search to find appropriate index
    public void addBinary( Comparable newVal ) { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while ( lo <= hi ) { //running until target is found or bounds cross

	    med = (lo + hi) / 2;
	    int x = _data.get(med).compareTo( newVal );
	        
	    if ( x == 0 ) { //equal value found, insert here
		_data.add( med, newVal );
		return;
	    }
	    else if ( x > 0 ) //newVal < med, so look at lower half of data
		hi = med - 1;
	    else //newVal > med, so look at upper half of data
		lo = med + 1;
	}
	// If you make it this far, newVal was not in the ArrayList.
	// So insert at lo. Q: How do you know lo is correct insertion index?
	_data.add( lo, newVal );
    }	


    // determine whether element present in data structure using linear search
    // return index of occurrence or -1 if not found
    public int findLin( Comparable target ) 
    { 
	for (int p=0; p<_data.size(); p++) {
	    if (target.compareTo(_data.get(p)) == 0) {
		return p;
	    }
	}
	return -1;
    }


    // determine whether element present in data structure using binary search
    // return index of occurrence or -1 if not found
    public int findBin( Comparable target ) 
    { 
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while (lo <= hi) {
	    med = (lo+hi)/2;
	    int x= _data.get(med).compareTo(target);

	    if (x==0) {
		return med;
	    }
	    else if (x>0) {
		hi = med - 1;
	    }
	    else {
		lo = med + 1;
	    }

	}

	return -1;
    }


    // main method solely for testing purposes
    public static void main( String[] args ) 
    {
	OrderedArrayList Franz = new OrderedArrayList();

	System.out.println("\nValues to add via addLinear() calls:");

	// testing linear search
	for( int i = 0; i < 15; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addLinear( valToAdd );
	}

	System.out.println("\nafter population via addLinear() calls:");
	System.out.println( Franz );
	System.out.println();

	Franz = new OrderedArrayList();

	System.out.println("\nValues to add via addBinary() calls:");

				
	// testing binary search
	for( int i = 0; i < 15; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    System.out.println( valToAdd );
	    Franz.addBinary( valToAdd );
	}

	System.out.println("\nafter population via addBinary() calls:");
	System.out.println( Franz );
	System.out.println("\n\n\n");

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   Method for timing: 
	   currentTimeMillis() returns the time from epoch in milliseconds as a long. If we record
	   this value at the start and end of the tests and then take the difference between those 
	   times, we will have the amount of time in milliseconds that the tests each took.
	   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	OrderedArrayList Jan = new OrderedArrayList();
	for(int i = 0; i < 100000; i++){
	    Jan._data.add(i);
	}
	System.out.println("Testing find function timing");
	System.out.println("Testing findLin for 100000 values, 100000 times...");
	
	long initialLin = System.currentTimeMillis();
	for (int i=0; i<100000; i++) {
	    int valToFind = (int)( 100000 * Math.random() );
	    Jan.findLin(valToFind);
	}
	long finalLin = System.currentTimeMillis();
	
	System.out.println("Testing findBin for 100000 values, 100000 times...");
	
	long initialBin = System.currentTimeMillis();
	for (int i=0; i<100000; i++) {
	    int valToFind = (int)( 100000 * Math.random() );
	    Jan.findBin(valToFind);
	}
	long finalBin = System.currentTimeMillis();

	System.out.println("findLin: " + (finalLin - initialLin) + " ms");
	System.out.println("findBin: " + (finalBin - initialBin) + " ms");
	/*
	  Test Cases:
	  - look for last element
	  - look for first element
	  - look for middle element
	  - look for (same) random element
	 */
    }

}//end class OrderedArrayList
 
