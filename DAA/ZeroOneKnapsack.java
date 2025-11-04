//Assignment No.4
//To solve a 0-1 Knapsack problem using dynamic programming or branch and bound strategy.

import java.util.*;

public class ZeroOneKnapsack {

    public static int knapSack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Dynamic Programming table fill
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        // Backtracking to find items included
        int res = dp[n][W];
        int w = W;
        int totalWeight = 0;

        System.out.println("\nItems included:");
        System.out.println("--------------------------------------------------");
        System.out.println("Item\tValue\tWeight");
        System.out.println("--------------------------------------------------");

        for (int i = n; i > 0 && res > 0; i--) {
            if (res != dp[i - 1][w]) {
                System.out.printf("%d\t%d\t%d%n", i, val[i - 1], wt[i - 1]);
                totalWeight += wt[i - 1];
                res -= val[i - 1];
                w -= wt[i - 1];
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Total Weight Used: " + totalWeight + " / " + W);
        return dp[n][W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int val[] = new int[n];
        int wt[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value of item " + (i + 1) + ": ");
            val[i] = sc.nextInt();

            System.out.print("Enter weight of item " + (i + 1) + ": ");
            wt[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        int maxValue = knapSack(W, wt, val, n);
        System.out.println("Maximum value in Knapsack = " + maxValue);

        sc.close();
    }
}

// Input-Output format

/*
 * Enter number of items: 3
 * Enter value of item 1: 60
 * Enter weight of item 1: 10
 * Enter value of item 2: 100
 * Enter weight of item 2: 20
 * Enter value of item 3: 120
 * Enter weight of item 3: 30
 * Enter knapsack capacity: 50
 * 
 * Items included:
 * --------------------------------------------------
 * Item Value Weight
 * --------------------------------------------------
 * 3 120 30
 * 2 100 20
 * --------------------------------------------------
 * Total Weight Used: 50 / 50
 * Maximum value in Knapsack = 220
 */
