package 14주차.2110;

import java.util.*;
import java.io.*;

public class 2110_java_Kyounglin{
    static int n;
    static int[] arr;
    static int c;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[n-1] - arr[0];
        int answer = 0;

        while(lt<=rt){
            int mid = (lt+rt) /2;
            if(canPlace(mid)){
                answer = mid;
                lt = mid+1;
            }else{
                rt = mid-1;
            }
        }
        System.out.println(answer);
        
    }
    static boolean canPlace(int mid){
        int cnt = 1;
        int last = arr[0];
        for(int i = 1; i<n; i++){
            if(arr[i]-last>=mid){
                last = arr[i];
                cnt++;
            }
        }
        
        return cnt>=c;
    }

}