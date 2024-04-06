import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 111352KB, 1134ms
 */

/*
 * 구술을 쏘아 벽돌을 깨트리는 게임.
 * 구슬: N번 쏠 수 있고, 벽돌 정보 W x H 행렬.(0:빈칸, 벽돌: 1~9)
 * 게임의 규칙:
 *  1. 구슬 이동: 좌우 ONLY => 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 *  2. 벽돌: 1 ~ 9, 
 *     구슬로 벽돌 명중 시, 상하좌우로 (벽돌숫자-1)칸 만큼 같이 제거.
 *  3. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.
 *  4. 빈 공간이 있을 경우 벽돌은 밑으로 떨어지게 된다.
 * 최대한 많은 벽돌을 제거하려 할때, 남은 벽돌의 개수를 구하라.
[입력]
1) 테케 개수 T.
각 테케] 1) N, W, H. (1 ≤ N ≤ 4, 2 ≤ W ≤ 12, 2 ≤ H ≤ 15)
    H개 줄) 벽돌 정보 행렬(H*W)
[출력]
#테케번호 <공백> 남은 벽돌개수의 최소
*/
public class SWEA_5656_벽돌깨기_박봉균 {
    static final int INF = (int) 1e9;
    static final int[] dy = { -1, 0, 1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    static int N, W, H, matrix[][], permu[];
    static boolean visited[][];
    static int ret = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 초기화!
            ret = INF;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            matrix = new int[H+4][W+4];
            visited = new boolean[H+4][W+4];
            permu = new int[N];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            choose(0);

            // 남은 벽돌 개수의 최소 출력
            bw.write("#" + String.valueOf(t) + " ");
            bw.write(String.valueOf(ret) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }

    /** 최상위 벽돌들에 대해 다 쳐보고 폭파 벽돌 많은 경우로 선택 */
    static void choose(int cnt) {
        if (cnt == N || getTop() <= 0) {
            //임시 배열
            int[][] tmpMatrix = new int[H+4][W+4];
            for (int j = 0; j < H; j++)
                tmpMatrix[j] = matrix[j].clone();

            for(int col : permu) {
                int row = 0;
                for(; row < H; row++) {
                    if(matrix[row][col] != 0) 
                        break;
                }
                if(row < H) {
                    go(row, col, matrix[row][col]);
                    pushDown();
                }
            }
            
            ret = Math.min(ret, getBricks());

            // 원복
            for (int j = 0; j < H; j++)
                matrix[j] = tmpMatrix[j].clone();
            return;
        }

        // 구슬로 쳐보기(go)
        for (int j = 0; j < W; j++) {
            permu[cnt] = j;
            choose(cnt + 1);
        }
    }

    /** 재귀적으로 벽돌을 폭파시키는 함수 */
    static void go(int y, int x, int power) {
        matrix[y][x] = 0;
        if (power == 1) 
            return;

        for (int d = 0; d < 4; d++) {
            for (int pow = 1; pow < power; pow++) {
                int ny = y + dy[d] * pow;
                int nx = x + dx[d] * pow;
                if (ny < 0 || ny >= H || nx < 0 || nx >= W)
                    continue;
                if (visited[ny][nx] == false && matrix[ny][nx] != 0) {
                    go(ny, nx, matrix[ny][nx]);
                }
            }
        }
    }

    /** 열 별로 벽돌을 밑으로 배치하는 함수 */
    static void pushDown() {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < W; j++) {
            list.clear();
            // 해당 열 원소 저장
            for (int i = H - 1; i >= 0; i--) {
                if (matrix[i][j] != 0) {
                    list.add(matrix[i][j]);
                    matrix[i][j] = 0;
                }
            }
            // 저장된 원소들 밑에서부터 순차적 배치
            int row = H - 1;
            for (int val : list) {
                matrix[row--][j] = val;
            }
        }
    }

    /** 벽돌 개수 구하는 함수 */
    static int getBricks() {
        int n= 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] != 0)
                    n++;
            }
        }
        return n;
    }

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
