package 5430;

import java.io.*;
import java.util.*;

public class 5430_java_Kyounglin {
    static boolean reversed;
    static Deque<Integer> dq;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++){
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int[] arr;
            if(n ==0 ) arr = new int[0];
            else arr = Arrays.stream(s.substring(1,s.length()-1).split(",")).mapToInt(Integer::parseInt).toArray();
            dq = new ArrayDeque<>();
            reversed = false;
            answer = new StringBuilder();
            for(int num: arr){
                dq.add(num);
            }
            for(char c: p.toCharArray()){
                if(c=='R') R();
                else D();
                if(answer.toString().equals("error")) break;
            }
            if(!answer.toString().equals("error")){
                answer.append('[');
                int idx = 0;
                while(!dq.isEmpty()){
                    int cur;
                    if(reversed) cur = dq.pollLast();
                    else cur = dq.pollFirst();

                    if(idx!=0) answer.append(',');
                    answer.append(cur);
                    idx++;
                }
                System.out.println(answer.append(']').toString());
            }else{
                System.out.println(answer.toString());
            }
        }
        
    }
    static void R(){
        reversed = !reversed;
    }
    static void D(){
        if(!dq.isEmpty()){
            if(!reversed){
                dq.pollFirst();
            }else{
                dq.pollLast();
            }
        }else{
            answer = new StringBuilder("error");
            return;
        }
        
    }
}