import java.io.*;
import java.util.*;

public class Main {
    static int cnt = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long A = Long.parseLong(st.nextToken());
        Long B = Long.parseLong(st.nextToken());

        dfs(4L, A, B);
        dfs(7L, A, B);

        System.out.println(cnt);
    }

    private static void dfs(Long i,Long Min,Long Max ) {
        if(i > Max) return;
        if(i >= Min) cnt++;

        dfs(10 * i + 4, Min, Max);
        dfs(10 * i + 7, Min, Max);
    }
}
