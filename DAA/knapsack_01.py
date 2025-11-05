# Assignment No.7
# Solve the 0-1 Knapsack problem using Dynamic Programming

def knapsack_01(values, weights, capacity, n):
    # Create DP table
    dp = [[0 for _ in range(capacity + 1)] for _ in range(n + 1)]

    # Build table dp[][] in bottom-up manner
    for i in range(1, n + 1):
        for w in range(1, capacity + 1):
            if weights[i - 1] <= w:
                dp[i][w] = max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w])
            else:
                dp[i][w] = dp[i - 1][w]

    # Store the result of Knapsack
    max_value = dp[n][capacity]

    # Traceback to find which items were included
    w = capacity
    included_items = []
    for i in range(n, 0, -1):
        if dp[i][w] != dp[i - 1][w]:
            included_items.append(i)
            w -= weights[i - 1]

    return max_value, included_items[::-1]  # reverse to get original order

values = []
weights = []

n = int(input("Enter number of items: "))
for i in range(n):
    val = int(input(f"Enter value of item {i + 1}: "))
    wt = int(input(f"Enter weight of item {i + 1}: "))
    values.append(val)
    weights.append(wt)

capacity = int(input("Enter knapsack capacity: "))

# Solve using Dynamic Programming
max_value, items_included = knapsack_01(values, weights, capacity, n)

print("\nItems included:")
print("--------------------------------------------------")
print("Item\tValue\tWeight")
print("--------------------------------------------------")

total_weight = 0
for i in items_included:
    print(f"{i}\t{values[i - 1]}\t{weights[i - 1]}")
    total_weight += weights[i - 1]

print("--------------------------------------------------")
print(f"Total Weight Used: {total_weight} / {capacity}")
print(f"Maximum value in Knapsack = {max_value}")

"""
INPUT-OUTPUT FORM:-

Enter number of items: 3
Enter value of item 1: 60
Enter weight of item 1: 10
Enter value of item 2: 100
Enter weight of item 2: 20
Enter value of item 3: 120
Enter weight of item 3: 30
Enter knapsack capacity: 50

Items included:
--------------------------------------------------
Item    Value   Weight
--------------------------------------------------
2       100     20
3       120     30
--------------------------------------------------
Total Weight Used: 50 / 50
Maximum value in Knapsack = 220

"""