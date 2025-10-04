# 백준 2606 바이러스 (실버)

from collections import deque

def BFS(n):
    global sum
    q = deque()
    q.append(n)
    visited2[n] = 1
    while q:
        temp = q.popleft()
        for i in graph[temp]:
            if visited2[i] == 0:
                 visited2[i] = 1
                 sum += 1
                 q.append(i)



# 시작점 1번 (1번 빼고 Node의 갯수 count 해야 함.)
node = int(input())
line = int(input())

graph = [[] for _ in range(node + 1)]
for i in range(line):
    a,b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)


visited2 = [0] * (node + 1)
sum = 0
BFS(1)
print(sum)