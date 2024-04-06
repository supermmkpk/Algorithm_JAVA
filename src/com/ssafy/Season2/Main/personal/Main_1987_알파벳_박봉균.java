import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 결과: 12316KB, 812ms
 */

/* <BJ1987_G4 : 알파벳>
 * <시간제한: 2초>
 * 세로 R칸, 가로 C칸 표 모양 보드가 있다.
 * 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행1열)에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하시오. (말이 지나는 칸은 좌측 상단의 칸도 포함된다.)
[입력]
1) R, C. (1 ≤ R,C ≤ 20)
R개 줄) 보드 행렬 (공백 X)
[출력]
말이 지날 수 있는 최대의 칸 수
*/
public class Main_1987_알파벳_박봉균 {
    static final int INF = (int) 1e9;
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};

    static int R, C;
    static char[][] matrix;
    static boolean[] visited = new boolean[26]; //대문자 알파벳별 방문 체크 배열
    static int ret = -INF;

    static void dfs(int y, int x, int cnt) {
        ret = Math.max(ret, cnt);

        for(int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || ny >= R || nx < 0 || nx >= C) 
                continue;
            if(visited[matrix[ny][nx] - 'A'] == false) {
                visited[matrix[ny][nx] - 'A'] = true;
                dfs(ny, nx, cnt + 1);
                visited[matrix[ny][nx] - 'A'] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        matrix = new char[R][C];

        for(int i = 0; i < R; i++) {
            String given = br.readLine(); 
            for(int j = 0; j < C; j++) {
                matrix[i][j] = given.charAt(j);
            }
        }

        visited[matrix[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        bw.write(String.valueOf(ret) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}