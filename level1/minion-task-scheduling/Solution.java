import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static int[] answer(int[] data, int n) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // Count occurrences of each number
        for (int num : data) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        // Filter out numbers that occur more than n times
        List<Integer> filteredList = new ArrayList<>();
        for (int num : data) {
            if (countMap.get(num) <= n) {
                filteredList.add(num);
            }
        }
        
        // Convert List<Integer> to int[]
        int[] result = new int[filteredList.size()];
        for (int i = 0; i < filteredList.size(); i++) {
            result[i] = filteredList.get(i);
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] testCase1 = {1, 2, 3};
        int[] result1 = answer(testCase1, 0);
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: 

        int[] testCase2 = {1, 2, 2, 3, 3, 3, 4, 5, 5};
        int[] result2 = answer(testCase2, 1);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: 1 4

        int[] testCase3 = {1, 2, 3};
        int[] result3 = answer(testCase3, 6);
        for (int num : result3) {
            System.out.print(num + " ");
        }
        System.out.println();  // Output: 1 2 3
    }
}
