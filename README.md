# N-queens
    Given an n x n chessboard, how many different ways can you place n queens, such that no two queens are in the same row,       column, and diagonal.
#### Developers 
    Hope Fetrow 

#### Purpose
    Final Project for CS 4310 - Design and Analysis of Algorithms

#### Objective
    Solve with a backtracking algorithm that uses a promising function. Make a timing graph Time vs. n. Include graph and observations in your report. Implement the Estimate() algorithm for estimating the number of nodes in the tree. Keep track of the actual number of nodes generated when backtracking and compare to the estimated number.  
 
#### Description 

#### Input 
    Number of queens or 0 to quit 

#### Output
    Each solution consists of an array of integers where each integer represents the column choice for the queen in the row  corresponding to array index in the solution and the results from the Monte Carlo estimate. 
    
    For example:
		 
		The solution [2 4 1 3] for 4-queens problem where columns and rows are numbered 1 to n:
	  
		Queen 1 at index 0 is in row 1, column 2 of the chess board. Queen 2 at index 1 is in row 2, column 4. Queen 3 at index 2 is in row 3, column 1. Queen 4 at index 3 is in row 4, column 3.

#### Installation 
    1. Copy and paste the contents of nqueens.java into any online java compiler, such as https://www.jdoodle.com/online-java-compiler/. 

#### What I learned 
    How to implement backtracking with a promising method and how it can greatly reduce the search time for DFS in some cases, in this case from O(n^n) to O(n!). How the Monte Carlos Estimate can determine if backtracking is a good choice for an algorithm. 
  
  
