import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        결과: 107292KB, 1192 ms
 */

/*
 * <SWEA12D6_1263_사람네트워크2_박봉균>
 * <시간제한: 20초>
 * CC: 각 정점의 다른 정점으로의 최단경로
 * [입력]
 * 1) 테케 수 T.
 * 각 테케] 1) 사람 수 N, 사람 네트워크의 인접 행렬이 행 우선 (row-by-row) 순으로 주어진다.
 * [출력]
 * #테케번호 <공백> CC의 최솟값.
 */
public class D6_1263_사람네트워크2_박봉균 {
    /** 상한 */
    static final int INF = (int) 1e9;

    static int N, dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 정점 개수
            dp = new int[N][N];

            // 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = INF;
                }
            }

            // 각 정점 연결 경로(존재 체크)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int given = Integer.parseInt(st.nextToken());
                    if (given == 1)
                        dp[i][j] = 1; // 간선
                }
            }

            // 경로 최적화
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }

            // CC: 각 정점에서 다른 정점으로의 최단 경로의 합(CC)
            int ret = INF;
            for (int i = 0; i < N; i++) {
                int cc = 0;
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        cc += dp[i][j];
                    }
                }
                ret = Math.min(ret, cc);
            }

            bw.write("#" + String.valueOf(t) + " ");
            bw.write(String.valueOf(ret) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
