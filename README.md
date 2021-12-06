# N-queens
A program written for my CS 4310 - Design and Analysis of Algorithms course. 

### Problem
Given an n x n chessboard, how many different ways can you place n queens, such that no two queens are in the same row, column, and diagonal. 
  
### Description  
Backtracking algorithm is used used to reduce upper bound on n-Queens problem. The Monte Carlo Estimate algorithm is also implemented. 

### Output
An array of length n where the indices correspond to the row number and the values correspond to the column number the queen for that row is placed in. 

# What I learned 
How to implement backtracking with a promising method and how it can greatly reduce the search time for DFS in some cases, in this case from O(n^n) to O(n!). How the Monte Carlos Estimate can determine if backtracking is a good choice for an algorithm. 
  
