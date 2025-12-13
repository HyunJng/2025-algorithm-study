import java.io.*;
import java.util.*;

public class Main {
    static int cityCount;
    static int[][] travelCost;
    static int[][] dp;
    static int allVisited;
    static final int START_CITY = 0;
    static final int INF = 1_000_000_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        cityCount = Integer.parseInt(br.readLine());
        travelCost = new int[cityCount][cityCount];
        
        for (int i = 0; i < cityCount; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < cityCount; j++) {
                travelCost[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        System.out.println(getMinCost() + "");
        
        br.close();
    }
    
    public static int getMinCost() {
        dp = new int[1 << cityCount][cityCount];
        allVisited = (1 << cityCount) - 1;
        
        for (int visitMask = 0; visitMask < (1 << cityCount); visitMask++) {
            Arrays.fill(dp[visitMask], -1);
        }
        
        return findPath(1 << START_CITY, START_CITY);
    }
    
    static int findPath(int visitMask, int currentCity) {
        if (dp[visitMask][currentCity] != -1) {
            return dp[visitMask][currentCity];
        }
        
        if (visitMask == allVisited) {
            if (travelCost[currentCity][START_CITY] == 0) return INF;
            return travelCost[currentCity][START_CITY];
        }
        
        int minCost = INF;
        for (int nextCity = 0; nextCity < cityCount; nextCity++) {
            if ((visitMask & (1 << nextCity)) != 0) continue;
            if (travelCost[currentCity][nextCity] == 0) continue;
            
            int newMask = visitMask | (1 << nextCity);
            int cost = travelCost[currentCity][nextCity] + findPath(newMask, nextCity);
            
            if (cost < minCost) {
                minCost = cost;
            }
        }
        
        dp[visitMask][currentCity] = minCost;
        return minCost;
    }
}