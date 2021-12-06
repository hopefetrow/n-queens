# N-queens
A program written for my CS 4310 - Design and Analysis of Algorithms course. 

### Problem
Given an n x n chessboard, how many different ways can you place n queens, such that no two queens are in the same row, column, and diagonal. 
  
### Description  
Backtracking algorithm is used used to reduce upper bound on n-Queens problem. The Monte Carlo Estimate algorithm is also implemented to test if backtracking was a good choice for the n-queens problem. 

### Input 
Number of queens or 0 to quit 

### Output
Each solution consists of an array of integers where each integer represents the column choice for the queen in the row corresponding to array index in the solution.

# What I learned 
How to implement backtracking with a promising method and how it can greatly reduce the search time for DFS in some cases, in this case from O(n^n) to O(n!). How the Monte Carlos Estimate can determine if backtracking is a good choice for an algorithm. 
  
