import java.io.*;
import java.util.*;

public class Main {
    public static class Line implements Comparable<Line>{
        long x, y;

        Line(long x, long y) {
            this.x = x; this.y = y;
        }

        public int compareTo(Line o) {
            if (o.x != x) {
                return Long.compare(x, o.x);
            }
            return Long.compare(y, o.y);
        }
    }

    static int N;
    static Line[] lines;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new Line[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(solution());
    }

    public static long solution() {
        Arrays.sort(lines);
        long sum = 0, now = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            Line target = lines[i];
            if(target.y <= now) continue;
            if (target.x <= now) {
                sum += target.y - now;
                now = target.y;
            } else {
                sum += target.y - target.x;
                now = target.y;
            }
        }

        return sum;
    }
}