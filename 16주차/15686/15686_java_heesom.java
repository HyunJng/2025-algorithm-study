import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<int[]> chickens = new ArrayList<>();
    static ArrayList<int[]> houses = new ArrayList<>();
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) houses.add(new int[]{i, j});
                else if (val == 2) chickens.add(new int[]{i, j});
            }
        }

        selected = new boolean[chickens.size()];
        backtrack(0, 0);
        System.out.println(answer);
    }

    static void backtrack(int count, int idx) {
        if (count == M) {
            int total = 0;
            for (int[] house : houses) {
                int dist = Integer.MAX_VALUE;
                for (int i = 0; i < chickens.size(); i++) {
                    if (selected[i]) {
                        int[] chicken = chickens.get(i);
                        dist = Math.min(dist, Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
                    }
                }
                total += dist;
            }
            answer = Math.min(answer, total);
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            selected[i] = true;
            backtrack(count + 1, i + 1);
            selected[i] = false;
        }
    }
}