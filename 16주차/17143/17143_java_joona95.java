import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class Main {

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {-1, 1, 0, 0};
    private static int R, C, M;
    private static List<Shark> sharks = new ArrayList<>();

    public static class Shark implements Comparable<Shark> {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        public void move() {
            if (d == UP || d == DOWN) {
                int cycle = (R - 1) * 2;
                int dist = (cycle == 0) ? 0 : (s % cycle);
                for (int i = 0; i < dist; i++) {
                    int nr = r + dy[d - 1];
                    if (nr < 1 || nr > R) {
                        d = (d == UP) ? DOWN : UP;
                        nr = r + dy[d - 1];
                    }
                    r = nr;
                }
            } else {
                int cycle = (C - 1) * 2;
                int dist = (cycle == 0) ? 0 : (s % cycle);
                for (int i = 0; i < dist; i++) {
                    int nc = c + dx[d - 1];
                    if (nc < 1 || nc > C) {
                        d = (d == RIGHT) ? LEFT : RIGHT;
                        nc = c + dx[d - 1];
                    }
                    c = nc;
                }
            }
        }

        @Override
        public int compareTo(Shark o) {
            if (this.r == o.r) {
                if (this.c == o.c) {
                    return Integer.compare(o.z, this.z);
                }
                return Integer.compare(this.c, o.c);
            }
            return Integer.compare(r, o.r);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(r, c, s, d, z);
            sharks.add(shark);
        }

        bw.write(String.valueOf(solution()));

        bw.flush();
        br.close();
        bw.close();
    }

    public static int solution() {

        int answer = 0;

        for (int i = 1; i <= C; i++) {
            int fisherman = i;


            Optional<Shark> caught = sharks.stream()
                    .filter(shark -> shark.c == fisherman)
                    .min(Comparator.comparingInt(shark -> shark.r));

            if (caught.isPresent()) {
                answer += caught.get().z;
                sharks.remove(caught.get());
            }

            for (Shark shark : sharks) {
                shark.move();
            }

            Collections.sort(sharks);
            List<Shark> copy = new ArrayList<>();
            for (int j = 0; j < sharks.size(); j++) {
                Shark current = sharks.get(j);
                if (copy.isEmpty()) {
                    copy.add(current);
                    continue;
                }
                Shark last = copy.get(copy.size() - 1);
                if (last.r == current.r && last.c == current.c) {
                    continue;
                }
                copy.add(current);
            }
            sharks = copy;
        }

        return answer;
    }
}