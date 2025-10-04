# 7562 나이트의 이동 (실버)

from collections import deque

dx = [2,1,-1,-2,-2,-1,1,2]
dy = [1,2,2,1,-1,-2,-2,-1]

def BFS(s1, s2):
    q = deque()
    q.append((s1, s2, 0))
    visit[s1][s2] = 1

    while q:
        temp = q.popleft()
        if temp[0] == e1 and temp[1] == e2:
            print(temp[2])

        for i in range(8):
            x = temp[0] + dx[i]
            y = temp[1] + dy[i]
            if 0 <= x < l and 0 <= y <l and visit[x][y] == 0:
                visit[x][y] = 1
                q.append((x,y, temp[2] + 1))



tc = int(input())
for _ in range(tc):
    l = int(input())    # 체스판의 한 변의 길이
    s1, s2 = map(int, input().split())  # 현재 있는 칸
    e1, e2 = map(int, input().split())  # 이동하려고 하는 칸


    # 각 테스트 케이스마다 나이트가 최소 몇 번 만에 이동할 수 있는지
    visit = [[ 0 for _ in range(l)] for _ in range(l)]
    BFS(s1, s2)


