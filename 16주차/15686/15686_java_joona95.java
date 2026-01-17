import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int EMPTY = 0;
    private static final int HOUSE = 1;
    private static final int CHICKEN_PLACE = 2;
    private static int n, m;
    private static final List<Point> houses = new ArrayList<>();
    private static final List<Point> chickenPlaces = new ArrayList<>();
    private static boolean[] checked;
    private static int answer = Integer.MAX_VALUE;

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calculateDistance(Point p) {
            return Math.abs(x - p.x) + Math.abs(y - p.y);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == EMPTY) {
                    continue;
                }
                if (type == HOUSE) {
                    houses.add(new Point(i, j));
                }
                if (type == CHICKEN_PLACE) {
                    chickenPlaces.add(new Point(i, j));
                }
            }
        }

        checked = new boolean[chickenPlaces.size()];
        dfs(0, 0);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int depth, int idx) {

        if (depth == m) {
            answer = Math.min(getChickenDistanceInCity(), answer);
            return;
        }

        for (int i = idx; i < chickenPlaces.size(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                dfs(depth + 1, i);
                checked[i] = false;
            }
        }
    }

    public static int getChickenDistanceInCity() {

        int sum = 0;
        for (Point house : houses) {
            sum += getChickenDistance(house);
        }
        return sum;
    }

    public static int getChickenDistance(Point house) {

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chickenPlaces.size(); i++) {
            if (checked[i]) {
                int chickenDistance = house.calculateDistance(chickenPlaces.get(i));
                min = Math.min(chickenDistance, min);
            }
        }
        return min;
    }
}