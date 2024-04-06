import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        결과: 18024KB, 220ms
 */

/*
 * <BJ9205_G5 : 맥주 마시면서 걸어가기>
 * <시간제한: 1초>
 * 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다.
 * 맥주 한 박스에는 맥주가 20개 들어있다.
 * 50미터당 한 병씩 마시려고 한다.
 * 50미터를 가려면 직전에 맥주 한 병을 마셔야 한다.
 * 편의점에서 더 구매해야 할 수도 있다.
 * 편의점: 빈 병 버리고, 새 맥주 구입.
 * 박스: 최대 20병
 * 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
 * 편의점, 상근 집, 페스티벌의 좌표가 주어질 때, 페스티벌에 도착 여부를 구하시오.
 * [입력]
 * 1) 테케 개수 T. (t ≤ 50)
 * 각 테케] 1) 편의점 수 N. (0 ≤ n ≤ 100).
 * N+2개 줄) 상근 집, 편의점, 페스티벌 좌표. (좌표 -32768 ≤ x, y ≤ 32767)
 * 송도는 직사각형 모양으로 생긴 도시이다.
 * 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이. (맨해튼 거리)
 * [출력]
 * 각 테케] 페스티벌 가능: "happy", 맥주가 바닥나서 불가능: "sad".
 */
public class Main_9205_맥주마시면서걸어가기_박봉균 {
    /** 상한 */
    static final int INF = (int) 1e9;

    static int N;
    static int dp[][]; // i -> j 경로 최적화
    static Pair[] point;

    /** i -> k가 가능한지 체크하는 함수 */
    static boolean check(int i, int k) {
        // 맨하튼 거리가 20병*50m 이내이면 가능!
        if (Math.abs(point[i].first - point[k].first)
                + Math.abs(point[i].second - point[k].second) <= 1000) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테케 개수

        // T개의 테케에 대하여
        for (int t = 1; t <= T; t++) {
            // 초기화!!
            N = Integer.parseInt(br.readLine()); // 편의점 수
            dp = new int[N + 2][N + 2];
            point = new Pair[N + 2];

            // 좌표 입력
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                point[i] = new Pair(y, x);
            }

            // 초기화
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (i != j)
                        dp[i][j] = INF;
                    else
                        dp[i][j] = 0;
                }
            }

            // 경유 경로
            for (int i = 0; i < N + 2; i++) {
                for (int k = 0; k < N + 2; k++) {
                    if (i != k) {
                        // i->k 가능하다면
                        if (check(i, k))
                            dp[i][k] = 1;
                    }
                }
            }

            // 경로 최적화
            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }

            // 출력 조건에 따라 출력
            if (dp[0][N + 1] != INF) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }
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
