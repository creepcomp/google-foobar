import java.util.Arrays;

class Elevator implements Comparable<Elevator>{
    int major;
    int minor;
    int revision;
    String str;

    public Elevator(String elevator) {
        String[] div = elevator.split("\\.");
        this.str = elevator;
        major = Integer.parseInt(div[0]);
        minor = div.length > 1 ? Integer.parseInt(div[1]) : -1;
        revision = div.length > 2 ? Integer.parseInt(div[2]) : -1;
    }

    @Override
    public int compareTo(Elevator o) {
        if (this.major != o.major) return Integer.compare(this.major, o.major);
        if (this.minor != o.minor) return Integer.compare(this.minor, o.minor);
        return Integer.compare(this.revision, o.revision);
    }
}

class Solution {
    public static String[] solution(String[] l) {
        int len = l.length;
        Elevator[] els = new Elevator[len];
        for (int i = 0; i < len; i++) {
            els[i] = new Elevator(l[i]);
        }
        Arrays.sort(els);
        String[] finals = new String[len];
        for (int j = 0; j < len; j++) {
            finals[j] = els[j].str;
        }
        return finals;
    }
}
