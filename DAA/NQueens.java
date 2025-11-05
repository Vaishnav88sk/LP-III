//Assignment No.4
//Implement 8-Queens problem using Backtracking

import java.util.*;

public class NQueens {
    static int n;
    static int[] board;
    static int solutionCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        n = sc.nextInt();
        board = new int[n];
        solve(0);
        
        if (solutionCount == 0)
            System.out.println("No solution exists for n = " + n);
        else
            System.out.println("\nTotal " + solutionCount + " solution(s) exist for n = " + n);
        
        sc.close();
    }

    static void solve(int col) {
        if (col == n) {
            solutionCount++;
            printBoard();
            return;
        }
        for (int row = 0; row < n; row++) {
            if (isSafe(row, col)) {
                board[col] = row;
                solve(col + 1); // don't stop after first solution
            }
        }
    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[i] == row || Math.abs(board[i] - row) == Math.abs(i - col))
                return false;
        }
        return true;
    }

    static void printBoard() {
        System.out.println("\nSolution " + solutionCount + ":");
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++)
                System.out.print((board[c] == r ? "Q " : "x "));
            System.out.println();
        }
    }
}

// Input-Output format

/*
 * n = 2
 * No solution
 * 
 * n = 3
 * No solution
 * 
 * n = 4
 * One of the possible solutions:
 * x Q x x
 * x x x Q
 * Q x x x
 * x x Q x
 * 
 * n = 5
 * One of the possible solutions:
 * Q x x x x
 * x x Q x x
 * x x x x Q
 * x Q x x x
 * x x x Q x
 * 
 * n = 6
 * One of the possible solutions:
 * x x Q x x x
 * x x x x Q x
 * Q x x x x x
 * x x x Q x x
 * x Q x x x x
 * x x x x x Q

 */
