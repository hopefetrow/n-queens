import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * -------------------------------------------------------------------------
 * DESCRIPTION: 
 * -------------------------------------------------------------------------
 * Problem:
 * Given an n x n chessboard, how many different ways can you place n queens, 
 * such that no two queens are in the same row, column, and diagonal.
 * 
 * This program produces all solutions to the n-Queens problem using DFS with
 * backtracking, where once a path is determined to lead to a non-solution, or
 * is non-promising, the search discontinues down that path and backtracks up
 * the tree to try a different path.
 * 
 * Before solving, the estimated number of nodes that will be checked before
 * finding all possible solutions is obtained, which is used to estimate the
 * efficiency of backtracking for the n-queens problem.
 * -------------------------------------------------------------------------
 * INPUT: 
 * -------------------------------------------------------------------------
 * The number of queens to place or 0 to quit. 
 * 
 * 	| This corresponds to number of rows and columns in the chessboard. 
 *	| Example: 4 queens --> 4x4 chessboard
 * -------------------------------------------------------------------------
 * OUTPUT: 
 * -------------------------------------------------------------------------
 * Solutions, total number of solutions, estimate for number of nodes
 * that will be checked, actual number of nodes checked while searching
 *	
 *  
 *	| Each solution consists of an array of integers where each value represents
 *	| the column placement for the queen in that
 * 	| row number (which corresponds to the index).
 * 	|
 *	| For example, the solution [2 4 1 3] for 4-queens problem represents:
 * 	|  	Queen 1 at index 0 is in row 1, column 2
 * 	|  	Queen 2 at index 1 is in row 2, column 4
 * 	|	Queen 3 at index 2 is in row 3, column 1
 * 	|	Queen 4 at index 3 is in row 4, column 3
 *
 * 
 * @author Hope Fetrow
 * @date June 2021
 * 
 */
public class Main {
	static int col[] = null;
	// for counts
	static int total_checked;
	static int total_solutions;
	// for estimate
	static int[] promising_children;
	static int n;

	public static void main(String[] args) {

		// get user input for number of queens from console
		Scanner input = new Scanner(System.in);
		System.out.print("Number of queens (< 1 to quit): ");
		n = input.nextInt();
		System.out.println();

		// while user input is an int greater than 0
		while (n > 0) {
			// print number of queens entered by user
			System.out.println("\n\nNumber of queens: " + n + "\n");
			// initialize/reset counters to 0
			total_checked = 0;
			total_solutions = 0;
			// to store our solution in temporarily
			int[] temp = new int[n];

			// function call to get estimate
			long estimated = estimate(n);

			try {
				// function call to solve
				solve(temp, 0);
			} catch (Exception e) {
				System.out.println("Error while solving problem");
				e.printStackTrace();
			}

			// print results
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
				// for this row, check each column position to see if queen can be placed
				for (int i = 1; i <= x.length; i++) {
					x[k] = i;
					// increment counter for the total number of nodes checked while searching
					total_checked++;
					// check if current position is promising
					if (promising(x, k)) {
						// if promising, then go down in tree (next row)
						solve(x, k + 1);
					}
				}
		} catch (Exception e) {
			System.out.println("Error with input, enter a number that is between 1 and 13: ");
		}
	}

	/**
	 * Determines if node does not lead to a solution based on from conflicts with
	 * earlier values. If a node is decided to be a dead end, the method returns
	 * false, otherwise it returns true.
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
	 * Uses the Monte Carlo technique to estimate the number of nodes that will be
	 * in the pruned state space tree, or the number of nodes that will be checked
	 * before finding all possible solutions. This algorithm follows a random path 
	 * in a state space tree and produces an estimate of the number of nodes in the 
	 * tree. 
	 *
	 * @param n number of queens
	 * @return estimated number of nodes in the pruned state space tree
	 */
	public static long estimate(int n) {
		// indices for col[]
		int i = 0, j = 1;
		// for calculating estimate
		long m = 1, mprod = 1, numNodes = 1;
		// create current col
		col = new int[n + 1];
		// for storing promising children (length of factor value for n)
		promising_children = new int[n];

		/* ---------------------- Monte Carlo technique ----------------------- */
		while (m != 0 && i != n) {
			// product of promising children
			mprod = mprod * m;
			// n is number of children of t
			numNodes = numNodes + mprod * n;
			i++;
			m = 0;
			// determine promising children
			for (j = 1; j < n; j++) {
				// set pointer to current row
				col[i] = j;
				// if current row is promising
				if (promising(col, i) == true) {
					// increment number of promising children
					m++;
					// add to set of promising children
					promising_children[(int) m] = j;
				}
			}
			// if there are promising children
			if (m != 0) {
				// randomly selection from promising children
				int rnd = new Random().nextInt(promising_children.length);
				j = promising_children[rnd];
				col[i] = j;
			}
		}
		return numNodes;
	}

	/**
	 * Prints a solution to the n-queens problem
	 */
	public static void printSolution(int[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.print(" " + x[i] + " ");
		}
		System.out.println();
	}
}
