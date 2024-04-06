import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 아이디어: BFS(가중치 다를 때 처리법)
 * 결과: 31360KB, 139ms
 */

/* <SWEA1249_D4 : 보급로>
 * <시간제한: 20초>
 * 도로가 파여진 깊이 = 복구 시간
 * 출발지(S)에서 도착지(G)까지 가는 경로 중에 복구 시간이 가장 짧은 경로의 복구 시간을 구하시오.
[입력]
1) 테케 수 T.
각 테케] 1) 행렬 크기 N. (<= 100)
    N개 줄) 지도 정보 행렬. (공백X)
[출력]
#테케번호 <공백> 복구시간 최솟값
*/
public class D4_1249_보급로_박봉균 {
    /** 4방 방향벡터 */
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};

    static int N, matrix[][], visited[][];

    /** BFS 전체 탐색하며 최단 경로를 구하는 함수 */
    static int bfs(int sy, int sx) {
        Queue<Pair> q = new ArrayDeque<>();
        visited[sy][sx] = 1;
        q.add(new Pair(sy, sx));

        while(!q.isEmpty()) {
            int y = q.peek().first;
            int x = q.remove().second;

            for(int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N) //오버,언더 체크
                    continue;

                //첫 방문이거나 이전 경로 길이보다 여기가 더 짧다면 방문+갱신!
                if(visited[ny][nx] == 0 || visited[ny][nx] > visited[y][x] + matrix[y][x]) {
                    visited[ny][nx] = visited[y][x] + matrix[y][x];
                    q.add(new Pair(ny, nx));
                }
            }
        }
        //현재 정점 기준 최단거리 갱신했으므로 목적지점 가중치 더해서 반환
        return visited[N - 1][N - 1] - 1 + matrix[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); //테케개수 T 

        //T개 테케에 대하여
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine()); //행렬 크기
            matrix = new int[N][N]; //지도 정보 행렬
            visited = new int[N][N]; //방문체크+최단거리

            //지도 행렬 입력
            for(int i = 0; i < N; i++) {
                String given = br.readLine();
                for(int j = 0; j < N; j++) {
                    matrix[i][j] = given.charAt(j) - '0';
                }
            }

            //전체 탐색하며 최단 거리를 구하고 출력
            bw.write("#" + String.valueOf(t) + " ");
            bw.write(String.valueOf(bfs(0, 0)) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }

    /** 좌표 저장 위한 Pair 클래스 */
    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
