import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int computer = Integer.parseInt(br.readLine());
        visited = new boolean[computer+1];
        int count = Integer.parseInt(br.readLine());

        for (int i = 1; i <= computer; i++) {
            graph.put(i, new ArrayList<>());
        }

        while (count-- > 0) {
            addEdge(br.readLine());
        }

        dfs(1);

        long result = infectedCount();
        System.out.println(result-1);
    }

    public static int infectedCount() {
        int result = 0;
        for (boolean b : visited) {
            if (b) result++;
        }
        return result;
    }

    public static void addEdge(String line){
        String[] split = line.split(" ");
        int num = Integer.parseInt(split[0]);
        int value = Integer.parseInt(split[1]);
        graph.get(num).add(value);
        graph.get(value).add(num);
    }

    public static void dfs(int num) {
        visited[num] = true;
        for(Integer neighbor : graph.get(num)) {
            if(!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}