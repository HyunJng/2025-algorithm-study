import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());

            int[] days = new int[n];
            int[][] descDays = new int[n][2];
            boolean[] isSold = new boolean[n];
            long ans = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                days[i] = Integer.parseInt(st.nextToken());

                descDays[i][0] = days[i];
                descDays[i][1] = i;
            }

            Arrays.sort(descDays,(i1,i2)-> i2[0] - i1[0] );

            for(int i=0;i<n;i++){
                if(isSold[i]) continue;

                int cost = days[i];

                for(int j=0;j<n;j++){
                    if( cost >= descDays[j][0]) break;
                    if( descDays[j][0] > cost && descDays[j][1] > i){
                        isSold[descDays[j][1]] = true;
                        ans += descDays[j][0] - cost;
                        break;
                    }
                }
            }

            System.out.println(ans);

        }


    }
}