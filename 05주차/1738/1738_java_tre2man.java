// package org.example;

import java.io.*;
import java.util.*;

/**
 * 양의 사이클이 존재하면서 답으로 추정되는 경로 내 사이클이 존재한다면, 답을 도출할 수 없다.
 *
 * 1. 양수일 경우 높은 점수를 주는 벨만 포드 알고리즘으로 최대 경로 탐색을 진행한다.
 * 2. 양의 사이클이 생길 경우 추후 탐색을 위해 저장한다.
 * 3. n 노드에서 bfs 탐색 시 2에서 저장한 노드에 도달 가능하다면, 답을 도출할 수 없다.
 * 4. 1에서 경로 탐색 시 저장한 prev 경로 배열을 역으로 추적하며 답을 도출한다.
 */
public class Main {
    static int n, m;
    static int[] distance, prev;
    static ArrayList<Integer> cycleNodes;
    static boolean[] visited;
    static Queue<Integer> q;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<ArrayList<Node>> revGraph;
    static int MIN = -1_000_000_000;

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    /**
     * 답 도출 가능 여부 반환
     */
    static boolean bfs(int start) {
        q = new LinkedList<>();
        q.add(start);
        visited = new boolean[n + 1];
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (cycleNodes.contains(now)) {
                return false;
            }
            for (Node node : revGraph.get(now)) {
                if (!visited[node.end]) {
                    visited[node.end] = true;
                    q.add(node.end);
                }
            }
        }
        return true;
    }

    /**
     * 벨만 포드 알고리즘을 활용하여 탐색하고, 답을 구할 수 있는지 확인
     * 양의 사이클이 존재하는 동시에, 해당 사이클의 노드에서 n 노드까지 도달이 가능하다면 답을 구할 수 없다.
     */
    static void bf(int start) {
        distance[start] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Node node : graph.get(j)) {
                    if (distance[node.end] < distance[j] + node.weight) {
                        distance[node.end] = distance[j] + node.weight;
                        prev[node.end] = j;
                    }
                }
            }
        }

        // 변경사항이 일어난다면 양의 사이클에 속한 노드이다.
        for (int i = 1; i <= n; i++) {
            for (Node node : graph.get(i)) {
                if (distance[node.end] < distance[i] + node.weight) {
                    cycleNodes.add(node.end);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cycleNodes = new ArrayList<>();
        graph = new ArrayList<>();
        revGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            int w = Integer.parseInt(st2.nextToken());
            graph.get(u).add(new Node(v, w));
            // 추후 경로 내 양의 순환 사이클이 있는 경우를 판별하기 위함
            revGraph.get(v).add(new Node(u, w));
        }
        distance = new int[n + 1];
        prev = new int[n + 1];
        prev[0] = -1;
        Arrays.fill(distance, MIN);

        bf(1);

        if (!bfs(n)) {
            System.out.println(-1);
        } else {
            List<Integer> path = new ArrayList<>();
            int k = n;
            while (true) {
                path.add(k);
                if (k == 1)
                    break;
                k = prev[k];
            }
            Collections.reverse(path);
            StringBuilder sb = new StringBuilder();
            for (int v : path) {
                sb.append(v).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}