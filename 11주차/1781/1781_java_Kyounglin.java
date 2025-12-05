import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<int[]> arr = new ArrayList<>();

        StringTokenizer st;
        int maxDeadLine = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.add(new int[]{a,b});  //문제, 데드라인, 라면
            maxDeadLine = Math.max(maxDeadLine, a);
        }
        arr.sort((a, b)-> a[0]-b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int[] array: arr){
            pq.add(array[1]);
            if(pq.size()>array[0]){
                pq.poll();
            }
        }
        int answer = 0;
        while(!pq.isEmpty()){
            answer += pq.poll();
        }
        
        System.out.println(answer);

        
    }
}
