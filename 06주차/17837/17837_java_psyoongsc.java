import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Horse {
    int x, y;
    int direction;

    Horse(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

public class Main {
    static int N, K;
    static int[][] color;
    static ArrayList<Integer>[][] board;
    static Horse[] horses;

    static int[] dx = { 0, 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        K = input[1];
        color = new int[N][N];
        board = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayList<>();
            }
        }
        horses = new Horse[K];

        for (int i = 0; i < N; i++) {
            color[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < K; i++) {
            int[] input_horse = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            horses[i] = new Horse(input_horse[1] - 1, input_horse[0] - 1, input_horse[2]);
            board[input_horse[0] - 1][input_horse[1] - 1].add(i);
        }

        int turn = 0;
        while (true) {
            turn++;

            if(turn > 1000) {
                System.out.println("-1");
                return;
            }

            for (int i = 0; i < K; i++) {
                Horse horse = horses[i];
                int curX = horse.x;
                int curY = horse.y;

                int toX = curX + dx[horse.direction];
                int toY = curY + dy[horse.direction];

                if (toX >= 0 && toX < N && toY >= 0 && toY < N) {
                    if (color[toY][toX] == 2) {
                        switch (horse.direction) {
                            case 1: horse.direction = 2; break;
                            case 2: horse.direction = 1; break;
                            case 3: horse.direction = 4; break;
                            case 4: horse.direction = 3; break;
                        }

                        toX = curX + dx[horse.direction];
                        toY = curY + dy[horse.direction];
                    }

                    if (toX >= 0 && toX < N && toY >= 0 && toY < N) {
                        if (color[toY][toX] == 2) {
                            continue;
                        }

                        int idx = board[curY][curX].indexOf(i);
                        ArrayList<Integer> moving = new ArrayList<>(board[curY][curX].subList(idx, board[curY][curX].size()));

                        board[curY][curX].subList(idx, board[curY][curX].size()).clear();

                        for (int num: moving) {
                            horses[num].x = toX;
                            horses[num].y = toY;
                        }

                        if (color[toY][toX] == 1) {
                            Collections.reverse(moving);
                        }

                        board[toY][toX].addAll(moving);
                    }
                } else {
                    switch (horse.direction) {
                        case 1: horse.direction = 2; break;
                        case 2: horse.direction = 1; break;
                        case 3: horse.direction = 4; break;
                        case 4: horse.direction = 3; break;
                    }

                    toX = curX + dx[horse.direction];
                    toY = curY + dy[horse.direction];

                    if (toX >= 0 && toX < N && toY >= 0 && toY < N) {
                        if (color[toY][toX] == 2) {
                            continue;
                        }

                        int idx = board[curY][curX].indexOf(i);
                        ArrayList<Integer> moving = new ArrayList<>(board[curY][curX].subList(idx, board[curY][curX].size()));

                        board[curY][curX].subList(idx, board[curY][curX].size()).clear();

                        for (int num: moving) {
                            horses[num].x = toX;
                            horses[num].y = toY;
                        }

                        if (color[toY][toX] == 1) {
                            Collections.reverse(moving);
                        }

                        board[toY][toX].addAll(moving);
                    }
                }

                if (toY >= 0 && toY < N && toX >= 0 && toX < N && board[toY][toX].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }
    }
}