import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static void solution(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        // 데이터 파싱
        int computerCount = sc.nextInt();
        int lineCount = sc.nextInt();
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        while (sc.hasNext()) {
            int point1 = sc.nextInt();
            int point2 = sc.nextInt();
            map.putIfAbsent(point1, new HashSet<>());
            map.putIfAbsent(point2, new HashSet<>());
            map.get(point1).add(point2);
            map.get(point2).add(point1);
        }
        boolean[] visited = new boolean[computerCount + 1];

        // DFS 로 탐색
        Stack<Integer> stack = new Stack<>();
        // 시작 노드는 1로 고정
        stack.push(1);
        visited[1] = true;
        int answer = 0;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            // 현재 노드와 연결된 노드들 탐색
            for (int nextNode : map.getOrDefault(current, new HashSet<>())) {
                // 방문하지 않은 노드라면 방문 처리 후 스택에 추가
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    stack.push(nextNode);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws FileNotFoundException {
        solution(args);
    }
}