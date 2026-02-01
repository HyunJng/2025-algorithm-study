package 18주차.14938;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int node;
    int time;
    public Node(int node, int time){
        this.node = node;
        this.time = time;
    }

    @Override
    public int compareTo(Node ob){
        return this.time - ob.time;
    }
}
public class 14938_java_Kyounglin {
    static int n;
    static int m;
    static int r;
    static int[] node;
    static ArrayList<Node>[] list;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        node = new int[n+1];
        list = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<n+1; i++){
            node[i] = Integer.parseInt(st.nextToken());
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, l));
            list[b].add(new Node(a, l));
        }

        for(int i = 1; i<n+1; i++){
            dfs(i);
        }
        System.out.println(max);
    

    }

    static void dfs(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[n+1];
        q.add(new Node(start, 0));
        int[] dist = new int[n+1];
        for(int i = 1; i<n+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int curNode = cur.node;
            if(!visited[curNode]){
                visited[curNode] = true;
                for(Node next: list[curNode]){
                    if(dist[next.node]> dist[curNode]+next.time){
                        dist[next.node] = dist[curNode]+next.time;
                        q.add(new Node(next.node, dist[next.node]));
                    }
                }
            }
        }
        int sum = 0;
        for(int i =1; i<n+1; i++){
            if(dist[i]<= m){
                sum+= node[i];
            }
        }
        max = Math.max(max, sum);

    }

}
