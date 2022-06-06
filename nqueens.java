import java.util.Random;
import java.util.Scanner;

/**
 * ---------------------------------------------------------------------------
 * DESCRIPTION:
 * ---------------------------------------------------------------------------
 * Problem: Given an n x n chessboard, how many different ways can you place n
 * queens such that no two queens are in the same row, column, or diagonal.
 * 
 * This program produces all solutions to the n-Queens problem using DFS with
 * backtracking, where once a path is determined to lead to a non-solution, or
 * is non-promising, the search discontinues down that path and backtracks up
 * the tree to try a different path.
 * 
 * Before solving, the estimated number of nodes that will be checked before
 * finding all possible solutions is obtained, which is used to estimate the
 * efficiency of backtracking for the n-queens problem.
 * ---------------------------------------------------------------------------
 * INPUT:
 * ---------------------------------------------------------------------------
 * The number of queens to place or 0 to quit.
 * 
 * This corresponds to number of rows and columns in the chessboard. (i.e. 4
 * queens results in a 4x4 chessboard)
 * ---------------------------------------------------------------------------
 * OUTPUT:
 * ---------------------------------------------------------------------------
 * Solutions, total number of solutions, estimate for number of nodes that will
 * be checked, actual number of nodes checked while searching
 * 
 * 
 * Each solution consists of an array of integers where each value represents
 * the column placement for the queen in that row number (which corresponds to
 * the index).
 * 
 * The solution [2 4 1 3] for 4-queens problem represents:
 * 
 * - Queen 1 at index 0 is in row 1 column 2
 * 
 * - Queen 2 at index 1 is in row 2 column 4
 * 
 * - Queen 3 at index 2 is in row 3 column 1
 * 
 * - Queen 4 at index 3 is in row 4, column 3
 *
 * 
 * @author Hope Fetrow
 * @since June 2021
 * 
 */
public class Main {
	static int col[] = null;
	// for counts
	static int checkedCount;
	static int solutionCount;
	// for estimate
	static int[] est;
	static int n;

	public static void main(String[] args) {
        	int[] temp;
		long estimate;
		
		// get user input for number of queens from console
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nNumber of queens (0 to quit): ");
		n = input.nextInt();
		System.out.println();

		// while user input is a number greater than 0
		while (n > 0) {
			// print number of queens that was entered by user
			System.out.println("\n\nNumber of queens: " + n + "\n");

			// start counters at 0
			checkedCount = 0;
			solutionCount = 0;
			// to build a solution
			temp = new int[n];

			// function call to get estimate
			estimate = estimate(n);

			// function call to solve
			try {
				solve(temp, 0);
			} catch (Exception e) {
				System.out.println("Error while solving problem");
				e.printStackTrace();
			}
			// print results
			System.out.println("\nNumber of solutions: " + solutionCount);
			System.out.println("Estimated nodes checked: " + estimate);
			System.out.println("Actual nodes checked: " + checkedCount);
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
	 * @param x the current partial solution
	 * @param k the row position of node
	 */
	public static void solve(int[] x, int k) {
		try {
			// if all four queens have been placed
			if (k >= x.length) {
				// print the solution
				printSolution(x);
				// increment solution counter
				solutionCount++;
			} else
				// for this row, check each column position to see if queen can be placed
				for (int i = 1; i <= x.length; i++) {
					x[k] = i;
					// increment counter for the total number of nodes checked while searching
					checkedCount++;
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
	 * earlier values, which means there is a queen already placed in that row, col,
	 * or diagonal. If a node is decided to be a dead end, the method returns false,
	 * otherwise it returns true.
	 *
	 * @param x the current partial solution
	 * @param i the position to be tested for conflict with earlier values
	 * @return false if node conflicts with earlier values, true otherwise
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
		int i = 0, j = 1, rnd = 0;

		/* estimate variables need to start at 1 or else mprod would always be 0 */

		// number of promising children
		long m = 1;
		// product of mprod and m (part of function to find estimate)
		long mprod = 1;
		// number of nodes in the estimation of state space tree size
		long nodeCount = 1;
		// create current col
		col = new int[n + 1];
		// for storing promising children (length of factor value for n)
		est = new int[n];

		/* ---------------------- Monte Carlo function ----------------------- */
		while (m != 0 && i != n) {
			// get product of promising children and m
			mprod = m * m;
			// function to get estimate (n is number of children of current node)
			nodeCount = nodeCount + mprod * n;
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
					est[(int) m] = j;
				}
			}
			// check if there are promising children
			if (nodeCount != 0) {
				// randomly select from promising children
				rnd = new Random().nextInt(est.length);
				j = est[rnd];
				col[i] = j;
			}
		}
		// return size of estimated tree
		return nodeCount;
	}

	/**
	 * Prints a solution to the n-queens problem
	 * 
	 * @param x a single solution to print
	 */
	public static void printSolution(int[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.print(" " + x[i] + " ");
		}
		System.out.println();
	}
}
