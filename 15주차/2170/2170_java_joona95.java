import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }

        bw.write(String.valueOf(solution(points)));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution(List<Point> points) {

        int answer = 0;

        Collections.sort(points);

        int length = points.size();
        int min = points.get(0).x;
        int max = points.get(0).y;
        for (int i = 1; i < length; i++) {
            Point point = points.get(i);
            if (point.x > max) {
                answer += (max - min);
                min = point.x;
                max = point.y;
            } else {
                max = Math.max (max, point.y);
            }
        }
        answer += (max - min);

        return answer;
    }
}