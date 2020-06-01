package vEBTree;

import ptolemy.plot.*;
import ptolemy.plot.PlotApplication;

public class vEBTreeDriver {
	public static void main(String[] args) {
		Plot myPlot = new Plot(); // plot for the times
		myPlot.setTitle("Running times by size of inuts");
		myPlot.setXLabel("Number of Operations");
		myPlot.setYLabel("Running Time (NS)");
		
		vEBTree tree = new vEBTree(32); // the van em boas tree
		long[] insertTimes = new long[5]; // holds the times for each insertion time
		long[] successorTimes = new long[5]; // holds the successor times for each sample
		int[] operations = {1, 2, 4, 8, 16}; // the number of opeations being performed in each sample
		
		long startTime = System.nanoTime();
		tree.insert(2);
		long stopTime = System.nanoTime();
		insertTimes[0] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.insert(3);
		tree.insert(4);
		stopTime = System.nanoTime();
		insertTimes[1] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.insert(8);
		stopTime = System.nanoTime();
		insertTimes[2] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.insert(9);
		tree.insert(10);
		tree.insert(11);
		tree.insert(12);
		tree.insert(13);
		tree.insert(14);
		tree.insert(15);
		tree.insert(16);
		stopTime = System.nanoTime();
		insertTimes[3] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.insert(17);
		tree.insert(18);
		tree.insert(19);
		tree.insert(20);
		tree.insert(21);
		tree.insert(22);
		tree.insert(23);
		tree.insert(24);
		tree.insert(25);
		tree.insert(26);
		tree.insert(27);
		tree.insert(28);
		tree.insert(29);
		tree.insert(30);
		tree.insert(31);
		tree.insert(32);
		stopTime = System.nanoTime();
		insertTimes[4] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.successor(2);
		stopTime = System.nanoTime();
		successorTimes[0] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.successor(3);
		tree.successor(4);
		stopTime = System.nanoTime();
		successorTimes[1] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.successor(5);
		tree.successor(6);
		tree.successor(7);
		tree.successor(8);
		stopTime = System.nanoTime();
		successorTimes[2] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.successor(9);
		tree.successor(10);
		tree.successor(11);
		tree.successor(12);
		tree.successor(13);
		tree.successor(14);
		tree.successor(15);
		tree.successor(16);
		stopTime = System.nanoTime();
		successorTimes[3] = stopTime - startTime;
		
		startTime = System.nanoTime();
		tree.successor(17);
		tree.successor(18);
		tree.successor(19);
		tree.successor(20);
		tree.successor(21);
		tree.successor(22);
		tree.successor(23);
		tree.successor(24);
		tree.successor(25);
		tree.successor(26);
		tree.successor(27);
		tree.successor(28);
		tree.successor(29);
		tree.successor(30);
		tree.successor(31);
		tree.successor(32);
		stopTime = System.nanoTime();
		successorTimes[4] = stopTime - startTime;
		
		//plots the insertion times
		for(int i=0; i<5; i++) {
			myPlot.addPoint(0, operations[i], insertTimes[i], true);
		}
		
		//plots the successor times
		for(int i=0; i<5; i++) {
			myPlot.addPoint(1, operations[i], successorTimes[i], true);
		}
		
		myPlot.addLegend(0, "Insertion Times");
		myPlot.addLegend(1, "Successor Times");
		
		// renders the plot
		PlotApplication app = new PlotApplication(myPlot);
	}
}
