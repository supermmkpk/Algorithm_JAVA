import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 14264KB, 108ms
 */
/* <BJ1194_G1 : 달이 차오른다, 가자>
 * <시간제한: 2초>
 * 민식이는 미로 속에 있다. 미로는 직사각형 모양이고, 이를 탈출하려고 한다. 
 * 빈 칸: 이동 O ('.')
 * 벽: 이동 X ('#')
 * 열쇠: 언제나 이동 가능. 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
 * 문: 대응하는 열쇠가 있을 때만 이동. ('A', 'B', 'C', 'D', 'E', 'F')'
 * 민식 위치: ('0')
 * 출구: 미로 탈출. ('1')
 * 이동: 상하좌우 한 칸. -> 가중치가 1로 동일 ==> BFS
 * 탈출까지 이동 횟수의 최솟값을 구하시오.
[입력]
1) 행크기 N, 열크기 M. (1 ≤ N, M ≤ 50)
N개 줄) 미로 행렬. 
    - 같은 열쇠/문 여러 개 가능, 문에 대응하는 열쇠가 없을 수도 있다. 
    - '0'은 한 개, '1'은 적어도 한 개 있다. 
    - 열쇠는 여러 번 사용할 수 있다.
[출력]
미로를 탈출하는데 드는 이동 횟수의 최솟값. (탈출 불가: -1)
 */
public class Main_1194_달이차오른다_박봉균 {
    /** 4방 방향벡터 */
    static final int[] dy = { -1, 0, 1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    static int N, M;
    static char[][] matrix = new char[54][54];

    static int visited[][][]; // y, x, key(비트마스킹)

    /** 키를 저장하며 BFS 탐색하는 함수 */
    static int bfs(int sy, int sx, int skey) {
        visited[sy][sx][skey] = 1;
        Queue<Tuple> q = new ArrayDeque<>();
        q.add(new Tuple(sy, sx, skey));

        while (!q.isEmpty()) {
            int y = q.peek().y;
            int x = q.peek().x;
            int key = q.remove().key;

            if(matrix[y][x] == '1') 
                return visited[y][x][key] - 1;

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                    continue;

                char next = matrix[ny][nx];
                if (visited[ny][nx][key] == 0 && next != '#') { // 갈 수 있다면
                    // 문
                    if ('A' <= next && next <= 'F') {
                        int idx = next - 'A'; // 0~5
                        
                        // 키 있다면 이동 가능
                        if ((key & 1 << idx) != 0) {
                            visited[ny][nx][key] = visited[y][x][key] + 1;
                            q.add(new Tuple(ny, nx, key));
                        }
                    }
                    // 키
                    else if ('a' <= next && next <= 'f') {
                        // 키 추가
                        int idx = next - 'a';
                        int nkey = key | 1 << idx;
                        visited[ny][nx][nkey] = visited[y][x][key] + 1;
                        q.add(new Tuple(ny, nx, nkey));
                    }
                    //빈칸 또는 종료 지점
                    else {
                        visited[ny][nx][key] = visited[y][x][key] + 1;
                        q.add(new Tuple(ny, nx, key));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행 크기
        M = Integer.parseInt(st.nextToken()); // 열 크기
        visited = new int[N][M][1 << 6];

        // 미로 입력
        Pair start = new Pair();
        for (int i = 0; i < N; i++) {
            String given = br.readLine();
            for (int j = 0; j < given.length(); j++) {
                matrix[i][j] = given.charAt(j);
                if (matrix[i][j] == '0') {
                    start.first = i;
                    start.second = j;
                }
            }
        }

        int ret = bfs(start.first, start.second, 0);

        bw.write(String.valueOf(ret) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }

    /** 좌표 저장 위한 Pair 클래스 */
    static class Pair {
        int first, second;
        Pair() {}
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    /** 좌표,키 저장 위한 Tuple 클래스 */
    static class Tuple {
        int y, x, key;
        Tuple(int y, int x, int key) {
            this.y = y;
            this.x = x;
            this.key = key;
        }
    }
}
