import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
@contrib Hope Fetrow
*/
public class Main {
	static int col[] = null;
	// for counts
	static int prom;
	static int backChecked;
	static int est;
	static int fact;
	static int solutions;
	// for estimate method
	static int[] promChildren;
	static int n;

	/**
	 * This program produces all solutions to the n-Queens problem with DFS and backtracking. 
	 * Before finding solutions, the estimated number of nodes that will be checked 
	 * backtracking is found using the Monte Carlo technique. The estimated number of 
	 * nodes is the number of nodes that will be in the pruned state space tree created 
	 * implicitly during backtracking or the number of nodes that are checked before all
	 * solutions are found. 
	 *
	 *
	 *
	 * OUTPUT: 
	 *
	 * The number of solutions along with the Monte Carlo estimate, the Actual number of nodes 
	 * checked while searching, and the number of promising nodes found is printed. 
	 *
	 * If n<10, the solutions are printed while searching. 	Each solution consists of an 
	 * array of integers where each integer represents the column choice for the queen in the
	 * row corresponding to array index in the solution.
	 *
	 * For example, the solution [2 4 1 3] for 4-queens problem where columns and rows are
	 * numbered 1 to n:
	 * 	Queen 1 at index 0 is in row 1, column 2. 
	 *	Queen 2 at index 1 is in row 2, column 4. 
	 *	Queen 3 at index 2 is in row 3, column 1. 
	 *	Queen 4 at index 3 is in row 4, column 3.
	 *
	 *
	 *
	 */
	public static void main(String[] args) {

		// get user input for number of queens
		Scanner input = new Scanner(System.in);
		System.out.print("Number of queens (< 1 to quit): ");
		n = input.nextInt();
		System.out.println();

        	// while user input is an int greater than 0
		while (n > 0) { 
			prom = 0;
			backChecked = 0;
			solutions = 0;
			// for finding and storing one solution
			int[] partialSol = new int[n];
			
			// function call to get estimate
			long est = estimate(n);
	
			try {
				// function call to solve
				solve(partialSol, 0, fr);
			} catch (Exception e) {
				System.out.println("Error while solving problem");
				e.printStackTrace();
			}

			// printing to console
			System.out.println("\n\n[Number of solutions]: " + solutions);
			System.out.println("\n-----------------------------------------------------\n");
			System.out.format("%-8s%-18s%-18s%-18s\n", "", "Estimated number", "Number of ", "Number of ");
			System.out.format("%-8s%-18s%-18s%-18s\n", "", "promising nodes", "nodes checked", "promising");
			System.out.format("%-8s%-18s%-18s%-18s\n", "n", "(monte carlo)", "(backtracking)", "found");
			System.out.println("\n-----------------------------------------------------\n");
			System.out.format("%-8d%-18d%-18d%-18d\n", n, est, backChecked, prom);

			// print prompt for input
			System.out.print("\n\nNumber of Queens: ");
			// grab user input
			n = input.nextInt();
			System.out.println("\n\n");
		}
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
				if (n < 10) {
					// print the solution
					printSolution(x);
				} else {
					// write solution
					for (int i = 0; i < x.length; i++) {
						fr.write(" " + x[i] + " ");
					}
					fr.write("");
				}
				// increment solution counter
				solutions++;
			} else
				// see if queen in row (i+1) can be placed for each of the n columns
				for (int i = 1; i <= x.length; i++) {
					x[k] = i;
					// increment nodes checked during backtracking counter
					backChecked++;
					// if node is promising
					if (promising(x, k)) {
						// add to promising nodes count
						prom++;
						// then go down in tree
						solve(x, k + 1);
					}
				}
		} catch (Exception e) {
			System.out.println("Error writing to file ");
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
	 */
	public static void printSolution(int[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.print(" " + x[i] + " ");
		}
		System.out.println();
	}
}
