import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Answer {
    
    public static int answer(int[] dimensions, int[] your_position, int[] guard_position, int distance) {
        Map<Double, Double> hitYourself = getAngles(dimensions, your_position, your_position, distance);
        Map<Double, Double> hitGuard = getAngles(dimensions, your_position, guard_position, distance);
        int solutions = 0;
        
        for (Double key : hitGuard.keySet()) {
            if (!hitYourself.containsKey(key) || hitGuard.get(key) < hitYourself.get(key)) {
                solutions++;
            }
        }
        return solutions;
    }
    
    public static double distance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));
    }
    
    public static double angle(int[] p1, int[] p2) {
        return Math.atan2(p2[1] - p1[1], p2[0] - p1[0]);
    }
    
    public static ArrayList<int[]> cartesianProduct(ArrayList<ArrayList<Integer>> series) {
        ArrayList<int[]> points = new ArrayList<>();
        
        for (int i : series.get(0)) {
            for (int j : series.get(1)) {
                points.add(new int[]{i, j});
            }
        }
        return points;
    }
    
    public static ArrayList<Integer> generateSeries(int[] dimensions, int[] your_position, int[] guard_position, int distance, int i) {
        int minimum = your_position[i] - distance;
        int maximum = your_position[i] + distance;
        
        int[] segment = {dimensions[i] - guard_position[i], guard_position[i]};
        ArrayList<Integer> series = new ArrayList<>();
        
        int current = guard_position[i];
        int segmentIndex = 0;
        while (current <= maximum) {
            series.add(current);
            current += 2 * segment[segmentIndex];
            segmentIndex = (segmentIndex + 1) % 2;
        }
        
        current = guard_position[i];
        segmentIndex = 1;
        while (current >= minimum) {
            series.add(current);
            current -= 2 * segment[segmentIndex];
            segmentIndex = (segmentIndex + 1) % 2;
        }
        
        return series;
    }
    
    public static ArrayList<ArrayList<Integer>> generateAllSeries(int[] dimensions, int[] your_position, int[] guard_position, int distance) {
        ArrayList<ArrayList<Integer>> series = new ArrayList<>();
        
        for (int i = 0; i < 2; i++) {
            series.add(generateSeries(dimensions, your_position, guard_position, distance, i));
        }
        
        return series;
    }
    
    public static Map<Double, Double> getAngles(int[] dimensions, int[] your_position, int[] guard_position, int distance) {
        Map<Double, Double> polarMap = new HashMap<>();
        
        for(int[] point : cartesianProduct(generateAllSeries(dimensions, your_position, guard_position, distance))) {
            double r = distance(point, your_position);
            double angle = angle(point, your_position);
            
            if ((point[0] != your_position[0] || point[1] != your_position[1]) && (r <= distance)) {
                polarMap.put(angle, polarMap.containsKey(angle) ? Math.min(polarMap.get(angle), r) : r);
            }
        }
        
        return polarMap;
    }
}
