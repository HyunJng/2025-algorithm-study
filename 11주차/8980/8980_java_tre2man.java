import java.io.*;
import java.util.*;

public class Main {

    static class Delivery {
        int from;
        int to;
        int boxes;

        Delivery(int from, int to, int boxes) {
            this.from = from;
            this.to = to;
            this.boxes = boxes;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        Delivery[] arr = new Delivery[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int boxes = Integer.parseInt(st.nextToken());
            arr[i] = new Delivery(from, to, boxes);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.to == b.to) {
                return a.from - b.from;
            }
            return a.to - b.to;
        });

        int[] remain = new int[N + 1];
        Arrays.fill(remain, C);

        int answer = 0;

        for (Delivery d : arr) {
            int from = d.from;
            int to = d.to;
            int boxes = d.boxes;

            int minRemain = Integer.MAX_VALUE;
            for (int i = from; i < to; i++) {
                minRemain = Math.min(minRemain, remain[i]);
            }

            int load = Math.min(minRemain, boxes);
            if (load <= 0) {
                continue;
            }

            for (int i = from; i < to; i++) {
                remain[i] -= load;
            }

            answer += load;
        }

        System.out.println(answer);
    }
}