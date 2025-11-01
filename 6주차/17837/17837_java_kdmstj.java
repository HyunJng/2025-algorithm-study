import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[][] map;
    static Node[] nodeArr;
    static List<Integer>[][] nodeMap;
    static int answer = 1;
    static final int RED = 1, BLUE = 2;
    static final int MAX_STACK = 4;

    public static void main(String[] args) throws IOException {
        input();

        simulation();

        output();
    }

    private static void output() {
        answer = answer > 1000 ? -1 : answer;
        System.out.println(answer);
    }

    private static void simulation() {
        while (true) {
            for (int num = 1; num <= K; num++) {
                Node node = nodeArr[num];
                int curX = node.x, curY = node.y, curDir = node.dir;
                int nX = curX + dx[curDir];
                int nY = curY + dy[curDir];

                if (isOutOfBound(nX, nY) || map[nX][nY] == BLUE) {
                    node.dir = (curDir + 2) % 4;
                    nX = curX + dx[node.dir];
                    nY = curY + dy[node.dir];

                    if (isOutOfBound(nX, nY) || map[nX][nY] == BLUE) continue; //만약 또 블루라면, 그대로 둔다.
                }

                //삭제해야 하는 노드의 인덱스 구하기
                List<Integer> nodeList = nodeMap[curX][curY];
                int removeIdx = nodeList.indexOf(num);


                //노드 + 위에 있는 노드에 대해 nodeArr, nodeMap 수정
                List<Integer> aboveNodeList = new ArrayList<>(nodeList.subList(removeIdx, nodeList.size()));
                nodeList.subList(removeIdx, nodeList.size()).clear();

                if(map[nX][nY] == RED){
                    Collections.reverse(aboveNodeList);
                }

                for(int nodeNum : aboveNodeList){
                    nodeArr[nodeNum].x = nX;
                    nodeArr[nodeNum].y = nY;
                    nodeMap[nX][nY].add(nodeNum);
                }


                //종료 조건
                if (nodeMap[nX][nY].size() >= MAX_STACK || answer > 1_000) {
                    return;
                }
            }
            answer++;
        }
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N]; //0-based
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nodeMap = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nodeMap[i][j] = new ArrayList<>();
            }
        }

        nodeArr = new Node[K + 1]; //1-based
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());  //오, 왼, 위, 아래
            dir = dir != 1 ? (dir != 2 ? (dir != 3 ? 1 : 3) : 2) : 0;
            Node node = new Node(x, y, dir);
            nodeArr[i] = node;
            nodeMap[x][y].add(i);
        }
    }

    //오, 아래, 왼, 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static class Node {
        int x, y, dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}