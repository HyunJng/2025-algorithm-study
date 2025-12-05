import java.io.*;
import java.util.*;

public class {
    static class Box {
        int x;      
        int y;      
        int box;   

        public Box(int x, int y, int box) {
            this.x = x;
            this.y = y;
            this.box = box;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        int c = Integer.parseInt(st.nextToken()); 

        int m = Integer.parseInt(br.readLine()); 

        Box[] arr = new Box[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());
            arr[i] = new Box(x, y, box);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.y != b.y) return a.y - b.y;
            return a.x - b.x;
        });

        int[] load = new int[n + 1];
        int answer = 0;

        for (Box b : arr) {
            int maxAdd = b.box;
            
            int cur = 0;
            for(int i = b.x; i<b.y; i++){
                cur = Math.max(cur, load[i]); // 지나가는 구간 중에서 가장 크게 담겨있는 값
            }
            
            int canAdd = Math.min(maxAdd, c - cur); 

            if(canAdd>0){
                answer+= canAdd;
                for(int i = b.x; i<b.y; i++){
                    load[i]+= canAdd;
                }
                
            }

        }

        System.out.println(answer);
    }
}
