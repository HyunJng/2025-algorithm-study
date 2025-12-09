import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Problem implements Comparable<Problem> {
        int number;
        int deadLine;
        int cupRamenCnt;

        public Problem(int number, int deadLine, int cupRamenCnt) {
            this.number = number;
            this.deadLine = deadLine;
            this.cupRamenCnt = cupRamenCnt;
        }

        @Override
        public int compareTo(Problem o) {
            return Integer.compare(this.deadLine, o.deadLine);
        }
    }

    public static final List<Problem> problems = new ArrayList<>();
    public static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cupRamenCnt = Integer.parseInt(st.nextToken());

            problems.add(new Problem(i, deadLine, cupRamenCnt));
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static long solution() {

        Collections.sort(problems);

        PriorityQueue<Integer> selected = new PriorityQueue<>();

        long sum = 0;
        for (Problem problem : problems) {

            selected.add(problem.cupRamenCnt);
            sum += problem.cupRamenCnt;

            if (!selected.isEmpty() && selected.size() > problem.deadLine) {
                sum -= selected.poll();
            }
        }

        return sum;
    }
}