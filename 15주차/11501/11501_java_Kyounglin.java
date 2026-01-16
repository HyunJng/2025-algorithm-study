package 11501;
import java.io.*;
import java.util.*;

public class 11501_java_Kyounglin {
   
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            
            int answer = 0;
            int max = arr[n-1];
            for(int j = n-2; j>=0; j--){
                
                if(arr[j]>max){
                    max = arr[j];
                }else{
                    answer+= (max-arr[j]);
                }
            }
            
            System.out.println(answer);
        }
    }  
}
