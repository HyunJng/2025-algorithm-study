import java.io.*;
import java.util.*;

public class Main {

    public static class Delivery {
        int from, to;
        int box;

        public Delivery(int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }

        public int from() {
            return from;
        }

        public int to() {
            return to;
        }
    }

    static int N, C;
    static List<Delivery> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int time = Integer.parseInt(br.readLine());
        while (time-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Delivery(a, b, c));
        }

        System.out.println(solution());
    }

    public static int solution() {
        Comparator<Delivery> cm = Comparator.comparing(Delivery::to)
                .thenComparing(Delivery::from, Collections.reverseOrder());
        list.sort(cm);

        int result = 0;
        int[] weights = new int[N + 1];
        for (Delivery d : list) {
            int min = Integer.MAX_VALUE;;
            for (int i = d.from; i < d.to; i++) {
                int temp = (C < d.box + weights[i]) ? C - weights[i] : d.box;
                min = Math.min(min, temp);
            }
            if(min == 0) continue;

            for (int i = d.from; i < d.to; i++) {
                weights[i] += min;
            }
            result += min;
        }
        return result;
    }
}