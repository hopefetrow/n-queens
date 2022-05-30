# N-queens
Given an n x n chessboard, how many different ways can you place n queens, such that no two queens are in the same row,       column, and diagonal.

<h2> Purpose </h2>
Final Project for CS 4310 - Design and Analysis of Algorithms

<h2> Objective </h2>
Solve with DFS and backtracking. Make a timing graph 'Time vs. n' and document observations. Implement the Monte Carlo estimate() algorithm for estimating the number of nodes in the tree. Keep track of the actual number of nodes generated when backtracking and compare to the estimated number.  

<h2> Description </h2>
DFS is used with backtracking to reduce upper bound on n-Queens problem. An estimate algorithm is also implemented to estimate efficiency of backtracking by estimating number of nodes that will be checked before finding all possible solutions.
<br>
<h3><strong> Input: </strong></h3>
Number of queens or 0 to quit 
<h3><strong> Output: </strong></h3>
The solution(s), the total number of solutions, the actual number of nodes generated, and the estimated number of nodes that would be generated. Each 		solution consists of an array of integers where each integer represents the column choice for the queen in the row corresponding to array index in the 		solution and the results from the Monte Carlo estimate. For example, <br><br>

The solution [2 4 1 3] for the 4-queens problem is saying:<br>
<ul>
	<li>Queen 1 at index 0 is in row 1, column 2 of the chess board. </li>
	<li>Queen 2 at index 1 is in row 2, column 4. </li>
	<li>Queen 3 at index 2 is in row 3, column 1. </li>
	<li>Queen 4 at index 3 is in row 4, column 3. </li>
</ul>
<h2> Installation </h2>
Copy and paste the contents of nqueens.java into editor of a java IDE or online java compiler (https://www.onlinegdb.com/online_java_compiler). 

<h2> Developers </h2>
Hope Fetrow 

<h2> What I learned </h2>
How to implement backtracking with a promising method and how it can greatly reduce the search time for DFS in some cases, in this case from O(n<sup>n</sup>) to O(n!). How the Monte Carlos Estimate can determine if backtracking is a good choice for an algorithm. 
  
  
