package vEBTree;

import java.util.Hashtable;

// van em boas tree structure 
// Utilizes a hash table instead of an array for the cluster
public class vEBTree {
	private Integer min; // min value in the tree
	private Integer max; // max value in the tree
	private int u; // size of the universe
	private vEBTree summary; // summary of clusters
	private Hashtable<Integer, vEBTree> cluster; // cluster of van em boas tree
	
	// calculates log base 2
	private static int log(int x)
	{
	    return (int) (Math.log(x) / Math.log(2) + 1e-10);
	}
	
	// gets the upper sqrt
	private static int upperSqrt(int x) {
		return (int) Math.pow(2, Math.ceil(log(x)/2));
	}
	
	// gets the lower sqrt
	private static int lowerSqrt(int x) {
		return (int) Math.pow(2,  Math.floor(log(x)/2));
	}
	
	private Integer high(int x) {
		return (int) Math.floor(x/lowerSqrt(this.u));
	}
	
	private Integer low(int x) {
		return (int) (x % lowerSqrt(this.u));
	}
	
	private Integer index(int x, int y) {
		return (int) (x * lowerSqrt(this.u) + y);
	}
	
	// Initializes the tree
	public vEBTree(int u) {
		this.min = null; 
		this.max = null;
		this.u = u;
		
		// creates a summary and cluster field if the universe size is larger than 2
		if(u > 2) {
			this.summary = new vEBTree(upperSqrt(u));
			this.cluster = new Hashtable<>();
		}
		
		// otherwise there is no need for a summary or cluster
		else {
			this.summary = null;
			this.cluster = null;
		}
	}
	
	// gets min value of the tree
	public Integer getMin() {
		return this.min;
	}
	
	// gets the max value of the tree
	public Integer getMax() {
		return this.max;
	}
	
	// creates a new vEBTree for the cluster
	private vEBTree createWidget(int x) {
		vEBTree w = new vEBTree(lowerSqrt(this.u));
		w.insert(x);
		return w;
	}
	
	// gets the successor of x in the tree
	public Integer successor(int x) {
		// final layer of the tree
		if(this.u == 2) {
			// checks if the max is 1 else returns null
			if(x == 0 && this.max == 1) {
				return 1;
			}
			else {
				return null;
			}
		}
		
		// if x is less than the min the successor is the min
		else if(this.min != null && x < this.min) {
			return this.min;
		}
		
		// finds the successor of x in the tree
		else {
			vEBTree maxLow = this.cluster.get(high(x));
			
			// checks the current widget for the successor of x
			if(maxLow != null && this.low(x) < maxLow.getMax()) {
				Integer offset = this.cluster.get(high(x)).successor(this.low(x));
				return this.index(this.high(x), offset);
			}
			
			// finds the next widget that could have the successor of x 
			else {
				Integer succCluster = this.summary.successor(this.high(x));
				
				// no successor cluster
				if(succCluster == null) {
					return null;
				}
				else {
					Integer offset = this.cluster.get(succCluster).getMin();
					return this.index(succCluster, offset);
				}
			}
		}
	}
	
	// inserts x into the tree
	public void insert(int x) {
		// if the tree is empty
		if(this.min == null) {
			this.min = x;
			this.max = x;
		}
		
		// if x is less than the current min value
		else if(x < this.min) {
			this.min = x;
		}
		
		// if the size of the universe is larger than 2
		if(this.u > 2) {
			// if the next cluster exists
			if(this.cluster.get(high(x)) == null) {
				this.summary.insert(high(x));
				this.cluster.put(high(x), createWidget(low(x)));
			}
			else {
				this.cluster.get(high(x)).insert(low(x));
			}
		}
		
		// if x is larger than the current max in the tree
		if(x > this.max) {
			this.max = x;
		}
	}
	
	// prints the tree
	public void print() {
		System.out.println("U: " + this.u);
		System.out.println("Min: " + this.min);
		System.out.println("Max: " + this.max);
		System.out.println();
		if(this.summary != null) {
			System.out.println("Summary:");
			this.summary.print();
			for(Integer t : this.cluster.keySet()) {
				System.out.println("Index: " + t);
				this.cluster.get(t).print();
			}
		}
	}
}
