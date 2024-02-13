class Solution {
    public static int solution(int n) {
        // Initialize a 2D array with dimensions (n+1) x (n+1) and fill it with zeros
        int[][] arr = new int[n + 1][n + 1];

        // Base case: there is one way to make 0 using no numbers
        arr[0][0] = 1;

        // Dynamic programming to fill the array
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                arr[i][j] = arr[i - 1][j];  // Exclude the current number
                if (j >= i) {
                    arr[i][j] += arr[i - 1][j - i];  // Include the current number
                }
            }
        }

        // Return the result, subtracting 1 because we don't include the case of using no numbers
        return arr[n][n] - 1;
    }

    public static void main(String[] args) {
        int n1 = 3;
        System.out.println(solution(n1));

        int n2 = 200;
        System.out.println(solution(n2));
    }
}
