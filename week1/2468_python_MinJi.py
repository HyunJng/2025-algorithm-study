# 2468 안전영역 (실버)

from collections import deque

n = int(input())

graph = []
for _ in range(n):
    graph.append(list(map(int, input().split())))
visit = [[0 for _ in range(n)] for _ in range(n)]

# 높이 구하기
height = 0
for i in range(n):
    for j in range(n):
        height = max(height, graph[i][i])

dx = [1, 0, -1, 0]
dy = [0, -1, 0, 1]

def BFS(a,b, height):
    q = deque()
    q.append((a,b))
    visit[a][b] = 1

    while q:
        temp = q.popleft()
        for i in range(4):
            x = temp[0] + dx[i]
            y = temp[1] + dy[i]
            if 0 <= x <= n-1 and 0 <= y <= n-1 and visit[x][y] == 0 and graph[x][y] >= height:
                visit[x][y] = 1
                q.append((x,y))

result = 1
for h in range(1, height+1):
    visit = [[0 for _ in range(n)] for _ in range(n)]
    count = 0
    for a in range(n):
        for b in range(n):
            if visit[a][b] == 0 and graph[a][b] >= h:
                BFS(a,b,h)
                count += 1
    result = max(result, count)
print(result)