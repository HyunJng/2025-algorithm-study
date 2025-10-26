import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static Edge[] edges;
    public static int n, m, scity, ecity;
    public static long[] distance, cityMoney;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        scity = Integer.parseInt(st.nextToken());
        ecity = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new Edge[m];
        distance = new long[n];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, price);
        }

        cityMoney = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.fill(distance, Long.MIN_VALUE);
        distance[scity] = cityMoney[scity];

        solution();

        if(distance[ecity] == Long.MIN_VALUE) {
            bw.append("gg");
        }
        else if(distance[ecity] == Long.MAX_VALUE) {
            bw.append("Gee");
        }
        else {
            bw.append(distance[ecity] + "");
        }
        bw.flush();
        bw.close();
    }

    public static void solution() {
        for(int i = 0; i <= n + 100; i++) {
            for(int j = 0; j < m; j++) {
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].weight;

                if(distance[start] != Long.MIN_VALUE){
                    if(distance[start] == Long.MAX_VALUE) {
                        distance[end] = Long.MAX_VALUE;
                    } else if(distance[end] < distance[start] + cityMoney[end] - price) {
                        distance[end] = distance[start] + cityMoney[end] - price;
                        if(i >= n - 1) distance[end] = Long.MAX_VALUE;
                    }
                }
            }
        }
    }

    public static class Edge {
        int start, end, weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}