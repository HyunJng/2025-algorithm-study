import java.util.*;
import java.io.*;

public class 2470_java_Kyounglin{

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int lt = 0;
        int rt = n-1;

        long num1 = 0;
        long num2 = 0;
        long min = 2000000000;
        while(lt<rt){
            long sum = arr[lt]+arr[rt];
            if(Math.abs(sum)<min){
                min = Math.abs(sum);
                num1 = arr[lt];
                num2 = arr[rt];
            }
            if(sum==0){
                break;
            }else if(sum<0){
                lt++;
            }else if(sum>0){
                rt--;
            }
        }
        System.out.println(num1+" "+num2);
    }

}