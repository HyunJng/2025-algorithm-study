// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    // 테스트케이스, 노드, 간선, 가중치
    static int TC, N, M, W;
    static int[] distance;
    static ArrayList<Edge> graph;
    static final int INF = 1_000_000_000;

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static boolean bf(int start) {
        distance[start] = 0;
        // N번 만큼 간선 업데이트 반복
        for (int i = 0; i < N; i++) {
            // 모든 간선에 대해서 업데이트 진행
            for (Edge edge : graph) {
                if (distance[edge.end] > distance[edge.start] + edge.weight) {
                    // N 번에서 업데이트가 이루어진다면 음수 사이클 존재
                    if (i == N - 1) {
                        return true;
                    }
                    distance[edge.end] = distance[edge.start] + edge.weight;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TC; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st2.nextToken());
            M = Integer.parseInt(st2.nextToken());
            W = Integer.parseInt(st2.nextToken());

            distance = new int[N + 1];
            graph = new ArrayList<>();

            for (int j = 0; j < M; j++) {
                StringTokenizer st3 = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st3.nextToken());
                int E = Integer.parseInt(st3.nextToken());
                int T = Integer.parseInt(st3.nextToken());
                graph.add(new Edge(S, E, T));
                graph.add(new Edge(E, S, T));
            }
            for (int j = 0; j < W; j++) {
                StringTokenizer st3 = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st3.nextToken());
                int E = Integer.parseInt(st3.nextToken());
                int T = Integer.parseInt(st3.nextToken());
                graph.add(new Edge(S, E, -T));
            }

            Arrays.fill(distance, INF);
            boolean negativeCycle = bf(1);
            if (negativeCycle) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}