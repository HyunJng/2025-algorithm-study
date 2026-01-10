package 2170;

import java.io.*;
import java.util.*;

class Line implements Comparable<Line>{
    int x;
    int y;
    
    public Line(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Line ob){
        if(this.x == ob.x) return this.y - ob.y;
        return this.x - ob.x;
    }
}
public class 2170_java_Kyounglin {
   
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Line> pq = new PriorityQueue<>();
        

        StringTokenizer st;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Line(x, y));
        }
        Line first = pq.poll();

        int start = first.x;
        int end = first.y;
        int answer = 0;

        int min = 0;
        while(!pq.isEmpty()){
            Line cur = pq.poll();

            if(cur.x > end ){ //시작이 전의 끝 보다 나중이면
                answer+= (end-start);
                start = cur.x;
                end = cur.y;
            }else{
                if(cur.y > end){
                    end =cur.y;
                }
            }
        }
        answer+= (end-start);
        System.out.println(answer);
    }  
}
