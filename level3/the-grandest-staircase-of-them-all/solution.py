def solution(n):
    # Initialize a 2D array with dimensions (n+1) x (n+1) and fill it with zeros
    arr = [[0] * (n + 1) for _ in range(n + 1)]

    # Base case: there is one way to make 0 using no numbers
    arr[0][0] = 1

    # Dynamic programming to fill the array
    for i in range(1, n + 1):
        for j in range(n + 1):
            arr[i][j] = arr[i - 1][j]  # Exclude the current number
            if j >= i:
                arr[i][j] += arr[i - 1][j - i]  # Include the current number

    # Return the result, subtracting 1 because we don't include the case of using no numbers
    return arr[n][n] - 1

if __name__ == "__main__":
    n1 = 3
    print(solution(n1))

    n2 = 200
    print(solution(n2))
