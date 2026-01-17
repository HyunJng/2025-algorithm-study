import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Board {
    public int r;
    public int c;

    public Board(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Shark {
    int r, c, speed, direction, size;

    public Shark(int r, int c, int speed, int direction, int size) {
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }

    public void move(Board board) {
        int R = board.r;
        int C = board.c;

        if (direction == 1 || direction == 2) {
            int cycle = 2 * (R - 1);
            int move = speed % cycle;
            for (int i = 0; i < move; i++) {
                if (direction == 1 && r == 1)
                    direction = 2;
                else if (direction == 2 && r == R)
                    direction = 1;
                r += (direction == 1 ? -1 : 1);
            }
        } else {
            int cycle = 2 * (C - 1);
            int move = speed % cycle;
            for (int i = 0; i < move; i++) {
                if (direction == 4 && c == 1)
                    direction = 3;
                else if (direction == 3 && c == C)
                    direction = 4;
                c += (direction == 4 ? -1 : 1);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] len = br.readLine().split(" ");

        int r = Integer.parseInt(len[0]);
        int c = Integer.parseInt(len[1]);
        int m = Integer.parseInt(len[2]);

        Board board = new Board(r, c);

        ArrayList<Shark> sharks = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String arr[] = br.readLine().split(" ");
            sharks.add(new Shark(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]),
                    Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4])));

        }

        int score = 0;

        for (int col = 1; col <= board.c; col++) {
            Shark caught = null;
            int minRow = Integer.MAX_VALUE;
            for (Shark s : sharks) {
                if (s.c == col && s.r < minRow) {
                    minRow = s.r;
                    caught = s;
                }
            }
            if (caught != null) {
                score += caught.size;
                sharks.remove(caught);
            }

            for (Shark s : sharks) {
                s.move(board);
            }

            Map<String, Shark> cell = new HashMap<>();
            for (Shark s : sharks) {
                String key = s.r + "," + s.c;
                Shark prev = cell.get(key);
                if (prev == null || prev.size < s.size) {
                    cell.put(key, s);
                }
            }
            sharks = new ArrayList<>(cell.values());
        }

        System.out.println(score);
    }
}