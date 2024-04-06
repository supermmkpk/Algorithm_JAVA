import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: 플로이드워셜: 모든 경유 경로를 고려하고 i->j 최적을 보장 (O(N^3) OK)
 *        ==> 모든 정점에 대해 (나로 들어오는 경유 정점수 + 내가 갈 수 있는 경유 정점수) == N-1 이면 가능
 *        결과: 103660KB, 1017ms
 */

/*
 * 1~N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어질 때,
 * 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하시오.
 * [입력]
 * 1) 테케 개수 T. (1 ≤ T ≤ 15)
 * 각 테케] 1) 학생들의 수 N. (2 ≤ N ≤ 500)
 * 2) 두 학생의 키를 비교한 횟수 M. (0 ≤ M ≤ N*(N-1)/2)
 * M개 줄) 두 학생의 키를 비교한 결과 a, b. (a키 < b키) (a -> b)
 * [출력]
 * #테케 번호 <공백> 자신이 키가 몇 번째인지 알 수 있는 학생수
 */
public class D4_5643_키순서_플로이드워셜 {
    static final int INF = (int) 1e9;

    static int N, M, ret;
    static int[][] dp; // i->j 경로 존재여부 + 최적경로 보장
    static int[] cnt; // 정점 별 앞뒤 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테케 개수 T

        // T개 테케에 대하여
        for (int t = 1; t <= T; t++) {
            // 초기화
            ret = 0;
            N = Integer.parseInt(br.readLine()); // 정점 개수(학생수)
            M = Integer.parseInt(br.readLine()); // 간선수(비교 횟수)
            dp = new int[N][N];
            cnt = new int[N];

            // dp 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = INF;
                }
            }

            // 경유 경로(간선) 저장
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                dp[from][to] = 1;
            }

            // 경로 최적화
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }

            // 모든 정점에 대하여, 자기로 올 수 있는, 자기가 갈 수 있는 정점 개수 저장하기
            // 즉 i->j 경로 존재시, 모든 경유 정점 개수
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dp[i][j] != 0 && dp[i][j] != INF) {
                        ++cnt[i];
                        ++cnt[j];
                    }
                }
            }

            // 들어오는,나가는 경유 정점이 N - 1개면 순서 알 수 있음
            for (int i = 0; i < N; i++) {
                if (cnt[i] == N - 1)
                    ++ret;
            }

            bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
