import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * This program produces all solutions to the n-Queens problem using DFS with backtracking. 
 * 
 * Before finding solutions, the estimated number of nodes that will be checked 
 * before finding all solutions, or the number of nodes that will be in the pruned state space tree, 
 * is obtained using the Monte Carlo technique. 
 *
 * 
 * 
 * INPUT: 
 * The number of queens to find placement for which corresponds to number of rows and columns 
 * Entering 4 results in finding all solutions to placing 4 queens on a 4x4 chessboard that are not in the same diagonal,row, or column. 
 *
 *
 * OUTPUT: 
 * 	The solutions, the total number of solutions, the Monte Carlo estimate for number of nodes that will be checked, 
 *	and the actual number of nodes checked while searching is printed. 
 *
 * 	Each solution consists of an array of integers where each value represents the column placement for the queen in that
 * 	row number (which corresponds to the index). 
 *		
 *		For example, the solution [2 4 1 3] for 4-queens problem represents: 
 * 	
 *		Queen 1 at index 0 is in row 1, column 2 
 *		Queen 2 at index 1 is in row 2, column 4
 *		Queen 3 at index 2 is in row 3, column 1
 *		Queen 4 at index 3 is in row 4, column 3
 *
 *
 *
 *
 *
 * @author Hope Fetrow
 * @date June 2021
 *
 *
 */
public class Main {
	static int col[] = null;
	// for counts
	static int total_promising;
	static int total_checked;
	static int fact;
	static int total_solutions;
	// for estimate method
	static int[] promChildren;
	static int n;

	
	public static void main(String[] args) {

		// get user input for number of queens
		Scanner input = new Scanner(System.in);
		System.out.print("Number of queens (< 1 to quit): ");
		n = input.nextInt();
		System.out.println();

        	// while user input is an int greater than 0
		while (n > 0) { 
		    System.out.println("\n\nNumber of queens: " + n + "\n");
			total_promising = 0;
			total_checked = 0;
			total_solutions = 0;
			// for finding and storing one solution
			int[] partialSol = new int[n];
			
			// function call to get estimate
			long estimated = estimate(n);
	
			try {
				// function call to solve
				solve(partialSol, 0);
			} catch (Exception e) {
				System.out.println("Error while solving problem");
				e.printStackTrace();
			}

			// printing results
			System.out.println("\nNumber of solutions: " + total_solutions);
			System.out.println("Estimated nodes checked: " + estimated);
			System.out.println("Actual nodes checked: " + total_checked);
		
			// prompt for user input in console
			System.out.print("\n\nEnter number of queens (between 1 and 13): ");
			
			// grab user input
			n = input.nextInt();
		}
		input.close();
	}

	/**
	 * Finds a placement for a queen, if no queen found, nothing happens.
	 *
	 * @param x the current partial solution Its length is the problem size.
	 * @param k the next position to be assigned.
	 */
	public static void solve(int[] x, int k) {
		try {
		    // if all four queens have been placed
			if (k >= x.length) { 
				    // print the solution
					printSolution(x);
			
				// increment solution counter
				total_solutions++;
			} else
				// see if queen in row (i+1) can be placed for each of the n columns
				for (int i = 1; i <= x.length; i++) {
					x[k] = i;
					// increment nodes checked during backtracking counter
					total_checked++;
					// if node is promising
					if (promising(x, k)) {
						// add to promising nodes count
						total_promising++;
						// then go down in tree
						solve(x, k + 1);
					}
				}
		} catch (Exception e) {
			System.out.println("Error with input, enter a number that is between 1 and 13: ");
		}
	}

	/**
	 * Determines if node does not lead to a solution based on from conflicts with
	 * earlier values. Once a node is decided to be a dead end, the method returns
	 * false or true if node is found to be promising.
	 *
	 * @param x the current partial solution
	 * @param i the position to be tested for conflict with earlier values
	 */
	public static boolean promising(int[] x, int i) {
		// check if any queen threatens the queen in the ith row
		for (int k = 0; k < i; k++) {
			// there is already a queen in the col or diagonal 
			if (x[i] == x[k] || Math.abs(x[i] - x[k]) == i - k) { 
				return false;
			}
		}
		return true;
	}

	/**
	 * Uses the monte carlo technique to estimate the number of nodes that will be
	 * in the pruned state space tree, or the number of nodes that will be checked
	 * before finding all possible solutions. It uses the same promise method that
	 * the backtracking algorithm does.
	 *
	 * @param n number of queens
	 * @return estimated number of nodes in the pruned state space tree
	 */
	public static long estimate(int n) {
		// indices for col[]
		int i = 0, j = 1;
		// for calculating estimate
		long m = 1, mprod = 1, numNodes = 1;
		// to work with current col
		col = new int[n + 1];
		// for storing promising children (length of factor value for n)
		promChildren = new int[n];
		while (m != 0 && i != n) {
		    // product of promising children
			mprod = mprod * m;
			// n is number of children of t
			numNodes = numNodes + mprod * n; 
			i++;
			m = 0;
			// determine promising children.
			for (j = 1; j < n; j++) {
			    // set pointer to current row
				col[i] = j; 
				// if current row is promising
				if (promising(col, i) == true) { 
				    // increment number of promising children
					m++; 
					// add to set of promising children
					promChildren[(int) m] = j;
				}
			}
			// if there are promising children
			if (m != 0) { 
				// randomly selection from promising children
				int rnd = new Random().nextInt(promChildren.length);
				j = promChildren[rnd];
				col[i] = j;
			}
		}
		return numNodes;
	}

	/**
	 * Prints a solution to the n-queens problem
	 * 
	 * @param x a solution consisting of the queens column placement, where each index represents the corresponding row/queen for that row (row # = index #)
	 */
	public static void printSolution(int[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.print(" " + x[i] + " ");
		}
		System.out.println();
	}
}
