import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());

        for (int i = 0; i < len; i++) {
            int day = Integer.parseInt(br.readLine().trim());
            int[] prices = new int[day];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < day; j++) {
                prices[j] = Integer.parseInt(st.nextToken());
            }

            long profit = 0L;
            int max = prices[day - 1];

            for (int j = day - 1; j >= 0; j--) {
                int price = prices[j];
                if (price < max) {
                    profit += max - price;
                } else if (price > max) {
                    max = price;
                }
            }
            System.out.println(profit);

        }
    }

}
