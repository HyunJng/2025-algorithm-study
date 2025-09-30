import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public int bfs() {

        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        while(!queue.isEmpty()){
            int currentNode =  queue.poll();
            for (int node : graph.get(currentNode)){
                if(!visited[node]){
                    queue.offer(node);
                    visited[node] = true;
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Main main = new Main();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        bw.write(String.valueOf(main.bfs()));
        bw.flush();

        br.close();
        bw.close();
    }
}