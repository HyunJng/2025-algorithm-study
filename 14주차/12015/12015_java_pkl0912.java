import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

      
        int[] lis = new int[n];
        int size = 0;
        for(int x: arr){
            int idx = Arrays.binarySearch(lis, 0, size, x );
            if(idx<0){
                idx = -(idx+1);
            }
            lis[idx] = x;
            if(idx==size){
                size++;
            }
        }
        System.out.println(size);


        
    }

}