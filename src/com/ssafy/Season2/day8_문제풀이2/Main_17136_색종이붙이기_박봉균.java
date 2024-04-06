import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: 큰 거부터 붙여보면서 다 해보기 + 원복(백트랙)
 *        결과: 19800KB, 184ms
 */
/*
 * 1×1, 2×2, 3×3, 4×4, 5×5 색종이를 5개씩 가지고 있다.
 * 이 색종이를 10×10 종이 위에 붙이려 한다. 종이는 1×1 크기의 칸(0또는1)으로 나누어져 있다.
 * 1: 모두 색종이로 덮여야 한다. 색종이를 붙일 때는 종이의 경계 밖으로 나가서는 안되고, 겹쳐도 안 된다.
 * 0: 색종이가 있으면 안 된다. 또, 칸의 경계와 일치하게 붙여야 한다.
 * 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수는?
 * [입력]
 * 10개 줄) 종이 정보 행렬.
 * [출력]
 * 1을 덮는데 필요한 색종이의 최소 개수. (불가능한 경우: -1)
 */
public class Main_17136_색종이붙이기_박봉균 {
    static final int INF = (int) 1e9;

    static int[][] matrix = new int[10][10];
    static int[] cnt = new int[6];
    static int used = 0;
    static int ret = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 큰 거부터 가능하면 visited 체크하고 넘어가기
        // 돌아오면 -1 작은 사이즈에 대해서도 확인
        dfs(0, 0);

        bw.write(String.valueOf(ret == INF ? -1 : ret) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int y, int x) {
        // 1(붙여야하는 칸)을 찾을 때까지 증가
        boolean todo = false; // 붙일 칸 존재 여부
        for (; y < 10; y++) {
            for (x = 0; x < 10; x++) {
                if (matrix[y][x] == 1) {
                    todo = true;
                    break;
                }
            }
            if (todo)
                break;
        }

        // 다 붙였거나, 유망성 없으면 최소 갱신, return
        if (todo == false || ret <= used) {
            ret = Math.min(ret, used);
            return;
        }

        // 큰 거부터 가능하면 붙여보고 넘어가기
        for (int i = 5; i >= 1; i--) {
            if (cnt[i] < 5 && check(y, x, i)) {
                // 붙이기
                for (int j = y; j < y + i; j++) {
                    for (int k = x; k < x + i; k++) {
                        matrix[j][k] = 0; // 붙였으니까 0
                    }
                }
                used++;
                cnt[i]++;

                // 다음 1에 대하여 같은 과정 반복
                dfs(y, x);

                // 원복
                for (int j = y; j < y + i; j++) {
                    for (int k = x; k < x + i; k++) {
                        matrix[j][k] = 1;
                    }
                }
                used--;
                cnt[i]--;
            }
        }
    }

    /**
     * 크기에 따라 정사각형이 모두 1인지 체크가는 함수
     * 
     * @param sy   : 시작 행
     * @param sx   : 시작 열
     * @param size : 크기
     */
    static boolean check(int sy, int sx, int size) {
        for (int y = sy; y < sy + size; y++) {
            for (int x = sx; x < sx + size; x++) {
                if (y < 0 || y >= 10 || x < 0 || x >= 10) // 종이 벗어나는 경우
                    return false;
                if (matrix[y][x] == 0) // 0 발견!
                    return false;
            }
        }
        return true;
    }
}
