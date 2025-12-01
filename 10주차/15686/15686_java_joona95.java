import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static final int EMPTY = 0;
    public static final int HOUSE = 1;
    public static final int CHICKEN_PLACE = 2;
    public static final List<Point> houses = new ArrayList<>();
    public static final List<Point> chickenPlaces = new ArrayList<>();
    public static final List<Point> checkedChickenPlaces = new ArrayList<>();
    public static int answer = Integer.MAX_VALUE;
    public static int[][] arr;
    public static int n, m;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == HOUSE) {
                    houses.add(new Point(i, j));
                }
                if (arr[i][j] == CHICKEN_PLACE) {
                    chickenPlaces.add(new Point(i, j));
                }
            }
        }

        markChickenPlaces(0, 0);

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void markChickenPlaces(int level, int idx) {

        if (level == m) {
            answer = Math.min(answer, calculateCityChickenDistance());
            return;
        }

        for (int i = idx; i < chickenPlaces.size(); i++) {
            Point chickenPlace = chickenPlaces.get(i);
            checkedChickenPlaces.add(chickenPlace);
            markChickenPlaces(level + 1, i + 1);
            checkedChickenPlaces.remove(chickenPlace);
        }

    }

    private static int calculateCityChickenDistance() {

        int totalDistance = 0;
        for (Point house : houses) {
            int minDistance = Integer.MAX_VALUE;
            for (Point checkedChickenPlace : checkedChickenPlaces) {
                int distance = Math.abs(house.x - checkedChickenPlace.x) + Math.abs(house.y - checkedChickenPlace.y);
                minDistance = Math.min(minDistance, distance);
            }
            totalDistance += minDistance;
        }
        return totalDistance;
    }
}