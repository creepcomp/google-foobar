import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Solution {
    static List<int[]> convertToPath(int[] perm) {
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{0, perm[0] + 1});
        for (int i = 0; i < perm.length - 1; i++) {
            path.add(new int[]{perm[i] + 1, perm[i + 1] + 1});
        }
        path.add(new int[]{perm[perm.length - 1] + 1, -1});
        return path;
    }

    public static int[] answer(int[][] time, int timeLimit) {
        int rows = time.length;
        int bunnies = rows - 2;

        // Floyd-Warshall algorithm for finding shortest paths
        for (int k = 0; k < rows; k++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < rows; j++) {
                    if (time[i][j] > time[i][k] + time[k][j]) {
                        time[i][j] = time[i][k] + time[k][j];
                    }
                }
            }
        }

        // Check for negative cycles
        for (int r = 0; r < rows; r++) {
            if (time[r][r] < 0) {
                int[] result = new int[bunnies];
                for (int i = 0; i < bunnies; i++) {
                    result[i] = i;
                }
                return result;
            }
        }

        // Try different permutations to find the maximum number of bunnies
        for (int i = bunnies; i >= 0; i--) {
            for (int[] perm : generatePermutations(bunnies, i)) {
                int total_time = 0;
                for (int[] path : convertToPath(perm)) {
                    total_time += time[path[0]][path[1]];
                }
                if (total_time <= timeLimit) {
                    Arrays.sort(perm);
                    for (int j = 0; j < perm.length; j++) {
                        perm[j]--;
                    }
                    return perm;
                }
            }
        }
        return null;
    }

    static List<int[]> generatePermutations(int n, int k) {
        List<int[]> permutations = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        generatePermutationsHelper(permutations, nums, 0, k);
        return permutations;
    }

    static void generatePermutationsHelper(List<int[]> permutations, int[] nums, int start, int k) {
        if (start == k) {
            permutations.add(Arrays.copyOf(nums, k));
        } else {
            for (int i = start; i < nums.length; i++) {
                swap(nums, i, start);
                generatePermutationsHelper(permutations, nums, start + 1, k);
                swap(nums, i, start);
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
