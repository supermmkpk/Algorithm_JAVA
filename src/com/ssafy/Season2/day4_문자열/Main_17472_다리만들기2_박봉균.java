import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 12092KB, 84ms
 */
/*
 * 다리: 바다에만 건설, 길이: 차지하는 칸 수. 
 * 다리를 연결해서 모든 섬을 연결하려고 한다. 
 * 길이>=2, 방향:가로/세로, 방향에 맞게 연결돼야 함.
 * 모든 섬을 연결하는 다리 길이의 최솟값은?
[입력]
1) 세로크기 N, 열크기 M. (1 ≤ N, M ≤ 10, 3 ≤ N×M ≤ 100)
N개 줄) 지도행렬 (0: 바다, 1: 땅, 2 ≤ 섬의 개수 ≤ 6)
[출력]
모든 섬을 연결하는 다리 길이의 최솟값. (연결 불가: -1)
 */
public class Main_17472_다리만들기2_박봉균 {
    static final int[] dy = { -1, 0, 1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    static int N, M, matrix[][];
    static int islandNum; // 섬 개수
    static boolean[][] visited;
    static boolean[] visitedIsland = new boolean[6];
    static int[] parent = new int[7];
    static int[][] island = new int[7][4]; // y1, y2, x1, x2
    static int ret;
   
    static List<Tuple> list = new ArrayList<>(); // {거리, 섬1, 섬2}
    static LinkedList<Integer>[] adjList = new LinkedList[6];
    static Queue<Integer> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < adjList.length; i++) 
            adjList[i] = new LinkedList<>();

        //정점 생성
        for (int i = 0; i < 6; i++)
            parent[i] = i; // 자기 자신을 부모로 지정

        // 섬 정보 행렬
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0) {
                    if(visited[i][j] == false)  
                        islandNum++;     
                    dfs(i, j);
                }
            }
        }

        // 섬 간의 거리 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] != 0) {
                    getDistance(matrix[i][j], i, j);
                }
            }
        }

        Collections.sort(list);

        // MST
        for (Tuple t : list) {
            int dist = t.dist;
            int island1 = t.from;
            int island2 = t.to;

            // 다리길이 2 이상
            if (dist < 2) 
                continue;

            // 두 섬간의 다리 건설 시
            if (union(island1, island2))
                ret += dist;
        }

        // 섬이 모두 연결되어 있는지 확인
        int cnt = 1;
        q.add(1);

        while (!q.isEmpty()) {
            int now = q.remove();
            visitedIsland[now] = true;

            for (int next : adjList[now]) {
                if (visitedIsland[next] == false) {
                    q.add(next);
                    cnt++;
                }
            }
        }

        if (cnt != islandNum) 
            bw.write("-1\n");
        else 
            bw.write(String.valueOf(ret) + '\n');
        
        bw.flush();
        bw.close();
        br.close();
    }

    // 섬 번호 부여하기
    static void dfs(int y, int x) {        
        if(visited[y][x])
            return;
        visited[y][x] = true;
        matrix[y][x] = islandNum; // 섬 번호

        island[islandNum][0] = Math.min(island[islandNum][0], y); //y 최소
        island[islandNum][1] = Math.max(island[islandNum][1], y); //y 최대
        island[islandNum][2] = Math.min(island[islandNum][2], x); //x 최소
        island[islandNum][3] = Math.max(island[islandNum][3], x); //x 최대

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) 
                continue;
            if (matrix[ny][nx] != 0 && visited[ny][nx] == false) {
                dfs(ny, nx);
            }
        }
    }

    // 섬 간의 최소거리 구하기
    static void getDistance(int now, int y, int x) {
        for (int d = 0; d < 4; d++) {
            int nowY = y;
            int nowX = x;
            int dist = 0;

            while (true) {
                nowY += dy[d];
                nowX += dx[d];
                
                // 범위 이탈 또는 현재 섬일 경우 탈출
                if (nowY < 0 || nowY >= N || nowX < 0 || nowX >= M || matrix[nowY][nowX] == now) 
                    break;

                if (matrix[nowY][nowX] != 0) {
                    // {거리, 출발한 섬, 도착한 섬}
                    list.add(new Tuple(dist, now, matrix[nowY][nowX]));
                    break;
                }
                dist++;
            }
        }
    }

    static int find(int u) {
        if (u == parent[u]) 
            return u;
        else 
            return find(parent[u]);
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u != v) {
            // 노드 결합
            parent[u] = v;

            // 간선 저장
            adjList[u].add(v);
            adjList[v].add(u);
            return true;
        }
        else 
            return false;  
    }

    static class Tuple implements Comparable<Tuple> {
        int dist, from, to;
        Tuple(int dist, int from, int to) {
            this.dist = dist;
            this.from = from;
            this.to = to;
        }
        public int compareTo(Tuple o) {
            if(this.dist == o.dist) {
                if(this.from == o.from) {
                    return Integer.compare(this.to, o.to);
                }
                else {
                    return Integer.compare(this.from, o.from);
                }
            }
            else {
                return Integer.compare(this.dist, o.dist);
            }
        }
    }
}
