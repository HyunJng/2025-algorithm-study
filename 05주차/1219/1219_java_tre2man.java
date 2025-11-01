// package org.example;

import java.io.*;
import java.util.*;

/**
 * 음수가 포함된 그래프이므로 벨만-포드 알고리즘을 사용한다.
 * 다음 노드로 이동할 때 cost 와 각 노드에 도착할 때 주는 가중치를 고려한다.
 * gg 는 도착 노드에 연결되지 않을 경우 출력
 * Gee 가 되는 조건은 다음과 같다
 * 1. 양의 순환 사이클이 존재하는 경우
 * 2. 해당 사이클에서 도착지점까지 갈 수 있는 경우
 */
public class Main {
    static int n, m, start, end, u, v, w;
    static boolean[] visited;
    static Queue<Integer> q;
    static long[] earn, distance;
    static ArrayList<Integer> cycleNodes;
    static ArrayList<ArrayList<Node>> graph;
    // 적당히 작은 값
    static final long MIN = Long.MIN_VALUE / 2;

    static class Node {
        int end;
        long weight;

        public Node(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    /**
     * 해당 노드부터 목적지 노드까지 도달 가능한지 여부를 반환
     */
    static boolean bfs(int start) {
        visited = new boolean[n + 1];
        q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == end) {
                return true;
            }
            for (Node node : graph.get(now)) {
                if (!visited[node.end]) {
                    visited[node.end] = true;
                    q.add(node.end);
                }

            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        earn = new long[n];
        distance = new long[n];
        cycleNodes = new ArrayList<>();
        graph = new ArrayList<>();

        Arrays.fill(distance, MIN);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, -w));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            earn[i] = Integer.parseInt(st.nextToken());
        }

        distance[start] = earn[start];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                for (Node node : graph.get(j)) {
                    if (distance[j] != MIN && distance[node.end] < distance[j] + node.weight + earn[node.end]) {
                        distance[node.end] = distance[j] + node.weight + earn[node.end];
                    }
                }
            }
        }

        // 사이클 노드 찾기
        for (int i = 0; i < n; i++) {
            for (Node node : graph.get(i)) {
                if (distance[i] != MIN && distance[node.end] < distance[i] + node.weight + earn[node.end]) {
                    cycleNodes.add(node.end);
                }
            }
        }
        // 해당 사이클 노드에서 목적지까지 도달하는지 확인
        for (Integer cycleNode : cycleNodes) {
            boolean reached = bfs(cycleNode);
            if (reached) {
                System.out.println("Gee");
                return;
            }
        }

        if (distance[end] == MIN) {
            System.out.println("gg");
            return;
        }
        System.out.println(distance[end]);
    }
}