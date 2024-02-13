import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int stingy(int total_lambs) {
        List<Integer> stingyList = new ArrayList<>();
        stingyList.add(1);
        stingyList.add(1);
        int x = 2, total = 2;
        while (x <= total_lambs) {
            int value = stingyList.get(x - 1) + stingyList.get(x - 2);
            stingyList.add(value);
            total += value;
            if (total > total_lambs)
                break;
            x++;
        }
        return stingyList.size();
    }

    public static int generous(int total_lambs) {
        List<Integer> generousList = new ArrayList<>();
        int x = 0, total = 0;
        while (x <= total_lambs) {
            int current = (int) Math.pow(2, x);
            generousList.add(current);
            total += current;
            if (total > total_lambs)
                break;
            x++;
        }
        return generousList.size();
    }

    public static int solution(int total_lambs) {
        return stingy(total_lambs) - generous(total_lambs);
    }
}
