import java.util.*;
import java.io.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: An = An-2 + An-3 (A0 = A1 = A2 = 1)
 *        결과: 11716KB, 84ms
 */

/*
 * <BJ9461_S3 : 파도반 수열>
 * <시간제한: 1초>
 * 파도반 수열 P(N)에 대하여,
 * ex) P(1)부터 P(10)까지 첫 10개 숫자는 1, 1, 1, 2, 2, 3, 4, 5, 7, 9.
 * N이 주어졌을 때, P(N)을 구하시오.
 * [입력]
 * 1) 테케 개수 T.
 * 각 테케] N. (1 ≤ N ≤ 100)
 * [출력]
 * 각 테스트 케이스마다 P(N)을 출력한다.
 */
public class Main_9461_파도반수열_박봉균 {
    static int N;
    static long[] dp;

    static long pado(int n) {
        // 기저사례
        if (n <= 2)
            return 1;

        // 메모이제이션
        if (dp[n] != 0)
            return dp[n];

        // 로직
        return dp[n] = pado(n - 2) + pado(n - 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            dp = new long[N];

            // dp 초기화(default 0)

            bw.write(String.valueOf(pado(N - 1)) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
