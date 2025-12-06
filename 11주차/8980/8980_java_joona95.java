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

    public static class Box implements Comparable<Box> {
        int from;
        int to;
        int count;

        public Box(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }

        @Override
        public int compareTo(Box o) {
            if (this.to == o.to) {
                return Integer.compare(this.from, o.from);
            }
            return Integer.compare(this.to, o.to);
        }
    }

    public static int n, c, m;
    public static int[] trucks;
    public static List<Box> boxes = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int send = Integer.parseInt(st.nextToken());
            int receive = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            boxes.add(new Box(send, receive, count));
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {
        int answer = 0;

        trucks = new int[n + 1];
        Collections.sort(boxes);
        for (Box box : boxes) {
            int from = box.from;
            int to = box.to;
            int count = box.count;

            int canLoad = count;
            for (int i = from; i < to; i++) {
                canLoad = Math.min(canLoad, c - trucks[i]);
            }

            if (canLoad <= 0) {
                continue;
            }

            for (int i = box.from; i < box.to; i++) {
                trucks[i] += canLoad;
            }
            answer += canLoad;
        }

        return answer;
    }
}