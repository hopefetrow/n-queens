# N-queens
Given an n x n chessboard, how many different ways can you place n queens, such that no two queens are in the same row,       column, and diagonal.

### Developers 
Hope Fetrow 

### Purpose
Final Project for CS 4310 - Design and Analysis of Algorithms

### Objective
Solve with a backtracking algorithm that uses a promising function. Make a timing graph Time vs. n. Include graph and observations in your report. Implement the Monte Carlo estimate() algorithm for estimating the number of nodes in the tree. Keep track of the actual number of nodes generated when backtracking and compare to the estimated number.  

### Description 
The promising function prevents the continuation down a dead end path by only continuing traversal down path if the node is promising. If a node isn’t promising, then it isn’t travelled to and a different path is chosen, where the node is promising. This drastically reduces the number of nodes that are checked while finding all possible solutions. 

The estimate() function uses the Monte Carlo method and can be used to determine if backtracking would be an efficient choice for solving a problem. In this implementation, estimate() returns the estimated number of nodes that would be checked to find all possible solutions. 

### Input 
Number of queens or 0 to quit 

### Output
The solution(s), the total number of solutions, the actual number of nodes generated, and the estimated number of nodes that would be generated. 
   
Each solution consists of an array of integers where each integer represents the column choice for the queen in the row corresponding to array index in the solution and the results from the Monte Carlo estimate. 
    
   Solution explanation:
   
   For solution [2 4 1 3] for the 4-queens problem:
	Queen 1 at index 0 is in row 1, column 2 of the chess board. 
	Queen 2 at index 1 is in row 2, column 4. 
	Queen 3 at index 2 is in row 3, column 1. 
	Queen 4 at index 3 is in row 4, column 3.

### Installation 
Copy and paste the contents of nqueens.java into editor of a java IDE or online java compiler (https://www.onlinegdb.com/online_java_compiler). 

### What I learned 
How to implement backtracking with a promising method and how it can greatly reduce the search time for DFS in some cases, in this case from O(n^n) to O(n!). How the Monte Carlos Estimate can determine if backtracking is a good choice for an algorithm. 
  
  
