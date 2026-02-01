package 18주차.11779;


import java.io.*;
import java.util.*;

class City implements Comparable<City>{
    int b;
    int c;
    public City(int b, int c){
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(City ob){
        return this.c - ob.c;
    }
}
public class 11779_java_Kyounglin  {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] price = new int[n+1];
        Arrays.fill(price, Integer.MAX_VALUE);

        List<City>[] arr = new ArrayList[n+1];
        for(int i = 1; i<n+1; i++){
            arr[i] = new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();

        StringTokenizer st;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(new City(b, c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        PriorityQueue<City> q = new PriorityQueue<>();
        q.add(new City(start, 0));

        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        price[start] = 0;

        List<Integer> list = new ArrayList<>();
        list.add(start);
        map.put(start, list);


        while(!q.isEmpty()){
            City cur = q.poll();
            int curCity = cur.b;
            int p = cur.c;

            
            if(curCity==end) break;

            List<Integer> curPath = map.get(curCity);

            for(City next: arr[curCity]){
                if(price[next.b]>price[curCity]+next.c){
                    price[next.b] = price[curCity]+next.c;       
                    List<Integer> newPath = new ArrayList<>(curPath);
                    newPath.add(next.b);
                    map.put(next.b, newPath);
                    q.add(new City(next.b, price[next.b]));
                }
            }
        }

        System.out.println(price[end]);
        System.out.println(map.get(end).size());
        for(int num: map.get(end)){
            System.out.print(num+" ");
        }

    }

}
