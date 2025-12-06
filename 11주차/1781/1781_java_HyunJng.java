import java.io.*;
import java.util.*;

public class Main {

    public static class Task {
        int deadline;
        int ramen;

        Task(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        public int deadline() {
            return deadline;
        }
    }

    static int N;
    static List<Task> candidate = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            candidate.add(new Task(temp[0], temp[1]));
        }

        bw.append(String.valueOf(solution()));
        bw.flush();
        bw.close();
    }

    public static int solution() {
        Comparator<Task> comp = Comparator.comparing(Task::deadline);
        Queue<Integer> queue = new PriorityQueue<>();
        candidate.sort(comp);

        for (Task task : candidate) {
            queue.add(task.ramen);
            if(queue.size() > task.deadline) queue.poll();
        }
        return queue.stream().mapToInt(i -> i).sum();
    }
}