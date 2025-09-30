import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.valueOf(br.readLine());
        int couple = Integer.valueOf(br.readLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        IntStream.rangeClosed(1, number).forEach(i -> graph.put(i, new ArrayList<>()));

        for (int i = 0; i < couple; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Integer a = Integer.valueOf(st.nextToken());
            Integer b = Integer.valueOf(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        System.out.println(solution(number, couple, graph));
    }

    public static int solution(int number, int couple, Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        visited.add(1);

        dfs(1, visited, graph);
        return visited.size() - 1;
    }

    public static void dfs(int start, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        for(int neighbor : graph.get(start)) {
            if (visited.contains(neighbor)) continue;
            visited.add(neighbor);
            dfs(neighbor, visited, graph);
        }
    }
}