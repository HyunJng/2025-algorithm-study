// package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int n;

    static class Problem {
        int d;
        int ramen;

        Problem(int d, int ramen) {
            this.d = d;
            this.ramen = ramen;
        }
    }

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Problem[] arr = new Problem[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            arr[i] = new Problem(d, ramen);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.d == b.d)
                return b.ramen - a.ramen;
            return a.d - b.d;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(arr[i].ramen);
            if (pq.size() > arr[i].d) {
                pq.poll();
            }
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}