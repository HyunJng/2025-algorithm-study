import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Location {
    private int row;
    private int column;

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

public class Main {
    static int answer = Integer.MAX_VALUE;

    public static void backtrack(List<Location> shops, List<Location> selected, List<Location> houses, int start,
            int M) {
        if (selected.size() == M) {
            int dist = calculateDistance(selected, houses);
            answer = Math.min(answer, dist);
            return;
        }

        for (int i = start; i < shops.size(); i++) {
            selected.add(shops.get(i));
            backtrack(shops, selected, houses, i + 1, M);
            selected.remove(selected.size() - 1);
        }
    }

    public static int calculateDistance(List<Location> selected, List<Location> houses) {
        int chickenDist = 0;

        for (Location house : houses) {
            int minDist = Integer.MAX_VALUE;
            for (Location shop : selected) {
                int dist = Math.abs(house.getRow() - shop.getRow())
                        + Math.abs(house.getColumn() - shop.getColumn());
                minDist = Math.min(minDist, dist);
                if (minDist == 1)
                    break;
            }
            chickenDist += minDist;
        }
        return chickenDist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] len = br.readLine().split(" ");

        int n = Integer.parseInt(len[0]);
        int m = Integer.parseInt(len[1]);

        List<Location> houses = new ArrayList<>();
        List<Location> shops = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 1; j <= arr.length; j++) {
                if (arr[j - 1].equals("1")) {
                    houses.add(new Location(i, j));
                } else if (arr[j - 1].equals("2")) {
                    shops.add(new Location(i, j));
                }
            }
        }

        backtrack(shops, new ArrayList<>(), houses, 0, m);
        System.out.println(answer);

    }

}
